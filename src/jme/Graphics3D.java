package jme;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Small software 3D rasteriser (replacement for the M3G Graphics3D used by the game).
 * Pixels are collected in an ARGB buffer and composited onto the 2D target lazily:
 * any 2D drawing call on the target flushes pending 3D first, so draw order is preserved.
 * The depth buffer persists until clearDepth().
 */
public final class Graphics3D {
    static Graphics pendingTarget;

    private static Graphics target;
    private static final int W = Canvas.LOGICAL_W, H = Canvas.LOGICAL_H;
    private static final BufferedImage colorImg = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
    private static final int[] color = ((DataBufferInt) colorImg.getRaster().getDataBuffer()).getData();
    private static final float[] depth = new float[W * H];
    private static int dMinX, dMinY, dMaxX, dMaxY;
    private static boolean dirty;
    private static int vpX, vpY, vpW = W, vpH = H;
    private static int clipX0, clipY0, clipX1, clipY1;

    // camera
    private static float[] view = identity();   // world -> camera
    private static float[] proj = identity();

    private Graphics3D() { }

    // ---------------------------------------------------------------- matrices (row-major 4x4)
    public static float[] identity() {
        return new float[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
    }

    public static float[] mul(float[] a, float[] b) {
        float[] r = new float[16];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                float s = 0;
                for (int k = 0; k < 4; k++) s += a[i * 4 + k] * b[k * 4 + j];
                r[i * 4 + j] = s;
            }
        return r;
    }

    public static float[] invert(float[] m) {
        double[] a = new double[32];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) a[i * 8 + j] = m[i * 4 + j];
            a[i * 8 + 4 + i] = 1;
        }
        for (int c = 0; c < 4; c++) {
            int p = c;
            for (int r = c + 1; r < 4; r++) if (Math.abs(a[r * 8 + c]) > Math.abs(a[p * 8 + c])) p = r;
            if (Math.abs(a[p * 8 + c]) < 1e-12) return identity();
            if (p != c) for (int k = 0; k < 8; k++) { double t = a[c * 8 + k]; a[c * 8 + k] = a[p * 8 + k]; a[p * 8 + k] = t; }
            double d = a[c * 8 + c];
            for (int k = 0; k < 8; k++) a[c * 8 + k] /= d;
            for (int r = 0; r < 4; r++) if (r != c) {
                double f = a[r * 8 + c];
                if (f != 0) for (int k = 0; k < 8; k++) a[r * 8 + k] -= f * a[c * 8 + k];
            }
        }
        float[] out = new float[16];
        for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) out[i * 4 + j] = (float) a[i * 8 + 4 + j];
        return out;
    }

    public static float[] translation(float x, float y, float z) {
        float[] m = identity();
        m[3] = x; m[7] = y; m[11] = z;
        return m;
    }

    /** Rotation by angle (degrees) around the axis, like M3G Transform.postRotate. */
    public static float[] rotation(float angleDeg, float ax, float ay, float az) {
        double len = Math.sqrt(ax * ax + ay * ay + az * az);
        if (len == 0) return identity();
        double x = ax / len, y = ay / len, z = az / len;
        double a = Math.toRadians(angleDeg), c = Math.cos(a), s = Math.sin(a), t = 1 - c;
        float[] m = identity();
        m[0] = (float) (t * x * x + c);     m[1] = (float) (t * x * y - s * z); m[2] = (float) (t * x * z + s * y);
        m[4] = (float) (t * x * y + s * z); m[5] = (float) (t * y * y + c);     m[6] = (float) (t * y * z - s * x);
        m[8] = (float) (t * x * z - s * y); m[9] = (float) (t * y * z + s * x); m[10] = (float) (t * z * z + c);
        return m;
    }

    public static float[] perspective(float fovyDeg, float aspect, float near, float far) {
        float f = (float) (1.0 / Math.tan(Math.toRadians(fovyDeg) * 0.5));
        float[] m = new float[16];
        m[0] = f / aspect;
        m[5] = f;
        m[10] = -(far + near) / (far - near);
        m[11] = -2 * far * near / (far - near);
        m[14] = -1;
        return m;
    }

    public static void transform(float[] m, float[] v) {
        float x = v[0], y = v[1], z = v[2], w = v[3];
        v[0] = m[0] * x + m[1] * y + m[2] * z + m[3] * w;
        v[1] = m[4] * x + m[5] * y + m[6] * z + m[7] * w;
        v[2] = m[8] * x + m[9] * y + m[10] * z + m[11] * w;
        v[3] = m[12] * x + m[13] * y + m[14] * z + m[15] * w;
    }

    // ---------------------------------------------------------------- state
    public static void setCamera(float[] cameraToWorld, float[] projection) {
        view = invert(cameraToWorld);
        proj = projection;
    }

    public static void bindTarget(Graphics g, int x, int y, int w, int h) {
        flushPending();
        target = g;
        vpX = x; vpY = y; vpW = w; vpH = h;
        clipX0 = Math.max(0, g.getClipX());
        clipY0 = Math.max(0, g.getClipY());
        clipX1 = Math.min(W, g.getClipX() + g.getClipWidth());
        clipY1 = Math.min(H, g.getClipY() + g.getClipHeight());
    }

    public static void releaseTarget() {
        flushPending();
        target = null;
    }

    public static void clearDepth() {
        java.util.Arrays.fill(depth, Float.POSITIVE_INFINITY);
    }

    static void flushPending() {
        if (!dirty || pendingTarget == null) { dirty = false; pendingTarget = null; return; }
        Graphics t = pendingTarget;
        pendingTarget = null;
        int w = dMaxX - dMinX + 1, h = dMaxY - dMinY + 1;
        if (w > 0 && h > 0) {
            t.g2.drawImage(colorImg, dMinX, dMinY, dMaxX + 1, dMaxY + 1, dMinX, dMinY, dMaxX + 1, dMaxY + 1, null);
            for (int y = dMinY; y <= dMaxY; y++) java.util.Arrays.fill(color, y * W + dMinX, y * W + dMaxX + 1, 0);
        }
        dirty = false;
    }

    // ---------------------------------------------------------------- rendering
    private static final int MAXV = 12;

    // scratch buffers reused across calls (no per-triangle / per-model allocation)
    private static float[] clipBuf = new float[4 * 1024];
    private static final float[][] poly = new float[MAXV][10];
    private static final float[][] tmp = new float[MAXV][10];
    private static final float[] sx = new float[3], sy = new float[3], sz = new float[3], iw = new float[3];

    public static void render(Model m, float[] model) {
        if (target == null || m == null) return;
        float[] mv = mul(view, model);
        float[] mvp = mul(proj, mv);
        int n = m.vertexCount;
        if (clipBuf.length < n * 4) clipBuf = new float[n * 4];
        float[] clip = clipBuf;
        float[] pos = m.pos;
        for (int i = 0; i < n; i++) {
            float x = pos[i * 3], y = pos[i * 3 + 1], z = pos[i * 3 + 2];
            clip[i * 4] = mvp[0] * x + mvp[1] * y + mvp[2] * z + mvp[3];
            clip[i * 4 + 1] = mvp[4] * x + mvp[5] * y + mvp[6] * z + mvp[7];
            clip[i * 4 + 2] = mvp[8] * x + mvp[9] * y + mvp[10] * z + mvp[11];
            clip[i * 4 + 3] = mvp[12] * x + mvp[13] * y + mvp[14] * z + mvp[15];
        }
        final float[] uv = m.uv;
        final int[] colors = m.colors;
        for (Model.Sub s : m.subs) {
            int[] t = s.tris;
            for (int i = 0; i + 2 < t.length; i += 3) {
                // trivial reject: all three vertices outside the same frustum plane
                int i0 = t[i] * 4, i1 = t[i + 1] * 4, i2 = t[i + 2] * 4;
                float w0 = clip[i0 + 3], w1 = clip[i1 + 3], w2 = clip[i2 + 3];
                if (clip[i0] > w0 && clip[i1] > w1 && clip[i2] > w2) continue;
                if (clip[i0] < -w0 && clip[i1] < -w1 && clip[i2] < -w2) continue;
                if (clip[i0 + 1] > w0 && clip[i1 + 1] > w1 && clip[i2 + 1] > w2) continue;
                if (clip[i0 + 1] < -w0 && clip[i1 + 1] < -w1 && clip[i2 + 1] < -w2) continue;
                if (clip[i0 + 2] > w0 && clip[i1 + 2] > w1 && clip[i2 + 2] > w2) continue;

                int cnt = 0;
                for (int k = 0; k < 3; k++) {
                    int vi = t[i + k];
                    float[] p = poly[cnt++];
                    p[0] = clip[vi * 4]; p[1] = clip[vi * 4 + 1]; p[2] = clip[vi * 4 + 2]; p[3] = clip[vi * 4 + 3];
                    if (uv != null) { p[4] = uv[vi * 2]; p[5] = uv[vi * 2 + 1]; } else { p[4] = 0; p[5] = 0; }
                    int c = colors[vi];
                    p[6] = (c >> 16) & 255; p[7] = (c >> 8) & 255; p[8] = c & 255; p[9] = (c >>> 24);
                }
                // clip against near plane: z >= -w
                int outCnt = 0;
                for (int k = 0; k < cnt; k++) {
                    float[] a = poly[k], b = poly[(k + 1) % cnt];
                    float da = a[2] + a[3], db = b[2] + b[3];
                    if (da >= 0) copy(a, tmp[outCnt++]);
                    if ((da >= 0) != (db >= 0)) {
                        float f = da / (da - db);
                        float[] o = tmp[outCnt++];
                        for (int q = 0; q < 10; q++) o[q] = a[q] + (b[q] - a[q]) * f;
                    }
                }
                if (outCnt < 3) continue;
                for (int k = 1; k + 1 < outCnt; k++) drawTri(s, tmp[0], tmp[k], tmp[k + 1]);
            }
        }
    }

    private static void copy(float[] a, float[] b) { System.arraycopy(a, 0, b, 0, 10); }

    private static void drawTri(Model.Sub s, float[] a, float[] b, float[] c) {
        float[] v0 = a, v1 = b, v2 = c;
        for (int i = 0; i < 3; i++) {
            float[] v = i == 0 ? v0 : i == 1 ? v1 : v2;
            float w = v[3];
            if (w <= 1e-6f) return;
            float inv = 1f / w;
            iw[i] = inv;
            sx[i] = vpX + (v[0] * inv * 0.5f + 0.5f) * vpW;
            sy[i] = vpY + (-v[1] * inv * 0.5f + 0.5f) * vpH;
            sz[i] = v[2] * inv;
        }
        float area = (sx[1] - sx[0]) * (sy[2] - sy[0]) - (sx[2] - sx[0]) * (sy[1] - sy[0]); // >0: clockwise on screen (y down)
        if (area == 0) return;
        boolean ccwOnScreen = area < 0;            // counter-clockwise as seen by the viewer
        boolean front = s.windingCCW ? ccwOnScreen : !ccwOnScreen;
        if (s.culling == Model.CULL_BACK && !front) return;
        if (s.culling == Model.CULL_FRONT && front) return;

        float fminX = Math.min(sx[0], Math.min(sx[1], sx[2])), fmaxX = Math.max(sx[0], Math.max(sx[1], sx[2]));
        float fminY = Math.min(sy[0], Math.min(sy[1], sy[2])), fmaxY = Math.max(sy[0], Math.max(sy[1], sy[2]));
        if (fmaxX < clipX0 || fminX > clipX1 || fmaxY < clipY0 || fminY > clipY1) return;
        int minX = Math.max(clipX0, (int) Math.floor(fminX));
        int maxX = Math.min(clipX1 - 1, (int) Math.ceil(fmaxX));
        int minY = Math.max(clipY0, (int) Math.floor(fminY));
        int maxY = Math.min(clipY1 - 1, (int) Math.ceil(fmaxY));
        if (minX > maxX || minY > maxY) return;

        final float invArea = 1f / area;
        // barycentric weights are linear in (x, y): step them instead of recomputing
        final float dw0dx = (sy[1] - sy[2]) * invArea, dw0dy = (sx[2] - sx[1]) * invArea;
        final float dw1dx = (sy[2] - sy[0]) * invArea, dw1dy = (sx[0] - sx[2]) * invArea;
        final float px0 = minX + 0.5f;
        final float iw0 = iw[0], iw1 = iw[1], iw2 = iw[2];
        final float sz0 = sz[0], sz1 = sz[1], sz2 = sz[2];

        final Model.Tex tex = s.tex;
        final int[] texPx = tex != null ? tex.argb : null;
        final int tw = tex != null ? tex.w : 0, th = tex != null ? tex.h : 0;
        final boolean clamp = s.clamp, modulate = s.texFunc == Model.FUNC_MODULATE;
        final boolean alphaBlend = s.blending == Model.BLEND_ALPHA;
        final boolean depthTest = s.depthTest, depthWrite = s.depthWrite;
        final int thr = s.alphaThreshold;
        final float[] depth = Graphics3D.depth;
        final int[] color = Graphics3D.color;

        for (int y = minY; y <= maxY; y++) {
            float py = y + 0.5f;
            float w0 = ((sx[1] - px0) * (sy[2] - py) - (sx[2] - px0) * (sy[1] - py)) * invArea;
            float w1 = ((sx[2] - px0) * (sy[0] - py) - (sx[0] - px0) * (sy[2] - py)) * invArea;
            boolean entered = false;
            int di = y * W + minX;
            for (int x = minX; x <= maxX; x++, di++, w0 += dw0dx, w1 += dw1dx) {
                float w2 = 1f - w0 - w1;
                if (w0 < 0 || w1 < 0 || w2 < 0) {
                    if (entered) break;          // triangle is convex: row is finished
                    continue;
                }
                entered = true;
                float z = w0 * sz0 + w1 * sz1 + w2 * sz2;
                if (z < -1f || z > 1f) continue;
                if (depthTest && z > depth[di]) continue;
                // perspective-correct interpolation
                float p0 = w0 * iw0, p1 = w1 * iw1, p2 = w2 * iw2;
                float ps = p0 + p1 + p2;
                if (ps == 0) continue;
                float invPs = 1f / ps;
                float k0 = p0 * invPs, k1 = p1 * invPs, k2 = p2 * invPs;
                int r, g, bl, al;
                float vr = k0 * a[6] + k1 * b[6] + k2 * c[6];
                float vg = k0 * a[7] + k1 * b[7] + k2 * c[7];
                float vb = k0 * a[8] + k1 * b[8] + k2 * c[8];
                float va = k0 * a[9] + k1 * b[9] + k2 * c[9];
                if (texPx != null) {
                    float u = (k0 * a[4] + k1 * b[4] + k2 * c[4]) * tw;
                    float t = (k0 * a[5] + k1 * b[5] + k2 * c[5]) * th;
                    int tx = (int) u; if (u < 0) tx--;
                    int ty = (int) t; if (t < 0) ty--;
                    if (clamp) {
                        tx = tx < 0 ? 0 : (tx >= tw ? tw - 1 : tx);
                        ty = ty < 0 ? 0 : (ty >= th ? th - 1 : ty);
                    } else {
                        tx %= tw; if (tx < 0) tx += tw;
                        ty %= th; if (ty < 0) ty += th;
                    }
                    int tc = texPx[ty * tw + tx];
                    r = (tc >> 16) & 255; g = (tc >> 8) & 255; bl = tc & 255; al = tc >>> 24;
                    if (modulate) {
                        r = (int) (r * vr / 255f); g = (int) (g * vg / 255f); bl = (int) (bl * vb / 255f); al = (int) (al * va / 255f);
                    }
                } else {
                    r = (int) vr; g = (int) vg; bl = (int) vb; al = (int) va;
                }
                if (thr > 0 && al < thr) continue;
                if (!alphaBlend) al = 255;
                if (depthWrite) depth[di] = z;
                int dst = color[di];
                if (al >= 255) {
                    color[di] = 0xFF000000 | (r << 16) | (g << 8) | bl;
                } else if (al > 0) {
                    int da = dst >>> 24;
                    if (da == 0) {
                        color[di] = (al << 24) | (r << 16) | (g << 8) | bl;
                    } else {
                        float fa = al / 255f;
                        int nr = (int) (r * fa + ((dst >> 16) & 255) * (1 - fa));
                        int ng = (int) (g * fa + ((dst >> 8) & 255) * (1 - fa));
                        int nb = (int) (bl * fa + (dst & 255) * (1 - fa));
                        color[di] = 0xFF000000 | (nr << 16) | (ng << 8) | nb;
                    }
                } else continue;
                if (!dirty) { dirty = true; dMinX = x; dMaxX = x; dMinY = y; dMaxY = y; }
                else { if (x < dMinX) dMinX = x; if (x > dMaxX) dMaxX = x; if (y < dMinY) dMinY = y; if (y > dMaxY) dMaxY = y; }
                pendingTarget = target;
            }
        }
    }
}
