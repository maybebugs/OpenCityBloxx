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

    public final void translate(float f, float f2, float f3) {
        if (f < 1.0E-4f && f > -1.0E-4f) {
            f = 0.0f;
        }
        if (f2 < 1.0E-4f && f2 > -1.0E-4f) {
            f2 = 0.0f;
        }
        if (f3 < 1.0E-4f && f3 > -1.0E-4f) {
            f3 = 0.0f;
        }
        this.transform = Graphics3D.mul(this.transform, Graphics3D.translation(f, f2, f3));
    }

    public final void rotate(float f, float f2, float f3, float f4) {
        this.transform = Graphics3D.mul(this.transform, Graphics3D.rotation(360.0f * f, f2, f3, f4));
    }

    public final void setupAppearance() {
        this.mesh.setupAppearance();
    }

    public final void resetTransform() {
        this.transform = Graphics3D.identity();
    }
}
