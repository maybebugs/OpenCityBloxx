

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import jme.Command;
import jme.Display;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class HighScores implements ModalScreen {
    private static Image headerScoreIcon;
    private static Image unusedIcon;
    private static Image gradientHeader;
    private static Image gradientRow;
    private static boolean scrolledDown = false;
    private static boolean atTop = false;
    private static boolean firstPaint = true;
    private static boolean endVisible = false;
    private static ICanvas canvas;
    private static int[][] layout;
    private static Command okCommand;
    private static Command backCommand;
    private static final int rankWidth = Font.getFont(32, 0, 8).stringWidth("3. ");
    private static final int nameX = (rankWidth + 8);
    private static final int rightEdge = ((GameMIDlet.screenWidth - 8) - rankWidth);
    private static final int scoreRight = (rightEdge - 8);
    private static final int scoreLeft = (nameX + 8);
    private static final int lineHeight = Font.getFont(32, 0, 8).getHeight();
    private static final Font bodyFont = Font.getFont(32, 0, 8);
    private static final Font boldFont = Font.getFont(32, 1, 8);
    private static Image rowScoreIcon;
    private String[][] names = ((String[][]) null);
    private int[][][] scores = ((int[][][]) null);
    private String playerName;
    private int[] pendingScore;
    private int pendingMode;
    private int pendingRank;
    private GameMIDlet midlet;
    private MenuController menu;
    private int requestedState = 0;
    private int state = 0;
    private int returnState = 0;
    private boolean submitRequested;
    private Command[] softkeys;

    static {
        Font.getFont(32, 0, 8).stringWidth("999999");
    }

    public HighScores() {
        rowScoreIcon = Resources.getImage(6);
        headerScoreIcon = Resources.getImage(7);
        unusedIcon = Resources.getImage(8);
        gradientHeader = HighScores.createGradient(GameMIDlet.screenWidth, lineHeight);
        gradientRow = HighScores.createGradient(GameMIDlet.screenWidth, lineHeight * 2);
    }

    private void save() {
        try {
            DataOutputStream dos = Storage.openWrite("HoF");
            dos.writeUTF(this.playerName);
            for (int i = 0; i < layout.length; i++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    dos.writeUTF(this.names[i][i2]);
                    for (int writeInt : this.scores[i][i2]) {
                        dos.writeInt(writeInt);
                    }
                }
            }
            dos.close();
            Storage.close();
        } catch (Exception e) {
        }
    }

    private void paintScoreTable(Graphics g) {
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        g.setFont(bodyFont);
        g.setColor(0);

        boolean isRtl = (Storage.getSetting(4) == 1);
        int topSectionX = isRtl ? (rightEdge - 8) : (nameX + 8);
        int topAnchor = isRtl ? 24 : 20;

        g.setFont(boldFont);
        g.drawString(Resources.getString(92) + ":", topSectionX, 42, topAnchor);

        g.setFont(bodyFont);
        int curY = lineHeight + 42;
        String modeLabel = isRtl ? (" " + Resources.getString(154)) : (Resources.getString(154) + " ");
        g.drawString(modeLabel, topSectionX, curY, topAnchor);
        curY += lineHeight;

        g.drawImage(gradientHeader, 0, curY - 3, 20);

        int iconX = isRtl ? (topSectionX - headerScoreIcon.getWidth()) : topSectionX;
        g.setClip(iconX, curY + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
        g.drawImage(headerScoreIcon, iconX, curY + 2, 20);
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);

        String scoreStr = this.midlet.formatNumber(this.scores[0][0][0]);
        if (isRtl) {
            g.drawString(scoreStr, (iconX - 8) - bodyFont.stringWidth("000000"), curY, 20);
        } else {
            g.drawString(scoreStr, (iconX + headerScoreIcon.getWidth() + 8) + bodyFont.stringWidth("000000"), curY, 24);
        }

        curY += (lineHeight * 3) / 2;
        g.setFont(boldFont);
        g.drawString(Resources.getString(91) + ":", topSectionX, curY, topAnchor);

        g.setFont(bodyFont);
        int rowY = curY + lineHeight;
        for (int row = 0; row < 3; row++) {
            g.drawImage(gradientRow, 0, rowY - 3, 20);
            String rankStr = (row + 1) + ". ";
            if (isRtl) {
                g.drawString(rankStr, rightEdge, rowY, 20);
            } else {
                g.drawString(rankStr, nameX, rowY, 24);
            }

            String playerName = this.names[1][row];
            if (isRtl) {
                int nameIconX = scoreRight - rowScoreIcon.getWidth();
                g.setClip(nameIconX, rowY + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
                g.drawImage(rowScoreIcon, nameIconX, rowY + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                g.drawString(playerName, nameIconX - 8, rowY, 24);
            } else {
                g.setClip(scoreLeft, rowY + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
                g.drawImage(rowScoreIcon, scoreLeft, rowY + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                g.drawString(playerName, scoreLeft + rowScoreIcon.getWidth() + 8, rowY, 20);
            }

            rowY += lineHeight;
            String rowScoreStr = this.midlet.formatNumber(this.scores[1][row][0]);
            if (isRtl) {
                int hIconX = scoreRight - headerScoreIcon.getWidth();
                g.setClip(hIconX, rowY + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
                g.drawImage(headerScoreIcon, hIconX, rowY + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                int scoreTextX = (hIconX - 8) - bodyFont.stringWidth("000000");
                g.drawString(rowScoreStr, scoreTextX, rowY, 20);

                String floorsStr = this.midlet.formatNumber(this.scores[1][row][1]);
                int uIconX = (scoreTextX - 64) - unusedIcon.getWidth();
                g.setClip(uIconX, rowY + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
                g.drawImage(unusedIcon, uIconX, rowY + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                g.drawString(floorsStr, (uIconX - 8) - bodyFont.stringWidth("000"), rowY, 20);
            } else {
                g.setClip(scoreLeft, rowY + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
                g.drawImage(headerScoreIcon, scoreLeft, rowY + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                int scoreTextX = (scoreLeft + headerScoreIcon.getWidth() + 8) + bodyFont.stringWidth("000000");
                g.drawString(rowScoreStr, scoreTextX, rowY, 24);

                String floorsStr = this.midlet.formatNumber(this.scores[1][row][1]);
                int uIconX = scoreTextX + 64;
                g.setClip(uIconX, rowY + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
                g.drawImage(unusedIcon, uIconX, rowY + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                g.drawString(floorsStr, (uIconX + unusedIcon.getWidth() + 8) + bodyFont.stringWidth("000"), rowY, 24);
            }

            rowY += lineHeight;
        }

        int arrowY = lineHeight + rowY;
        if ((lineHeight * 3) + arrowY <= GameMIDlet.screenHeight - 8) {
            endVisible = true;
            HighScores.paintArrow(g, arrowY, false);
            return;
        }
        Ui.paintDownArrow(g, (GameMIDlet.screenWidth - 18) / 2, GameMIDlet.screenHeight - 36, false);
    }

    private static void paintArrow(Graphics g, int y, boolean withArrow) {
        if (withArrow) {
            Ui.paintUpArrow(g, (GameMIDlet.screenWidth - 18) >> 1, y, true);
            y = (y + 18) + 8;
        }
        g.setFont(bodyFont);
        String label155 = "- " + Resources.getString(155);
        String label156 = "- " + Resources.getString(156);
        String label157 = "- " + Resources.getString(157);
        if (Storage.getSetting(4) == 1) {
            int icon1X = scoreRight - rowScoreIcon.getWidth();
            g.setClip(icon1X, y + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
            g.drawImage(rowScoreIcon, icon1X, y + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(label155, icon1X - 8, y, 24);

            int y2 = y + lineHeight;
            int icon2X = scoreRight - headerScoreIcon.getWidth();
            g.setClip(icon2X, y2 + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
            g.drawImage(headerScoreIcon, icon2X, y2 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(label156, icon2X - 8, y2, 24);

            int y3 = y2 + lineHeight;
            int icon3X = scoreRight - unusedIcon.getWidth();
            g.setClip(icon3X, y3 + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
            g.drawImage(unusedIcon, icon3X, y3 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(label157, icon3X - 8, y3, 24);
        } else {
            g.setClip(scoreLeft, y + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
            g.drawImage(rowScoreIcon, scoreLeft, y + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(label155, scoreLeft + rowScoreIcon.getWidth() + 8, y, 20);

            int y2 = y + lineHeight;
            g.setClip(scoreLeft, y2 + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
            g.drawImage(headerScoreIcon, scoreLeft, y2 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(label156, scoreLeft + headerScoreIcon.getWidth() + 8, y2, 20);

            int y3 = y2 + lineHeight;
            g.setClip(scoreLeft, y3 + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
            g.drawImage(unusedIcon, scoreLeft, y3 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(label157, scoreLeft + unusedIcon.getWidth() + 8, y3, 20);
        }
    }

    private void insertScore(int[] score, String name) {
        int i;
        int[] copy = new int[score.length];
        for (i = 0; i < score.length; i++) {
            copy[i] = score[i];
        }
        i = 0;
        for (int slot = 2; slot >= 0; slot--) {
            if (score[0] > this.scores[this.pendingMode][slot][0] && i == 0) {
                this.scores[this.pendingMode][slot + 1] = this.scores[this.pendingMode][slot];
                this.names[this.pendingMode][slot + 1] = this.names[this.pendingMode][slot];
                this.pendingRank--;
                if (slot == 0) {
                    this.scores[this.pendingMode][slot] = copy;
                    this.names[this.pendingMode][slot] = name;
                    i = 1;
                }
            }
            if (score[0] <= this.scores[this.pendingMode][slot][0] && i == 0) {
                this.scores[this.pendingMode][slot + 1] = copy;
                this.names[this.pendingMode][slot + 1] = name;
                i = 1;
            }
        }
        save();
    }

    /** Installs the soft-key commands for the current sub-page. */
    private void installSoftkeys() {
        if (this.softkeys != null) {
            for (int idx = 0; idx < this.softkeys.length; idx++) {
                canvas.removeCommand(this.softkeys[idx]);
            }
            this.softkeys = null;
        }
        if (this.requestedState == 2) {
            Ui.openDialog(null, Resources.getString(146), 0, 0, 0, -1, -1, null);
            Storage.setText(0, this.playerName);
            okCommand = new Command(Resources.getString(5), 4, 1);
            this.softkeys = new Command[]{okCommand};
            this.menu.enterSubMenu(3, 0, this.softkeys);
        } else if (this.requestedState == 1) {
            this.returnState = -2;
            Ui.openDialog(null, Resources.getString(151), 0, 0, 0, -1, -1, "");
            Ui.setText("");
            new Command(Resources.getString(143), 4, 1);
            backCommand = new Command(Resources.getString(7), 2, 1);
            this.softkeys = new Command[]{backCommand};
            this.menu.enterSubMenu(1, 1, this.softkeys);
        }
        this.state = this.requestedState;
    }

    private static final Image createGradient(int width, int height) {
        int[] pixels = new int[(width * height)];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int i5 = (col * 2) - width;
                i5 = (i5 * i5) / width;
                i5 = (i5 * i5) / width;
                int i6 = (row * 2) - height;
                pixels[(row * width) + col] = ((((((width - ((i5 * i5) / width)) * 160) * (height - ((i6 * i6) / height))) / (width * height)) & 255) << 24) | 16777215;
            }
        }
        return Image.createRGBImage(pixels, width, height, true);
    }

    private void submitNameAndSave() {
        switch (this.state) {
            case 2:
                this.requestedState = -1;
                this.playerName = Storage.getText(0);
                insertScore(this.pendingScore, this.playerName);
                ((House) this.midlet).clearSavedGame(1);
                return;
            default:
                return;
        }
    }

    private void cancelEntry() {
        try {
            DataInputStream dis = Storage.openRead("HoF");
            this.playerName = dis.readUTF();
            for (int i = 0; i < layout.length; i++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    this.names[i][i2] = dis.readUTF();
                    for (int i3 = 0; i3 < this.scores[i][i2].length; i3++) {
                        this.scores[i][i2][i3] = dis.readInt();
                    }
                }
            }
            dis.close();
            Storage.close();
        } catch (Exception e) {
        }
    }

    public final int submitScore(int mode, int[] score, String name) {
        int newState;
        HighScores self;
        this.pendingRank = 4;
        for (newState = 0; newState < score.length; newState++) {
            this.pendingScore[newState] = score[newState];
        }
        this.pendingMode = mode;
        HighScores self2;
        if (this.pendingScore[0] <= this.scores[mode][2][0]) {
            self2 = this;
            self = self2;
            newState = -1;
        } else if (name == null) {
            newState = 2;
            self = this;
        } else {
            int[] target;
            if (name != "-") {
                target = this.pendingScore;
                self = this;
            } else {
                target = this.pendingScore;
                name = "-";
                self = this;
            }
            self.insertScore(target, name);
            self2 = this;
            self = self2;
            newState = -1;
        }
        self.requestedState = newState;
        return this.pendingRank;
    }

    public final void update(int delta, int elapsedMs) {
        if (this.requestedState != 0) {
            installSoftkeys();
            this.requestedState = 0;
        }
        if (this.submitRequested) {
            submitNameAndSave();
            this.submitRequested = false;
            return;
        }
        this.menu.update(delta, elapsedMs);
    }

    public final void init(GameMIDlet app, MenuController menuCtl, ICanvas canvasArg) {
        canvas = canvasArg;
        this.midlet = app;
        this.menu = menuCtl;
        Display.getDisplay(app);
        layout = this.midlet.getHighScoreLayout();
        this.names = (String[][]) Array.newInstance(String.class, new int[]{layout.length, 4});
        int length = layout[0].length - 1;
        if (length == 0) {
            length = 1;
        }
        this.pendingScore = new int[length];
        this.scores = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{layout.length, 4, length});
        try {
            DataInputStream dis = Storage.openRead("HoF");
            if (dis != null) {
                dis.close();
                Storage.close();
                cancelEntry();
                return;
            }
            Storage.close();
            this.playerName = "-";
            for (int i = 0; i < this.scores.length; i++) {
                for (int i2 = 0; i2 < this.scores[i].length; i2++) {
                    this.names[i][i2] = "-";
                    for (length = 0; length < this.scores[i][i2].length; length++) {
                        this.scores[i][i2][length] = 0;
                    }
                }
            }
            save();
        } catch (Exception e) {
        }
    }

    public final void commandAction(Command command) {
        switch (command.getCommandType()) {
            case 2:
            case 3:
                if (this.state == 2) {
                    this.menu.commandAction(command);
                    return;
                } else {
                    this.requestedState = this.returnState;
                    return;
                }
            case 4:
                this.submitRequested = true;
                return;
            default:
                return;
        }
    }

    public final void paint(Graphics g, boolean fullRedraw) {
        if (this.state == 1) {
            Ui.paint(g);
            if (atTop || firstPaint || endVisible) {
                paintScoreTable(g);
            }
            if (scrolledDown && !endVisible) {
                HighScores.paintArrow(g, 42, true);
            }
        }
    }

    public final void keyPressed(int keyCode, int gameAction) {
        if (keyCode == 56 || gameAction == 6) {
            scrolledDown = true;
            atTop = false;
            firstPaint = false;
        } else if (keyCode == 50 || gameAction == 1) {
            scrolledDown = false;
            atTop = true;
        } else if (keyCode != 53 && gameAction != 8) {
            this.menu.keyPressed(keyCode, gameAction);
        }
    }

    public final void onEnter() {
        if (Storage.getSetting(13) == 1) {
            this.requestedState = 1;
            Storage.setSetting(13, 0);
        }
    }

    public final void onModeChange() {
        MenuController menuCtl;
        int page;
        if (this.state == -2) {
            menuCtl = this.menu;
            page = 2;
        } else if (this.state == -1) {
            menuCtl = this.menu;
            page = 1;
        } else {
            return;
        }
        menuCtl.gotoMenu(page);
    }
}
