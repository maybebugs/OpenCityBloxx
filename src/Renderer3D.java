

import java.util.Hashtable;
import jme.Graphics;
import jme.Graphics3D;
import jme.Model;

public final class Renderer3D {
    public static float[] projected = new float[4];
    private static Hashtable models = new Hashtable();
    private static float[] cameraTransform = Graphics3D.identity();
    private static float[] projection = Graphics3D.identity();
    private static float fov;
    private static float aspect;
    private static float aspectInv;
    private static int viewTop;
    private static int viewWidth;
    private static int viewHeight;

    private Renderer3D() {
    }

    private static String resourceName(int i2) {
        return "/" + (Integer.MAX_VALUE & i2);
    }

    public static final Mesh3D getModel(int i, int i2, boolean z) {
        Mesh3D dVar = (Mesh3D) models.get(Integer.valueOf(i));
        if (dVar != null || !z) {
            return dVar;
        }
        Model mesh = Model.find(i, resourceName(i2));
        if (mesh == null) {
            return null;
        }
        Mesh3D dVar2 = new Mesh3D(mesh);
        dVar2.setupAppearance();
        models.put(Integer.valueOf(i), dVar2);
        return dVar2;
    }

    public static final void setFov(float f) {
        fov = f;
        projection = Graphics3D.perspective(fov * aspectInv, aspect, 10.0f, 10000.0f);
    }

    public static final void lookAt(float eyeX, float eyeY, float eyeZ, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dirX < 1.0E-4f && dirX > -1.0E-4f) {
            dirX = 0.0f;
        }
        if (dirY < 1.0E-4f && dirY > -1.0E-4f) {
            dirY = 0.0f;
        }
        if (dirZ < 1.0E-4f && dirZ > -1.0E-4f) {
            dirZ = 0.0f;
        }
        float f10 = (dirY * upZ) - (dirZ * upY);
        float f11 = (dirZ * upX) - (dirX * upZ);
        float f12 = (dirX * upY) - (dirY * upX);
        float sqrt = 1.0f / ((float) Math.sqrt((double) (((f10 * f10) + (f11 * f11)) + (f12 * f12))));
        f10 *= sqrt;
        f11 *= sqrt;
        f12 *= sqrt;
        sqrt = (f11 * dirZ) - (f12 * dirY);
        float f13 = (f12 * dirX) - (f10 * dirZ);
        float f14 = (f10 * dirY) - (f11 * dirX);
        float[] matrix = new float[16];
        matrix[0] = f10;
        matrix[1] = sqrt;
        matrix[2] = -dirX;
        matrix[3] = eyeX;
        matrix[4] = f11;
        matrix[5] = f13;
        matrix[6] = -dirY;
        matrix[7] = eyeY;
        matrix[8] = f12;
        matrix[9] = f14;
        matrix[10] = -dirZ;
        matrix[11] = eyeZ;
        matrix[12] = 0.0f;
        matrix[13] = 0.0f;
        matrix[14] = 0.0f;
        matrix[15] = 1.0f;
        cameraTransform = matrix;
        Graphics3D.setCamera(cameraTransform, projection);
    }

    public static final void init(int i, int i2, int i3) {
        models = new Hashtable();
        Renderer3D.setupCamera(i, i2, i3);
    }

    public static final void beginFrame(Object obj) {
        Graphics g = (Graphics) obj;
        Renderer3D.setClip(g);
        Graphics3D.bindTarget(g, 0, 0, viewWidth, viewHeight);
        Graphics3D.clearDepth();
        Graphics3D.setCamera(cameraTransform, projection);
    }

    public static final void project(float[] fArr) {
        float[] inv = Graphics3D.invert(cameraTransform);
        Graphics3D.transform(inv, fArr);
        Graphics3D.transform(projection, fArr);
        fArr[0] = (((0.5f * ((float) viewWidth)) * fArr[0]) / fArr[3]) + ((float) (viewWidth >> 1));
        fArr[1] = (((-0.5f * ((float) viewHeight)) * fArr[1]) / fArr[3]) + ((float) (viewHeight >> 1));
    }

    public static final void preloadModels(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            getModel(iArr[i2], i, true);
        }
    }

    public static final void endFrame() {
        Graphics3D.releaseTarget();
    }

    private static final void setupCamera(int i, int i2, int i3) {
        viewTop = i;
        viewWidth = i2;
        viewHeight = i3;
        aspect = ((((float) viewWidth) / ((float) (viewHeight - i))) * 0.7f) + 0.3f;
        aspectInv = ((((float) (viewHeight - i)) / ((float) viewWidth)) * 0.7f) + 0.3f;
        Renderer3D.setFov(60.0f);
        cameraTransform = Graphics3D.identity();
    }

    private static void setClip(Graphics g) {
        g.setClip(0, viewTop, viewWidth, viewHeight - viewTop);
    }
}
