

import jme.Canvas;
import jme.Command;
import jme.Display;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class GameCanvas extends Canvas implements ICanvas {
    protected GameMIDlet a = null;
    private boolean b = false;
    private boolean c = false;
    private Command d = null;
    private Command e = null;
    private Image f = null;
    private Image g = null;
    private Font h = Font.getFont(32, 1, 8);

    private void paintSoftKeys(Graphics graphics) {
        int i = GameMIDlet.screenHeight - 2;
        graphics.setClip(0, GameMIDlet.screenHeight - ((this.h.getHeight() + 2) + 4), GameMIDlet.screenWidth, (this.h.getHeight() + 2) + 4);
        graphics.setFont(this.h);
        if (this.d != null) {
            if (this.f != null) {
                graphics.drawImage(this.f, 2, i, 36);
            } else {
                String label = this.d.getLabel();
                int stringWidth = this.h.stringWidth(label);
                int x = (this.e != null) ? 4 : (GameMIDlet.screenWidth >> 1) - (stringWidth >> 1);
                int i2 = i - 1;
                graphics.setColor(0);
                graphics.drawString(label, x - 1, i2, 36);
                graphics.drawString(label, x, i2 - 1, 36);
                graphics.drawString(label, x + 1, i2, 36);
                graphics.drawString(label, x, i2 + 1, 36);
                graphics.setColor(16777215);
                graphics.drawString(label, x, i2, 36);
            }
        }
        if (this.e == null) {
            return;
        }
        if (this.g != null) {
            graphics.drawImage(this.g, GameMIDlet.screenWidth - 2, i, 40);
            return;
        }
        int i3 = GameMIDlet.screenWidth - 3;
        i--;
        graphics.setColor(0);
        graphics.drawString(this.e.getLabel(), i3 - 1, i, 40);
        graphics.drawString(this.e.getLabel(), i3, i - 1, 40);
        graphics.drawString(this.e.getLabel(), i3 + 1, i, 40);
        graphics.drawString(this.e.getLabel(), i3, i + 1, 40);
        graphics.setColor(16777215);
        graphics.drawString(this.e.getLabel(), i3, i, 40);
    }

    public final void setMidlet(GameMIDlet gameMIDlet) {
        this.a = gameMIDlet;
        setCloseHandler(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        try { a.destroyFromLauncher(); } catch (Throwable t) { }
                        System.exit(0);
                    }
                }).start();
                new Thread(new Runnable() {
                    public void run() {
                        try { Thread.sleep(4000); } catch (InterruptedException e) { }
                        System.exit(0);
                    }
                }).start();
            }
        });
    }

    public final void removeCommand(Command command) {
        switch (command.getCommandType()) {
            case 1:
            case 4:
            case 5:
            case 8:
                this.d = null;
                this.f = null;
                return;
            case 2:
            case 3:
            case 6:
            case 7:
                this.e = null;
                this.g = null;
                return;
            default:
                return;
        }
    }

    public final void setCommand(Command command, Image image) {
        switch (command.getCommandType()) {
            case 1:
            case 4:
            case 5:
            case 8:
                this.d = command;
                this.f = image;
                return;
            case 2:
            case 3:
            case 6:
            case 7:
                this.e = command;
                this.g = image;
                return;
            default:
                return;
        }
    }

    public final void setActive(boolean z) {
        if (z) {
            this.c = false;
            this.b = true;
            Display.getDisplay(this.a).setCurrent(this);
            return;
        }
        this.c = true;
    }

    protected final void hideNotify() {
        try {
            if (!this.c) {
                this.a.pauseApp();
            }
        } catch (Throwable th) {
        }
    }

    protected final void keyPressed(int i) {
        if (i == -6) {
            try {
                if (this.d != null) {
                    this.a.handleCommand(this.d);
                }
            } catch (Throwable th) {
            }
        } else if (i == -7) {
            if (this.e != null) {
                this.a.handleCommand(this.e);
            }
        } else if (i != -11 && i != -12) {
            this.a.keyPressedRaw(i);
        }
    }

    protected final void keyReleased(int i) {
        if (i != -6 && i != -7) {
            try {
                this.a.keyReleasedRaw(i);
            } catch (Throwable th) {
            }
        }
    }

    protected final void keyRepeated(int i) {
        if (i != -6 && i != -7) {
            try {
                this.a.keyRepeatedRaw(i);
            } catch (Throwable th) {
            }
        }
    }

    public final void paint(Graphics graphics) {
        try {
            this.a.paintCanvas(graphics);
            paintSoftKeys(graphics);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected final void showNotify() {
        try {
            if (this.b) {
                this.b = false;
            } else {
                this.a.resume();
            }
        } catch (Throwable th) {
        }
    }

    @Override
    protected final void pointerPressed(int x, int y) {
        if (y >= GameMIDlet.screenHeight - 26) {
            if (x < GameMIDlet.screenWidth / 3) {
                keyPressed(-6);
                keyReleased(-6);
                return;
            } else if (x > GameMIDlet.screenWidth * 2 / 3) {
                keyPressed(-7);
                keyReleased(-7);
                return;
            } else {
                keyPressed(-5);
                keyReleased(-5);
                return;
            }
        }
        Screen currentScreen = this.a != null ? this.a.getCurrentScreen() : null;
        if (currentScreen instanceof MenuController) {
            int item = Ui.getItemAt(x, y);
            if (item >= 0) {
                ((MenuController) currentScreen).selectItem(item);
                return;
            }
            keyPressed(-5);
            keyReleased(-5);
            return;
        } else if (currentScreen instanceof House) {
            if (House.getMode() == 2) {
                if (CityMode.handleClick(x, y)) {
                    return;
                }
            } else if (House.getMode() != 7) {
                House.dropBlock();
                return;
            }
        }
        keyPressed(-5);
        keyReleased(-5);
    }

    @Override
    protected final void pointerReleased(int x, int y) {
    }
}
