

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import jme.Command;
import jme.Display;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class HighScores implements ModalScreen {
    private static Image A;
    private static Image B;
    private static Image C;
    private static Image D;
    private static boolean E = false;
    private static boolean F = false;
    private static boolean G = true;
    private static boolean H = false;
    private static ICanvas n;
    private static int[][] o;
    private static Command p;
    private static Command q;
    private static final int r = Font.getFont(32, 0, 8).stringWidth("3. ");
    private static final int s = (r + 8);
    private static final int t = ((GameMIDlet.screenWidth - 8) - r);
    private static final int u = (t - 8);
    private static final int v = (s + 8);
    private static final int w = Font.getFont(32, 0, 8).getHeight();
    private static final Font x = Font.getFont(32, 0, 8);
    private static final Font y = Font.getFont(32, 1, 8);
    private static Image z;
    private String[][] a = ((String[][]) null);
    private int[][][] b = ((int[][][]) null);
    private String c;
    private int[] d;
    private int e;
    private int f;
    private GameMIDlet g;
    private MenuController h;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private boolean l;
    private Command[] m;

    static {
        Font.getFont(32, 0, 8).stringWidth("999999");
    }

    public HighScores() {
        z = Resources.getImage(6);
        A = Resources.getImage(7);
        B = Resources.getImage(8);
        C = HighScores.c(GameMIDlet.screenWidth, w);
        D = HighScores.c(GameMIDlet.screenWidth, w * 2);
    }

    private void save() {
        try {
            DataOutputStream b = Storage.openWrite("HoF");
            b.writeUTF(this.c);
            for (int i = 0; i < o.length; i++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    b.writeUTF(this.a[i][i2]);
                    for (int writeInt : this.b[i][i2]) {
                        b.writeInt(writeInt);
                    }
                }
            }
            b.close();
            Storage.close();
        } catch (Exception e) {
        }
    }

    private void paintScoreTable(Graphics graphics) {
        int i;
        String stringBuffer;
        Graphics graphics2;
        int i2;
        int i3;
        int i4;
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        graphics.setFont(x);
        StringBuffer stringBuffer2 = new StringBuffer();
        graphics.setColor(0);
        if (Storage.getSetting(4) == 1) {
            graphics.setFont(y);
            graphics.drawString(new StringBuffer().append(Resources.getString(92)).append(":").toString(), t - 8, 42, 24);
            graphics.setFont(x);
            i = w + 42;
            graphics.drawString(new StringBuffer().append(" ").append(Resources.getString(154)).toString(), t - 8, i, 24);
            i += w;
            graphics.drawImage(C, 0, i - 3, 20);
            graphics.setClip((t - 8) - A.getWidth(), i + 2, A.getWidth(), A.getHeight());
            graphics.drawImage(A, (t - 8) - A.getWidth(), i + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            graphics.drawString(this.g.formatNumber(this.b[0][0][0]), (((t - 8) - A.getWidth()) - 8) - x.stringWidth("000000"), i, 20);
            i += (w * 3) / 2;
            graphics.setFont(y);
            stringBuffer = new StringBuffer().append(Resources.getString(91)).append(":").toString();
            graphics2 = graphics;
            i2 = i;
            i3 = t - 8;
            i4 = i;
            i = 24;
        } else {
            graphics.setFont(y);
            graphics.drawString(new StringBuffer().append(Resources.getString(92)).append(":").toString(), s + 8, 42, 20);
            graphics.setFont(x);
            i = w + 42;
            graphics.drawString(new StringBuffer().append(Resources.getString(154)).append(" ").toString(), s + 8, i, 20);
            i += w;
            graphics.drawImage(C, 0, i - 3, 20);
            graphics.setClip(s + 8, i + 2, A.getWidth(), A.getHeight());
            graphics.drawImage(A, s + 8, i + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            graphics.drawString(this.g.formatNumber(this.b[0][0][0]), (((s + 8) + A.getWidth()) + 8) + x.stringWidth("000000"), i, 24);
            i += (w * 3) / 2;
            graphics.setFont(y);
            stringBuffer = new StringBuffer().append(Resources.getString(91)).append(":").toString();
            graphics2 = graphics;
            i2 = i;
            i3 = s + 8;
            i4 = i;
            i = 20;
        }
        graphics2.drawString(stringBuffer, i3, i4, i);
        graphics.setFont(x);
        i3 = i2 + w;
        for (int i5 = 0; i5 < 3; i5++) {
            String stringBuffer3;
            int i6;
            String str;
            Graphics graphics3;
            graphics.drawImage(D, 0, i3 - 3, 20);
            if (Storage.getSetting(4) == 1) {
                stringBuffer3 = new StringBuffer().append(i5 + 1).append(". ").toString();
                i6 = t;
                str = stringBuffer3;
                graphics3 = graphics;
                i4 = i3;
                i = 20;
            } else {
                stringBuffer3 = new StringBuffer().append(i5 + 1).append(". ").toString();
                i6 = s;
                str = stringBuffer3;
                graphics3 = graphics;
                i4 = i3;
                i = 24;
            }
            graphics3.drawString(str, i6, i4, i);
            if (Storage.getSetting(4) == 1) {
                graphics.setClip(u - z.getWidth(), i3 + 2, z.getWidth(), z.getHeight());
                graphics.drawImage(z, u - z.getWidth(), i3 + 2, 20);
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = (u - z.getWidth()) - 8;
                str = this.a[1][i5];
                graphics3 = graphics;
                i4 = i3;
                i = 24;
            } else {
                graphics.setClip(v, i3 + 2, z.getWidth(), z.getHeight());
                graphics.drawImage(z, v, i3 + 2, 20);
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = (v + z.getWidth()) + 8;
                str = this.a[1][i5];
                graphics3 = graphics;
                i4 = i3;
                i = 20;
            }
            graphics3.drawString(str, i6, i4, i);
            i3 += w;
            if (Storage.getSetting(4) == 1) {
                stringBuffer3 = this.g.formatNumber(this.b[1][i5][0]);
                graphics.setClip(u - A.getWidth(), i3 + 2, A.getWidth(), A.getHeight());
                graphics.drawImage(A, u - A.getWidth(), i3 + 2, 20);
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((u - A.getWidth()) - 8) - x.stringWidth("000000");
                str = stringBuffer3;
                graphics3 = graphics;
                i4 = i3;
                i = 20;
            } else {
                stringBuffer3 = this.g.formatNumber(this.b[1][i5][0]);
                graphics.setClip(v, i3 + 2, A.getWidth(), A.getHeight());
                graphics.drawImage(A, v, i3 + 2, 20);
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((v + A.getWidth()) + 8) + x.stringWidth("000000");
                str = stringBuffer3;
                graphics3 = graphics;
                i4 = i3;
                i = 24;
            }
            graphics3.drawString(str, i6, i4, i);
            if (Storage.getSetting(4) == 1) {
                stringBuffer3 = this.g.formatNumber(this.b[1][i5][1]);
                graphics.setClip(((((u - A.getWidth()) - 8) - x.stringWidth("000000")) - 64) - B.getWidth(), i3 + 2, B.getWidth(), B.getHeight());
                graphics.drawImage(B, ((((u - A.getWidth()) - 8) - x.stringWidth("000000")) - 64) - B.getWidth(), i3 + 2, 20);
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((((((u - A.getWidth()) - 8) - x.stringWidth("000000")) - 64) - B.getWidth()) - 8) - x.stringWidth("000");
                str = stringBuffer3;
                graphics3 = graphics;
                i4 = i3;
                i = 20;
            } else {
                stringBuffer3 = this.g.formatNumber(this.b[1][i5][1]);
                graphics.setClip((((v + A.getWidth()) + 8) + x.stringWidth("000000")) + 64, i3 + 2, B.getWidth(), B.getHeight());
                graphics.drawImage(B, (((v + A.getWidth()) + 8) + x.stringWidth("000000")) + 64, i3 + 2, 20);
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                i6 = ((((((v + A.getWidth()) + 8) + x.stringWidth("000000")) + 64) + B.getWidth()) + 8) + x.stringWidth("000");
                str = stringBuffer3;
                graphics3 = graphics;
                i4 = i3;
                i = 24;
            }
            graphics3.drawString(str, i6, i4, i);
            i3 += w;
        }
        i = w + i3;
        if ((w * 3) + i <= GameMIDlet.screenHeight - 8) {
            H = true;
            HighScores.paintArrow(graphics, i, false);
            return;
        }
        Ui.paintProgressBar(graphics, (GameMIDlet.screenWidth - 18) / 2, GameMIDlet.screenHeight - 36, false);
    }

    private static void paintArrow(Graphics graphics, int i, boolean withArrow) {
        int i2;
        String stringBuffer;
        int i3;
        int i4 = 24;
        if (withArrow) {
            Ui.paintArrowIndicator(graphics, (GameMIDlet.screenWidth - 18) >> 1, i, true);
            i = (i + 18) + 8;
        }
        graphics.setFont(x);
        if (Storage.getSetting(4) == 1) {
            graphics.setClip(u - z.getWidth(), i + 2, z.getWidth(), z.getHeight());
            graphics.drawImage(z, u - z.getWidth(), i + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            graphics.drawString(new StringBuffer().append("- ").append(Resources.getString(155)).toString(), (u - z.getWidth()) - 8, i, 24);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i2 = w + i;
            graphics.setClip(u - A.getWidth(), i2 + 2, A.getWidth(), A.getHeight());
            graphics.drawImage(A, u - A.getWidth(), i2 + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            graphics.drawString(new StringBuffer().append("- ").append(Resources.getString(156)).toString(), (u - A.getWidth()) - 8, i2, 24);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i2 += w;
            graphics.setClip(u - B.getWidth(), i2 + 2, B.getWidth(), B.getHeight());
            graphics.drawImage(B, u - B.getWidth(), i2 + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            stringBuffer = new StringBuffer().append("- ").append(Resources.getString(157)).toString();
            int i5 = i2;
            i2 = (u - B.getWidth()) - 8;
            i3 = i5;
        } else {
            graphics.setClip(v, i + 2, z.getWidth(), z.getHeight());
            graphics.drawImage(z, v, i + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            graphics.drawString(new StringBuffer().append("- ").append(Resources.getString(155)).toString(), (v + z.getWidth()) + 8, i, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i4 = w + i;
            graphics.setClip(v, i4 + 2, A.getWidth(), A.getHeight());
            graphics.drawImage(A, v, i4 + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            graphics.drawString(new StringBuffer().append("- ").append(Resources.getString(156)).toString(), (v + A.getWidth()) + 8, i4, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            i4 += w;
            graphics.setClip(v, i4 + 2, B.getWidth(), B.getHeight());
            graphics.drawImage(B, v, i4 + 2, 20);
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            stringBuffer = new StringBuffer().append("- ").append(Resources.getString(157)).toString();
            i2 = (v + B.getWidth()) + 8;
            i3 = i4;
            i4 = 20;
        }
        graphics.drawString(stringBuffer, i2, i3, i4);
    }

    private void insertScore(int[] iArr, String str) {
        int i;
        int[] iArr2 = new int[iArr.length];
        for (i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[i];
        }
        i = 0;
        for (int i2 = 2; i2 >= 0; i2--) {
            if (iArr[0] > this.b[this.e][i2][0] && i == 0) {
                this.b[this.e][i2 + 1] = this.b[this.e][i2];
                this.a[this.e][i2 + 1] = this.a[this.e][i2];
                this.f--;
                if (i2 == 0) {
                    this.b[this.e][i2] = iArr2;
                    this.a[this.e][i2] = str;
                    i = 1;
                }
            }
            if (iArr[0] <= this.b[this.e][i2][0] && i == 0) {
                this.b[this.e][i2 + 1] = iArr2;
                this.a[this.e][i2 + 1] = str;
                i = 1;
            }
        }
        save();
    }

    /** Installs the soft-key commands for the current sub-page (private void b()). Rewritten from bytecode. */
    private void installSoftkeys() {
        if (this.m != null) {
            for (int idx = 0; idx < this.m.length; idx++) {
                n.removeCommand(this.m[idx]);
            }
            this.m = null;
        }
        if (this.i == 2) {
            Ui.openDialog(null, Resources.getString(146), 0, 0, 0, -1, -1, null);
            Storage.setText(0, this.c);
            p = new Command(Resources.getString(5), 4, 1);
            this.m = new Command[]{p};
            this.h.a(3, 0, this.m);
        } else if (this.i == 1) {
            this.k = -2;
            Ui.openDialog(null, Resources.getString(151), 0, 0, 0, -1, -1, "");
            Ui.setTitle("");
            new Command(Resources.getString(143), 4, 1);
            q = new Command(Resources.getString(7), 2, 1);
            this.m = new Command[]{q};
            this.h.a(1, 1, this.m);
        }
        this.j = this.i;
    }

    private static final Image c(int i, int i2) {
        int[] iArr = new int[(i * i2)];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = (i4 * 2) - i;
                i5 = (i5 * i5) / i;
                i5 = (i5 * i5) / i;
                int i6 = (i3 * 2) - i2;
                iArr[(i3 * i) + i4] = ((((((i - ((i5 * i5) / i)) * 160) * (i2 - ((i6 * i6) / i2))) / (i * i2)) & 255) << 24) | 16777215;
            }
        }
        return Image.createRGBImage(iArr, i, i2, true);
    }

    private void submitNameAndSave() {
        switch (this.j) {
            case 2:
                this.i = -1;
                this.c = Storage.getText(0);
                insertScore(this.d, this.c);
                ((House) this.g).b(1);
                return;
            default:
                return;
        }
    }

    private void cancelEntry() {
        try {
            DataInputStream a = Storage.openRead("HoF");
            this.c = a.readUTF();
            for (int i = 0; i < o.length; i++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    this.a[i][i2] = a.readUTF();
                    for (int i3 = 0; i3 < this.b[i][i2].length; i3++) {
                        this.b[i][i2][i3] = a.readInt();
                    }
                }
            }
            a.close();
            Storage.close();
        } catch (Exception e) {
        }
    }

    public final int submitScore(int i, int[] iArr, String str) {
        int i2;
        HighScores hVar;
        this.f = 4;
        for (i2 = 0; i2 < iArr.length; i2++) {
            this.d[i2] = iArr[i2];
        }
        this.e = i;
        HighScores hVar2;
        if (this.d[0] <= this.b[i][2][0]) {
            hVar2 = this;
            hVar = hVar2;
            i2 = -1;
        } else if (str == null) {
            i2 = 2;
            hVar = this;
        } else {
            int[] iArr2;
            if (str != "-") {
                iArr2 = this.d;
                hVar = this;
            } else {
                iArr2 = this.d;
                str = "-";
                hVar = this;
            }
            hVar.insertScore(iArr2, str);
            hVar2 = this;
            hVar = hVar2;
            i2 = -1;
        }
        hVar.i = i2;
        return this.f;
    }

    public final void update(int i, int i2) {
        if (this.i != 0) {
            installSoftkeys();
            this.i = 0;
        }
        if (this.l) {
            submitNameAndSave();
            this.l = false;
            return;
        }
        this.h.update(i, i2);
    }

    public final void init(GameMIDlet gameMIDlet, MenuController jVar, ICanvas mVar) {
        n = mVar;
        this.g = gameMIDlet;
        this.h = jVar;
        Display.getDisplay(gameMIDlet);
        o = this.g.getHighScoreLayout();
        this.a = (String[][]) Array.newInstance(String.class, new int[]{o.length, 4});
        int length = o[0].length - 1;
        if (length == 0) {
            length = 1;
        }
        this.d = new int[length];
        this.b = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{o.length, 4, length});
        try {
            DataInputStream a = Storage.openRead("HoF");
            if (a != null) {
                a.close();
                Storage.close();
                cancelEntry();
                return;
            }
            Storage.close();
            this.c = "-";
            for (int i = 0; i < this.b.length; i++) {
                for (int i2 = 0; i2 < this.b[i].length; i2++) {
                    this.a[i][i2] = "-";
                    for (length = 0; length < this.b[i][i2].length; length++) {
                        this.b[i][i2][length] = 0;
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
                if (this.j == 2) {
                    this.h.commandAction(command);
                    return;
                } else {
                    this.i = this.k;
                    return;
                }
            case 4:
                this.l = true;
                return;
            default:
                return;
        }
    }

    public final void paint(Graphics graphics, boolean z) {
        if (this.j == 1) {
            Ui.paint(graphics);
            if (F || G || H) {
                paintScoreTable(graphics);
            }
            if (E && !H) {
                HighScores.paintArrow(graphics, 42, true);
            }
        }
    }

    public final void keyPressed(int i, int i2) {
        if (i == 56 || i2 == 6) {
            E = true;
            F = false;
            G = false;
        } else if (i == 50 || i2 == 1) {
            E = false;
            F = true;
        } else if (i != 53 && i2 != 8) {
            this.h.keyPressed(i, i2);
        }
    }

    public final void onEnter() {
        if (Storage.getSetting(13) == 1) {
            this.i = 1;
            Storage.setSetting(13, 0);
        }
    }

    public final void onModeChange() {
        MenuController jVar;
        int i;
        if (this.j == -2) {
            jVar = this.h;
            i = 2;
        } else if (this.j == -1) {
            jVar = this.h;
            i = 1;
        } else {
            return;
        }
        jVar.a(i);
    }
}
