package jme;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * A mesh loaded from an M3G (JSR-184) file, plus the file loader.
 * Only the subset of M3G used by the game is implemented (meshes, vertex buffers,
 * strips, appearances, textures, compositing and polygon modes).
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
        public int[] tris;           // index triples (strips already expanded)
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
    private static Map<Integer, Model> world;

    public static synchronized Model find(int userId, String resource) {
        if (world == null) {
            try {
                world = load(resource);
            } catch (Exception e) {
                e.printStackTrace();
                world = new HashMap<Integer, Model>();
            }
        }
        return world.get(userId);
    }

    private static final class Raw { int type; byte[] body; Object parsed; boolean busy; }

    private static Map<Integer, Model> load(String resource) throws IOException {
        InputStream in = Model.class.getResourceAsStream(resource);
        if (in == null) throw new IOException("missing resource " + resource);
        byte[] file;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) bo.write(buf, 0, n);
            file = bo.toByteArray();
        } finally { in.close(); }
        ByteBuffer bb = ByteBuffer.wrap(file).order(ByteOrder.LITTLE_ENDIAN);
        bb.position(12);
        java.util.ArrayList<Raw> objs = new java.util.ArrayList<Raw>();
        objs.add(null); // index 0 = null
        while (bb.remaining() > 13) {
            int comp = bb.get() & 255;
            int total = bb.getInt();
            int unc = bb.getInt();
            byte[] data = new byte[total - 13];
            bb.get(data);
            bb.getInt(); // adler
            if (comp == 1) {
                Inflater inf = new Inflater();
                inf.setInput(data);
                byte[] out = new byte[unc];
                try { int off = 0; while (off < unc && !inf.finished()) off += inf.inflate(out, off, unc - off); }
                catch (Exception e) { throw new IOException(e); }
                inf.end();
                data = out;
            }
            ByteBuffer sb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
            while (sb.remaining() >= 5) {
                Raw r = new Raw();
                r.type = sb.get() & 255;
                int len = sb.getInt();
                r.body = new byte[len];
                sb.get(r.body);
                objs.add(r);
            }
        }
        Map<Integer, Model> result = new HashMap<Integer, Model>();
        for (int i = 1; i < objs.size(); i++) {
            Raw r = objs.get(i);
            if (r.type == 14) {
                Model m = (Model) get(objs, i);
                if (m != null) result.put(m.userId, m);
            }
        }
        return result;
    }

    private static ByteBuffer buf(Raw r) { return ByteBuffer.wrap(r.body).order(ByteOrder.LITTLE_ENDIAN); }

    /** Skips Object3D header, returns userId. */
    private static int object3d(ByteBuffer b) {
        int uid = b.getInt();
        int tracks = b.getInt();
        b.position(b.position() + 4 * tracks);
        int params = b.getInt();
        for (int i = 0; i < params; i++) { b.getInt(); int l = b.getInt(); b.position(b.position() + l); }
        return uid;
    }

    private static void transformable(ByteBuffer b) {
        if (b.get() != 0) b.position(b.position() + 40);
        if (b.get() != 0) b.position(b.position() + 64);
    }

    private static Object get(java.util.ArrayList<Raw> objs, int idx) {
        if (idx <= 0 || idx >= objs.size()) return null;
        Raw r = objs.get(idx);
        if (r.parsed != null) return r.parsed;
        if (r.busy) return null;
        r.busy = true;
        ByteBuffer b = buf(r);
        switch (r.type) {
            case 20: { // VertexArray
                object3d(b);
                int cs = b.get() & 255, cc = b.get() & 255, enc = b.get() & 255, vc = b.getShort() & 0xFFFF;
                int[] data = new int[vc * cc];
                if (cs == 1) {
                    int prev[] = new int[cc];
                    for (int v = 0; v < vc; v++) for (int c = 0; c < cc; c++) {
                        int val = b.get();
                        if (enc == 1) { val += prev[c]; prev[c] = val; }
                        data[v * cc + c] = (byte) val;
                    }
                } else {
                    int prev[] = new int[cc];
                    for (int v = 0; v < vc; v++) for (int c = 0; c < cc; c++) {
                        int val = b.getShort();
                        if (enc == 1) { val += prev[c]; prev[c] = val; }
                        data[v * cc + c] = (short) val;
                    }
                }
                r.parsed = new int[][]{{cs, cc, vc}, data};
                break;
            }
            case 11: { // TriangleStripArray
                object3d(b);
                int enc = b.get() & 255;
                int[] idxs;
                if (enc >= 128) {
                    int n = b.getInt();
                    idxs = new int[n];
                    for (int i = 0; i < n; i++) idxs[i] = enc == 128 ? b.getInt() : (enc == 129 ? (b.get() & 255) : (b.getShort() & 0xFFFF));
                    int ns = b.getInt();
                    int[] strips = new int[ns];
                    for (int i = 0; i < ns; i++) strips[i] = b.getInt();
                    r.parsed = expand(idxs, strips, -1);
                } else {
                    int first = enc == 0 ? b.getInt() : (enc == 1 ? (b.get() & 255) : (b.getShort() & 0xFFFF));
                    int ns = b.getInt();
                    int[] strips = new int[ns];
                    for (int i = 0; i < ns; i++) strips[i] = b.getInt();
                    r.parsed = expand(null, strips, first);
                }
                break;
            }
            case 10: { // Image2D
                object3d(b);
                int fmt = b.get() & 255;
                boolean mutable = b.get() != 0;
                Tex t = new Tex();
                t.w = b.getInt(); t.h = b.getInt();
                t.argb = new int[t.w * t.h];
                if (!mutable) {
                    int pal = b.getInt();
                    byte[] palette = new byte[pal];
                    b.get(palette);
                    int pix = b.getInt();
                    byte[] px = new byte[pix];
                    b.get(px);
                    int bpp = fmt == 99 ? 3 : fmt == 100 ? 4 : fmt == 98 ? 2 : 1;
                    for (int i = 0; i < t.w * t.h; i++) {
                        byte[] src; int o;
                        if (pal > 0) { src = palette; o = (px[i] & 255) * bpp; } else { src = px; o = i * bpp; }
                        if (o + bpp > src.length) break;
                        int a = 255, rr, gg, bl;
                        switch (fmt) {
                            case 96: a = src[o] & 255; rr = gg = bl = 255; break;
                            case 97: rr = gg = bl = src[o] & 255; break;
                            case 98: rr = gg = bl = src[o] & 255; a = src[o + 1] & 255; break;
                            case 99: rr = src[o] & 255; gg = src[o + 1] & 255; bl = src[o + 2] & 255; break;
                            default: rr = src[o] & 255; gg = src[o + 1] & 255; bl = src[o + 2] & 255; a = src[o + 3] & 255; break;
                        }
                        t.argb[i] = (a << 24) | (rr << 16) | (gg << 8) | bl;
                    }
                }
                r.parsed = t;
                break;
            }
            case 17: { // Texture2D
                object3d(b);
                transformable(b);
                int img = b.getInt();
                b.position(b.position() + 3);
                int blending = b.get() & 255, ws = b.get() & 255, wt = b.get() & 255;
                Tex src = (Tex) get(objs, img);
                r.parsed = new Object[]{src, blending, ws};
                break;
            }
            case 6: { // CompositingMode
                object3d(b);
                boolean dt = b.get() != 0, dw = b.get() != 0;
                b.get(); b.get();
                int blend = b.get() & 255, thr = b.get() & 255;
                r.parsed = new int[]{dt ? 1 : 0, dw ? 1 : 0, blend, thr};
                break;
            }
            case 8: { // PolygonMode
                object3d(b);
                int cull = b.get() & 255; b.get(); int wind = b.get() & 255;
                r.parsed = new int[]{cull, wind};
                break;
            }
            case 3: { // Appearance
                object3d(b);
                b.get();
                int comp = b.getInt(), fog = b.getInt(), poly = b.getInt(), mat = b.getInt();
                int tc = b.getInt();
                int tex = tc > 0 ? b.getInt() : 0;
                Sub s = new Sub();
                int[] cm = (int[]) get(objs, comp);
                if (cm != null) { s.depthTest = cm[0] != 0; s.depthWrite = cm[1] != 0; s.blending = cm[2]; s.alphaThreshold = cm[3]; }
                int[] pm = (int[]) get(objs, poly);
                if (pm != null) { s.culling = pm[0]; s.windingCCW = pm[1] == 168; }
                Object[] t = (Object[]) get(objs, tex);
                if (t != null) { s.tex = (Tex) t[0]; s.texFunc = (Integer) t[1]; s.clamp = ((Integer) t[2]) == 240; }
                r.parsed = s;
                break;
            }
            case 21: { // VertexBuffer -> kept as raw holder
                object3d(b);
                int col = b.getInt();
                int posIdx = b.getInt();
                float[] bias = {b.getFloat(), b.getFloat(), b.getFloat()};
                float scale = b.getFloat();
                int norIdx = b.getInt(), colIdx = b.getInt();
                int ntc = b.getInt();
                int tcIdx = 0; float[] tcBias = null; float tcScale = 0;
                for (int i = 0; i < ntc; i++) {
                    int ti = b.getInt();
                    float[] tb = {b.getFloat(), b.getFloat(), b.getFloat()};
                    float ts = b.getFloat();
                    if (i == 0) { tcIdx = ti; tcBias = tb; tcScale = ts; }
                }
                Model m = new Model();
                m.defaultColor = col;
                int[][] p = (int[][]) get(objs, posIdx);
                m.vertexCount = p[0][2];
                m.pos = new float[m.vertexCount * 3];
                for (int v = 0; v < m.vertexCount; v++)
                    for (int c = 0; c < 3; c++) m.pos[v * 3 + c] = p[1][v * p[0][1] + c] * scale + bias[c];
                if (tcIdx != 0) {
                    int[][] t = (int[][]) get(objs, tcIdx);
                    m.uv = new float[m.vertexCount * 2];
                    for (int v = 0; v < m.vertexCount; v++)
                        for (int c = 0; c < 2; c++) m.uv[v * 2 + c] = t[1][v * t[0][1] + c] * tcScale + tcBias[c];
                }
                m.colors = new int[m.vertexCount];
                if (colIdx != 0) {
                    int[][] c = (int[][]) get(objs, colIdx);
                    for (int v = 0; v < m.vertexCount; v++) {
                        int cc = c[0][1];
                        int rr = c[1][v * cc] & 255, gg = c[1][v * cc + 1] & 255, bl = c[1][v * cc + 2] & 255;
                        int a = cc > 3 ? c[1][v * cc + 3] & 255 : 255;
                        m.colors[v] = (a << 24) | (rr << 16) | (gg << 8) | bl;
                    }
                } else {
                    java.util.Arrays.fill(m.colors, col);
                }
                r.parsed = m;
                break;
            }
            case 14: { // Mesh
                int uid = object3d(b);
                transformable(b);
                b.get(); b.get(); b.get(); b.getInt();
                if (b.get() != 0) { b.get(); b.get(); b.getInt(); b.getInt(); }
                int vb = b.getInt();
                int n = b.getInt();
                Model base = (Model) get(objs, vb);
                if (base == null) { r.parsed = null; r.busy = false; return null; }
                Model m = new Model();
                m.userId = uid;
                m.vertexCount = base.vertexCount; m.pos = base.pos; m.uv = base.uv; m.colors = base.colors; m.defaultColor = base.defaultColor;
                m.subs = new Sub[n];
                for (int i = 0; i < n; i++) {
                    int ib = b.getInt(), ap = b.getInt();
                    Sub s = (Sub) get(objs, ap);
                    Sub copy = new Sub();
                    if (s != null) {
                        copy.tex = s.tex; copy.blending = s.blending; copy.alphaThreshold = s.alphaThreshold;
                        copy.depthTest = s.depthTest; copy.depthWrite = s.depthWrite; copy.culling = s.culling;
                        copy.windingCCW = s.windingCCW; copy.texFunc = s.texFunc; copy.clamp = s.clamp;
                    }
                    copy.tris = (int[]) get(objs, ib);
                    if (copy.tris == null) copy.tris = new int[0];
                    m.subs[i] = copy;
                }
                r.parsed = m;
                break;
            }
            default:
                r.parsed = null;
        }
        r.busy = false;
        return r.parsed;
    }

    private static int[] expand(int[] idxs, int[] strips, int first) {
        int total = 0;
        for (int s : strips) total += Math.max(0, s - 2) * 3;
        int[] out = new int[total];
        int o = 0, base = 0;
        for (int s : strips) {
            for (int i = 0; i + 2 < s; i++) {
                int a = idxs != null ? idxs[base + i] : first + base + i;
                int b2 = idxs != null ? idxs[base + i + 1] : first + base + i + 1;
                int c = idxs != null ? idxs[base + i + 2] : first + base + i + 2;
                if ((i & 1) == 0) { out[o++] = a; out[o++] = b2; out[o++] = c; }
                else { out[o++] = b2; out[o++] = a; out[o++] = c; }
            }
            base += s;
        }
        return out;
    }

    /** What Mesh3D.setupAppearance() did on the original: textures use REPLACE + clamp. */
    public void setupAppearance() {
        for (Sub s : subs) { s.texFunc = FUNC_REPLACE; s.clamp = true; }
    }
}
