

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
        int i;
        String stringBuffer;
        Graphics graphics2;
        int i2;
        int i3;
        int i4;
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        g.setFont(bodyFont);
        StringBuffer stringBuffer2 = new StringBuffer();
        g.setColor(0);
        if (Storage.getSetting(4) == 1) {
            g.setFont(boldFont);
            g.drawString(new StringBuffer().append(Resources.getString(92)).append(":").toString(), rightEdge - 8, 42, 24);
            g.setFont(bodyFont);
            i = lineHeight + 42;
            g.drawString(new StringBuffer().append(" ").append(Resources.getString(154)).toString(), rightEdge - 8, i, 24);
            i += lineHeight;
            g.drawImage(gradientHeader, 0, i - 3, 20);
            g.setClip((rightEdge - 8) - headerScoreIcon.getWidth(), i + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
            g.drawImage(headerScoreIcon, (rightEdge - 8) - headerScoreIcon.getWidth(), i + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(this.midlet.formatNumber(this.scores[0][0][0]), (((rightEdge - 8) - headerScoreIcon.getWidth()) - 8) - bodyFont.stringWidth("000000"), i, 20);
            i += (lineHeight * 3) / 2;
            g.setFont(boldFont);
            stringBuffer = new StringBuffer().append(Resources.getString(91)).append(":").toString();
            graphics2 = g;
            i2 = i;
            i3 = rightEdge - 8;
            i4 = i;
            i = 24;
        } else {
            g.setFont(boldFont);
            g.drawString(new StringBuffer().append(Resources.getString(92)).append(":").toString(), nameX + 8, 42, 20);
            g.setFont(bodyFont);
            i = lineHeight + 42;
            g.drawString(new StringBuffer().append(Resources.getString(154)).append(" ").toString(), nameX + 8, i, 20);
            i += lineHeight;
            g.drawImage(gradientHeader, 0, i - 3, 20);
            g.setClip(nameX + 8, i + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
            g.drawImage(headerScoreIcon, nameX + 8, i + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(this.midlet.formatNumber(this.scores[0][0][0]), (((nameX + 8) + headerScoreIcon.getWidth()) + 8) + bodyFont.stringWidth("000000"), i, 24);
            i += (lineHeight * 3) / 2;
            g.setFont(boldFont);
            stringBuffer = new StringBuffer().append(Resources.getString(91)).append(":").toString();
            graphics2 = g;
            i2 = i;
            i3 = nameX + 8;
            i4 = i;
            i = 20;
        }
        graphics2.drawString(stringBuffer, i3, i4, i);
        g.setFont(bodyFont);
        i3 = i2 + lineHeight;
        for (int i5 = 0; i5 < 3; i5++) {
            String stringBuffer3;
            int i6;
            String str;
            Graphics graphics3;
            g.drawImage(gradientRow, 0, i3 - 3, 20);
            if (Storage.getSetting(4) == 1) {
                stringBuffer3 = new StringBuffer().append(i5 + 1).append(". ").toString();
                i6 = rightEdge;
                str = stringBuffer3;
                graphics3 = g;
                i4 = i3;
                i = 20;
            } else {
                stringBuffer3 = new StringBuffer().append(i5 + 1).append(". ").toString();
                i6 = nameX;
                str = stringBuffer3;
                graphics3 = g;
                i4 = i3;
                i = 24;
            }
            graphics3.drawString(str, i6, i4, i);
            if (Storage.getSetting(4) == 1) {
                g.setClip(scoreRight - rowScoreIcon.getWidth(), i3 + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
                g.drawImage(rowScoreIcon, scoreRight - rowScoreIcon.getWidth(), i3 + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = (scoreRight - rowScoreIcon.getWidth()) - 8;
                str = this.names[1][i5];
                graphics3 = g;
                i4 = i3;
                i = 24;
            } else {
                g.setClip(scoreLeft, i3 + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
                g.drawImage(rowScoreIcon, scoreLeft, i3 + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = (scoreLeft + rowScoreIcon.getWidth()) + 8;
                str = this.names[1][i5];
                graphics3 = g;
                i4 = i3;
                i = 20;
            }
            graphics3.drawString(str, i6, i4, i);
            i3 += lineHeight;
            if (Storage.getSetting(4) == 1) {
                stringBuffer3 = this.midlet.formatNumber(this.scores[1][i5][0]);
                g.setClip(scoreRight - headerScoreIcon.getWidth(), i3 + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
                g.drawImage(headerScoreIcon, scoreRight - headerScoreIcon.getWidth(), i3 + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((scoreRight - headerScoreIcon.getWidth()) - 8) - bodyFont.stringWidth("000000");
                str = stringBuffer3;
                graphics3 = g;
                i4 = i3;
                i = 20;
            } else {
                stringBuffer3 = this.midlet.formatNumber(this.scores[1][i5][0]);
                g.setClip(scoreLeft, i3 + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
                g.drawImage(headerScoreIcon, scoreLeft, i3 + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((scoreLeft + headerScoreIcon.getWidth()) + 8) + bodyFont.stringWidth("000000");
                str = stringBuffer3;
                graphics3 = g;
                i4 = i3;
                i = 24;
            }
            graphics3.drawString(str, i6, i4, i);
            if (Storage.getSetting(4) == 1) {
                stringBuffer3 = this.midlet.formatNumber(this.scores[1][i5][1]);
                g.setClip(((((scoreRight - headerScoreIcon.getWidth()) - 8) - bodyFont.stringWidth("000000")) - 64) - unusedIcon.getWidth(), i3 + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
                g.drawImage(unusedIcon, ((((scoreRight - headerScoreIcon.getWidth()) - 8) - bodyFont.stringWidth("000000")) - 64) - unusedIcon.getWidth(), i3 + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((((((scoreRight - headerScoreIcon.getWidth()) - 8) - bodyFont.stringWidth("000000")) - 64) - unusedIcon.getWidth()) - 8) - bodyFont.stringWidth("000");
                str = stringBuffer3;
                graphics3 = g;
                i4 = i3;
                i = 20;
            } else {
                stringBuffer3 = this.midlet.formatNumber(this.scores[1][i5][1]);
                g.setClip((((scoreLeft + headerScoreIcon.getWidth()) + 8) + bodyFont.stringWidth("000000")) + 64, i3 + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
                g.drawImage(unusedIcon, (((scoreLeft + headerScoreIcon.getWidth()) + 8) + bodyFont.stringWidth("000000")) + 64, i3 + 2, 20);
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((((((scoreLeft + headerScoreIcon.getWidth()) + 8) + bodyFont.stringWidth("000000")) + 64) + unusedIcon.getWidth()) + 8) + bodyFont.stringWidth("000");
                str = stringBuffer3;
                graphics3 = g;
                i4 = i3;
                i = 24;
            }
            graphics3.drawString(str, i6, i4, i);
            i3 += lineHeight;
        }
        i = lineHeight + i3;
        if ((lineHeight * 3) + i <= GameMIDlet.screenHeight - 8) {
            endVisible = true;
            HighScores.paintArrow(g, i, false);
            return;
        }
        Ui.paintDownArrow(g, (GameMIDlet.screenWidth - 18) / 2, GameMIDlet.screenHeight - 36, false);
    }

    private static void paintArrow(Graphics g, int y, boolean withArrow) {
        int i2;
        String stringBuffer;
        int i3;
        int i4 = 24;
        if (withArrow) {
            Ui.paintUpArrow(g, (GameMIDlet.screenWidth - 18) >> 1, y, true);
            y = (y + 18) + 8;
        }
        g.setFont(bodyFont);
        if (Storage.getSetting(4) == 1) {
            g.setClip(scoreRight - rowScoreIcon.getWidth(), y + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
            g.drawImage(rowScoreIcon, scoreRight - rowScoreIcon.getWidth(), y + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(new StringBuffer().append("- ").append(Resources.getString(155)).toString(), (scoreRight - rowScoreIcon.getWidth()) - 8, y, 24);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i2 = lineHeight + y;
            g.setClip(scoreRight - headerScoreIcon.getWidth(), i2 + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
            g.drawImage(headerScoreIcon, scoreRight - headerScoreIcon.getWidth(), i2 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(new StringBuffer().append("- ").append(Resources.getString(156)).toString(), (scoreRight - headerScoreIcon.getWidth()) - 8, i2, 24);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i2 += lineHeight;
            g.setClip(scoreRight - unusedIcon.getWidth(), i2 + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
            g.drawImage(unusedIcon, scoreRight - unusedIcon.getWidth(), i2 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            stringBuffer = new StringBuffer().append("- ").append(Resources.getString(157)).toString();
            int i5 = i2;
            i2 = (scoreRight - unusedIcon.getWidth()) - 8;
            i3 = i5;
        } else {
            g.setClip(scoreLeft, y + 2, rowScoreIcon.getWidth(), rowScoreIcon.getHeight());
            g.drawImage(rowScoreIcon, scoreLeft, y + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(new StringBuffer().append("- ").append(Resources.getString(155)).toString(), (scoreLeft + rowScoreIcon.getWidth()) + 8, y, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i4 = lineHeight + y;
            g.setClip(scoreLeft, i4 + 2, headerScoreIcon.getWidth(), headerScoreIcon.getHeight());
            g.drawImage(headerScoreIcon, scoreLeft, i4 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            g.drawString(new StringBuffer().append("- ").append(Resources.getString(156)).toString(), (scoreLeft + headerScoreIcon.getWidth()) + 8, i4, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i4 += lineHeight;
            g.setClip(scoreLeft, i4 + 2, unusedIcon.getWidth(), unusedIcon.getHeight());
            g.drawImage(unusedIcon, scoreLeft, i4 + 2, 20);
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            stringBuffer = new StringBuffer().append("- ").append(Resources.getString(157)).toString();
            i2 = (scoreLeft + unusedIcon.getWidth()) + 8;
            i3 = i4;
            i4 = 20;
        }
        g.drawString(stringBuffer, i2, i3, i4);
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
