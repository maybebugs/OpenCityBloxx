

import jme.Canvas;
import jme.Command;
import jme.Display;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class GameCanvas extends Canvas implements ICanvas {
    protected GameMIDlet midlet = null;
    private boolean justActivated = false;
    private boolean suppressPause = false;
    private Command leftCommand = null;
    private Command rightCommand = null;
    private Image leftIcon = null;
    private Image rightIcon = null;
    private Font softKeyFont = Font.getFont(32, 1, 8);

    private void paintSoftKeys(Graphics g) {
        int baselineY = GameMIDlet.screenHeight - 2;
        g.setClip(0, GameMIDlet.screenHeight - ((this.softKeyFont.getHeight() + 2) + 4), GameMIDlet.screenWidth, (this.softKeyFont.getHeight() + 2) + 4);
        g.setFont(this.softKeyFont);
        if (this.leftCommand != null) {
            if (this.leftIcon != null) {
                g.drawImage(this.leftIcon, 2, baselineY, 36);
            } else {
                String label = this.leftCommand.getLabel();
                int stringWidth = this.softKeyFont.stringWidth(label);
                int x = (this.rightCommand != null) ? 4 : (GameMIDlet.screenWidth >> 1) - (stringWidth >> 1);
                int textY = baselineY - 1;
                g.setColor(0);
                g.drawString(label, x - 1, textY, 36);
                g.drawString(label, x, textY - 1, 36);
                g.drawString(label, x + 1, textY, 36);
                g.drawString(label, x, textY + 1, 36);
                g.setColor(16777215);
                g.drawString(label, x, textY, 36);
            }
        }
        if (this.rightCommand == null) {
            return;
        }
        if (this.rightIcon != null) {
            g.drawImage(this.rightIcon, GameMIDlet.screenWidth - 2, baselineY, 40);
            return;
        }
        int rightX = GameMIDlet.screenWidth - 3;
        baselineY--;
        g.setColor(0);
        g.drawString(this.rightCommand.getLabel(), rightX - 1, baselineY, 40);
        g.drawString(this.rightCommand.getLabel(), rightX, baselineY - 1, 40);
        g.drawString(this.rightCommand.getLabel(), rightX + 1, baselineY, 40);
        g.drawString(this.rightCommand.getLabel(), rightX, baselineY + 1, 40);
        g.setColor(16777215);
        g.drawString(this.rightCommand.getLabel(), rightX, baselineY, 40);
    }

    public final void setMidlet(GameMIDlet gameMIDlet) {
        this.midlet = gameMIDlet;
        setCloseHandler(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        try { midlet.destroyFromLauncher(); } catch (Throwable t) { }
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
                this.leftCommand = null;
                this.leftIcon = null;
                return;
            case 2:
            case 3:
            case 6:
            case 7:
                this.rightCommand = null;
                this.rightIcon = null;
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
                this.leftCommand = command;
                this.leftIcon = image;
                return;
            case 2:
            case 3:
            case 6:
            case 7:
                this.rightCommand = command;
                this.rightIcon = image;
                return;
            default:
                return;
        }
    }

    public final void setActive(boolean active) {
        if (active) {
            this.suppressPause = false;
            this.justActivated = true;
            Display.getDisplay(this.midlet).setCurrent(this);
            return;
        }
        this.suppressPause = true;
    }

    protected final void hideNotify() {
        try {
            if (!this.suppressPause) {
                this.midlet.pauseApp();
            }
        } catch (Throwable t) {
        }
    }

    protected final void keyPressed(int keyCode) {
        if (keyCode == -6) {
            try {
                if (this.leftCommand != null) {
                    this.midlet.handleCommand(this.leftCommand);
                }
            } catch (Throwable t) {
            }
        } else if (keyCode == -7) {
            if (this.rightCommand != null) {
                this.midlet.handleCommand(this.rightCommand);
            }
        } else if (keyCode != -11 && keyCode != -12) {
            this.midlet.keyPressedRaw(keyCode);
        }
    }

    protected final void keyReleased(int keyCode) {
        if (keyCode != -6 && keyCode != -7) {
            try {
                this.midlet.keyReleasedRaw(keyCode);
            } catch (Throwable t) {
            }
        }
    }

    protected final void keyRepeated(int keyCode) {
        if (keyCode != -6 && keyCode != -7) {
            try {
                this.midlet.keyRepeatedRaw(keyCode);
            } catch (Throwable t) {
            }
        }
    }

    public final void paint(Graphics g) {
        try {
            this.midlet.paintCanvas(g);
            paintSoftKeys(g);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected final void showNotify() {
        try {
            if (this.justActivated) {
                this.justActivated = false;
            } else {
                this.midlet.resume();
            }
        } catch (Throwable t) {
        }
    }

    @Override
    protected final void pointerPressed(int pointerX, int pointerY) {
        if (pointerY >= GameMIDlet.screenHeight - 26) {
            if (pointerX < GameMIDlet.screenWidth / 3) {
                keyPressed(-6);
                keyReleased(-6);
                return;
            } else if (pointerX > GameMIDlet.screenWidth * 2 / 3) {
                keyPressed(-7);
                keyReleased(-7);
                return;
            } else {
                keyPressed(-5);
                keyReleased(-5);
                return;
            }
        }
        Screen currentScreen = this.midlet != null ? this.midlet.getCurrentScreen() : null;
        if (currentScreen instanceof MenuController) {
            int item = Ui.getItemAt(pointerX, pointerY);
            if (item >= 0) {
                ((MenuController) currentScreen).selectItem(item);
                return;
            }
            keyPressed(-5);
            keyReleased(-5);
            return;
        } else if (currentScreen instanceof House) {
            if (House.getMode() == 2) {
                if (CityMode.handleClick(pointerX, pointerY)) {
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
    protected final void pointerReleased(int pointerX, int pointerY) {
    }
}
