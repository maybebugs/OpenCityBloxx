/** Vibration wrapper. Desktop has no vibration motor, so this is a no-op. (obfuscated: b) */
public final class Vibra {
    private boolean enabled = true;

    public final void vibrate(int ms) {
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
