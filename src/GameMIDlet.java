

import java.io.DataInputStream;
import java.io.DataOutputStream;
import jme.Canvas;
import jme.Command;
import jme.Graphics;
import jme.MIDlet;

public abstract class GameMIDlet extends MIDlet implements Runnable, Screen {
    public static int screenWidth;
    public static int screenHeight;
    private static GameMIDlet instance;
    protected HighScores highScores;
    public boolean painting;
    public int loadState = -1;
    public Thread thread;
    private int elapsedMs = -1;
    private long lastFrameTime = -1;
    private int dtSum = -1;
    private Screen currentScreen;
    private boolean exitRequested;
    private boolean started;
    private boolean destroyedExternally;
    private boolean paused;
    private boolean resourcesReleased;
    private boolean forceFullPaint;
    private boolean loading;
    private boolean soundAllowed = true;
    public int dtIndex = -1;
    public int[] dtHistory;
    protected ICanvas canvas;
    protected SoundPlayer sound;
    protected Vibra vibra;
    protected MenuController menu;

    public GameMIDlet() {
        instance = this;
        try {
            this.dtHistory = new int[8];
            resetTiming();
            this.canvas = PlatformFactory.createCanvas();
            screenWidth = this.canvas.getWidth();
            screenHeight = this.canvas.getHeight();
            this.sound = PlatformFactory.createSoundPlayer();
            this.sound.setEnabled(true);
            Storage.setSetting(3, 1);
            Storage.setSetting(0, 1);
            this.vibra = PlatformFactory.createVibra();
            this.menu = new MenuController(this.canvas);
            DataInputStream dis = Storage.openRead("settings");
            if (dis != null) {
                Storage.setSetting(0, dis.readInt());
                Storage.setSetting(3, dis.readInt());
                if (Storage.getSetting(3) == 0) {
                    this.sound.setEnabled(false);
                }
                dis.close();
            }
            Storage.close();
            Resources.init();
            Ui.setColorPalette(MenuController.getMenuHierarchy());
            Storage.setMenuFlag(4, 1);
            this.highScores = PlatformFactory.createHighScores();
            this.highScores.init(this, this.menu, this.canvas);
            Storage.setMenuFlag(7, 1);
            Storage.setMenuFlag(6, 1);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void handleLoadingKeyPressed(int keyCode, int gameAction) {
        switch (this.loadState) {
            case 3:
                keyPressed(keyCode, gameAction);
                return;
            default:
                return;
        }
    }

    private void paintLoadingOverlay(Graphics g) {
        switch (this.loadState) {
            case 3:
                paintLoading(g);
                return;
            default:
                return;
        }
    }

    private boolean processLoading(int keyCode, int gameAction) {
        Ui.update(0);
        switch (this.loadState) {
            case -1:
                onLoadBeginWrapper();
                this.loadState = 3;
                return processLoading(keyCode, gameAction);
            case 3:
                Storage.setSetting(4, Resources.getString(152).compareTo("1") == 0 ? 1 : 0);
                if (!this.soundAllowed) {
                    this.sound.setEnabled(false);
                }
                if (loadStep(gameAction)) {
                    return true;
                }
                if (!this.soundAllowed) {
                    if (Storage.getSetting(3) == 1) {
                        this.sound.setEnabled(true);
                    }
                    this.soundAllowed = true;
                }
                onLoadEndWrapper();
                this.loadState = -1;
                this.loading = false;
                Storage.setScreenId(0);
                return false;
            default:
                return true;
        }
    }

    private void releaseResources() {
        if (!this.resourcesReleased) {
            Resources.close();
            this.resourcesReleased = true;
        }
    }

    private void onLoadBeginWrapper() {
        onLoadBegin();
    }

    private void onLoadEndWrapper() {
        onLoadEnd();
    }

    private int calculateDeltaTime() {
        int i = 500;
        long currentTimeMillis = nowMs();
        int i2 = (int) (currentTimeMillis - this.lastFrameTime);
        this.elapsedMs += i2;
        this.lastFrameTime = currentTimeMillis;
        if (i2 <= 500) {
            i = i2;
        }
        this.dtSum -= this.dtHistory[this.dtIndex];
        this.dtSum += i;
        this.dtHistory[this.dtIndex] = i;
        this.dtIndex = (this.dtIndex + 1) & 7;
        return this.dtSum >> 3;
    }

    private Screen getActiveScreen() {
        if (this.loading) {
            return null;
        }
        switch (Storage.getScreenId()) {
            case 0:
                return this.menu;
            case 1:
                return this;
            case 2:
                return this.highScores;
            case 3:
                return this.currentScreen;
            case 4:
                this.exitRequested = true;
                return null;
            case 6:
                return this.currentScreen;
            default:
                return this.currentScreen;
        }
    }

    public static GameMIDlet getInstance() {
        return instance;
    }

    private static void saveSettingsRecord() throws Exception {
        DataOutputStream dos = Storage.openWrite("settings");
        dos.writeInt(Storage.getSetting(0));
        dos.writeInt(Storage.getSetting(3));
        dos.close();
        Storage.close();
    }

    protected abstract void onInit();

    public abstract void commandAction(Command command);

    protected abstract void paintLoading(Graphics g);

    protected abstract void onPause();

    public abstract void keyPressed(int keyCode, int gameAction);

    public final void handleCommand(Command command) {
        if (this.currentScreen != null) {
            this.currentScreen.commandAction(command);
        } else if (this.loading && this.loadState == 3) {
            commandAction(command);
            handleLoadingKeyPressed(53, 8);
        }
    }

    protected abstract void paintBackground(Graphics g);

    protected abstract void saveGame();

    protected abstract void saveSettings();

    public final void paintCanvas(Graphics g) {
        if (!this.painting) {
            return;
        }
        if (this.currentScreen != null) {
            if (this.currentScreen != this && Storage.dirty) {
                paintBackground(g);
            }
            this.currentScreen.paint(g, this.forceFullPaint);
        } else if (this.loading) {
            paintLoadingOverlay(g);
        }
    }

    protected abstract boolean loadStep(int step);

    protected void destroyApp(boolean unconditional) {
        try {
            this.destroyedExternally = true;
            this.exitRequested = true;
            if (this.thread != null) {
                this.thread.join();
                this.thread = null;
            }
        } catch (Throwable t) {
        }
    }

    protected abstract void onLoad();

    public void keyReleasedRaw(int keyCode) {
        if (this.currentScreen != null) {
            this.canvas.getGameAction(keyCode);
        }
    }

    public abstract String formatNumber(int number);

    protected abstract void updateBackground(int dt);

    protected abstract void onLoadBegin();

    protected abstract void onLoadEnd();

    protected abstract void onMenuTick();

    public final void keyPressedRaw(int keyCode) {
        int i2 = 0;
        try {
            i2 = this.canvas.getGameAction(keyCode);
        } catch (Exception e) {
        }
        if (this.currentScreen != null) {
            this.currentScreen.keyPressed(keyCode, i2);
        } else if (this.loading) {
            handleLoadingKeyPressed(keyCode, i2);
        }
    }

    public final void keyRepeatedRaw(int keyCode) {
        if (this.currentScreen != null) {
            this.canvas.getGameAction(keyCode);
        }
    }

    public abstract int[][] getHighScoreLayout();

    protected void pauseApp() {
        try {
            this.sound.stopAll();
            if (!(!this.loading || this.loadState == 2 || this.loadState == 1)) {
                this.soundAllowed = false;
                this.sound.setEnabled(false);
            }
            this.paused = true;
            onPause();
        } catch (Throwable t) {
        }
    }

    protected final void resume() {
        try {
            Storage.dirty = true;
            this.lastFrameTime = nowMs();
            this.paused = false;
            if (this.loadState != -1) {
                Ui.update(0);
            }
        } catch (Throwable t) {
        }
    }

    /** Target frame rate of the desktop loop (game logic is dt-based, so this only affects smoothness). */
    private static final int TARGET_FPS = 60;
    private static final long FRAME_NS = 1000000000L / TARGET_FPS;
    private long nextFrameNs;

    /** Monotonic millisecond clock (immune to system clock changes / coarse timer ticks). */
    private static long nowMs() {
        return System.nanoTime() / 1000000L;
    }

    /**
     * Fixed-step frame limiter: sleeps most of the remaining time, then yields for the last
     * ~1.5 ms so frames land on an even cadence instead of the 30 ms +/- timer jitter of
     * Thread.sleep.  If we fall behind, the schedule resets instead of trying to catch up.
     */
    private void paceFrame(long frameStartNs) {
        long target = nextFrameNs == 0 ? frameStartNs + FRAME_NS : nextFrameNs + FRAME_NS;
        long now = System.nanoTime();
        if (target < now - FRAME_NS) target = now;      // fell behind: resync
        nextFrameNs = target;
        try {
            long left;
            while ((left = target - System.nanoTime()) > 1500000L) {
                Thread.sleep((left - 1000000L) / 1000000L);
            }
            while (target - System.nanoTime() > 0) {
                Thread.yield();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        while (!this.exitRequested) {
            long frameStartNs = System.nanoTime();
            try {
                if (!this.paused) {
                    int i = calculateDeltaTime();
                    this.currentScreen = getActiveScreen();
                    if (this.currentScreen != null) {
                        this.currentScreen.onModeChange();
                        Screen p = getActiveScreen();
                        if (this.currentScreen != p) {
                            if (p != null) {
                                p.onEnter();
                                resetTiming();
                            }
                            this.currentScreen = p;
                        } else if (this.currentScreen == this.menu) {
                            onMenuTick();
                        }
                        if (!(this.exitRequested || this.paused)) {
                            if (this.currentScreen != this) {
                                updateBackground(i);
                            }
                            this.currentScreen.update(i, this.elapsedMs);
                            repaintNow();
                        }
                    } else if (this.loading) {
                        boolean d = processLoading(i, this.elapsedMs);
                        if (!(this.exitRequested || this.paused || !d)) {
                            repaintNow();
                        }
                    }
                }
                paceFrame(frameStartNs);
            } catch (Throwable t) {
                t.printStackTrace();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
        }
        try {
            if (this.exitRequested) {
                this.sound.stopAll();
                saveSettingsRecord();
                saveSettings();
                saveGame();
                releaseResources();
                if (!this.destroyedExternally) {
                    notifyDestroyed();
                }
            }
        } catch (Throwable th2) {
        }
    }

    public final void repaintNow() {
        this.painting = true;
        Canvas canvas = (Canvas) this.canvas;
        canvas.repaint();
        canvas.serviceRepaints();
        this.painting = false;
    }

    protected void startApp() {
        try {
            if (this.started) {
                resume();
                return;
            }
            this.loading = true;
            onInit();
            onLoad();
            this.canvas.setMidlet(this);
            this.canvas.setActive(true);
            this.thread = new Thread(this, "game-loop");
            this.thread.start();
            this.started = true;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public final void fullRepaint() {
        this.forceFullPaint = true;
        repaintNow();
        this.forceFullPaint = false;
    }

    protected final void resetTiming() {
        int i = 0;
        this.elapsedMs = 0;
        this.lastFrameTime = nowMs();
        this.dtIndex = 0;
        while (i < 8) {
            this.dtHistory[i] = 40;
            i++;
        }
        this.dtSum = 320;
    }

    public Screen getCurrentScreen() {
        return this.currentScreen;
    }
}
