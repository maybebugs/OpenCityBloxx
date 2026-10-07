

import java.io.DataInputStream;
import java.io.DataOutputStream;
import jme.Canvas;
import jme.Command;
import jme.Graphics;
import jme.MIDlet;

public abstract class GameMIDlet extends MIDlet implements Runnable, Screen {
    public static int screenWidth;
    public static int screenHeight;
    private static GameMIDlet m;
    protected HighScores highScores;
    public boolean painting;
    public int loadState = -1;
    public Thread thread;
    private int a = -1;
    private long b = -1;
    private int c = -1;
    private Screen d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l = true;
    public int dtIndex = -1;
    public int[] dtHistory;
    protected ICanvas canvas;
    protected SoundPlayer sound;
    protected Vibra vibra;
    protected MenuController menu;

    public GameMIDlet() {
        m = this;
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
            DataInputStream a = Storage.openRead("settings");
            if (a != null) {
                Storage.setSetting(0, a.readInt());
                Storage.setSetting(3, a.readInt());
                if (Storage.getSetting(3) == 0) {
                    this.sound.setEnabled(false);
                }
                a.close();
            }
            Storage.close();
            Resources.init();
            Ui.setColorPalette(MenuController.getMenuHierarchy());
            Storage.setMenuFlag(4, 1);
            this.highScores = PlatformFactory.createHighScores();
            this.highScores.init(this, this.menu, this.canvas);
            Storage.setMenuFlag(7, 1);
            Storage.setMenuFlag(6, 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void handleLoadingKeyPressed(int i, int i2) {
        switch (this.loadState) {
            case 3:
                keyPressed(i, i2);
                return;
            default:
                return;
        }
    }

    private void paintLoadingOverlay(Graphics graphics) {
        switch (this.loadState) {
            case 3:
                paintLoading(graphics);
                return;
            default:
                return;
        }
    }

    private boolean processLoading(int i, int i2) {
        Ui.update(0);
        switch (this.loadState) {
            case -1:
                onLoadBeginWrapper();
                this.loadState = 3;
                return processLoading(i, i2);
            case 3:
                Storage.setSetting(4, Resources.getString(152).compareTo("1") == 0 ? 1 : 0);
                if (!this.l) {
                    this.sound.setEnabled(false);
                }
                if (loadStep(i2)) {
                    return true;
                }
                if (!this.l) {
                    if (Storage.getSetting(3) == 1) {
                        this.sound.setEnabled(true);
                    }
                    this.l = true;
                }
                onLoadEndWrapper();
                this.loadState = -1;
                this.k = false;
                Storage.setScreenId(0);
                return false;
            default:
                return true;
        }
    }

    private void releaseResources() {
        if (!this.i) {
            Resources.close();
            this.i = true;
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
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = (int) (currentTimeMillis - this.b);
        this.a += i2;
        this.b = currentTimeMillis;
        if (i2 <= 500) {
            i = i2;
        }
        this.c -= this.dtHistory[this.dtIndex];
        this.c += i;
        this.dtHistory[this.dtIndex] = i;
        this.dtIndex = (this.dtIndex + 1) & 7;
        return this.c >> 3;
    }

    private Screen getActiveScreen() {
        if (this.k) {
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
                return this.d;
            case 4:
                this.e = true;
                return null;
            case 6:
                return this.d;
            default:
                return this.d;
        }
    }

    public static GameMIDlet getInstance() {
        return m;
    }

    private static void saveSettingsRecord() throws Exception {
        DataOutputStream b = Storage.openWrite("settings");
        b.writeInt(Storage.getSetting(0));
        b.writeInt(Storage.getSetting(3));
        b.close();
        Storage.close();
    }

    protected abstract void onInit();

    public abstract void commandAction(Command command);

    protected abstract void paintLoading(Graphics graphics);

    protected abstract void onPause();

    public abstract void keyPressed(int i, int i2);

    public final void handleCommand(Command command) {
        if (this.d != null) {
            this.d.commandAction(command);
        } else if (this.k && this.loadState == 3) {
            commandAction(command);
            handleLoadingKeyPressed(53, 8);
        }
    }

    protected abstract void paintBackground(Graphics graphics);

    protected abstract void saveGame();

    protected abstract void saveSettings();

    public final void paintCanvas(Graphics graphics) {
        if (!this.painting) {
            return;
        }
        if (this.d != null) {
            if (this.d != this && Storage.dirty) {
                paintBackground(graphics);
            }
            this.d.paint(graphics, this.j);
        } else if (this.k) {
            paintLoadingOverlay(graphics);
        }
    }

    protected abstract boolean loadStep(int i);

    protected void destroyApp(boolean z) {
        try {
            this.g = true;
            this.e = true;
            if (this.thread != null) {
                this.thread.join();
                this.thread = null;
            }
        } catch (Throwable th) {
        }
    }

    protected abstract void onLoad();

    public void keyReleasedRaw(int i) {
        if (this.d != null) {
            this.canvas.getGameAction(i);
        }
    }

    public abstract String formatNumber(int i);

    protected abstract void updateBackground(int i);

    protected abstract void onLoadBegin();

    protected abstract void onLoadEnd();

    protected abstract void onMenuTick();

    public final void keyPressedRaw(int i) {
        int i2 = 0;
        try {
            i2 = this.canvas.getGameAction(i);
        } catch (Exception e) {
        }
        if (this.d != null) {
            this.d.keyPressed(i, i2);
        } else if (this.k) {
            handleLoadingKeyPressed(i, i2);
        }
    }

    public final void keyRepeatedRaw(int i) {
        if (this.d != null) {
            this.canvas.getGameAction(i);
        }
    }

    public abstract int[][] getHighScoreLayout();

    protected void pauseApp() {
        try {
            this.sound.stopAll();
            if (!(!this.k || this.loadState == 2 || this.loadState == 1)) {
                this.l = false;
                this.sound.setEnabled(false);
            }
            this.h = true;
            onPause();
        } catch (Throwable th) {
        }
    }

    protected final void resume() {
        try {
            Storage.dirty = true;
            this.b = System.currentTimeMillis();
            this.h = false;
            if (this.loadState != -1) {
                Ui.update(0);
            }
        } catch (Throwable th) {
        }
    }

    public void run() {
        while (!this.e) {
            long frameStart = System.currentTimeMillis();
            try {
                if (!this.h) {
                    int i = calculateDeltaTime();
                    this.d = getActiveScreen();
                    if (this.d != null) {
                        this.d.onModeChange();
                        Screen p = getActiveScreen();
                        if (this.d != p) {
                            if (p != null) {
                                p.onEnter();
                                resetTiming();
                            }
                            this.d = p;
                        } else if (this.d == this.menu) {
                            onMenuTick();
                        }
                        if (!(this.e || this.h)) {
                            if (this.d != this) {
                                updateBackground(i);
                            }
                            this.d.update(i, this.a);
                            repaintNow();
                        }
                    } else if (this.k) {
                        boolean d = processLoading(i, this.a);
                        if (!(this.e || this.h || !d)) {
                            repaintNow();
                        }
                    }
                }
                try {
                    // Desktop frame limiter (~33 fps). Without it the loop spins at thousands of
                    // frames/s, the integer dt average truncates to 0 and game time never advances.
                    long spent = System.currentTimeMillis() - frameStart;
                    long wait = 30 - spent;
                    Thread.sleep(wait < 2 ? 2 : wait);
                } catch (Exception e) {
                }
            } catch (Throwable th) {
                th.printStackTrace();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
        }
        try {
            if (this.e) {
                this.sound.stopAll();
                saveSettingsRecord();
                saveSettings();
                saveGame();
                releaseResources();
                if (!this.g) {
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
            if (this.f) {
                resume();
                return;
            }
            this.k = true;
            onInit();
            onLoad();
            this.canvas.setMidlet(this);
            this.canvas.setActive(true);
            this.thread = new Thread(this, "game-loop");
            this.thread.start();
            this.f = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void fullRepaint() {
        this.j = true;
        repaintNow();
        this.j = false;
    }

    protected final void resetTiming() {
        int i = 0;
        this.a = 0;
        this.b = System.currentTimeMillis();
        this.dtIndex = 0;
        while (i < 8) {
            this.dtHistory[i] = 40;
            i++;
        }
        this.c = 320;
    }

    public Screen getCurrentScreen() {
        return this.d;
    }
}
