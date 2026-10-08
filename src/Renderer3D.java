

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

    private static String resourceName(int resId) {
        return "/" + (Integer.MAX_VALUE & resId);
    }

    public static final Mesh3D getModel(int id, int resId, boolean load) {
        Mesh3D cached = (Mesh3D) models.get(Integer.valueOf(id));
        if (cached != null || !load) {
            return cached;
        }
        Model mesh = Model.find(id, resourceName(resId));
        if (mesh == null) {
            return null;
        }
        Mesh3D created = new Mesh3D(mesh);
        created.setupAppearance();
        models.put(Integer.valueOf(id), created);
        return created;
    }

    public static final void setFov(float fovDegrees) {
        fov = fovDegrees;
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
        float crossX = (dirY * upZ) - (dirZ * upY);
        float crossY = (dirZ * upX) - (dirX * upZ);
        float crossZ = (dirX * upY) - (dirY * upX);
        float invLength = 1.0f / ((float) Math.sqrt((double) (((crossX * crossX) + (crossY * crossY)) + (crossZ * crossZ))));
        crossX *= invLength;
        crossY *= invLength;
        crossZ *= invLength;
        float finalUpX = (crossY * dirZ) - (crossZ * dirY);
        float finalUpY = (crossZ * dirX) - (crossX * dirZ);
        float finalUpZ = (crossX * dirY) - (crossY * dirX);
        float[] matrix = new float[16];
        matrix[0] = crossX;
        matrix[1] = finalUpX;
        matrix[2] = -dirX;
        matrix[3] = eyeX;
        matrix[4] = crossY;
        matrix[5] = finalUpY;
        matrix[6] = -dirY;
        matrix[7] = eyeY;
        matrix[8] = crossZ;
        matrix[9] = finalUpZ;
        matrix[10] = -dirZ;
        matrix[11] = eyeZ;
        matrix[12] = 0.0f;
        matrix[13] = 0.0f;
        matrix[14] = 0.0f;
        matrix[15] = 1.0f;
        cameraTransform = matrix;
        Graphics3D.setCamera(cameraTransform, projection);
    }

    public static final void init(int top, int width, int height) {
        models = new Hashtable();
        Renderer3D.setupCamera(top, width, height);
    }

    public static final void beginFrame(Object obj) {
        Graphics g = (Graphics) obj;
        Renderer3D.setClip(g);
        Graphics3D.bindTarget(g, 0, 0, viewWidth, viewHeight);
        Graphics3D.clearDepth();
        Graphics3D.setCamera(cameraTransform, projection);
    }

    public static final void project(float[] vertexCoords) {
        float[] invCamera = Graphics3D.invert(cameraTransform);
        Graphics3D.transform(invCamera, vertexCoords);
        Graphics3D.transform(projection, vertexCoords);
        vertexCoords[0] = (((0.5f * ((float) viewWidth)) * vertexCoords[0]) / vertexCoords[3]) + ((float) (viewWidth >> 1));
        vertexCoords[1] = (((-0.5f * ((float) viewHeight)) * vertexCoords[1]) / vertexCoords[3]) + ((float) (viewHeight >> 1));
    }

    public static final void preloadModels(int[] ids, int resId) {
        for (int k = 0; k < ids.length; k++) {
            getModel(ids[k], resId, true);
        }
    }

    public static final void endFrame() {
        Graphics3D.releaseTarget();
    }

    private static final void setupCamera(int top, int width, int height) {
        viewTop = top;
        viewWidth = width;
        viewHeight = height;
        aspect = ((((float) viewWidth) / ((float) (viewHeight - top))) * 0.7f) + 0.3f;
        aspectInv = ((((float) (viewHeight - top)) / ((float) viewWidth)) * 0.7f) + 0.3f;
        Renderer3D.setFov(60.0f);
        cameraTransform = Graphics3D.identity();
    }

    private static void setClip(Graphics g) {
        g.setClip(0, viewTop, viewWidth, viewHeight - viewTop);
    }
}
