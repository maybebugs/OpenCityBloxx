import jme.Graphics3D;
import jme.Model;

/** Model instance with its own transform (software 3D). */
public final class Mesh3D {
    private float[] transform = Graphics3D.identity();
    private final Model mesh;

    public Mesh3D(Model mesh) {
        this.mesh = mesh;
    }

    public final void render() {
        Graphics3D.render(this.mesh, this.transform);
    }

    public final void translate(float dx, float dy, float dz) {
        if (dx < 1.0E-4f && dx > -1.0E-4f) {
            dx = 0.0f;
        }
        if (dy < 1.0E-4f && dy > -1.0E-4f) {
            dy = 0.0f;
        }
        if (dz < 1.0E-4f && dz > -1.0E-4f) {
            dz = 0.0f;
        }
        this.transform = Graphics3D.mul(this.transform, Graphics3D.translation(dx, dy, dz));
    }

    public final void rotate(float angleFraction, float axisX, float axisY, float axisZ) {
        this.transform = Graphics3D.mul(this.transform, Graphics3D.rotation(360.0f * angleFraction, axisX, axisY, axisZ));
    }

    public final void setupAppearance() {
        this.mesh.setupAppearance();
    }

    public final void resetTransform() {
        this.transform = Graphics3D.identity();
    }
}
