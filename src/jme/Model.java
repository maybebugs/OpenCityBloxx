package jme;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * A mesh loaded from a Wavefront OBJ file (assets/models/*.obj) with its MTL + PNG textures.
 *
 * The game's models used to be read from a JSR-184 (M3G) file; they were extracted once with
 * tools/M3gToObj.java and are now plain OBJ assets.  Supported OBJ/MTL subset:
 *   OBJ: v x y z [r g b], vt s t, f (v, v/vt, v/vt/vn, v//vn; polygons are fan-triangulated;
 *        negative indices allowed), g / o, usemtl, mtllib, "#@default_color AARRGGBB"
 *   MTL: newmtl, map_Kd, plus "#@key value" render-state comments written by the extractor:
 *        blend (ALPHA|REPLACE), alpha_threshold, depth_test, depth_write,
 *        cull (BACK|FRONT|NONE), winding (CCW|CW), texture_function (REPLACE|MODULATE), clamp
 * V texture coordinates are stored flipped in OBJ (1 - t) and flipped back here.
 * The index file assets/models/models.txt maps the game's numeric model ids to OBJ files.
 */
public final class Model {
    public static final int BLEND_ALPHA = 64, BLEND_REPLACE = 68;
    public static final int FUNC_REPLACE = 228, FUNC_MODULATE = 227;
    public static final int CULL_BACK = 160, CULL_FRONT = 161, CULL_NONE = 162;

    /** Decoded 2D texture. */
    public static final class Tex {
        public int w, h;
        public int[] argb;
    }

    /** One drawable part of a mesh. */
    public static final class Sub {
        public int[] tris;           // index triples
        public Tex tex;
        public int blending = BLEND_REPLACE;
        public int alphaThreshold = 0;
        public boolean depthTest = true, depthWrite = true;
        public int culling = CULL_BACK;
        public boolean windingCCW = true;
        public int texFunc = FUNC_MODULATE;
        public boolean clamp = false;
    }

    public int userId;
    public int vertexCount;
    public float[] pos;      // x,y,z
    public float[] uv;       // s,t (may be null)
    public int[] colors;     // ARGB per vertex (or default colour repeated)
    public int defaultColor = 0xFFFFFFFF;
    public Sub[] subs;

    // ------------------------------------------------------------------ loader
    private static final String MODEL_DIR = "models/";
    private static Map<Integer, String> index;
    private static final Map<Integer, Model> cache = new HashMap<Integer, Model>();
    private static final Map<String, Tex> textures = new HashMap<String, Tex>();

    /** Returns the model registered under the given numeric id, or null. */
    public static synchronized Model find(int userId) {
        Model m = cache.get(userId);
        if (m != null) return m;
        if (index == null) index = loadIndex();
        String file = index.get(userId);
        if (file == null) return null;
        try {
            m = loadObj(MODEL_DIR + file);
            m.userId = userId;
            cache.put(userId, m);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<Integer, String> loadIndex() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        try {
            InputStream in = Assets.open(MODEL_DIR + "models.txt");
            if (in == null) return map;
            BufferedReader r = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0 || line.charAt(0) == '#') continue;
                String[] p = line.split("\\s+");
                if (p.length >= 2) map.put(Integer.valueOf(p[0]), p[1]);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /** Render state of one MTL material. */
    private static final class Mat {
        String texPath;
        int blending = BLEND_REPLACE, alphaThreshold = 0, culling = CULL_BACK, texFunc = FUNC_MODULATE;
        boolean depthTest = true, depthWrite = true, windingCCW = true, clamp = false;
    }

    private static final class FloatList {
        float[] a = new float[256];
        int n;
        void add(float v) { if (n == a.length) a = java.util.Arrays.copyOf(a, n * 2); a[n++] = v; }
    }

    private static final class IntList {
        int[] a = new int[256];
        int n;
        void add(int v) { if (n == a.length) a = java.util.Arrays.copyOf(a, n * 2); a[n++] = v; }
        int[] toArray() { return java.util.Arrays.copyOf(a, n); }
    }

    private static BufferedReader reader(String path) throws java.io.IOException {
        InputStream in = Assets.open(path);
        if (in == null) throw new java.io.IOException("missing asset " + path);
        return new BufferedReader(new InputStreamReader(in, "UTF-8"));
    }

    private static Model loadObj(String objPath) throws java.io.IOException {
        FloatList vx = new FloatList(), vt = new FloatList(), vc = new FloatList();
        boolean haveColors = false;
        int nv = 0, nvt = 0;
        int defaultColor = 0xFFFFFFFF;
        Map<String, Mat> materials = new HashMap<String, Mat>();

        // unified vertices: unique (position index, texcoord index) pairs
        Map<Long, Integer> unify = new HashMap<Long, Integer>();
        FloatList outPos = new FloatList(), outUv = new FloatList(), outCol = new FloatList();
        boolean anyUv = false;

        List<Mat> subMats = new ArrayList<Mat>();
        List<IntList> subTris = new ArrayList<IntList>();
        IntList current = null;
        Mat curMat = new Mat();
        boolean curDirty = true;   // start a new submesh on the first face

        BufferedReader r = reader(objPath);
        try {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0) continue;
                if (line.charAt(0) == '#') {
                    if (line.startsWith("#@default_color ")) {
                        defaultColor = (int) Long.parseLong(line.substring(16).trim(), 16);
                    }
                    continue;
                }
                String[] t = line.split("\\s+");
                String key = t[0];
                if (key.equals("v")) {
                    vx.add(Float.parseFloat(t[1])); vx.add(Float.parseFloat(t[2])); vx.add(Float.parseFloat(t[3]));
                    if (t.length >= 7) {
                        haveColors = true;
                        vc.add(Float.parseFloat(t[4])); vc.add(Float.parseFloat(t[5])); vc.add(Float.parseFloat(t[6]));
                    } else {
                        vc.add(-1f); vc.add(-1f); vc.add(-1f);
                    }
                    nv++;
                } else if (key.equals("vt")) {
                    vt.add(Float.parseFloat(t[1]));
                    vt.add(1f - (t.length > 2 ? Float.parseFloat(t[2]) : 0f));
                    nvt++;
                } else if (key.equals("mtllib")) {
                    String mtlPath = Assets.dirOf(objPath) + line.substring(6).trim();
                    parseMtl(mtlPath, materials);
                } else if (key.equals("usemtl")) {
                    Mat m = materials.get(line.substring(6).trim());
                    curMat = m != null ? m : new Mat();
                    curDirty = true;
                } else if (key.equals("f")) {
                    if (curDirty) {
                        current = new IntList();
                        subTris.add(current);
                        subMats.add(curMat);
                        curDirty = false;
                    }
                    int cnt = t.length - 1;
                    int[] face = new int[cnt];
                    for (int k = 0; k < cnt; k++) {
                        String[] c = t[k + 1].split("/", -1);
                        int vi = idx(Integer.parseInt(c[0]), nv);
                        int ti = (c.length > 1 && c[1].length() > 0) ? idx(Integer.parseInt(c[1]), nvt) : -1;
                        long ukey = ((long) vi << 32) | (ti + 1L);
                        Integer u = unify.get(ukey);
                        if (u == null) {
                            u = outPos.n / 3;
                            unify.put(ukey, u);
                            outPos.add(vx.a[vi * 3]); outPos.add(vx.a[vi * 3 + 1]); outPos.add(vx.a[vi * 3 + 2]);
                            if (ti >= 0) { outUv.add(vt.a[ti * 2]); outUv.add(vt.a[ti * 2 + 1]); anyUv = true; }
                            else { outUv.add(0f); outUv.add(0f); }
                            outCol.add(vc.a[vi * 3]); outCol.add(vc.a[vi * 3 + 1]); outCol.add(vc.a[vi * 3 + 2]);
                        }
                        face[k] = u;
                    }
                    for (int k = 1; k + 1 < cnt; k++) {   // fan triangulation
                        current.add(face[0]); current.add(face[k]); current.add(face[k + 1]);
                    }
                }
            }
        } finally {
            r.close();
        }

        Model m = new Model();
        m.defaultColor = defaultColor;
        m.vertexCount = outPos.n / 3;
        m.pos = java.util.Arrays.copyOf(outPos.a, outPos.n);
        m.uv = anyUv ? java.util.Arrays.copyOf(outUv.a, outUv.n) : null;
        m.colors = new int[m.vertexCount];
        for (int v = 0; v < m.vertexCount; v++) {
            float cr = outCol.a[v * 3];
            if (!haveColors || cr < 0f) {
                m.colors[v] = defaultColor;
            } else {
                int rr = Math.round(cr * 255f), gg = Math.round(outCol.a[v * 3 + 1] * 255f), bb = Math.round(outCol.a[v * 3 + 2] * 255f);
                m.colors[v] = 0xFF000000 | (rr << 16) | (gg << 8) | bb;
            }
        }
        m.subs = new Sub[subTris.size()];
        for (int i = 0; i < m.subs.length; i++) {
            Mat mt = subMats.get(i);
            Sub s = new Sub();
            s.tris = subTris.get(i).toArray();
            s.blending = mt.blending; s.alphaThreshold = mt.alphaThreshold;
            s.depthTest = mt.depthTest; s.depthWrite = mt.depthWrite;
            s.culling = mt.culling; s.windingCCW = mt.windingCCW;
            s.texFunc = mt.texFunc; s.clamp = mt.clamp;
            s.tex = mt.texPath != null ? loadTexture(mt.texPath) : null;
            m.subs[i] = s;
        }
        return m;
    }

    private static int idx(int i, int count) {
        return i > 0 ? i - 1 : count + i;
    }

    private static void parseMtl(String path, Map<String, Mat> out) {
        BufferedReader r = null;
        try {
            r = reader(path);
            Mat cur = null;
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("newmtl ")) {
                    cur = new Mat();
                    out.put(line.substring(7).trim(), cur);
                } else if (cur == null) {
                    continue;
                } else if (line.startsWith("map_Kd ")) {
                    cur.texPath = Assets.dirOf(path) + line.substring(7).trim();
                } else if (line.startsWith("#@")) {
                    String[] p = line.substring(2).trim().split("\\s+", 2);
                    if (p.length < 2) continue;
                    String k = p[0], v = p[1].trim();
                    if (k.equals("blend")) cur.blending = v.equals("ALPHA") ? BLEND_ALPHA : BLEND_REPLACE;
                    else if (k.equals("alpha_threshold")) cur.alphaThreshold = Integer.parseInt(v);
                    else if (k.equals("depth_test")) cur.depthTest = Boolean.parseBoolean(v);
                    else if (k.equals("depth_write")) cur.depthWrite = Boolean.parseBoolean(v);
                    else if (k.equals("cull")) cur.culling = v.equals("FRONT") ? CULL_FRONT : v.equals("NONE") ? CULL_NONE : CULL_BACK;
                    else if (k.equals("winding")) cur.windingCCW = !v.equals("CW");
                    else if (k.equals("texture_function")) cur.texFunc = v.equals("REPLACE") ? FUNC_REPLACE : FUNC_MODULATE;
                    else if (k.equals("clamp")) cur.clamp = Boolean.parseBoolean(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (r != null) r.close(); } catch (Exception ignored) { }
        }
    }

    private static Tex loadTexture(String path) {
        Tex t = textures.get(path);
        if (t != null) return t;
        try {
            byte[] data = Assets.readBytes(path);
            if (data == null) return null;
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
            if (bi == null) return null;
            t = new Tex();
            t.w = bi.getWidth();
            t.h = bi.getHeight();
            t.argb = bi.getRGB(0, 0, t.w, t.h, null, 0, t.w);
            textures.put(path, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** What Mesh3D.setupAppearance() did on the original: textures use REPLACE + clamp. */
    public void setupAppearance() {
        for (Sub s : subs) { s.texFunc = FUNC_REPLACE; s.clamp = true; }
    }
}
