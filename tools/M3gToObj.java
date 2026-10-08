import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/**
 * Build-time tool: extracts every mesh of an M3G file into Wavefront OBJ + MTL + PNG textures.
 *
 * Usage: java M3gToObj <in.m3g> <out_models_dir>
 *
 * Output (per mesh):  <name>.obj  <name>.mtl  textures/<name>_<n>.png     plus  models.txt (index)
 *
 * OBJ conventions:
 *   v  x y z r g b      positions + per-vertex colour (de-facto standard extension, 0..1)
 *   vt s 1-t            V is flipped so the PNGs look right in any OBJ viewer (loader flips back)
 *   usemtl / g          one group + material per M3G submesh (triangle strips are expanded)
 *   f  a/a b/b c/c      vertex and texcoord indices are identical (M3G has one index per vertex)
 * MTL extras: standard Kd / d / map_Kd plus "#@key value" comment lines which carry the M3G
 * render state (blend mode, alpha threshold, depth test/write, culling, winding, clamp, function).
 * Viewers ignore these comments; the game's loader reads them.
 */
public class M3gToObj {
    static String nameFor(int id) {
        switch (id) {
            case 7: return "crane_hook_static";
            case 8: return "crane_hook";
            case 9: return "ground";
        }
        if (id >= 10 && id <= 13) return "floor_" + (id - 9);
        if (id >= 20 && id <= 23) return "falling_" + (id - 19);
        if (id >= 30 && id <= 33) return "top_" + (id - 29);
        if (id >= 40 && id <= 43) return "top_bonus_" + (id - 39);
        return "model_" + id;
    }

    static String f(float v) {
        String s = String.format(Locale.ROOT, "%.6f", v);
        if (s.indexOf('.') >= 0) { s = s.replaceAll("0+$", ""); if (s.endsWith(".")) s += "0"; }
        return s;
    }

    public static void main(String[] args) throws Exception {
        File outDir = new File(args[1]);
        File texDir = new File(outDir, "textures");
        texDir.mkdirs();
        Map<Integer, M3gReader> models = M3gReader.loadFile(args[0]);
        StringBuilder index = new StringBuilder("# model-id <TAB> obj path relative to assets/models/\n");
        for (int id : new TreeSet<Integer>(models.keySet())) {
            M3gReader m = models.get(id);
            String name = nameFor(id);
            index.append(id).append('\t').append(name).append(".obj\n");

            StringBuilder obj = new StringBuilder();
            obj.append("# ").append(name).append(" (M3G user id ").append(id).append(") extracted by M3gToObj\n");
            obj.append("#@default_color ").append(String.format("%08X", m.defaultColor)).append('\n');
            obj.append("mtllib ").append(name).append(".mtl\n");
            obj.append("o ").append(name).append('\n');
            for (int v = 0; v < m.vertexCount; v++) {
                int c = m.colors[v];
                obj.append("v ").append(f(m.pos[v * 3])).append(' ').append(f(m.pos[v * 3 + 1])).append(' ').append(f(m.pos[v * 3 + 2]))
                   .append(' ').append(f(((c >> 16) & 255) / 255f)).append(' ').append(f(((c >> 8) & 255) / 255f))
                   .append(' ').append(f((c & 255) / 255f)).append('\n');
            }
            if (m.uv != null) {
                for (int v = 0; v < m.vertexCount; v++)
                    obj.append("vt ").append(f(m.uv[v * 2])).append(' ').append(f(1f - m.uv[v * 2 + 1])).append('\n');
            }
            StringBuilder mtl = new StringBuilder("# materials for " + name + "\n");
            IdentityHashMap<M3gReader.Tex, String> texFiles = new IdentityHashMap<M3gReader.Tex, String>();
            for (int s = 0; s < m.subs.length; s++) {
                M3gReader.Sub sub = m.subs[s];
                String mat = name + "_mat" + s;
                String texName = null;
                if (sub.tex != null) {
                    texName = texFiles.get(sub.tex);
                    if (texName == null) {
                        texName = name + "_" + texFiles.size() + ".png";
                        texFiles.put(sub.tex, texName);
                        BufferedImage bi = new BufferedImage(sub.tex.w, sub.tex.h, BufferedImage.TYPE_INT_ARGB);
                        bi.setRGB(0, 0, sub.tex.w, sub.tex.h, sub.tex.argb, 0, sub.tex.w);
                        ImageIO.write(bi, "png", new File(texDir, texName));
                    }
                }
                mtl.append("\nnewmtl ").append(mat).append('\n');
                mtl.append("Ka 1.0 1.0 1.0\nKd 1.0 1.0 1.0\nKs 0.0 0.0 0.0\nd 1.0\nillum 0\n");
                if (texName != null) mtl.append("map_Kd textures/").append(texName).append('\n');
                mtl.append("#@blend ").append(sub.blending == M3gReader.BLEND_ALPHA ? "ALPHA" : "REPLACE").append('\n');
                mtl.append("#@alpha_threshold ").append(sub.alphaThreshold).append('\n');
                mtl.append("#@depth_test ").append(sub.depthTest).append('\n');
                mtl.append("#@depth_write ").append(sub.depthWrite).append('\n');
                mtl.append("#@cull ").append(sub.culling == M3gReader.CULL_BACK ? "BACK" : sub.culling == M3gReader.CULL_FRONT ? "FRONT" : "NONE").append('\n');
                mtl.append("#@winding ").append(sub.windingCCW ? "CCW" : "CW").append('\n');
                mtl.append("#@texture_function ").append(sub.texFunc == M3gReader.FUNC_REPLACE ? "REPLACE" : "MODULATE").append('\n');
                mtl.append("#@clamp ").append(sub.clamp).append('\n');

                obj.append("g ").append(name).append("_part").append(s).append('\n');
                obj.append("usemtl ").append(mat).append('\n');
                for (int t = 0; t + 2 < sub.tris.length; t += 3) {
                    obj.append('f');
                    for (int k = 0; k < 3; k++) {
                        int vi = sub.tris[t + k] + 1;
                        obj.append(' ').append(vi);
                        if (m.uv != null) obj.append('/').append(vi);
                    }
                    obj.append('\n');
                }
            }
            write(new File(outDir, name + ".obj"), obj.toString());
            write(new File(outDir, name + ".mtl"), mtl.toString());
        }
        write(new File(outDir, "models.txt"), index.toString());
        System.out.println("exported " + models.size() + " meshes to " + outDir);
    }

    static void write(File f, String s) throws IOException {
        Writer w = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
        w.write(s); w.close();
    }
}
