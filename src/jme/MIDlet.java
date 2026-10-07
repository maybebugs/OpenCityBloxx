package jme;

import java.util.HashMap;
import java.util.Map;

/** Minimal application base class (replacement for javax.microedition.midlet.MIDlet). */
public abstract class MIDlet {
    private static final Map<String, String> PROPS = new HashMap<String, String>();
    static {
        PROPS.put("DChoc-Rain-Probability", "20");
        PROPS.put("DChoc-Rain-Duration", "60");
        PROPS.put("DChoc-Snow-Probability", "30");
        PROPS.put("DChoc-Snow-Duration", "50");
    }

    protected abstract void startApp();
    protected abstract void pauseApp();
    protected abstract void destroyApp(boolean unconditional);

    public final String getAppProperty(String key) { return PROPS.get(key); }

    public final void notifyDestroyed() {
        Display.shutdown();
    }

    public final void startFromLauncher() { startApp(); }
    public final void destroyFromLauncher() { destroyApp(true); }
}
