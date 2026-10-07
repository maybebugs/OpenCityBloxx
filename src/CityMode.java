

import java.io.DataInputStream;
import java.io.DataOutputStream;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class CityMode {
    private static final int[] A = new int[]{175, 229, 255, -87, -89, 0};
    private static final int[] B = new int[]{-8556946, -1318431, -3351282, -5399421, -11456464, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -11456464, -1318431, -8556946, -15463412};
    private static final int[] C = new int[]{-12357161, -2024952, -15618292, -2449664, -16044658, -8122095, -16491520, -11520000};
    private static final int[] D = new int[]{-12355561, -11506121, -10983088, -10329750};
    private static final int[] E = new int[]{-8864728, -8409274, -8084131, -7693196};
    private static final int[] F = new int[]{-16755552, -6290944, -16738304, -7120384, -12793601, -38586, -13107401, -983296};
    private static boolean G;
    private static boolean H;
    private static boolean I;
    private static boolean J;
    private static boolean K;
    private static Image L;
    private static Image M;
    private static Image N;
    private static Image O;
    private static Image[] P;
    private static Image Q;
    private static Image R;
    private static Image S;
    private static Image T;
    private static Image U;
    private static final int[] levelThresholds = new int[]{0, 75, 150, 250, 400, 600, 800, 1000, 1400, 1800, 2200, 3000, 4000, 5000, 6500, 8000, 9500, 11500, 14000, 17000, 19000};
    private static int W;
    private static int X;
    private static int Y;
    private static int Z;
    public static int[] grid = null;
    private static int aA;
    private static int[] aB;
    private static int aC;
    private static int aD;
    private static int aE;
    private static int aF;
    private static int aG;
    private static boolean aH = true;
    private static int aa;
    private static int ab;
    private static Font ac;
    private static String[][] ad;
    private static String[] ae;
    private static String[] af;
    private static String[] ag;
    private static String[] ah;
    private static final int[] ai = new int[]{0, 3, 6, 10};
    private static final int[] aj = new int[]{8, 12, 14, 16};
    private static int ak;
    private static final int[] al = new int[]{0, 1, 4, 7, 9, 11, 13, 15, 18, 20};
    private static String[] am;
    private static String[] an;
    private static String[] ao;
    private static int ap;
    private static Image aq;
    private static int ar;
    private static boolean as;
    private static boolean at = false;
    private static final char[] au = "626428826".toCharArray();
    private static int av;
    private static int aw;
    private static int ax;
    private static boolean ay;
    private static int az;
    public static int[] b;
    public static Image[] c;
    public static boolean d = false;
    public static int e;
    public static int f;
    public static int population;
    public static int level;
    public static int cursorCol;
    public static int cursorRow;
    public static int k;
    public static boolean l;
    public static int m;
    public static int n;
    static boolean o;
    public static int p = -1;
    static boolean q;
    public static boolean[] r;
    public static int s;
    public static int t;
    public static int u;
    public static boolean v;
    public static String[] w;
    public static int x;
    public static int y;
    static int z = -1;

    static {
        int[] iArr = new int[]{2, 8, 4, 6, 5};
    }

    public static void init() {
        ac = House.getGameFont();
        aC = 51;
        aC -= 2;
        aC /= ac.getHeight();
        grid = new int[75];
        b = new int[25];
        r = new boolean[46];
        aB = new int[16];
        ao = new String[]{Resources.getString(83), Resources.getString(84), Resources.getString(85), Resources.getString(86), Resources.getString(87), Resources.getString(88), Resources.getString(89), Resources.getString(90)};
        ad = new String[12][];
        int i = GameMIDlet.screenWidth - 20;
        String[] strArr = new String[]{Resources.getString(99), Resources.getString(100), Resources.getString(101), Resources.getString(102)};
        for (int i2 = 0; i2 < 4; i2++) {
            ad[i2] = House.wrapText(strArr[i2], ac, i);
            ad[i2 + 4] = House.wrapText(Resources.getString(62, new String[]{ao[i2], new StringBuffer().append("").append(House.l[i2]).toString()}), ac, i);
            ad[i2 + 8] = House.wrapText(Resources.getString(63, new String[]{new StringBuffer().append("").append(levelThresholds[ai[i2]]).toString()}), ac, i);
        }
        ae = House.wrapText(Resources.getString(65), ac, i);
        af = House.wrapText(Resources.getString(66), ac, i);
        ag = House.wrapText(Resources.getString(64), ac, i);
    }

    private static void showTutorialHint(int i) {
        boolean[] zArr;
        int i2;
        switch (i) {
            case 0:
                House.showPrompt(Resources.getString(36), null, null);
                zArr = r;
                i2 = 0;
                break;
            case 3:
                House.showPrompt(Resources.getString(39), null, null);
                zArr = r;
                i2 = 3;
                break;
            case 38:
                if (House.d == 2) {
                    House.showPrompt(Resources.getString(46), null, null);
                    zArr = r;
                    i2 = 38;
                    break;
                }
                return;
            default:
                return;
        }
        zArr[i2] = true;
        o = true;
    }

    public static void handleKeyPressed(int i, int i2) {
        int c = mapGameAction(i, i2);
        if (c == 0) {
            G = true;
        } else if (c == 1) {
            H = true;
        } else if (c == 2) {
            I = true;
        } else if (c == 3) {
            J = true;
        } else if (c == 5) {
            K = true;
        }
        c = i - 48;
        if (c >= 0 && c <= 9) {
            char[] cArr = au;
            int i3 = av;
            av = i3 + 1;
            if (c == cArr[i3] - 48) {
                if (av == au.length) {
                    at = true;
                    av = 0;
                    updateUnlocks();
                    return;
                }
                return;
            }
        }
        av = 0;
    }

    public static void startPlacement(int i, int i2, int i3) {
        d = false;
        f = 1;
        W = i;
        X = i2;
        Y = i3;
        cursorCol = 2;
        cursorRow = 2;
        aF = 700;
        aG = 0;
        aH = true;
        if (at && i2 == 0) {
            randomizeBuildingStats();
        }
        if (!r[3]) {
            House.showPrompt(new StringBuffer().append(Resources.getString(39)).append('\n').append(Resources.getString(158)).toString(), null, null);
            r[3] = true;
            o = true;
        }
        if (!r[38] && House.d == 2) {
            House.showPrompt(Resources.getString(46), null, null);
            r[38] = true;
            o = true;
        }
    }

    public static void paint(Graphics graphics) {
        if (L == null) {
            return;
        }
        int i;
        int i2;
        int i3 = 0;
        int i4 = GameMIDlet.screenHeight - 55;
        for (i = 58; i <= i4; i++) {
            graphics.setColor(A[0] + ((A[3] * (i - 58)) / (i4 - 58)), A[1] + ((A[4] * (i - 58)) / (i4 - 58)), A[2] + ((A[5] * (i - 58)) / (i4 - 58)));
            graphics.drawLine(0, i, GameMIDlet.screenWidth, i);
        }
        i = 0;
        for (i4 = 0; i4 < 18; i4++) {
            graphics.setColor(B[i4]);
            if (i4 == 2) {
                graphics.fillRect(3, i, 56, 40);
            } else if (i4 == 3) {
                graphics.fillRect(59, i, (GameMIDlet.screenWidth - 6) - 56, 40);
                i += 40;
            } else {
                graphics.drawLine(3, i, GameMIDlet.screenWidth - 3, i);
                i++;
            }
        }
        i4 = GameMIDlet.screenWidth - 6;
        if (level < levelThresholds.length - 1) {
            i = level;
            while (i < levelThresholds.length - 2 && population + u > levelThresholds[i + 1]) {
                i++;
            }
            i2 = levelThresholds[i];
            i = (i4 * ((population + u) - i2)) / (levelThresholds[i + 1] - i2);
        } else {
            i = i4;
        }
        graphics.setColor(-89856);
        graphics.fillRect(3, 43, i, 6);
        graphics.setColor(-130816);
        graphics.fillRect(3, 49, i, 3);
        graphics.setClip(0, 0, 3, L.getHeight());
        graphics.drawImage(L, 0, 0, 20);
        graphics.setClip(GameMIDlet.screenWidth - 3, 0, 3, L.getHeight());
        graphics.drawImage(L, (GameMIDlet.screenWidth - 3) - 3, 0, 20);
        i4 = 0;
        i = -15922164;
        if (level == 0) {
            i4 = -42;
            i = -4602071;
        }
        graphics.setClip(8, 12, 14, M.getHeight());
        graphics.drawImage(M, i4 + 8, 12, 20);
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        graphics.setColor(-5858534);
        graphics.drawLine(24, 16, 53, 16);
        graphics.setColor(i);
        graphics.fillRect(24, 17, 30, 10);
        graphics.setColor(-1971082);
        graphics.drawLine(24, 27, 53, 27);
        if (level > 0) {
            drawBitmapNumber(graphics, S, new StringBuffer().append(level).append("<20").toString(), 54, 18, 7, 6, 1, 0);
        }
        graphics.setClip(76, 12, 14, M.getHeight());
        graphics.drawImage(M, 62, 12, 20);
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        if (s >= 0) {
            drawBitmapNumber(graphics, aq, ":", 96, 16, 7, 0, 0, 0);
        }
        i4 = 0;
        while (i4 < 6) {
            i = i4 == 5 ? 3 : 5 - i4 <= t ? ap : 0;
            graphics.setClip((i4 * 14) + 100, 10, 14, 25);
            graphics.drawImage(N, ((i4 - i) * 14) + 100, 10, 20);
            i4++;
        }
        Image image = T;
        if (t == 0 && s >= 0 && s % 400 < 200) {
            image = aq;
        }
        drawBitmapNumber(graphics, image, new StringBuffer().append("").append(population).toString(), 167, 16, 7, 14, 5, t);
        int i5 = cursorCol >= 0 ? grid[(((cursorRow * 5) + cursorCol) * 3) + 0] : 0;
        if (f == 0 || y >= 0 || l) {
            i = -3686478;
            i4 = -5399421;
            i2 = -56;
        } else {
            i = -1907998;
            i4 = -15922164;
            i2 = -28;
        }
        int i6 = GameMIDlet.screenWidth - 60;
        graphics.setClip(i6, 12, 14, M.getHeight());
        graphics.drawImage(M, i2 + i6, 12, 20);
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        int i7 = i6 + 20;
        graphics.setColor(i);
        graphics.fillRect(i7, 8, 30, 12);
        graphics.setColor(i4);
        graphics.fillRect(i7 + 1, 9, 28, 10);
        if (i5 == 0) {
            i = -3686478;
            i4 = -5399421;
        }
        graphics.setColor(i);
        graphics.fillRect(i7, 21, 30, 12);
        graphics.setColor(i4);
        graphics.fillRect(i7 + 1, 22, 28, 10);
        if (f == 1 && y < 0 && !l) {
            graphics.setColor(C[(W + 4) - 1]);
            graphics.fillRect(i7 + 1, 9, 7, 10);
            graphics.setColor(C[W - 1]);
            graphics.fillRect(i7 + 1, 9, 6, 9);
            drawBitmapNumber(graphics, S, new StringBuffer().append("").append(X).toString(), i7 + 26, 9, 7, 6, 1, 0);
            if (i5 > 0) {
                graphics.setColor(C[(i5 + 4) - 1]);
                graphics.fillRect(i7 + 1, 22, 7, 10);
                graphics.setColor(C[i5 - 1]);
                graphics.fillRect(i7 + 1, 22, 6, 9);
                drawBitmapNumber(graphics, S, new StringBuffer().append("").append(grid[(((cursorRow * 5) + cursorCol) * 3) + 1]).toString(), i7 + 26, 22, 7, 6, 1, 0);
            }
        }
        i = GameMIDlet.screenHeight - 56;
        graphics.setColor(-15463412);
        graphics.drawLine(3, i, GameMIDlet.screenWidth - 3, i);
        graphics.setColor(-8556946);
        graphics.drawLine(3, i + 1, GameMIDlet.screenWidth - 3, i + 1);
        graphics.setColor(-1318431);
        graphics.drawLine(3, i + 2, GameMIDlet.screenWidth - 3, i + 2);
        graphics.setColor(-1);
        graphics.fillRect(3, i + 3, GameMIDlet.screenWidth - 6, 51);
        graphics.setColor(-1318431);
        graphics.drawLine(3, (i + 56) - 2, GameMIDlet.screenWidth - 3, (i + 56) - 2);
        graphics.setColor(-8556946);
        graphics.drawLine(3, (i + 56) - 1, GameMIDlet.screenWidth - 3, (i + 56) - 1);
        graphics.setClip(0, i, 3, L.getHeight());
        graphics.drawImage(L, -6, i, 20);
        graphics.setClip(GameMIDlet.screenWidth - 3, i, 3, L.getHeight());
        graphics.drawImage(L, GameMIDlet.screenWidth - 12, i, 20);
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        i4 = i + 4;
        graphics.setColor(-16777216);
        i = aD;
        while (i < aD + aC && i < w.length) {
            String str;
            Graphics graphics2;
            if (Storage.getSetting(4) == 1) {
                graphics.setFont(ac);
                i2 = 24;
                str = w[i];
                graphics2 = graphics;
                i3 = GameMIDlet.screenWidth - 10;
                i6 = i4;
            } else {
                graphics.setFont(ac);
                i2 = 20;
                str = w[i];
                graphics2 = graphics;
                i3 = 10;
                i6 = i4;
            }
            graphics2.drawString(str, i3, i6, i2);
            i4 += ac.getHeight();
            i++;
        }
        i = 0;
        if (y >= 0) {
            i = y - 750;
            if (i > 0) {
                i = -i;
            }
            i += 750;
        }
        int i8 = (((((GameMIDlet.screenWidth - 28) - 11) - 173) >> 1) + 28) + 11;
        i5 = (((((GameMIDlet.screenHeight - 60) - 56) - 173) + 1) >> 1) + 60;
        if (f == 1) {
            i4 = 0;
            if (y >= 0) {
                i4 = ((-((i8 - 37) + (O.getWidth() << 1))) * i) / 750;
            }
            graphics.drawImage(O, i4 + (i8 - 37), i5 + 140, 20);
        }
        graphics.setColor(-1);
        graphics.fillRect(i8, i5, 173, 173);
        graphics.setColor(-12566464);
        graphics.fillRect(i8 + 1, i5 + 1, 171, 171);
        i4 = e;
        if (f == 1) {
            i4 = W - 1;
        }
        i6 = (F[i4] >> 16) & 255;
        i3 = (F[i4] >> 8) & 255;
        int i9 = F[i4] & 255;
        int i10 = (F[i4 + 4] >> 16) & 255;
        int i11 = (F[i4 + 4] >> 8) & 255;
        i7 = F[i4 + 4] & 255;
        i2 = ax - 400;
        if (i2 > 0) {
            i2 = -i2;
        }
        i2 += 400;
        i6 = (((i6 + (((i10 - i6) * i2) / 400)) << 16) | ((i3 + (((i11 - i3) * i2) / 400)) << 8)) | (((i2 * (i7 - i9)) / 400) + i9);
        for (i10 = 0; i10 < 5; i10++) {
            for (i9 = 0; i9 < 5; i9++) {
                i3 = E[ak];
                i2 = D[ak];
                if (b[(i10 * 5) + i9] >= i4 && !l && y < 0 && e <= ak && (aF < 0 || f != 1)) {
                    i2 = i6;
                    i3 = i6;
                }
                graphics.setColor(D[ak]);
                graphics.fillRect((i8 + 5) + (i9 * 34), (i5 + 5) + (i10 * 34), 27, 27);
                graphics.setColor(i3);
                graphics.fillRect(((i8 + 5) + 1) + (i9 * 34), ((i5 + 5) + 1) + (i10 * 34), 25, 25);
                graphics.setColor(i2);
                graphics.fillRect(((i8 + 5) + 2) + (i9 * 34), ((i5 + 5) + 2) + (i10 * 34), 23, 23);
                graphics.setColor(E[ak]);
                graphics.fillRect(((i8 + 5) + 3) + (i9 * 34), ((i5 + 5) + 3) + (i10 * 34), 21, 21);
            }
        }
        graphics.setStrokeStyle(1);
        graphics.setColor(-7171438);
        for (i4 = 0; i4 < 4; i4++) {
            i2 = (((i8 + 5) + 27) + 3) + (i4 * 34);
            graphics.drawLine(i2, (i5 + 5) + 1, i2, ((i5 + 173) - 5) - 2);
        }
        for (i4 = 0; i4 < 4; i4++) {
            i2 = (((i5 + 5) + 27) + 3) + (i4 * 34);
            graphics.drawLine((i8 + 5) + 1, i2, ((i8 + 173) - 5) - 2, i2);
        }
        graphics.setStrokeStyle(0);
        graphics.setColor(-13108);
        i6 = (aA * 32) >> 8;
        for (i2 = 0; i2 < 4; i2++) {
            i3 = (aB[(i2 * 2) + 0] * 34) + (i5 + 5);
            i9 = (((i5 + 5) + (aB[(i2 * 2) + 1] * 34)) - 7) - 1;
            i10 = (i2 * 34) + (((i8 + 5) + 27) + 3);
            for (i4 = i3 + i6; i4 < i9; i4 += 9) {
                graphics.drawLine(i10 - 1, i4, i10 - 1, i4 + 1);
            }
            for (i4 = i9 - i6; i4 > i3; i4 -= 9) {
                graphics.drawLine(i10 + 1, i4, i10 + 1, i4 + 1);
            }
        }
        for (i2 = 0; i2 < 4; i2++) {
            i3 = (aB[((i2 * 2) + 8) + 0] * 34) + (i8 + 5);
            i9 = (((i8 + 5) + (aB[((i2 * 2) + 8) + 1] * 34)) - 7) - 1;
            i10 = (i2 * 34) + (((i5 + 5) + 27) + 3);
            for (i4 = i3 + i6; i4 < i9; i4 += 9) {
                graphics.drawLine(i4, i10 + 1, i4 + 1, i10 + 1);
            }
            for (i4 = i9 - i6; i4 > i3; i4 -= 9) {
                graphics.drawLine(i4, i10 - 1, i4 + 1, i10 - 1);
            }
        }
        for (i4 = 0; i4 < 25; i4++) {
            i2 = grid[(i4 * 3) + 0];
            i6 = grid[(i4 * 3) + 2];
            if (i2 != 0) {
                i3 = (i8 + 8) + ((i4 % 5) * 34);
                i9 = (((i5 + 29) + ((i4 / 5) * 34)) - P[i2 - 1].getHeight()) + 0;
                graphics.setClip(i3, i9, P[i2 - 1].getWidth() / 4, P[i2 - 1].getHeight());
                graphics.drawImage(P[i2 - 1], i3 - ((P[i2 - 1].getWidth() / 4) * i6), i9, 20);
            }
        }
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        if (f == 1 && y < 0) {
            Image image2 = null;
            if (cursorCol >= 0) {
                i6 = (i8 + 8) + (cursorCol * 34);
                i2 = i5 + 29;
                i4 = cursorRow * 34;
            } else {
                i6 = (i8 - 37) + 5;
                i2 = i5 + 140;
                i4 = 21;
            }
            i4 += i2;
            if (l) {
                i3 = ab - 2250;
                if (i3 > 0 && cursorCol >= 0) {
                    i6 += (i3 * 9) / 750;
                    i4 += (i3 * -9) / 750;
                }
                if (cursorCol >= 0) {
                    if (as) {
                        i2 = 6;
                    } else {
                        i3 = ab - 1500;
                        i2 = 4;
                    }
                    i2 = 5 - ((i2 * i3) / 750);
                    if (i3 >= 0 && i3 < 750) {
                        graphics.setClip(i6 - 10, i4 - 31, Q.getWidth() / 6, Q.getHeight());
                        i10 = i4;
                        i11 = i6;
                        i6 = (i6 - 10) - (i2 * (Q.getWidth() / 6));
                        image2 = Q;
                        i2 = i4;
                        i4 = -31;
                    }
                } else {
                    if (i3 >= 0 && i3 < 750) {
                        i2 = 5 - ((i3 * 6) / 750);
                        graphics.setClip(i6 - 8, i4 - 24, R.getWidth() / 6, R.getHeight());
                        i10 = i4;
                        i11 = i6;
                        i6 = (i6 - 8) - (i2 * (R.getWidth() / 6));
                        image2 = R;
                        i2 = i4;
                        i4 = -24;
                    }
                }
            } else {
                i2 = i6 + 9;
                i6 = i4 - 9;
                if (aF >= 0) {
                    i3 = i2 + ((aF * 20) / 700);
                    i2 = aF * -20;
                    i4 = 700;
                } else {
                    i4 = aG - 1000;
                    if (i4 > 0) {
                        i4 = -i4;
                    }
                    i4 += 1000;
                    i3 = ((i4 * -3) / 1001) + i2;
                    i2 = i4 * 3;
                    i4 = 1001;
                }
                i4 = (i2 / i4) + i6;
                if (k > 0) {
                    i3 += (Z * k) / 150;
                    i4 += (aa * k) / 150;
                }
                if (aF < 0) {
                    graphics.setClip(i3 - 2, i4 - 27, P[4].getWidth() / 5, P[4].getHeight());
                    i6 = (i3 - 2) - ((P[4].getWidth() / 5) << 2);
                    i10 = i4;
                    i11 = i3;
                    image2 = P[4];
                    i2 = i4;
                    i4 = -27;
                } else {
                    i6 = i3;
                }
            }
            if (image2 != null) {
                graphics.drawImage(image2, i6, i4 + i2, 20);
                i4 = i10;
                i6 = i11;
            }
            if (!l || cursorCol >= 0) {
                i4 = (i4 - P[W - 1].getHeight()) + 0;
                graphics.setClip(i6, i4, P[W - 1].getWidth() / 4, P[W - 1].getHeight());
                graphics.drawImage(P[W - 1], i6 - (Y * (P[W - 1].getWidth() / 4)), i4, 20);
            }
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        }
        i4 = i8 - 39;
        i3 = i5 + 0;
        if (f == 0) {
            i = y >= 0 ? ((i * (-(i4 + 56))) / 750) + i4 : i4;
            graphics.setColor(-8556946);
            graphics.fillRect(i, i3, 28, 100);
            graphics.setColor(-1);
            graphics.fillRect(i + 1, i3 + 1, 26, 98);
            for (i6 = 0; i6 < 4; i6++) {
                i4 = i + 3;
                i9 = (i6 * 24) + (i3 + 3);
                graphics.setColor(-5460820);
                if (i6 == e && ar < 250) {
                    graphics.setColor(-89856);
                }
                graphics.fillRect(i4, i9, 22, 22);
                if (i6 <= m) {
                    graphics.setClip(i4 - 5, i9 + 12, 10, M.getHeight());
                    graphics.drawImage(M, (((i4 - 5) - 28) - 14) - 28, i9 + 12, 20);
                }
                if (i6 <= ak) {
                    i2 = i4 + 3;
                    i4 = i9 + 19;
                    if (i6 == e) {
                        if (x < 250) {
                            i2 += 2;
                            i4 -= 2;
                        }
                        graphics.setClip(i2 - 2, i4 - 27, P[4].getWidth() / 5, P[4].getHeight());
                        graphics.drawImage(P[4], (i2 - 2) - ((P[4].getWidth() / 5) * i6), i4 - 27, 20);
                    }
                    i4 = (i4 - P[i6].getHeight()) + 0;
                    graphics.setClip(i2, i4, P[i6].getWidth() / 4, P[i6].getHeight());
                    graphics.drawImage(P[i6], i2 - ((P[i6].getWidth() / 4) * 3), i4, 20);
                }
                graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            }
        }
        if (o) {
            House.paintTutorialModal(graphics);
        }
        q = false;
    }

    private static void drawBitmapNumber(Graphics graphics, Image image, String str, int i, int i2, int i3, int i4, int i5, int i6) {
        if (image == null) {
            return;
        }
        char[] toCharArray = str.toCharArray();
        int height = image.getHeight();
        int length = toCharArray.length;
        if (i5 <= length) {
            i5 = length;
        }
        int length2 = (toCharArray.length - 1) - i6;
        while (i6 < i5) {
            length = length2 >= 0 ? toCharArray[length2] - 48 : 0;
            graphics.setClip((i - (i6 * i4)) - i3, i2, i3, height);
            graphics.drawImage(image, ((i - (i6 * i4)) - i3) - (length * i3), i2, 20);
            i6++;
            length2--;
        }
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
    }

    public static void enterCity() {
        d = false;
        f = 0;
        cursorCol = 2;
        cursorRow = 2;
        population = 0;
        t = 0;
        av = 0;
        m = -1;
        o = false;
        q = true;
        k = 0;
        l = false;
        s = 0;
        u = 0;
        v = false;
        population = 0;
        x = -1;
        y = -1;
        CityMode.loadState();
        updateUnlocks();
        recalculateSynergies();
        e = 0;
        if (!r[0]) {
            House.showPrompt(Resources.getString(36), null, null);
            r[0] = true;
            o = true;
        }
    }

    public static void update(int dt, int time) {
        int i3 = 0;
        int i4;
        String message = null;
        String[] strArr;
        if (k >= 0) {
            k -= dt;
        }
        if (s >= 0) {
            s -= dt;
            if (t > 0) {
                ap = (ap % 2) + 1;
                if (s < 0) {
                    t--;
                    s = t > 0 ? 300 : 1199;
                }
            }
        }
        aA += dt >> 4;
        aA %= 71;
        if (f == 0) {
            ar += dt;
            ar %= 500;
        }
        ax += dt;
        ax %= 800;
        if (!(o || r[1])) {
            House.showPrompt(Resources.getString(37), null, null);
            r[1] = true;
            o = true;
        }
        if (!(o || r[2])) {
            House.showPrompt(Resources.getString(38), null, null);
            r[2] = true;
            o = true;
        }
        if (!(o || r[5] || level != 1)) {
            House.showPrompt(Resources.getString(41, new String[]{new StringBuffer().append("").append(levelThresholds[level]).toString()}), null, null);
            r[5] = true;
            o = true;
        }
        if (!o && !r[level + 4] && level > 1 && level < 20) {
            switch (level) {
                case 5:
                    i3 = 58;
                    break;
                case 17:
                    i3 = 59;
                    break;
                case 19:
                    i3 = 60;
                    break;
                default:
                    i3 = 43;
                    break;
            }
            House.showPrompt(Resources.getString(i3, new String[]{new StringBuffer().append("").append(levelThresholds[level]).toString()}), null, null);
            r[level + 4] = true;
            o = true;
        }
        if (!(o || r[43] || level != 20)) {
            House.showPrompt(Resources.getString(52), null, null);
            r[43] = true;
            o = true;
        }
        if (!(o || r[24] || n != 1)) {
            House.showPrompt(Resources.getString(45), null, c[0]);
            p = 0;
            r[24] = true;
            o = true;
        }
        if (!(o || r[n + 23] || n <= 1)) {
            House.showPrompt(Resources.getString(44, new String[]{am[n - 1], am[n]}), null, c[n - 1]);
            p = n - 1;
            r[n + 23] = true;
            o = true;
        }
        if (!(o || r[33] || level != 2)) {
            House.showPrompt(Resources.getString(42), null, null);
            r[33] = true;
            o = true;
        }
        if (!(o || r[ak + 33] || ak <= 0 || l)) {
            House.showPrompt(an[ak - 1], null, null);
            r[ak + 33] = true;
            o = true;
        }
        if (!(o || r[37] || !r[34])) {
            House.showPrompt(Resources.getString(48), null, null);
            r[37] = true;
            o = true;
        }
        if (!(o || r[m + 39] || m < 0 || l)) {
            House.showPrompt(Resources.getString(51, new String[]{ao[m + 4]}), null, null);
            r[m + 39] = true;
            o = true;
        }
        if (!(o || r[44] || !ay)) {
            House.showPrompt(Resources.getString(53, new String[]{am[n]}), null, null);
            r[44] = true;
            o = true;
        }
        if (!(o || r[45] || !r[44])) {
            House.showPrompt(Resources.getString(54, new String[]{new StringBuffer().append("").append(levelThresholds[20]).toString()}), null, null);
            r[45] = true;
            o = true;
        }
        if (x >= 0) {
            x -= dt;
            if (x < 0) {
                d = true;
                clearInputState();
            }
        }
        if (f == 1) {
            if (aF >= 0) {
                aF -= dt;
            } else {
                aG += dt;
                aG %= 2000;
            }
        }
        if (y >= 0) {
            y -= dt;
            if (f == 1 && y < 750) {
                f = 0;
            }
        }
        if (l && !o) {
            boolean z = aH;
            ab -= dt;
            if (cursorCol >= 0) {
                u = 0;
                if (ab > 0) {
                    u = ((aw - population) * ab) / 3000;
                }
            }
            if (ab < 0 && aH && cursorCol >= 0) {
                grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = W;
                grid[(((cursorRow * 5) + cursorCol) * 3) + 1] = X;
                grid[(((cursorRow * 5) + cursorCol) * 3) + 2] = Y;
                CityMode.saveState();
                updateUnlocks();
                z = false;
            } else if (ab < 0) {
                if (cursorCol >= 0) {
                    v = true;
                    recalculateSynergies();
                }
                if (!r[4]) {
                    House.showPrompt(Resources.getString(40), null, null);
                    r[4] = true;
                    o = true;
                }
                y = 1500;
                l = false;
                z = true;
            }
            aH = z;
        }
        if (o) {
            if (G) {
                House.handleModalAction(-1);
                clearInputState();
            } else if (H) {
                if (House.handleModalAction(1)) {
                    o = false;
                    q = true;
                    CityMode.saveState();
                }
                clearInputState();
            } else if (K) {
                if (House.handleModalAction(0)) {
                    o = false;
                    q = true;
                    CityMode.saveState();
                }
                clearInputState();
            }
        } else if (f == 0) {
            if (x < 0 && y < 0) {
                if (G) {
                    if (e > 0) {
                        e--;
                    }
                } else if (H) {
                    if (e < 3) {
                        e++;
                    }
                } else if (K && ((e <= ak && e <= az) || at)) {
                    x = 500;
                }
            }
        } else if (aF < 0 && !l && y < 0) {
            if (G) {
                if (cursorCol >= 0 && cursorRow > 0) {
                    cursorRow--;
                    Z = 0;
                    aa = 34;
                    k = 150;
                }
            } else if (H) {
                if (cursorRow < 4) {
                    cursorRow++;
                    Z = 0;
                    aa = -34;
                    k = 150;
                }
            } else if (I) {
                if (cursorCol > 0) {
                    cursorCol--;
                    Z = 34;
                    aa = 0;
                    k = 150;
                } else if (cursorCol == 0) {
                    Z = ((cursorCol * 34) + 8) + 32;
                    aa = ((cursorRow * 34) + 29) - 161;
                    cursorCol--;
                    cursorRow = 4;
                    k = 150;
                }
            } else if (J) {
                if (cursorCol < 0) {
                    cursorCol++;
                    Z = -32 - ((cursorCol * 34) + 8);
                    aa = 161 - ((cursorRow * 34) + 29);
                    k = 150;
                } else if (cursorCol < 4) {
                    cursorCol++;
                    Z = -34;
                    aa = 0;
                    k = 150;
                }
            } else if (K && (cursorCol < 0 || b[(cursorRow * 5) + cursorCol] >= W - 1 || at)) {
                l = true;
                ab = 3000;
                if (cursorCol >= 0) {
                    aw = population;
                    population -= grid[(((cursorRow * 5) + cursorCol) * 3) + 1];
                    population += X;
                    u = aw - population;
                    aq = T;
                    i4 = GameMIDlet.screenWidth - 20;
                    if (population > aw) {
                        message = Resources.getString(67, new String[]{new StringBuffer().append("").append(population - aw).toString()});
                    } else if (population < aw) {
                        ah = House.wrapText(Resources.getString(69, new String[]{new StringBuffer().append("").append(aw - population).toString()}), ac, i4);
                        aq = U;
                        t = 0;
                        if (population / 10000 != aw / 10000) {
                            i3 = 5;
                        } else if (population / 1000 != aw / 1000) {
                            i3 = 4;
                        } else if (population / 100 != aw / 100) {
                            i3 = 3;
                        } else if (population / 10 == aw / 10) {
                            i3 = 2;
                        } else {
                            if (population != aw) {
                                i3 = 1;
                            }
                            if (t > 0) {
                                s = 300;
                            }
                            as = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                            grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                        }
                        t = i3;
                        if (t > 0) {
                            s = 300;
                        }
                        if (grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0) {
                        }
                        as = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                        grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                    } else {
                        message = Resources.getString(68);
                    }
                    ah = House.wrapText(message, ac, i4);
                    t = 0;
                    if (population / 10000 != aw / 10000) {
                        i3 = 5;
                    } else if (population / 1000 != aw / 1000) {
                        i3 = 4;
                    } else if (population / 100 != aw / 100) {
                        i3 = 3;
                    } else if (population / 10 == aw / 10) {
                        if (population != aw) {
                            i3 = 1;
                        }
                        if (t > 0) {
                            s = 300;
                        }
                        if (grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0) {
                        }
                        as = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                        grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                    } else {
                        i3 = 2;
                    }
                    t = i3;
                    if (t > 0) {
                        s = 300;
                    }
                    if (grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0) {
                    }
                    as = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                    grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                }
            }
        }
        clearInputState();
        Object obj = w;
        if (ad == null) {
            ad = new String[12][];
        }
        if (f == 0) {
            strArr = e <= m ? ad[e + 4] : e <= ak ? ad[e] : ad[e + 8];
        } else {
            if (ah == null) {
                i4 = GameMIDlet.screenWidth - 20;
                if (population > aw) {
                    message = Resources.getString(67, new String[]{new StringBuffer().append("").append(population - aw).toString()});
                } else if (population < aw) {
                    ah = House.wrapText(Resources.getString(69, new String[]{new StringBuffer().append("").append(aw - population).toString()}), ac, i4);
                    aq = U;
                } else {
                    message = Resources.getString(68);
                }
                ah = House.wrapText(message, ac, i4);
            }
            strArr = cursorCol < 0 ? ag : (l || y >= 0) ? ah : b[(cursorRow * 5) + cursorCol] < W + -1 ? af : ae;
        }
        w = strArr;
        aE -= time;
        if (!w.equals(obj)) {
            aE = 2000;
            aD = 0;
        }
        if (aE < 0 && w.length > aC) {
            aE = 2000;
            aD += aC;
            if (aD >= w.length + aC) {
                aD = 0;
            }
        }
    }

    private static int mapGameAction(int i, int i2) {
        if (i == 50) {
            return 0;
        }
        if (i == 56) {
            return 1;
        }
        if (i == 52) {
            return 2;
        }
        if (i == 54) {
            return 3;
        }
        if (i != 53) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 6) {
                return 1;
            }
            if (i2 == 2) {
                return 2;
            }
            if (i2 == 5) {
                return 3;
            }
            if (i2 != 8) {
                return -1;
            }
        }
        return 5;
    }

    public static volatile boolean assetsLoaded = false;

    public static boolean loadAssets() {
        assetsLoaded = false;
        House.b.stopAll();
        L = Resources.getImage(21);
        M = Resources.getImage(22);
        N = Resources.getImage(23);
        if (!House.updateLoadingProgress(20)) {
            return false;
        }
        O = Resources.getImage(24);
        P = new Image[5];
        P[0] = Resources.getImage(25);
        if (!House.updateLoadingProgress(25)) {
            return false;
        }
        P[1] = Resources.getImage(26);
        P[2] = Resources.getImage(27);
        P[3] = Resources.getImage(28);
        if (!House.updateLoadingProgress(30)) {
            return false;
        }
        P[4] = Resources.getImage(29);
        Q = Resources.getImage(30);
        if (!House.updateLoadingProgress(40)) {
            return false;
        }
        R = Resources.getImage(31);
        S = Resources.getImage(15);
        T = Resources.getImage(16);
        aq = T;
        if (!House.updateLoadingProgress(50)) {
            return false;
        }
        U = Resources.getImage(17);
        c = new Image[9];
        c[0] = Resources.getImage(71);
        if (!House.updateLoadingProgress(60)) {
            return false;
        }
        c[1] = Resources.getImage(72);
        c[2] = Resources.getImage(73);
        if (!House.updateLoadingProgress(70)) {
            return false;
        }
        c[3] = Resources.getImage(74);
        c[4] = Resources.getImage(75);
        if (!House.updateLoadingProgress(80)) {
            return false;
        }
        c[5] = Resources.getImage(76);
        c[6] = Resources.getImage(77);
        if (!House.updateLoadingProgress(90)) {
            return false;
        }
        c[7] = Resources.getImage(78);
        c[8] = Resources.getImage(79);
        if (!House.updateLoadingProgress(100)) {
            return false;
        }
        am = new String[]{"-", Resources.getString(71), Resources.getString(72), Resources.getString(73), Resources.getString(74), Resources.getString(75), Resources.getString(76), Resources.getString(77), Resources.getString(78), Resources.getString(79)};
        an = new String[]{Resources.getString(47), Resources.getString(49), Resources.getString(50)};
        assetsLoaded = true;
        return true;
    }

    public static int getBuildingTowerType() {
        return m >= e ? e + 5 : e + 1;
    }

    public static void clearInputState() {
        G = false;
        H = false;
        I = false;
        J = false;
        K = false;
    }

    public static boolean isCityModeActive() {
        return d;
    }

    public static void updateUnlocks() {
        int i;
        int i2 = 0;
        for (i = 0; i < levelThresholds.length; i++) {
            if (population + u >= levelThresholds[i]) {
                level = i;
            }
        }
        int i3 = ak;
        for (i = 0; i < ai.length; i++) {
            if (level >= ai[i] || at) {
                ak = i;
            }
            if (level >= aj[i] || at) {
                m = i;
            }
        }
        if (i3 != ak) {
            e = ak;
        }
        while (i2 < al.length) {
            if (level >= al[i2]) {
                n = i2;
            }
            i2++;
        }
    }

    public static void recalculateSynergies() {
        int i;
        int i2;
        int[] iArr = new int[4];
        boolean[] zArr = new boolean[3];
        az = 0;
        ay = true;
        for (i = 0; i < 5; i++) {
            for (i2 = 0; i2 < 5; i2++) {
                int i3;
                for (i3 = 0; i3 < iArr.length; i3++) {
                    iArr[i3] = 0;
                }
                for (i3 = 0; i3 < zArr.length; i3++) {
                    zArr[i3] = false;
                }
                if (i2 - 1 >= 0) {
                    iArr[0] = grid[(((i * 5) + (i2 - 1)) * 3) + 0];
                }
                if (i2 + 1 < 5) {
                    iArr[1] = grid[(((i * 5) + (i2 + 1)) * 3) + 0];
                }
                if (i - 1 >= 0) {
                    iArr[2] = grid[((((i - 1) * 5) + i2) * 3) + 0];
                }
                if (i + 1 < 5) {
                    iArr[3] = grid[((((i + 1) * 5) + i2) * 3) + 0];
                }
                i3 = 0;
                while (i3 < iArr.length) {
                    if (iArr[i3] > 0 && iArr[i3] < 4) {
                        zArr[iArr[i3] - 1] = true;
                    }
                    i3++;
                }
                i3 = (zArr[0] && zArr[1] && zArr[2]) ? 3 : (zArr[0] && zArr[1]) ? 2 : zArr[0] ? 1 : 0;
                b[(i * 5) + i2] = i3;
                if (i3 > az) {
                    az = i3;
                }
                if (grid[(((i * 5) + i2) * 3) + 0] == 0) {
                    ay = false;
                }
            }
        }
        // horizontal links: for each column pair (c, c+1) find the first/last row where both cells are built
        for (int c = 0; c < 4; c++) {
            int first = -1;
            int last = -1;
            for (int r = 0; r < 5; r++) {
                int left = grid[(((r * 5) + c) * 3) + 0];
                int right = grid[((((r * 5) + c) + 1) * 3) + 0];
                if (left > 0 && right > 0) {
                    if (first < 0) {
                        first = r;
                    }
                    last = r + 1;
                }
            }
            aB[(c * 2) + 0] = first;
            aB[(c * 2) + 1] = last;
        }
        // vertical links: for each row pair (r, r+1) find the first/last column where both cells are built
        for (int r = 0; r < 4; r++) {
            int first = -1;
            int last = -1;
            for (int c = 0; c < 5; c++) {
                int upper = grid[(((r * 5) + c) * 3) + 0];
                int lower = grid[((((r + 1) * 5) + c) * 3) + 0];
                if (upper > 0 && lower > 0) {
                    if (first < 0) {
                        first = c;
                    }
                    last = c + 1;
                }
            }
            aB[((r * 2) + 8) + 0] = first;
            aB[((r * 2) + 8) + 1] = last;
        }
    }

    public static boolean isCheatActive() {
        return at;
    }

    public static boolean consumeHighScoreFlag() {
        boolean z = v;
        v = false;
        return z;
    }

    public static int getPopulation() {
        return population;
    }

    public static void unloadAssets() {
        L = null;
        M = null;
        N = null;
        O = null;
        P = null;
        Q = null;
        R = null;
        S = null;
        T = null;
        U = null;
        c = null;
        am = null;
        an = null;
        System.gc();
    }

    /** Restores the city-mode state from the "citymode" record (CityMode::loadState()). */
    public static void loadState() {
        try {
            DataInputStream in = Storage.openRead("citymode");
            if (in == null) {
                return;
            }
            House.e = in.readInt();
            House.f = in.readInt();
            f = in.readInt();
            d = in.readBoolean();
            level = in.readInt();
            n = in.readInt();
            p = in.readInt();
            population = in.readInt();
            aw = in.readInt();
            u = in.readInt();
            X = in.readInt();
            cursorCol = in.readInt();
            cursorRow = in.readInt();
            W = in.readInt();
            Y = in.readInt();
            l = in.readBoolean();
            ab = in.readInt();
            ap = in.readInt();
            s = in.readInt();
            y = in.readInt();
            t = in.readInt();
            e = in.readInt();
            ar = in.readInt();
            ax = in.readInt();
            x = in.readInt();
            aF = in.readInt();
            aG = in.readInt();
            k = in.readInt();
            Z = in.readInt();
            aa = in.readInt();
            ak = in.readInt();
            az = in.readInt();
            m = in.readInt();
            aA = in.readInt();
            as = in.readBoolean();
            o = in.readBoolean();
            for (int i = 0; i < 16; i++) {
                aB[i] = in.readInt();
            }
            for (int i = 0; i < 25; i++) {
                b[i] = in.readInt();
            }
            for (int i = 0; i < 25; i++) {
                grid[(i * 3) + 0] = in.readByte();
                grid[(i * 3) + 1] = in.readInt();
                grid[(i * 3) + 2] = in.readByte();
            }
            for (int i = 0; i < r.length; i++) {
                r[i] = in.readBoolean();
            }
            z = in.readInt();
            r[z] = in.readBoolean();
            Storage.close();
            if (o) {
                o = false;
            }
            showTutorialHint(z);
        } catch (Exception ex) {
            System.out.println(new StringBuffer().append("Exception in CityMode::loadState(), e:").append(ex.getMessage()).toString());
        }
    }

    /** Persists the city-mode state to the "citymode" record (CityMode::saveState()). Skipped while the cheat is active. */
    public static void saveState() {
        if (at) {
            return;
        }
        try {
            DataOutputStream out = Storage.openWrite("citymode");
            out.writeInt(House.e);
            out.writeInt(House.f);
            out.writeInt(f);
            out.writeBoolean(d);
            out.writeInt(level);
            out.writeInt(n);
            out.writeInt(p);
            out.writeInt(population);
            out.writeInt(aw);
            out.writeInt(u);
            out.writeInt(X);
            out.writeInt(cursorCol);
            out.writeInt(cursorRow);
            out.writeInt(W);
            out.writeInt(Y);
            out.writeBoolean(l);
            out.writeInt(ab);
            out.writeInt(ap);
            out.writeInt(s);
            out.writeInt(y);
            out.writeInt(t);
            out.writeInt(e);
            out.writeInt(ar);
            out.writeInt(ax);
            out.writeInt(x);
            out.writeInt(aF);
            out.writeInt(aG);
            out.writeInt(k);
            out.writeInt(Z);
            out.writeInt(aa);
            out.writeInt(ak);
            out.writeInt(az);
            out.writeInt(m);
            out.writeInt(aA);
            out.writeBoolean(as);
            out.writeBoolean(o);
            for (int i = 0; i < 16; i++) {
                out.writeInt(aB[i]);
            }
            for (int i = 0; i < 25; i++) {
                out.writeInt(b[i]);
            }
            for (int i = 0; i < 25; i++) {
                out.writeByte(grid[(i * 3) + 0]);
                out.writeInt(grid[(i * 3) + 1]);
                out.writeByte(grid[(i * 3) + 2]);
            }
            for (int i = 0; i < r.length; i++) {
                out.writeBoolean(r[i]);
                if (i == 0 && r[i]) {
                    z = 0;
                } else if (i != 0 && !r[i] && r[i - 1]) {
                    z = i - 1;
                }
            }
            if (o) {
                r[z] = false;
            }
            out.writeInt(z);
            out.writeBoolean(r[z]);
            Storage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in CityMode::saveState(), e:").append(ex.getMessage()).toString());
        }
    }

    private static void randomizeBuildingStats() {
        Y = House.random(3);
        X = ((W - 1) * 100) + House.random(W * 100);
    }

    public static boolean handleClick(int px, int py) {
        if (o) {
            K = true;
            return true;
        }
        int i8 = (((((GameMIDlet.screenWidth - 28) - 11) - 173) >> 1) + 28) + 11;
        int i5 = (((((GameMIDlet.screenHeight - 60) - 56) - 173) + 1) >> 1) + 60;
        if (f == 0) {
            int selX = (i8 - 39) + 3;
            int selY = i5 + 3;
            if (px >= selX && px < selX + 26 && py >= selY && py < selY + 4 * 24) {
                int clickedType = (py - selY) / 24;
                if (clickedType >= 0 && clickedType < 4) {
                    if (e == clickedType && ((e <= ak && e <= az) || at)) {
                        x = 500;
                    } else {
                        e = clickedType;
                    }
                    return true;
                }
            }
        }
        int roofX = i8 - 37;
        int roofY = i5 + 140;
        if (px >= roofX - 5 && px <= roofX + 35 && py >= roofY && py <= roofY + 35) {
            if (cursorCol == -1) {
                K = true;
            } else {
                cursorCol = -1;
                cursorRow = 4;
            }
            return true;
        }
        int gridX = i8 + 5;
        int gridY = i5 + 5;
        if (px >= gridX && px < gridX + 5 * 34 && py >= gridY && py < gridY + 5 * 34) {
            int col = (px - gridX) / 34;
            int row = (py - gridY) / 34;
            if (col >= 0 && col < 5 && row >= 0 && row < 5) {
                if (cursorCol == col && cursorRow == row) {
                    K = true;
                } else {
                    cursorCol = col;
                    cursorRow = row;
                }
                return true;
            }
        }
        return false;
    }
}
