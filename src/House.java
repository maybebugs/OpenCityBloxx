

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Vector;
import jme.Command;
import jme.Font;
import jme.Graphics;
import jme.Image;

public class House extends GameMIDlet implements Screen {
    private static int G;
    private static int H;
    private static boolean T = true;
    private static Image U;
    private static Image V;
    private static Image W;
    private static Image X;
    private static Image Y;
    private static Image Z;
    static final int[][] a = new int[][]{new int[]{92, -1, -1}, new int[]{91, -1, -1}};
    private static int[] aA = new int[5];
    private static int[] aB = new int[5];
    private static int[] aC = new int[5];
    private static int[] aD = new int[5];
    private static int aE;
    private static byte[] aF = new byte[20];
    private static short[][] aG;
    private static int aH = 1664;
    private static int aI;
    private static int aJ;
    private static int aK;
    private static int aL;
    private static int aM = 0;
    private static int aN = 0;
    private static int aO = 0;
    private static int aP = 0;
    private static final int[] aQ = new int[]{213, 256, 298, 341, 384};
    private static final int[] aR = new int[]{85, 106, 128, 149, 170};
    private static final int[] aS = new int[]{1670, 1700, 1650, 1600, 1550, 1500, 1450};
    private static int aT;
    private static int aU;
    private static int aV;
    private static int aW;
    private static int aX;
    private static int aY;
    private static int aZ;
    private static Image aa;
    private static Image ab;
    private static Image ac;
    private static Image ad;
    private static Image ae;
    private static Image af;
    private static Image ag;
    private static Image ah;
    private static Image ai;
    private static Image aj;
    private static Image ak;
    private static Image al;
    private static Image am;
    private static Image an;
    private static int ao;
    private static int ap;
    private static int aq;
    private static int ar;
    private static int as;
    private static boolean at = false;
    private static boolean au = false;
    private static boolean av = false;
    private static int[] aw = new int[5];
    private static int[] ax = new int[5];
    private static int[] ay = new int[5];
    private static int[] az = new int[5];
    public static SoundPlayer b;
    private static int bA;
    private static int bB;
    private static int bC;
    private static boolean bD;
    private static int[][] bE = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{8, 8}));
    private static int[] bF = new int[11];
    private static int[] bG = new int[11];
    private static short[][] bH;
    private static byte[][] bI;
    private static int[] bJ;
    private static int bK;
    private static int bL;
    private static int bM;
    private static int bN;
    private static int[] bO = new int[]{11720434, 10143978, 8436711, 6729700, 5344996, 4225726, 1858454, 802684, 1257578, 3416140, 3615825, 2968395, 4876098, 6768419, 5449523, 8399403, 5315119};
    private static int bP;
    private static int bQ;
    private static int bR;
    private static int bS;
    private static int bT;
    private static int bU;
    private static int bV;
    private static int bW;
    private static int bX;
    private static String[] bY;
    private static int bZ;
    private static int ba;
    private static int bb;
    private static int bc;
    private static int bd = 0;
    private static int be = 0;
    private static boolean bf;
    private static int bg;
    private static int[] bh = new int[5];
    private static int[] bi = new int[5];
    private static int[] bj = new int[5];
    private static int bk;
    private static int bl;
    private static int bm = 0;
    private static int bn = 0;
    private static int bo = 0;
    private static int bp = 0;
    private static int bq;
    private static int br;
    private static int bs;
    private static int bt;
    private static int bu;
    private static int bv = 0;
    private static int bw;
    private static int bx;
    private static int by;
    private static int bz;
    public static Vibra c;
    private static Mesh3D cA;
    private static Mesh3D cB;
    private static Mesh3D cC;
    private static Mesh3D cD;
    private static Mesh3D cE;
    private static int[] cF = new int[5];
    private static int[] cG = new int[5];
    private static int[] cH = new int[5];
    private static int cI;
    private static boolean cJ;
    private static boolean cK;
    private static int cM = 0;
    private static int cN;
    private static int cO;
    private static int cP;
    private static int cQ;
    private static int cR;
    private static int cS;
    private static int cT;
    private static int cU;
    private static int[][] cV;
    private static boolean cW = false;
    private static boolean cX = false;
    private static boolean cY = false;
    private static boolean cZ = false;
    private static Image ca;
    private static int cb;
    private static int cc;
    private static int cd;
    private static Image[] ce;
    private static Random cf = new Random();
    private static int cg = 0;
    private static int ch;
    private static int[] ci;
    private static int[] cj = new int[6];
    private static final int[] ck = new int[]{0, 150, 350, 550, 750};
    private static int[] cl = new int[5];
    private static int[][] cm = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 5}));
    private static int cn;
    private static int co;
    private static int cp;
    private static int cq;
    private static int cr;
    private static int[] cs;
    private static int ct;
    private static int[] cu;
    private static int[] cv;
    private static Image cw;
    private static boolean cx;
    private static Mesh3D cy;
    private static Mesh3D cz;
    public static int d = 0;
    private static int[][] dA = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{9, 6}));
    private static final int[] dB = new int[]{42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, -1, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68};
    private static int dC;
    private static int dD = 0;
    private static int dE = 0;
    private static int[][] dF;
    private static final int[] dG = new int[]{7317456, 6070481, 5216461};
    private static int dH;
    private static boolean da = false;
    private static int[][] db = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 7}));
    private static int[][] dc = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{300, 7}));
    private static boolean dd = false;
    private static boolean de = false;
    private static int df = 0;
    private static int dg = 0;
    private static int dh = 0;
    private static int di = 0;
    private static int dj = 0;
    private static int dk = 0;
    private static int dl = 0;
    private static int dm = 0;
    private static int dn = 100;
    private static int do_ = 0;
    private static int dp = 0;
    private static int dq = 0;
    private static int dr = 0;
    private static int ds = 0;
    private static int dt = 0;
    private static int du = 0;
    private static int dv = 2;
    private static int dw = 0;
    private static int[] dx;
    private static final int[] dy = new int[28];
    private static final int[][] dz = new int[][]{new int[]{0, 0, 0, 2, 3, 2, 3, 3, 4, 5, 6, 6, 6, 7, 8, 8, 9, 9, 10, 10, 11, 12, 12, 13, 14, 15, 17, 18, 21}, new int[]{1, 1, 1, 3, 4, 4, 4, 5, 5, 6, 7, 7, 7, 999, 9, 10, 999, 999, 11, 999, 12, 13, 14, 999, 15, 16, 18, 19, 999}, new int[]{0, 30, 30, 20, 20, 40, 60, 60, 30, 30, 50, 50, 10, 50, 100, 20, 40, 50, 100, 30, 100, 100, 30, 10, 100, 100, 100, 100, 100}, new int[]{60, 2, 1, 3, 2, 2, -2, 3, -4, 5, 2, 2, 6, 0, 0, -2, 0, 0, 0, 0, 0, 0, 3, -3, 0, 0, 0, 0, -3}};
    public static int e;
    public static int f;
    static boolean g = false;
    static boolean h = false;
    static boolean i = false;
    static boolean j = false;
    public static final int[] k = new int[]{10, 20, 30, 40};
    public static int[] l;
    public static String[] m;
    public static boolean n = false;
    public static Font o = Font.getFont(32, 0, 8);
    public static boolean p = false;
    public static boolean q = false;
    private int I = -1;
    private int J = -1;
    private boolean K = false;
    private Image L = null;
    private Image M = null;
    private boolean N = false;
    private int O = 0;
    private Command P = null;
    private Image Q = null;
    private boolean R = true;
    private boolean S = false;
    private int cL;
    int r;
    int s;
    int t;

    static {
        House.M();
    }

    public House() {
        try {
            System.arraycopy(new byte[262144], 0, new byte[262144], 1, 261888);
        } catch (Throwable th) {
        }
        int i = 512;
        int i2 = 60000;
        int i3 = 307;
        int i4 = 50000;
        try {
            i = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Rain-Probability"));
        } catch (Exception e) {
        }
        try {
            i2 = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Rain-Duration")) * 1000;
        } catch (Exception e2) {
        }
        try {
            i3 = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Snow-Probability"));
        } catch (Exception e3) {
        }
        try {
            i4 = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Snow-Duration")) * 1000;
        } catch (Exception e4) {
        }
        cT = (i * 1024) / 100;
        cU = (i3 * 1024) / 100;
        int[][] weather = new int[2][];
        weather[0] = new int[]{i2 / 3, i2 / 30, i2 / 3, i2 / 12, i2 / 3, i2 / 30, 409, i2 / 3, 90, 1024, 32, 4, 5, 128, 16, 96, 0, 10, 256, 64};
        weather[1] = new int[]{i4 / 5, i4 / 25, (i4 * 3) / 5, i4 / 10, i4 / 5, i4 / 25, 819, i4 / 5, 90, 1024, 2, 0, 10, 50, 10, 2, 0, 10, 150, 32};
        cV = weather;
        this.Q = Resources.getImage(10);
        c = this.vibra;
        b = this.sound;
        this.sound.preload(-2147483568, false);
        this.sound.preload(-2147483567, false);
        this.sound.preload(-2147483566, false);
        this.sound.preload(-2147483565, false);
        this.sound.preload(-2147483564, false);
        this.sound.preload(-2147483563, false);
        Renderer3D.init(0, screenWidth, screenHeight);
        Renderer3D.preloadModels(new int[]{7, 8, 9, 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33, 40, 41, 42, 43}, -2147483558);
        G = screenWidth * 1024;
        H = screenHeight * 1024;
    }

    private static void A() {
        int i = 0;
        while (i < 8) {
            int i2 = bE[0][i];
            if (i2 != 0) {
                int i3 = 0;
                int i4 = cg - bE[4][i];
                int i5;
                int i6;
                switch (i2) {
                    case 1:
                        i2 = i4 / 500;
                        i3 = i4 - (i2 * 500);
                        if (i2 > 5) {
                            i2 = 5;
                            i3 = 500;
                        }
                        i5 = bE[1][i];
                        i6 = bE[2][i];
                        int i7 = bE[5][i];
                        bE[7][i] = (i5 - ((bG[i2] * (i5 - bE[9][i])) / 45)) - (((i5 - bE[9][i]) * ((bG[i2 + 1] - bG[i2]) * i3)) / 22500);
                        bE[8][i] = (i6 - ((bF[i2] * ((i6 - ((i7 - 1) * 256)) - 128)) / 15)) - (((i3 * (bF[i2 + 1] - bF[i2])) * ((i6 - ((i7 - 1) * 256)) - 128)) / 7500);
                        i2 = (((i4 - 1200) / 280) % 8) + 1;
                        if (i2 > 5) {
                            i2 = 5 - (i2 - 5);
                        }
                        if (i4 >= 2000) {
                            i3 = Math.min(4, bs - 1) - (bs - bE[5][i]);
                            if (Math.abs(bE[7][i] - bi[i3]) < 128 && bE[8][i] <= ((i7 - 1) * 256) + 128) {
                                bE[0][i] = 2;
                                i4 = bE[7][i] - bi[i3];
                                i3 = (((i7 - 1) * 256) + 128) - bE[8][i];
                                bE[1][i] = i4;
                                bE[2][i] = i3;
                                bE[4][i] = cg;
                                i3 = 1;
                                if (i4 < 0) {
                                    i3 = -1;
                                }
                                bE[9][i] = i3;
                                bE[6][i] = 1;
                                i3 = i2;
                                break;
                            }
                            i3 = i2;
                            break;
                        }
                        i3 = i2;
                        break;
                    case 2:
                        i3 = Math.min(4, bs - 1) - (bs - bE[5][i]);
                        i2 = bE[1][i];
                        i5 = bE[2][i];
                        i6 = bE[9][i];
                        bE[7][i] = (i2 + bi[i3]) - ((i6 * i4) / 12);
                        if (Math.abs(bE[7][i] - bi[i3]) < 64) {
                            bE[7][i] = bi[i3] + (i6 * 64);
                        }
                        bE[8][i] = Math.min(bj[i3], (i4 / 12) + (bj[i3] - i5));
                        if (bE[7][i] == bi[i3] + (i6 * 64) && bE[8][i] == bj[i3]) {
                            bE[1][i] = i6 * 64;
                            bE[0][i] = 5;
                            bE[4][i] = cg;
                        }
                        i3 = ((cg / 200) % 2) + 6;
                        break;
                    case 3:
                        i3 = bE[5][i];
                        bE[7][i] = bE[1][i] - ((i3 * i4) / 30);
                        bE[8][i] = bE[2][i] - ((Math.abs(10 - i3) * i4) / 30);
                        i3 = ((i4 / 280) % 8) + 1;
                        if (i3 > 5) {
                            i3 = 5 - (i3 - 5);
                            break;
                        }
                        break;
                    case 4:
                        i3 = bE[5][i];
                        bE[7][i] = bE[1][i] - (((bE[6][i] * (10 - i3)) * i4) / 15);
                        bE[8][i] = (((i3 * bE[6][i]) * i4) / 15) + bE[2][i];
                        if (i4 > 300) {
                            bE[1][i] = bE[7][i];
                            bE[2][i] = bE[8][i];
                            bE[0][i] = 3;
                            bE[4][i] = cg;
                        }
                        i3 = 0;
                        break;
                    case 5:
                        i3 = Math.min(4, bs - 1) - (bs - bE[5][i]);
                        bE[7][i] = bi[i3] + bE[1][i];
                        bE[8][i] = bj[i3];
                        if (i4 > 500) {
                            bE[0][i] = 0;
                        }
                        i3 = 7;
                        break;
                }
                if (bE[8][i] < aW - (be >> 1) || bE[7][i] < (aV - (bd >> 1)) - 256 || bE[7][i] > (aV + (bd >> 1)) + 256) {
                    bE[0][i] = 0;
                }
                bE[3][i] = i3;
            }
            i++;
        }
    }

    private static void A(int i) {
        cM = cg;
        c.vibrate(800);
        bb = i;
        ba -= i;
        bc = cg;
        if (ba == 0 && !CityMode.isCheatActive()) {
            bk = 2;
        }
    }

    private static void B() {
        int i;
        for (i = 0; i < 2; i++) {
            House.a(i, 0, 0, 0, 0, 0, 0);
        }
        for (i = 0; i < 300; i++) {
            int[] iArr = dc[i];
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            iArr[5] = 0;
            iArr[6] = 0;
        }
    }

    private static void C() {
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            int[] iArr = db[i2];
            int i3 = iArr[5] + 1;
            int i4 = iArr[6] + 1;
            int i5 = iArr[1] >> 10;
            for (int i6 = 0; i6 < i5; i6++) {
                int[] iArr2 = dc[i6 + i];
                iArr2[0] = 1;
                iArr2[1] = House.random(G);
                iArr2[2] = House.random(H);
                iArr2[3] = House.random(4);
                iArr2[4] = House.random(368640);
                iArr2[5] = ((iArr[3] + House.random(i3)) - (i3 / 2)) / (4 - iArr2[3]);
                iArr2[6] = ((iArr[4] + House.random(i4)) - (i4 / 2)) / (4 - iArr2[3]);
            }
            iArr[2] = 0;
            i += iArr[0];
        }
    }

    private static void D() {
        int i = 0;
        while (i < 9) {
            int[] iArr = dA[i];
            int i2 = iArr[0];
            if (i2 != 0) {
                iArr = dA[i];
                iArr[1] = iArr[1] + dz[3][i2];
                if (dA[i][1] > bd + dy[i2 - 1] || dA[i][1] < (-bd) - dy[i2 - 1] || ((aW * 3) / 4) - dA[i][2] > be + dy[i2 - 1]) {
                    dA[i][0] = 0;
                    dA[i][3] = cg + House.random(2000);
                    if (dx[i2] >= 0) {
                        iArr = dx;
                        iArr[i2] = iArr[i2] + 1;
                    }
                }
            } else if (iArr[3] < cg) {
                House.x(i);
            }
            i++;
        }
    }

    private static int E() {
        int i = -1;
        do {
            if (aA[0] < bj[dC]) {
                dC = Math.max(0, dC - 1);
            }
            if (aA[0] >= bj[dC] + 256 || aA[0] <= bj[dC] - 256 || az[0] >= bi[dC] + 256 || az[0] <= bi[dC] - 256) {
                return i;
            }
            i = dC;
            dC--;
        } while (dC >= 0);
        return i;
    }

    /**
     * Called when the swinging block is dropped (private static boolean F()).
     * Decides whether the block lands on the tower, how well (offset / perfect placement),
     * combo counting and the bonus/score for the floor. Rewritten from bytecode.
     *
     * @return true when the drop was handled as a landing, false otherwise
     */
    private static boolean F() {
        int slot = House.E();
        if (bk == 1 && dC != 4 && slot != 4) {
            bq = 0;
            aw[0] = 3;
            House.A(1);
            return false;
        }
        if (slot == -1) {
            return false;
        }
        aE = cg;
        int offset = az[0] - bi[slot];
        int abs = Math.abs(offset);
        if (bs == 0) {
            // first floor: foundation, no scoring
            dD = cg;
            cM = cg;
            c.vibrate(800);
            aw[0] = 4;
            ax[0] = 0;
            bo = offset;
            bn = offset;
            bm = offset;
            House.z(1);
            return true;
        }
        if (slot == Math.min(4, bs - 1)) {
            House.r(offset);
            if (offset > 127 || offset < -127) {
                // missed: the block falls off
                if (bA > 0) {
                    House.G();
                }
                if (bk == 1) {
                    bq = 0;
                    aw[0] = 3;
                    House.A(1);
                    return false;
                }
                aw[0] = 5;
                int direction = offset / Math.abs(offset);
                House.d(direction, -1);
                aB[0] = direction * 500;
                aD[0] = aA[0];
                ay[0] = (-direction) * 45;
                cH[0] = (1 - (House.random(2) * 2)) * 60;
                aE = cg;
                aC[0] = 50;
                return false;
            }
            // landed on the tower
            aw[0] = 4;
            dD = cg;
            if (bz == 0) {
                bB = 0;
                bz++;
            } else if (bA > 0) {
                bz++;
            }
            if (bz > 1) {
                bC = Math.max(bC, bz);
            }
            bm += offset;
            if (abs < 25) {
                // perfect placement
                if (cj[2] == 0 && !House.i && !j) {
                    if (e == 5) {
                        House.i = true;
                    } else if (e == 6) {
                        j = true;
                    }
                    House.showPrompt(Resources.getString(35), null, null);
                    f = 8;
                }
                aF[bs % 20] = (byte) 0;
                if (bk != 1) {
                    bA = 6000;
                }
                House.c(4, bs + 1);
                ch = cg;
            } else {
                aF[bs % 20] = (byte) offset;
                House.c(abs < 50 ? 3 : abs < 80 ? 2 : 1, bs + 1);
            }
            for (int k = 1; k < 5; k++) {
                cF[k - 1] = cF[k];
            }
            cF[slot] = offset / 4;
            if (House.random(2) == 0) {
                cF[slot] = -cF[slot];
            }
            House.z(1);
            if (bk == 1) {
                if (bD) {
                    bB = ((128 - abs) * bl) / 2;
                    bq = 2;
                } else {
                    bB = (128 - abs) / (5 - bl);
                    bq = 1;
                }
                House.y(bB);
                bA = 0;
            }
            return true;
        }
        // landed on a lower slot than the current top: the block topples over
        int direction = offset / abs;
        House.d(-direction, (Math.min(4, bs - 1) - slot) + 1);
        if (bA > 0) {
            House.G();
        }
        aw[0] = 5;
        aB[0] = direction * 500;
        aD[0] = aA[0];
        ay[0] = (-direction) * 45;
        aE = cg;
        aC[0] = 50;
        return true;
    }

    private static void G() {
        bt += bB;
        bz = 0;
        bA = 0;
    }

    private static void H() {
        at = false;
        au = false;
        av = false;
    }

    private void I() {
        int i = 0;
        int i2 = 0;
        if (!at) {
            if (av) {
                if ((f == 4 || f == 5 || f == 6 || f == 8) && House.handleModalAction(1)) {
                    i = 1;
                }
            } else if (au) {
                House.handleModalAction(-1);
            }
        } else if ((f == 4 || f == 5 || f == 6 || f == 8) && House.handleModalAction(0)) {
            i = 1;
        } else if ((f == 1 || f == 3) && aw[0] == 1) {
            aW = aX;
            aw[0] = 2;
            aD[0] = aL;
            ay[0] = 0;
            aE = cg;
            House.H();
        }
        if (i != 0) {
            if (f == 4) {
                int[] iArr;
                if (e == 5) {
                    iArr = cj;
                } else {
                    iArr = cj;
                    i2 = 1;
                }
                iArr[i2] = 1;
                saveSettings();
                f = 1;
            } else if (f == 5) {
                if (h) {
                    h = false;
                }
                if (g) {
                    g = false;
                }
                a(false);
                if (e == 6) {
                    b(1);
                }
            } else if (f == 6) {
                if (h) {
                    h = false;
                }
                if (g) {
                    g = false;
                }
                if (e == 5) {
                    CityMode.startPlacement(bl, bt, bq);
                    f = 7;
                    aZ = 2;
                } else {
                    this.R = false;
                    this.O = 3;
                    House.saveTowerQuickMode();
                }
                if (e == 6) {
                    b(1);
                }
            } else if (f == 8) {
                f = 1;
                cj[2] = 1;
                saveSettings();
                if (e == 5) {
                    House.i = false;
                } else if (e == 6) {
                    j = false;
                }
            }
        }
        House.H();
    }

    private static void J() {
        boolean z = true;
        aw = new int[5];
        ax = new int[5];
        ay = new int[5];
        az = new int[5];
        aA = new int[5];
        aB = new int[5];
        aC = new int[5];
        aD = new int[5];
        cg = 0;
        aF = new byte[20];
        bs = 0;
        bo = 0;
        bp = 0;
        bn = 0;
        aI = 0;
        aJ = 2432;
        bh = new int[5];
        bi = new int[5];
        bj = new int[5];
        bj[0] = -256;
        aK = 0;
        aV = aI;
        aW = aJ;
        aw[0] = 1;
        aH = 1664;
        aX = 512;
        aY = cg + 3000;
        aA[0] = aJ - aH;
        aL = aJ - aH;
        cF = new int[5];
        cG = new int[5];
        cH = new int[5];
        br = 0;
        bm = 0;
        bv = 0;
        bw = 0;
        cg = 0;
        bt = 0;
        bA = -2000;
        ba = 3;
        bq = 0;
        dC = 1;
        bE = (int[][]) Array.newInstance(Integer.TYPE, new int[]{11, 8});
        aP = 2000;
        cO = aS[bl];
        cP = 128;
        cQ = 64;
        cN = 0;
        ch = cg - 600;
        cp = -1;
        dD = cg - 100;
        House.K();
        dH = 0;
        bB = 0;
        bk = 4;
        bC = 0;
        cM = cg - 800;
        House.H();
        cW = cT >= House.random(1024);
        cX = cU >= House.random(1024);
        if (1024 < House.random(1024)) {
            z = false;
        }
        cY = z;
        cZ = false;
        da = false;
        dp = 0;
        dd = false;
        de = false;
        House.B();
        dA = (int[][]) Array.newInstance(Integer.TYPE, new int[]{9, 6});
        dx = new int[]{8, 3, 2, 1, 1, 8, 2, 8, 1, 1, 4, 4, -1, 8, -1, 1, 5, 4, -1, 5, 2, -1, 1, 1, -1, -1, -1, -1, -1};
        cm = (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 5});
    }

    private static void K() {
        dF = (int[][]) Array.newInstance(Integer.TYPE, new int[]{12, 7});
        int i = screenWidth / 12;
        for (int i2 = 0; i2 < 12; i2++) {
            int i3 = House.random(8) + 8;
            dF[i2][1] = i3;
            dF[i2][0] = (i2 * i) - House.random(i3);
            dF[i2][2] = House.random(256) + 640;
            dF[i2][3] = House.random(16) + 16;
            dF[i2][4] = 1;
            dF[i2][5] = dG[House.random(3)];
            dF[i2][6] = House.random(3);
        }
    }

    private static void L() {
        int i = 0;
        int i2 = 32768;
        long j = ((((long) 32768) * 31416) * 2) / 3600000;
        ci = new int[360];
        int i3 = 0;
        while (i < 360) {
            ci[i] = i3;
            i2 = (int) (((long) i2) - ((((long) i3) * j) >> 15));
            i3 = (int) (((long) i3) + ((((long) i2) * j) >> 15));
            i++;
        }
    }

    private static int[] M() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) == 0 ? i2 >>> 1 : (i2 >>> 1) ^ -306674912;
            }
            iArr[i] = i2;
        }
        return iArr;
    }

    private static void N() {
        bP = 0;
        bQ = 0;
        bX = 0;
        bY = null;
        ca = null;
        cb = 0;
    }

    private static boolean O() {
        return bQ == bP + -1;
    }

    private static int a(int i, int i2, int i3) {
        return i >> (i2 / i3);
    }

    private static int a(int i, int i2, int i3, int i4, int i5) {
        return i2 - i == 0 ? 0 : (((i4 - i3) * i5) / (i2 - i)) + i3;
    }

    private static int a(int i, int i2, boolean z) {
        if (!dd || !z) {
            return i;
        }
        int i3 = (((65280 & i) >> 8) * i2) >> 10;
        return ((Math.max(0, Math.min((((16711680 & i) >> 16) * i2) >> 10, 255)) << 16) | (Math.max(0, Math.min(i3, 255)) << 8)) | Math.max(0, Math.min((((i & 255) >> 0) * i2) >> 10, 255));
    }

    private static void a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int[] iArr = db[i];
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = 0;
        iArr[3] = i4;
        iArr[4] = i5;
        iArr[5] = i6;
        iArr[6] = i7;
    }

    public static void showPrompt(String str, String[] strArr, Image image) {
        House.N();
        m = null;
        if (str != null) {
            m = House.wrapText(str, o, screenWidth - ((bT + bS) << 1));
            int length = m.length;
            bW = o.getHeight() + 2;
            bV = (screenHeight - ((bU + bR) << 1)) / bW;
            bP = length / bV;
            if (length % bV > 0) {
                bP++;
            }
            if (strArr != null) {
                if ((length - ((bP - 1) * bV)) + strArr.length > bV) {
                    bP++;
                }
                bY = strArr;
            }
            if (image != null) {
                ca = image;
                cb = ca.getHeight();
                if (screenHeight - ((bU + bR) << 1) < ((length - ((bP - 1) * bV)) * bW) + cb) {
                    bP++;
                }
            }
            bX = cg;
        }
    }

    private static void a(Graphics graphics, int i) {
        int i2 = dH;
        while (i2 < bI.length) {
            short s = bH[i2][1];
            int i3 = ((aU - bH[i2][0]) - s) + i;
            if (i3 + s >= 0) {
                if (i3 <= screenHeight) {
                    graphics.setColor(House.a(bJ[bI[i2][2]], df, true));
                    graphics.fillRect(bI[i2][0] & 255, i3, bI[i2][1] & 255, s);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private static void a(Graphics graphics, int i, int i2, int i3) {
        if (i3 > 1) {
            graphics.setColor(14540287);
            graphics.fillRect(i + 1, i2 + 1, i3 - 1, i3 - 1);
            graphics.drawLine(i, i2, i, i2);
            graphics.drawLine(i + i3, i2, i + i3, i2);
            graphics.drawLine(i, i2 + i3, i, i2 + i3);
            graphics.drawLine(i + i3, i2 + i3, i + i3, i2 + i3);
            return;
        }
        graphics.setColor(11184861);
        graphics.fillRect(i, i2, i3 + 1, i3 + 1);
    }

    public static void a(Graphics graphics, int i, int i2, int i3, int i4) {
        graphics.setClip(i, i2, i3, i4);
    }

    private static void a(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        if (i5 < 0) {
            graphics.setColor(House.a(11184844, (df / 2) + 512, true));
            graphics.drawLine(i, i2, i3 + i, i4 + i2);
        }
        if (i5 < 2) {
            graphics.setColor(House.a(11184844, (df / 4) + 768, true));
            i6 = i2;
            i7 = i;
        } else {
            graphics.setColor(11184844);
            graphics.drawLine(i, i2, i3 + i, i4 + i2);
            graphics.setColor(16777215);
            i7 = i3 + i;
            i6 = i4 + i2;
        }
        graphics.drawLine(i7, i6, i3 + i, i4 + i2);
    }

    private static void a(Graphics graphics, int i, int i2, int i3, int i4, boolean z) {
        int i5 = 0;
        int i6 = i2 - 6;
        while (true) {
            if (i <= 0 && i5 >= i4) {
                break;
            }
            House.a(graphics, i6, i3, 7, 12);
            int i7 = i % 10;
            i /= 10;
            graphics.drawImage(Z, i6 - (i7 * 7), i3, 20);
            i6 -= 6;
            i5++;
        }
        if (z) {
            House.a(graphics, i6, i3, 7, 12);
            graphics.drawImage(Z, i6 - 70, i3, 20);
        }
    }

    private static void a(Graphics graphics, int i, boolean z, boolean z2) {
        int i2 = ((((i * 2) / 3) % cd) * 32) >> 8;
        int i3 = ((i * 2) / 3) / cd;
        if (i3 != cp) {
            int[] iArr;
            cp = i3;
            if (z) {
                bK = bO[(i3 % 3) + 1];
                iArr = bO;
                i3 = ((i3 + 1) % 3) + 1;
            } else {
                bK = bO[Math.min(cp, Math.max(9, ((cp - 9) % 8) + 9))];
                iArr = bO;
                i3 = Math.min(cp + 1, Math.max(9, (((cp + 1) - 9) % 8) + 9));
            }
            bL = iArr[i3];
            bM = ((((((bK & -65536) >> 16) + ((bL & -65536) >> 16)) >> 1) << 16) + (((((bK & -16711936) >> 8) + ((bL & -16711936) >> 8)) >> 1) << 8)) + (((bK & -16776961) + (bL & -16776961)) >> 1);
            bN = (screenWidth >> 2) + House.random(screenWidth >> 1);
        }
        if (i2 < screenHeight) {
            graphics.setColor(House.a(bK, df + dj, z2));
            graphics.fillRect(0, i2, screenWidth, screenHeight - i2);
        }
        if (i2 > 0) {
            graphics.setColor(House.a(bL, df + dj, z2));
            graphics.fillRect(0, 0, screenWidth, i2);
        }
        graphics.setColor(House.a(bM, df + dj, z2));
        graphics.fillRect(0, i2 - 7, bN, 7);
        graphics.fillRect(bN, i2 - 1, screenWidth - bN, 7);
        graphics.fillRect(bN - 24, i2 - 2, 48, 3);
        graphics.fillRect(bN - 18, i2 - 3, 36, 5);
        graphics.fillRect(bN - 16, i2 - 5, 32, 9);
        graphics.fillRect(bN - 15, i2 - 6, 30, 11);
        graphics.fillRect(bN - 13, i2 - 7, 26, 13);
    }

    private void a(boolean z) {
        String stringBuffer = new StringBuffer().append(Resources.getString(93, new String[]{new StringBuffer().append("").append(bt).toString()})).append('\n').toString();
        String bonusText = Resources.getString(96);
        if (z && bt > cj[3]) {
            bf = true;
            stringBuffer = new StringBuffer().append(stringBuffer).append(bonusText).append('\n').toString();
            cj[3] = bt;
        }
        stringBuffer = new StringBuffer().append(stringBuffer).append(Resources.getString(94, new String[]{new StringBuffer().append("").append(bs).toString()})).append('\n').toString();
        if (z && bs > cj[4]) {
            bf = true;
            stringBuffer = new StringBuffer().append(stringBuffer).append(bonusText).append('\n').toString();
            cj[4] = bs;
        }
        stringBuffer = new StringBuffer().append(stringBuffer).append(Resources.getString(95, new String[]{new StringBuffer().append("").append(bC).toString()})).toString();
        if (z && bC > cj[5]) {
            stringBuffer = new StringBuffer().append(stringBuffer).append('\n').append(bonusText).toString();
            cj[5] = bC;
        }
        saveSettings();
        House.showPrompt(stringBuffer, null, null);
        f = 6;
        bk = 3;
        if (e == 5) {
            h = true;
        } else if (e == 6) {
            g = true;
        }
        if (e == 6 && !CityMode.isCheatActive()) {
            this.highScores.submitScore(1, new int[]{bt, bs}, null);
        }
        if (e == 5) {
            d++;
        }
    }

    public static boolean a(int i) {
        return i == 3 ? Storage.getMenuFlag(8) == 0 : i == 1 ? Storage.getMenuFlag(9) == 0 : false;
    }

    /** Character-by-character word wrap into lines of at most maxWidth pixels (static String[] a(String,Font,int)). Rewritten from bytecode. */
    public static String[] wrapText(String text, Font font, int maxWidth) {
        int lineStart = 0;
        int pos = 0;
        int newline = 0;
        int length = text.length();
        Vector lines = new Vector();
        while (newline < length) {
            newline = text.indexOf(10, lineStart);
            if (newline == -1) {
                newline = length;
            }
            boolean done = false;
            while (!done) {
                pos = lineStart;
                int width = 0;
                int lastSpace = -1;
                while (width < maxWidth && pos < newline) {
                    char c = text.charAt(pos);
                    width += font.stringWidth(new StringBuffer().append("").append(c).toString());
                    pos++;
                    if (c == ' ') {
                        lastSpace = pos;
                    }
                }
                if (pos == newline && width <= maxWidth) {
                    done = true;
                } else if (lastSpace != -1) {
                    pos = lastSpace;
                } else {
                    pos--;
                }
                lines.addElement(text.substring(lineStart, pos));
                if (done && pos < length) {
                    pos++;
                }
                lineStart = pos;
            }
        }
        String[] result = new String[lines.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new String(((String) lines.elementAt(i)).trim());
        }
        return result;
    }

    private static void b(int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        if (i2 >= -256) {
            Mesh3D dVar;
            if (i4 == 888) {
                dVar = bD ? cA : cz;
            } else {
                if (i4 == 999) {
                    dVar = cB;
                    i4 = 0;
                } else {
                    dVar = cy;
                }
                i6 = i4;
            }
            dVar.resetTransform();
            dVar.translate((float) i, (float) i2, (float) i3);
            dVar.rotate(((float) i6) / 360.0f, 0.0f, 0.0f, 1.0f);
            dVar.rotate(((float) i5) / 360.0f, 0.0f, 1.0f, 0.0f);
            dVar.render();
        }
    }

    private static void b(Graphics graphics, int i, int i2, int i3) {
        int[] iArr = new int[]{512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        int[] iArr2 = new int[]{6885390, 12152341, 14540064, 15658544, 16777008, 16777088, 16777215, 16777215, 16777215, 16777215};
        House.random(10);
        int i4 = (i3 * 4) + 4;
        int i5 = (i3 * 4) + 4;
        for (int i6 = 0; i6 < 8; i6++) {
            int i7 = House.random(i4);
            int i8 = House.random(i5);
            graphics.setColor(iArr2[i6]);
            graphics.fillRect(i7 + i, i8 + i2, i3 + 1, i3 + 1);
        }
    }

    private static void b(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        House.a(graphics, i, i2, 21, 28);
        Image image = i5 == 1 ? aa : ab;
        if (i4 < 0) {
            graphics.drawImageFlipped(image, i - ((9 - i3) * 21), i2, 20);
            return;
        }
        int i6;
        if (i4 < 0) {
            i6 = i - ((7 - i3) * 21);
            i2 -= 28;
        } else {
            i6 = i - (i3 * 21);
        }
        graphics.drawImage(image, i6, i2, 20);
    }

    private static void b(Graphics graphics, int i, int i2, int i3, int i4, boolean z) {
        int i5 = 0;
        int i6 = i2 - 6;
        while (true) {
            if (i <= 0 && i5 >= i4) {
                break;
            }
            House.a(graphics, i6, i3, 7, 9);
            int i7 = i % 10;
            i /= 10;
            graphics.drawImage(Y, i6 - (i7 * 7), i3, 20);
            i6 -= 6;
            i5++;
        }
        if (z) {
            House.a(graphics, i6, i3, 7, 9);
            graphics.drawImage(Y, i6 - 70, i3, 20);
        }
    }

    private static void b(Graphics graphics, boolean z) {
        int[][] iArr;
        int i;
        int i2 = z ? db[0][0] : 0;
        if (z) {
            iArr = db;
            i = 1;
        } else {
            iArr = db;
            i = 0;
        }
        int[] iArr2 = iArr[i];
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            int[] iArr3 = dc[i3 + i2];
            if (iArr3[0] == 1) {
                switch (dv) {
                    case 1:
                        House.a(graphics, iArr3[1] >> 10, iArr3[2] >> 10, (iArr3[5] * 32) >> 10, (iArr3[6] * 32) >> 10, z ? iArr3[3] : -1);
                        break;
                    case 2:
                        House.a(graphics, iArr3[1] >> 10, iArr3[2] >> 10, iArr3[3] >> (z ? 0 : 1));
                        break;
                    case 3:
                        House.b(graphics, iArr3[1] >> 10, iArr3[2] >> 10, 0);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void c(int i, int i2) {
        if (bk != 1) {
            House.y(i);
            int i3 = i >> 1;
            for (int i4 = 0; i4 < 8 && i3 > 0; i4++) {
                if (bE[0][i4] == 0) {
                    bE[0][i4] = 1;
                    bE[5][i4] = i2;
                    int i5 = 1 - (House.random(2) * 2);
                    bE[1][i4] = aV + (((bd >> 1) + 256) * i5);
                    bE[9][i4] = bm;
                    int i6 = House.random(1000);
                    bE[2][i4] = (aW + (be >> 1)) + House.random(768);
                    bE[4][i4] = cg - i6;
                    bE[10][i4] = House.random(2);
                    bE[6][i4] = -i5;
                    i3--;
                }
            }
        }
    }

    public static void paintTutorialModal(Graphics graphics) {
        int i;
        int i2 = 0;
        int i3 = screenHeight - ((bT + bR) << 1);
        int min = Math.min(i3, ((cg - bX) * i3) / 600);
        House.a(graphics, 0, 0, screenWidth, screenHeight);
        graphics.setColor(8220270);
        graphics.drawRect(bT, bU + (i3 - min), screenWidth - (bT << 1), (screenHeight - (bU << 1)) - (i3 - min));
        graphics.setColor(15458785);
        graphics.drawRect(bT + 1, (bU + 1) + (i3 - min), screenWidth - ((bT + 1) << 1), (screenHeight - ((bU + 1) << 1)) - (i3 - min));
        graphics.drawRect(bT + 2, (bU + 2) + (i3 - min), screenWidth - ((bT + 2) << 1), (screenHeight - ((bU + 2) << 1)) - (i3 - min));
        graphics.setColor(9524565);
        graphics.drawRect(bT + 3, (bU + 3) + (i3 - min), screenWidth - ((bT + 3) << 1), (screenHeight - ((bU + 3) << 1)) - (i3 - min));
        graphics.setColor(16777215);
        graphics.fillRect(bT + 4, (bU + 4) + (i3 - min), (screenWidth - ((bT + 4) << 1)) + 1, ((screenHeight - ((bU + 4) << 1)) + 1) - (i3 - min));
        graphics.setColor(1313804);
        graphics.drawLine((screenWidth - bT) + 1, (bU + (i3 - min)) + 1, (screenWidth - bT) + 1, screenHeight - bU);
        graphics.drawLine(bT + 1, (screenHeight - bU) + 1, (screenWidth - bT) + 1, (screenHeight - bU) + 1);
        House.a(graphics, 0, (bU + 4) + (i3 - min), screenWidth, ((screenHeight - ((bU + 4) << 1)) + 1) - ((i3 - min) + 18));
        for (i = 0; i < bV; i++) {
            if ((bQ * bV) + i < m.length) {
                graphics.drawString(m[(bQ * bV) + i], screenWidth >> 1, ((bU + bR) + (bW * i)) + (i3 - min), 17);
            }
        }
        if (House.O() && bY != null) {
            i = Math.max(0, m.length - (bQ * bV));
            graphics.setColor(16266752);
            graphics.drawRect((bT + bS) + 4, ((bU + bR) + ((bZ + i) * bW)) + (i3 - min), screenWidth - (((bT + 4) + bS) << 1), bW);
            while (i2 < bY.length) {
                graphics.drawString(bY[i2], screenWidth >> 1, ((bU + bR) + ((i + i2) * bW)) + (i3 - min), 17);
                i2++;
            }
        } else if (House.O() && ca != null) {
            i = Math.max(0, m.length - (bQ * bV));
            graphics.drawImage(ca, screenWidth >> 1, ((i * bW) + (bU + bR)) + (i3 - min), 17);
        }
        if (cg - bX >= 600) {
            i = 1;
            if (House.O()) {
                i = 2;
            }
            House.a(graphics, (screenWidth - 18) >> 1, ((screenHeight - bU) - 18) - 4, 18, 18);
            graphics.drawImage(aj, (screenWidth - 18) >> 1, ((screenHeight - bU) - ((i + 1) * 18)) - 4, 20);
            if (bQ > 0) {
                House.a(graphics, (screenWidth - 18) >> 1, bU + 4, 18, 18);
                graphics.drawImage(aj, (screenWidth - 18) >> 1, bU + 4, 20);
            }
        }
    }

    private static void d(int i, int i2) {
        int i3;
        int i4;
        if (i2 != -1) {
            i3 = by;
            if (i2 > 4) {
                i2 = 4;
            }
            for (i4 = 0; i4 < i2; i4++) {
                if (bs == 1) {
                    House.A(1);
                    return;
                }
                ay[i4 + 1] = (-i) * 45;
                aC[i4 + 1] = ((4 - i4) * 30) + 50;
                aB[i4 + 1] = (i * 400) - ((4 - i4) * 30);
                aw[i4 + 1] = 5;
                aA[i4 + 1] = bj[i3];
                az[i4 + 1] = bi[i3];
                aD[i4 + 1] = aA[i4 + 1];
                cH[i4 + 1] = (1 - (House.random(2) * 2)) * 60;
                cG[i4 + 1] = 0;
                cl[i4 + 1] = 0;
                ax[i4 + 1] = bh[i3];
                House.t(bs);
                House.z(-1);
                i3--;
            }
            if (!cK) {
                House.A(1);
                return;
            }
            return;
        }
        int i5 = 20;
        i3 = 1;
        for (i4 = Math.min(4, bs - 1); i4 > 0; i4--) {
            int i6 = bi[i4] - bi[i4 - 1];
            if (Math.abs(i6) <= i5) {
                break;
            }
            i6 /= Math.abs(i6);
            Math.min(5 - i3, i4);
            ay[i3] = (-i6) * 45;
            aC[i3] = 50;
            aB[i3] = i6 * 400;
            aw[i3] = 7;
            cl[i3] = ck[i3];
            cH[i3] = (1 - (House.random(2) * 2)) * 60;
            cG[i3] = 0;
            i3++;
            i5 <<= 1;
        }
        House.A(1);
    }

    private static int e(int i, int i2) {
        int i3 = -1;
        if (i == 53) {
            i3 = 0;
        } else if (i == 56) {
            i3 = 2;
        } else if (i == 50) {
            i3 = 1;
        }
        return i2 == 8 ? 0 : i2 == 6 ? 2 : i2 == 1 ? 1 : i3;
    }

    private static void e(Graphics graphics) {
        int frameW;
        int frameH;
        int i = (aW * 3) / 4;
        for (int i2 = 0; i2 < 9; i2++) {
            int[] iArr = dA[i2];
            int i3 = iArr[0];
            if (i3 != 0) {
                int i4 = (aT + ((iArr[1] - aV) * 32)) >> 8;
                int i5 = (aU - ((iArr[2] - i) * 32)) >> 8;
                if (i3 == 13) {
                    graphics.setColor(255, 255, 255);
                    graphics.drawLine(i4, i5, i4, i5);
                } else if (i3 == 6 || i3 == 12) {
                    frameW = ce[i3 - 1].getWidth() >> 1;
                    frameH = ce[i3 - 1].getHeight();
                    House.a(graphics, i4 - (frameW >> 1), i5 - (frameH >> 1), frameW, frameH);
                    graphics.drawImage(ce[i3 - 1], (i4 - (frameW >> 1)) - (frameW * ((cg / 400) % 2)), i5 - (frameH >> 1), 20);
                    House.a(graphics, 0, 0, screenWidth, screenHeight);
                } else if (i3 == 28) {
                    frameW = ce[i3 - 1].getWidth();
                    frameH = ce[i3 - 1].getHeight() >> 1;
                    House.a(graphics, i4 - (frameW >> 1), i5 - (frameH >> 1), frameW, frameH);
                    graphics.drawImage(ce[i3 - 1], i4 - (frameW >> 1), (i5 - (frameH >> 1)) - (((cg / 400) % 2) * frameH), 20);
                    House.a(graphics, 0, 0, screenWidth, screenHeight);
                } else {
                    graphics.drawImage(ce[i3 - 1], i4, i5, 3);
                }
            }
        }
    }

    public static boolean updateLoadingProgress(int i) {
        cc = i;
        GameMIDlet.getInstance().fullRepaint();
        return !n;
    }

    protected static void saveTowerQuickMode() {
        try {
            int i;
            int i2;
            DataOutputStream b = Storage.openWrite("quickModeRS");
            b.writeInt(e);
            b.writeInt(f);
            b.writeInt(bk);
            b.writeInt(cg);
            b.writeInt(aT);
            b.writeInt(aU);
            b.writeInt(aV);
            b.writeInt(aW);
            b.writeInt(cI);
            b.writeInt(aX);
            b.writeInt(aY);
            b.writeInt(aI);
            b.writeInt(aJ);
            b.writeInt(aK);
            b.writeInt(aL);
            b.writeInt(aM);
            b.writeInt(aP);
            b.writeInt(cN);
            b.writeInt(aH);
            b.writeInt(cO);
            b.writeInt(aN);
            b.writeInt(aO);
            b.writeInt(cP);
            b.writeInt(cQ);
            b.writeInt(cp);
            b.writeInt(ao);
            b.writeInt(ap);
            b.writeInt(ar);
            b.writeInt(aq);
            b.writeInt(cd);
            b.writeInt(bs);
            b.writeInt(bg);
            b.writeInt(bl);
            b.writeInt(bo);
            b.writeInt(bp);
            b.writeInt(bn);
            b.writeInt(bm);
            b.writeInt(by);
            b.writeInt(bu);
            b.writeInt(bv);
            b.writeInt(bw);
            for (int i22 : bi) {
                b.writeInt(i22);
            }
            for (int i222 : bj) {
                b.writeInt(i222);
            }
            for (int i2222 : cF) {
                b.writeInt(i2222);
            }
            for (int i22222 : bh) {
                b.writeInt(i22222);
            }
            b.writeBoolean(bD);
            b.writeInt(dC);
            b.writeInt(bq);
            for (int i222222 : az) {
                b.writeInt(i222222);
            }
            for (int i2222222 : aA) {
                b.writeInt(i2222222);
            }
            for (int i22222222 : aw) {
                b.writeInt(i22222222);
            }
            for (int i222222222 : cG) {
                b.writeInt(i222222222);
            }
            for (int i2222222222 : ax) {
                b.writeInt(i2222222222);
            }
            for (int i22222222222 : aB) {
                b.writeInt(i22222222222);
            }
            for (int i222222222222 : aC) {
                b.writeInt(i222222222222);
            }
            for (int i2222222222222 : aD) {
                b.writeInt(i2222222222222);
            }
            for (int i22222222222222 : ay) {
                b.writeInt(i22222222222222);
            }
            for (byte writeByte : aF) {
                b.writeByte(writeByte);
            }
            for (int i222222222222222 : cH) {
                b.writeInt(i222222222222222);
            }
            for (int i222222222222222 = 0; i222222222222222 < 8; i222222222222222++) {
                for (i = 0; i < 8; i++) {
                    b.writeInt(bE[i222222222222222][i]);
                }
            }
            for (int i222222222222222 = 0; i222222222222222 < 2; i222222222222222++) {
                for (i = 0; i < 5; i++) {
                    b.writeInt(cm[i222222222222222][i]);
                }
            }
            b.writeInt(cn);
            b.writeInt(co);
            b.writeInt(bt);
            b.writeInt(ba);
            b.writeInt(bb);
            b.writeInt(bc);
            b.writeInt(ch);
            b.writeInt(bA);
            b.writeInt(bB);
            b.writeInt(bz);
            b.writeInt(dD);
            b.writeInt(dH);
            for (int i222222222222222 = 0; i222222222222222 < 12; i222222222222222++) {
                for (i = 0; i < 7; i++) {
                    b.writeInt(dF[i222222222222222][i]);
                }
            }
            for (int i222222222222222 = 0; i222222222222222 < 9; i222222222222222++) {
                for (i = 0; i < 6; i++) {
                    b.writeInt(dA[i222222222222222][i]);
                }
            }
            for (int i2222222222222222 : cj) {
                b.writeInt(i2222222222222222);
            }
            b.writeBoolean(g);
            b.writeInt(bC);
            b.writeBoolean(j);
            b.writeBoolean(bf);
            b.writeBoolean(cW);
            b.writeBoolean(cX);
            b.writeBoolean(cY);
            b.writeBoolean(cZ);
            b.writeBoolean(da);
            b.writeBoolean(dd);
            b.writeBoolean(de);
            b.writeInt(df);
            b.writeInt(dg);
            b.writeInt(dh);
            b.writeInt(di);
            b.writeInt(dj);
            b.writeInt(dk);
            b.writeInt(dl);
            b.writeInt(dm);
            b.writeInt(dn);
            b.writeInt(do_);
            b.writeInt(dp);
            b.writeInt(dq);
            b.writeInt(dr);
            b.writeInt(ds);
            b.writeInt(dt);
            b.writeInt(du);
            b.writeInt(dv);
            b.writeInt(dw);
            for (int i2222222222222222 = 0; i2222222222222222 < 2; i2222222222222222++) {
                for (i = 0; i < 7; i++) {
                    b.writeInt(db[i2222222222222222][i]);
                }
            }
            b.writeInt(cM);
            Storage.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in saveTowerQuickMode(), e:").append(e.getMessage()).toString());
        }
    }

    private void f(Graphics graphics) {
        int i;
        Mesh3D dVar = null;
        Renderer3D.setFov(55.0f);
        Renderer3D.lookAt((float) aV, (float) aW, (float) cI, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
        if (bk == 1 || aw[0] == 3 || (bk == 4 && aw[0] != 2)) {
            Renderer3D.projected[0] = (float) aK;
            Renderer3D.projected[1] = (float) (aL + 528);
            Renderer3D.projected[2] = 0.0f;
            Renderer3D.projected[3] = 1.0f;
            Renderer3D.project(Renderer3D.projected);
            graphics.setColor(0);
            i = aT + (((aI - aV) * 32) >> 8);
            int i2 = aU - (((aJ - aW) * 32) >> 8);
            int i3 = (int) Renderer3D.projected[0];
            int i4 = (int) Renderer3D.projected[1];
            graphics.drawImage(ak, i + 4, i2, 40);
            graphics.drawLine(i, i2, i3, i4);
            graphics.drawLine(i + 1, i2, i3 + 1, i4);
        }
        Renderer3D.beginFrame((Object) graphics);
        if (bs <= 5) {
            cC.resetTransform();
            cC.render();
        }
        if (bk == 0) {
            cD.resetTransform();
            cD.translate((float) aK, (float) aL, 0.0f);
            cD.rotate(((float) aM) / 540.0f, 0.0f, 0.0f, 1.0f);
            dVar = cD;
        } else if (bk == 1 || aw[0] == 3 || (bk == 4 && aw[0] != 2)) {
            cE.resetTransform();
            cE.translate((float) aK, (float) aL, 0.0f);
            dVar = cE;
        }
        if (dVar != null) {
            dVar.render();
        }
        i = 0;
        while (i < 5) {
            if (!(aw[i] == 4 || aw[i] == 0)) {
                House.b(az[i], aA[i], 0, ax[i], cG[i]);
            }
            i++;
        }
        for (i = 0; i < Math.min(bs, 5); i++) {
            House.b(bi[i], bj[i], cF[i], -bh[i], 0);
        }
        Renderer3D.endFrame();
    }

    private static void g(Graphics graphics) {
        House.a(graphics, (cr * 32) >> 8, true, false);
        int i = (screenWidth << 1) / 3;
        int height = (screenHeight >> 1) + (cw.getHeight() >> 3);
        graphics.drawImage(cw, screenWidth >> 1, height, 33);
        height += cw.getHeight() >> 3;
        graphics.setColor(-15463412);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 1, height + 1, i, 10);
        graphics.setColor(-8556946);
        graphics.fillRect((screenWidth >> 1) - (i >> 1), height, i, 10);
        graphics.setColor(-1);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 1, height + 1, i - 2, 8);
        graphics.setColor(-14475756);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 2, height + 2, i - 4, 6);
        graphics.setColor(-4980736);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 2, height + 2, (cc * (i - 4)) / 100, 6);
        graphics.setColor(-9895936);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 3, height + 3, ((cc * (i - 4)) / 100) - 1, 5);
        graphics.setColor(-487168);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 3, height + 3, ((cc * (i - 4)) / 100) - 2, 1);
        graphics.setColor(-510464);
        graphics.fillRect(((screenWidth >> 1) - (i >> 1)) + 3, height + 4, (((i - 4) * cc) / 100) - 2, 3);
    }

    protected static void saveTowerCityMode() {
        try {
            int i;
            int i2;
            DataOutputStream b = Storage.openWrite("cityModeRS");
            b.writeInt(e);
            b.writeInt(f);
            b.writeInt(bk);
            b.writeInt(cg);
            b.writeInt(aT);
            b.writeInt(aU);
            b.writeInt(aV);
            b.writeInt(aW);
            b.writeInt(cI);
            b.writeInt(aX);
            b.writeInt(aY);
            b.writeInt(aI);
            b.writeInt(aJ);
            b.writeInt(aK);
            b.writeInt(aL);
            b.writeInt(aM);
            b.writeInt(aP);
            b.writeInt(cN);
            b.writeInt(aH);
            b.writeInt(cO);
            b.writeInt(aN);
            b.writeInt(aO);
            b.writeInt(cP);
            b.writeInt(cQ);
            b.writeInt(cp);
            b.writeInt(ao);
            b.writeInt(ap);
            b.writeInt(ar);
            b.writeInt(aq);
            b.writeInt(cd);
            b.writeInt(bs);
            b.writeInt(bg);
            b.writeInt(bl);
            b.writeInt(bo);
            b.writeInt(bp);
            b.writeInt(bn);
            b.writeInt(bm);
            b.writeInt(by);
            b.writeInt(bu);
            b.writeInt(bv);
            b.writeInt(bw);
            for (int i22 : bi) {
                b.writeInt(i22);
            }
            for (int i222 : bj) {
                b.writeInt(i222);
            }
            for (int i2222 : cF) {
                b.writeInt(i2222);
            }
            for (int i22222 : bh) {
                b.writeInt(i22222);
            }
            b.writeBoolean(bD);
            b.writeInt(dC);
            b.writeInt(bq);
            for (int i222222 : az) {
                b.writeInt(i222222);
            }
            for (int i2222222 : aA) {
                b.writeInt(i2222222);
            }
            for (int i22222222 : aw) {
                b.writeInt(i22222222);
            }
            for (int i222222222 : cG) {
                b.writeInt(i222222222);
            }
            for (int i2222222222 : ax) {
                b.writeInt(i2222222222);
            }
            for (int i22222222222 : aB) {
                b.writeInt(i22222222222);
            }
            for (int i222222222222 : aC) {
                b.writeInt(i222222222222);
            }
            for (int i2222222222222 : aD) {
                b.writeInt(i2222222222222);
            }
            for (int i22222222222222 : ay) {
                b.writeInt(i22222222222222);
            }
            for (byte writeByte : aF) {
                b.writeByte(writeByte);
            }
            for (int i222222222222222 : cH) {
                b.writeInt(i222222222222222);
            }
            for (int i222222222222222 = 0; i222222222222222 < 8; i222222222222222++) {
                for (i = 0; i < 8; i++) {
                    b.writeInt(bE[i222222222222222][i]);
                }
            }
            for (int i222222222222222 = 0; i222222222222222 < 2; i222222222222222++) {
                for (i = 0; i < 5; i++) {
                    b.writeInt(cm[i222222222222222][i]);
                }
            }
            b.writeInt(cn);
            b.writeInt(co);
            b.writeInt(bt);
            b.writeInt(ba);
            b.writeInt(bb);
            b.writeInt(bc);
            b.writeInt(ch);
            b.writeInt(bA);
            b.writeInt(bB);
            b.writeInt(bz);
            b.writeInt(dD);
            b.writeInt(dH);
            for (int i222222222222222 = 0; i222222222222222 < 12; i222222222222222++) {
                for (i = 0; i < 7; i++) {
                    b.writeInt(dF[i222222222222222][i]);
                }
            }
            for (int i222222222222222 = 0; i222222222222222 < 9; i222222222222222++) {
                for (i = 0; i < 6; i++) {
                    b.writeInt(dA[i222222222222222][i]);
                }
            }
            for (int i2222222222222222 : cj) {
                b.writeInt(i2222222222222222);
            }
            b.writeBoolean(h);
            b.writeInt(bC);
            b.writeBoolean(House.i);
            b.writeBoolean(bf);
            b.writeBoolean(cW);
            b.writeBoolean(cX);
            b.writeBoolean(cY);
            b.writeBoolean(cZ);
            b.writeBoolean(da);
            b.writeBoolean(dd);
            b.writeBoolean(de);
            b.writeInt(df);
            b.writeInt(dg);
            b.writeInt(dh);
            b.writeInt(di);
            b.writeInt(dj);
            b.writeInt(dk);
            b.writeInt(dl);
            b.writeInt(dm);
            b.writeInt(dn);
            b.writeInt(do_);
            b.writeInt(dp);
            b.writeInt(dq);
            b.writeInt(dr);
            b.writeInt(ds);
            b.writeInt(dt);
            b.writeInt(du);
            b.writeInt(dv);
            b.writeInt(dw);
            for (int i2222222222222222 = 0; i2222222222222222 < 2; i2222222222222222++) {
                for (i = 0; i < 7; i++) {
                    b.writeInt(db[i2222222222222222][i]);
                }
            }
            b.writeInt(cM);
            Storage.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in saveTowerCityMode(), e:").append(e.getMessage()).toString());
        }
    }

    private static void h(Graphics graphics) {
        int i = 0;
        int i2 = cg - ch;
        if (i2 < 600) {
            int i3 = 48;
            int i4 = 32;
            int i5 = aT + (((bi[by] - aV) * 32) / 256);
            int i6 = aU - (((bj[by] - aW) * 32) / 256);
            if (i2 < 30) {
                graphics.setColor(16777215);
            } else if (i2 < 200) {
                graphics.setColor(255, 255 - (i2 >> 2), 0);
                i4 = 48 - ((i2 * 32) / 200);
                i3 = Math.max(2, 6 - ((i2 * 6) / 200));
                graphics.fillRect(i5 - (i4 >> 1), ((32 - i3) >> 1) + i6, i4, i3);
                i3 = ((i2 * 32) / 200) + 16;
                i4 = ((i2 * 32) / 400) + 16;
                graphics.drawLine(i5 + 16, i6 + 16, i5 + i3, i6 + i4);
                graphics.drawLine(i5 - 16, i6 + 16, i5 - i3, i6 + i4);
                graphics.drawLine(i5 - 16, i6 - 16, i5 - i4, i6 - i4);
            } else {
                i = (i2 / 50) % 3;
            }
            House.a(graphics, (i5 + i3) - 6, (i6 + i4) - 6, 13, 13);
            graphics.drawImage(ai, ((i5 + i3) - 6) - (i * 13), (i6 - 6) + i4, 20);
            House.a(graphics, (i5 - i3) - 6, (i6 + i4) - 6, 13, 13);
            graphics.drawImage(ai, ((i5 - i3) - 6) - (i * 13), i4 + (i6 - 6), 20);
            House.a(graphics, (i5 - i3) - 6, (i6 - i3) - 6, 13, 13);
            graphics.drawImage(ai, ((i5 - i3) - 6) - (i * 13), (i6 - 6) - i3, 20);
        }
    }

    public static int random(int i) {
        return Math.abs(cf.nextInt() % i);
    }

    private static void i(Graphics graphics) {
        int i;
        Renderer3D.lookAt((float) aV, (float) aW, (float) cI, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
        Renderer3D.projected[0] = 0.0f;
        Renderer3D.projected[1] = -19.0f;
        Renderer3D.projected[2] = -80.0f;
        Renderer3D.projected[3] = 1.0f;
        Renderer3D.project(Renderer3D.projected);
        int i2 = (((int) Renderer3D.projected[1]) - ao) + 9;
        House.a(graphics, aW, false, true);
        House.l(graphics);
        House.a(graphics, i2);
        House.e(graphics);
        if (i2 - as < screenHeight) {
            graphics.drawImage(ah, (screenWidth >> 1) + (ap >> 1), (i2 - as) + 9, 17);
        }
        if (i2 < screenHeight) {
            int i3 = (screenWidth >> 1) - (ap >> 1);
            Image image = ae;
            int i4 = i3;
            Graphics graphics2 = graphics;
            while (true) {
                graphics2.drawImage(image, i4, i2, 20);
                i3 = i4 - ar;
                if (i3 <= 0 - ar) {
                    break;
                }
                image = af;
                i4 = i3;
                graphics2 = graphics;
            }
            i3 = screenWidth >> 1;
            i = ap >> 1;
            while (true) {
                i3 += i;
                if (i3 >= screenWidth) {
                    break;
                }
                graphics.drawImage(ag, i3, i2, 20);
                i = aq;
            }
        }
        i = ao + i2;
        if (i < screenHeight) {
            graphics.setColor(4602900);
            graphics.fillRect(0, i, screenWidth, 2);
            graphics.setColor(1972495);
            graphics.fillRect(0, i + 2, screenWidth, (screenHeight - 2) - i);
        }
    }

    public static int j(int i) {
        return i < 0 ? -ci[Math.abs(i)] : ci[i];
    }

    private static void j(Graphics graphics) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        House.a(graphics, ((cn + (cn >> 1)) + 14) + 4, (screenHeight - co) - 72, 11, 72);
        i4 = 0;
        while (i4 <= 3) {
            i5 = (bl - 1) * 2;
            if (i4 > ba) {
                i5 = (cg - bc >= 100 || i4 > ba + bb) ? 8 : ((bl - 1) * 2) + 1;
            } else if (ba == 1 && (cg / 500) % 2 == 0) {
                i5 = 9;
            }
            graphics.drawImage(X, (((cn + (cn >> 1)) + 14) - (i5 * 11)) + 4, (screenHeight - co) - (i4 * 12), 20);
            i4++;
        }
        if (bs > 0) {
            House.a(graphics, bt, screenWidth - cn, ((screenHeight - co) - 2) - 12, 5, false);
            House.a(graphics, (((screenWidth - cn) - 28) - 3) - 11, ((screenHeight - co) - 2) - 19, 11, 19);
            graphics.drawImage(V, (((screenWidth - cn) - 28) - 3) - 11, ((screenHeight - co) - 2) - 19, 20);
        }
        if (e == 5) {
            House.a(graphics, cn - 3, ((screenHeight - co) - 21) - (bg * 3), 20, 21);
            i5 = bl - 1;
            if (bk == 1 && (cg / 500) % 2 == 0) {
                i5 = 4;
            }
            graphics.drawImage(U, (cn - 3) - (i5 * 20), ((screenHeight - co) - 21) - (bg * 3), 20);
            for (i5 = 0; i5 < Math.min(bs, bg - 1); i5++) {
                House.a(graphics, cn, ((screenHeight - co) - 2) - ((i5 + 1) * 3), 14, 3);
                graphics.drawImage(V, cn + 2, ((((screenHeight - co) - 2) - 20) - ((i5 + 1) * 3)) - ((bl - 1) * 3), 20);
            }
            House.a(graphics, cn - 1, (screenHeight - co) - 2, 14, 2);
            graphics.drawImage(V, (cn + 2) - 1, (((screenHeight - co) - 2) - 28) - 1, 20);
            House.a(graphics, 0, 0, screenWidth, screenHeight);
            graphics.setColor(4725760);
            graphics.drawLine(cn - 1, ((screenHeight - co) - 2) - ((bg - 1) * 3), cn - 1, (screenHeight - co) - 2);
            graphics.drawLine(cn + 14, ((screenHeight - co) - 2) - ((bg - 1) * 3), cn + 14, (screenHeight - co) - 2);
            graphics.setColor(16776960);
            graphics.drawLine(cn, ((screenHeight - co) - 2) - ((bg - 1) * 3), cn, (screenHeight - co) - 2);
            graphics.drawLine((cn + 14) - 1, ((screenHeight - co) - 2) - ((bg - 1) * 3), (cn + 14) - 1, (screenHeight - co) - 2);
            graphics.setColor(2301460);
            graphics.fillRect(cn + 1, ((screenHeight - co) - 2) - ((bg - 1) * 3), 12, ((bg - bs) - 1) * 3);
            if (cg - dD < 100 && bk != 2) {
                graphics.setColor(16776960);
                graphics.fillRect(cn + 1, ((screenHeight - co) - 2) - (bs * 3), 12, 3);
            }
        } else {
            House.a(graphics, 0, 0, screenWidth, screenHeight);
            graphics.drawImage(W, cn - 9, (screenHeight - co) - 50, 20);
            House.b(graphics, bs, (cn - 9) + 23, ((screenHeight - co) - 50) + 37, 3, false);
        }
        House.a(graphics, 0, 0, screenWidth, screenHeight);
        if (bA > 0) {
            graphics.setColor(107, 26, 0);
            graphics.drawRect((((screenWidth >> 1) - (screenWidth >> 2)) - 2) - 1, co - 1, ((screenWidth >> 1) + 4) + 1, 9);
            graphics.setColor(252, 255, 0);
            graphics.drawRect(((screenWidth >> 1) - (screenWidth >> 2)) - 2, co, ((screenWidth >> 1) + 4) - 1, 7);
            if (bA > 5850) {
                graphics.setColor(255, 255, 255);
            }
            graphics.fillRect((screenWidth >> 1) - (screenWidth >> 2), co + 2, (bA * (screenWidth >> 1)) / 6000, 4);
            House.a(graphics, ((screenWidth >> 1) - (screenWidth >> 2)) - 11, co - 7, 22, 22);
            graphics.drawImage(al, (((screenWidth >> 1) - (screenWidth >> 2)) - 11) - (((cg / 80) % 4) * 22), co - 7, 20);
            if (aw[0] == 1 || aw[0] == 2) {
                i5 = aT + (((az[0] - aV) * 32) >> 8);
                i4 = aU - (((aA[0] - aW) * 32) >> 8);
                House.a(graphics, i5 - 22, i4 - 22, 44, 44);
                graphics.drawImage(am, (i5 - 22) - (((cg / 100) % 3) * 44), i4 - 22, 20);
            }
            if (bz > 0) {
                for (i5 = by; i5 > Math.max(0, by - bz); i5--) {
                    i4 = aT + (((bi[i5] - aV) * 32) >> 8);
                    i = aU - (((bj[i5] - aW) * 32) >> 8);
                    House.a(graphics, i4 - 22, i - 22, 44, 44);
                    graphics.drawImage(am, (i4 - 22) - ((((cg / 100) + i5) % 3) * 44), i - 22, 20);
                }
                if (bz > 1) {
                    House.a(graphics, ((screenWidth >> 1) + (screenWidth >> 2)) + 5, co, 7, 12);
                    graphics.drawImage(Z, (((screenWidth >> 1) + (screenWidth >> 2)) + 5) - 91, co - 2, 20);
                    i5 = bz > 9 ? 2 : 1;
                    House.a(graphics, bz, ((((screenWidth >> 1) + (screenWidth >> 2)) + 10) + 2) + (i5 * 7), co - 2, i5, false);
                }
            }
            return;
        } else if (bB != 0 && bA > -2000 && ((-bA) / 100) % 2 == 0) {
            House.a(graphics, bB, (screenWidth >> 1) + 14, co, 3, true);
        }
    }

    public static int k(int i) {
        return House.j(i - 90);
    }

    private static void k(Graphics graphics) {
        for (int i = 0; i < 8; i++) {
            if (bE[0][i] != 0) {
                House.b(graphics, (aT + (((bE[7][i] - aV) * 32) >> 8)) - 10, (aU - (((bE[8][i] - aW) * 32) >> 8)) - 14, bE[3][i], bE[6][i], bE[10][i]);
            }
        }
    }

    private static void l(Graphics graphics) {
        int i = (aW * 32) >> 8;
        for (int i2 = 0; i2 < 12; i2++) {
            int[] iArr = dF[i2];
            if (iArr[4] != 0) {
                int i3;
                int i4 = dF[i2][2];
                int i5 = (aU - i4) + i;
                int i6 = i5 + i4;
                if (i5 < 0) {
                    i5 = 0;
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                if (i5 + i6 > screenHeight) {
                    i6 = screenHeight - i5;
                }
                if (i5 > screenHeight + 32) {
                    dF[i2][4] = 0;
                } else {
                    graphics.setColor(House.a(iArr[5], df, true));
                    int i7 = iArr[1];
                    if (i5 < screenHeight) {
                        graphics.fillRect(dF[i2][0], i5, i7, i6);
                    }
                    if (i3 != 0) {
                        int roofType = iArr[6];
                        if (roofType == 1) {
                            graphics.fillRect(dF[i2][0] + (i7 >> 1), i5 - dF[i2][3], 3, dF[i2][3]);
                            if (((cg + i4) / 200) % 2 == 0) {
                                graphics.setColor(16711680);
                                graphics.fillRect(dF[i2][0] + (i7 >> 1), i5 - dF[i2][3], 3, 3);
                            }
                        } else if (roofType == 2) {
                            graphics.fillRect(dF[i2][0] + (i7 / 10), i5 - 16, i7 - (i7 / 5), 16);
                        }
                    }
                }
            }
        }
    }

    public static boolean handleModalAction(int i) {
        if (cg >= bX && cg - bX < 300) {
            return false;
        }
        House.H();
        boolean O = House.O();
        if (!O || bY == null) {
            if (i == 0) {
                i = 1;
            }
            bQ += i;
            if (bQ < 0) {
                bQ = 0;
            } else if (bQ >= bP && O) {
                House.N();
                return true;
            }
            return false;
        }
        bZ = Math.min(bZ + i, bY.length - 1);
        if (bZ < 0) {
            bQ = Math.max(0, bQ - 1);
            bZ = 0;
        } else if (i == 0) {
            House.N();
            return true;
        }
        return false;
    }

    private static void o(int i) {
        aY = cg;
        aX = bs > 1 ? aX + i : 512;
    }

    public static Font getGameFont() {
        return o;
    }

    private static void p(int i) {
        aP += i;
        if (aw[0] == 6) {
            aH += (i * 2) / 3;
            int i2 = 1664;
            if (bk == 1) {
                i2 = 1408;
            }
            if (aH >= i2) {
                aH = i2;
                aw[0] = 1;
            }
        }
        aK = (cP * House.k(((aP * 200) / cO) % 360)) >> 15;
        aM = aK >> 4;
        aL = (-((cQ * House.j(((aP * 200) / cO) % 360)) >> 15)) + ((aJ - cN) - aH);
        if (aw[0] == 1 && bk != 4) {
            aB[0] = ((aK - aN) * 256) / i;
            aC[0] = ((aL - aO) * 256) / i;
        }
        aN = aK;
        aO = aL;
    }

    private static void q(int i) {
        int i2;
        cR = 0;
        if (bk != 2) {
            bw = (bw + i) % 3600;
            cR = House.k(bw / 10);
            i2 = (bv * cR) >> 16;
        } else if (bu > 0) {
            i2 = bu - 1;
        } else if (bu < 0) {
            i2 = bu + 1;
        } else {
            i2 = 0;
        }
        bu = i2;
        br = (-(cR * bv)) / 10000;
        House.k(bu);
    }

    private static void r(int i) {
        Math.max(Math.abs(0), 80);
        int i2 = 1;
        if (aB[0] != 0) {
            i2 = aB[0] / Math.abs(aB[0]);
        }
        if (bw < 1800) {
            i2 = bw - ((i2 * 0) * 2);
        } else {
            i2 = ((i2 * 0) * 2) + bw;
        }
        bw = i2;
    }

    /**
     * Per-frame physics of the swinging/falling blocks (private static void s(int dt)).
     * Rewritten from bytecode; slot states in aw[]: 1/6 = idle on crane, 2 = falling (placed),
     * 3 = lowering, 4 = landed, 5 = falling (bonus), 7 = waiting to respawn.
     */
    private static void s(int dt) {
        cK = false;
        boolean moved = false;
        for (int slot = 0; slot < 5; slot++) {
            int state = aw[slot];
            if (state == 1 || state == 6) {
                ax[slot] = aM;
                az[slot] = aK;
                aA[slot] = aL;
                return;
            }
            if (state == 3) {
                aA[slot] = aA[slot] + (((cg - aE) * 15) >> 8);
                if (aA[slot] >= aJ - 512 && bk != 2) {
                    aw[0] = 6;
                    aH = 0;
                    dC = Math.min(4, bs - 1);
                    cO = aS[1];
                }
                aK = az[0];
                aL = aA[0];
                ax[0] = 888;
                return;
            }
            if (state == 7) {
                if (aE + cl[slot] < cg) {
                    aA[slot] = bj[by];
                    az[slot] = bi[by];
                    aw[slot] = 5;
                    aD[slot] = aA[slot];
                    ax[slot] = bh[by];
                    House.t(bs);
                    House.z(-1);
                }
                moved = true;
            } else if (state == 2 || state == 5) {
                int t = (cg - aE) - cl[slot];
                if (ax[slot] < ay[slot] && ax[slot] != 999) {
                    ax[slot] = Math.min(ay[slot], ax[slot] + (((ay[slot] - ax[slot]) * t) / 500));
                } else if (ax[slot] > ay[slot] && ax[slot] != 999) {
                    ax[slot] = Math.max(ay[slot], ax[slot] - ((t * (ax[slot] - ay[slot])) / 500));
                }
                if (cG[slot] < cH[slot] && cG[slot] != 999) {
                    cG[slot] = Math.min(cH[slot], cG[slot] + (((cH[slot] - cG[slot]) * t) / 500));
                } else if (cG[slot] > cH[slot] && cG[slot] != 999) {
                    cG[slot] = Math.max(cH[slot], cG[slot] - ((t * (cG[slot] - cH[slot])) / 500));
                }
                int gravityDivisor = 200;
                if (bk == 1) {
                    gravityDivisor = 400;
                }
                int previousY = aA[slot];
                aA[slot] = (aD[slot] + ((aC[slot] * t) / 256)) - ((t * t) / gravityDivisor);
                if (aA[slot] < previousY - 256) {
                    aA[slot] = previousY - 256;
                }
                az[slot] = az[slot] + ((aB[slot] * dt) / 512);
                if (aA[slot] < aW - (be >> 1)) {
                    cm[0][slot] = aT + (((az[slot] - aV) * 32) >> 8);
                    cm[1][slot] = cg;
                    aw[slot] = 4;
                    if (state == 2) {
                        if (bA > 0) {
                            House.G();
                        }
                        House.A(1);
                        cK = true;
                    }
                }
                if (state == 2 && House.F()) {
                    if (bk == 4) {
                        bk = 0;
                    } else if (bs == bg - 1 && e == 5 && bk != 1) {
                        bk = 1;
                        if (bA > 0) {
                            House.G();
                        }
                        if (bD && bt < l[bl - 1]) {
                            bD = false;
                        }
                        aA[0] = aJ;
                        aw[0] = 6;
                        aH = 0;
                        dC = Math.min(4, bs - 1);
                        cO = aS[1];
                    } else if (bk == 1) {
                        bk = 2;
                        aE = cg;
                    }
                }
                moved = true;
            }
        }
        if (bk == 1 || bk == 2) {
            aK = az[0];
            aL = aA[0];
            if (bk != 2) {
                ax[0] = 888;
            }
        }
        if (aw[0] == 4 && aE < cg - 400 && !moved) {
            aH = 1664;
            if (bk != 2) {
                aw[0] = 1;
                az[0] = aK;
                aA[0] = aL;
                cH[0] = 0;
                cG[0] = 0;
                dC = Math.max(0, Math.min(4, bs - 1));
            }
        }
    }

    private static void t(int i) {
        int abs = Math.abs(aF[i % 20]);
        abs = abs < 25 ? 4 : abs < 50 ? 3 : abs < 80 ? 2 : 1;
        int i2 = abs >> 1;
        int i3 = 0;
        while (i3 < 8) {
            if (bE[5][i3] != i || bE[0][i3] == 0) {
                abs = i2;
            } else {
                bE[1][i3] = bE[7][i3];
                bE[2][i3] = bE[8][i3];
                bE[0][i3] = 3;
                bE[5][i3] = ((bE[1][i3] < 0 ? -1 : 1) * (bE[4][i3] - cg)) / 500;
                bE[4][i3] = cg;
                abs = i2 - 1;
            }
            i3++;
            i2 = abs;
        }
        i3 = 0;
        while (i3 < 8 && i2 > 0) {
            if (bE[0][i3] == 0) {
                bE[4][i3] = cg;
                bE[0][i3] = 4;
                bE[5][i3] = House.random(4);
                abs = House.random(2);
                if (abs == 0) {
                    abs = -1;
                }
                bE[6][i3] = abs;
                abs = Math.min(4, bs - 1) - (bs - i);
                bE[1][i3] = bi[abs];
                bE[2][i3] = bj[abs];
                abs = i2 - 1;
            } else {
                abs = i2;
            }
            i3++;
            i2 = abs;
        }
    }

    private static void u(int i) {
        int i2 = 2;
        du = Math.min(du + i, dq);
        switch (dp) {
            case 0:
                boolean z;
                if (cW && !cZ && aW >= 0 && aW <= 3840) {
                    cZ = true;
                    House.B();
                    House.v(1);
                    i2 = 1;
                } else if (!cX || da || aW < 10240 || aW > 12800) {
                    z = false;
                    if (z) {
                        df = 1024;
                        dj = 0;
                        dp = 1;
                        dq = dr;
                        du = 0;
                        return;
                    }
                    return;
                } else {
                    da = true;
                    House.B();
                    House.v(2);
                }
                dv = i2;
                dd = true;
                z = true;
                if (z) {
                    df = 1024;
                    dj = 0;
                    dp = 1;
                    dq = dr;
                    du = 0;
                    return;
                }
                return;
            case 1:
                df = House.a(0, di, 1024, dh, Math.min(du, di));
                db[0][1] = db[0][0] * House.a(0, dq, 0, 512, Math.min(du, dq));
                db[1][1] = House.a(0, dq, 0, 512, Math.min(du, dq)) * db[1][0];
                if (du == dq) {
                    dp = 2;
                    dq = ds;
                    du = 0;
                    break;
                }
                break;
            case 2:
                int i3;
                int i4;
                int i5;
                int i6;
                int[] iArr;
                int i7;
                int min;
                if (du < dq / 2) {
                    db[0][1] = db[0][0] * House.a(0, dq / 2, 512, 1024, Math.min(du, dq / 2) + 512);
                    int[] iArr2 = db[1];
                    i3 = db[1][0];
                    i4 = dq / 2;
                    i5 = 0;
                    i6 = i3;
                    iArr = iArr2;
                    i3 = 512;
                    i7 = i4;
                    i4 = 1024;
                    min = Math.min(du, dq / 2);
                    i2 = 512;
                } else {
                    db[0][1] = db[0][0] * House.a(dq / 2, dq, 1024, 512, Math.min(du - (dq / 2), dq / 2) + 1024);
                    int[] iArr3 = db[1];
                    i7 = db[1][0];
                    i3 = dq / 2;
                    i4 = dq;
                    i6 = i7;
                    iArr = iArr3;
                    i7 = i4;
                    i5 = i3;
                    i4 = 512;
                    i3 = 1024;
                    min = Math.min(du - (dq / 2), dq / 2);
                    i2 = 1024;
                }
                iArr[1] = House.a(i5, i7, i3, i4, i2 + min) * i6;
                if (dv == 1) {
                    dm = Math.min(dm + i, dn);
                    if (House.random(100) == 7) {
                        dk = 512;
                        dl = 0;
                        dm = 0;
                        dj = dk;
                    }
                    if (dj > 0) {
                        dj = House.a(dj, dm, dn);
                    }
                }
                if (du == dq) {
                    dp = 3;
                    dg = df;
                    dq = dt;
                    du = 0;
                    break;
                }
                break;
            case 3:
                df = House.a(0, di, dg, 1024, Math.min(du, di));
                db[0][1] = db[0][0] * House.a(0, dq - 1000, 512, 0, Math.min(du, dq - 1000));
                db[1][1] = House.a(0, dq - 1000, 512, 0, Math.min(du, dq - 1000)) * db[1][0];
                if (du == dq) {
                    dp = 0;
                    House.B();
                    return;
                }
                break;
            default:
                return;
        }
        House.w(i);
    }

    private static void v(int i) {
        int[][] iArr;
        int i2;
        if (i == 1) {
            iArr = cV;
            i2 = 0;
        } else {
            iArr = cV;
            i2 = 1;
        }
        int[] iArr2 = iArr[i2];
        dr = iArr2[0] + House.random(iArr2[1] + 1);
        ds = iArr2[2] + House.random(iArr2[3] + 1);
        dt = iArr2[4] + House.random(iArr2[5] + 1);
        dg = df;
        dh = iArr2[6];
        di = iArr2[7];
        int i3 = iArr2[8];
        int i4 = House.random(2) - 1;
        House.a(0, 300 - i3, 0, i4 * iArr2[10], iArr2[13], iArr2[11], iArr2[11]);
        House.a(1, i3, 0, i4 * iArr2[15], iArr2[18], iArr2[16], iArr2[16]);
    }

    private boolean v() {
        b.stopAll();
        if (!House.updateLoadingProgress(0)) {
            return false;
        }
        int i;
        DataInputStream b = Resources.openStream(89);
        try {
            this.r = b.readUnsignedByte();
            bI = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.r, 3});
            bH = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.r, 2});
            this.s = b.readUnsignedByte();
            bJ = new int[this.s];
            for (i = 0; i < this.s; i++) {
                bJ[i] = b.readInt();
            }
            for (i = 0; i < this.r; i++) {
                int readUnsignedByte = b.readUnsignedByte();
                int readUnsignedByte2 = b.readUnsignedByte();
                int readUnsignedShort = b.readUnsignedShort();
                int readUnsignedShort2 = b.readUnsignedShort();
                bI[i][0] = (byte) ((screenWidth * readUnsignedByte) / 176);
                bI[i][1] = (byte) Math.max(1, (((readUnsignedByte + readUnsignedByte2) * screenWidth) / 176) - bI[i][0]);
                bH[i][0] = (short) (((readUnsignedShort << 1) * 32) / 32);
                bH[i][1] = (short) Math.max(1, ((((readUnsignedShort + readUnsignedShort2) << 1) * 32) / 32) - bH[i][0]);
                bI[i][2] = (byte) b.readUnsignedByte();
            }
            b.close();
        } catch (Exception e) {
        }
        if (!House.updateLoadingProgress(10)) {
            return false;
        }
        if (cy == null) {
            cy = Renderer3D.getModel(bl + 9, -2147483558, true);
            if (!House.updateLoadingProgress(12)) {
                return false;
            }
            cB = Renderer3D.getModel(bl + 19, -2147483558, true);
            if (!House.updateLoadingProgress(13)) {
                return false;
            }
            cz = Renderer3D.getModel(bl + 29, -2147483558, true);
            if (!House.updateLoadingProgress(14)) {
                return false;
            }
            cA = Renderer3D.getModel(bl + 39, -2147483558, true);
            if (!House.updateLoadingProgress(16)) {
                return false;
            }
            cC = Renderer3D.getModel(9, -2147483558, true);
            if (!House.updateLoadingProgress(18)) {
                return false;
            }
            cD = Renderer3D.getModel(8, -2147483558, true);
            if (!House.updateLoadingProgress(19)) {
                return false;
            }
            cE = Renderer3D.getModel(7, -2147483558, true);
        }
        if (!House.updateLoadingProgress(20)) {
            return false;
        }
        Resources.getImage(32);
        if (!House.updateLoadingProgress(30)) {
            return false;
        }
        U = Resources.getImage(14);
        Y = Resources.getImage(15);
        Z = Resources.getImage(16);
        V = Resources.getImage(18);
        X = Resources.getImage(19);
        W = Resources.getImage(20);
        if (!House.updateLoadingProgress(40)) {
            return false;
        }
        try {
            ae = Resources.getImage(33);
            af = Resources.getImage(34);
            ag = Resources.getImage(35);
            ah = Resources.getImage(36);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!House.updateLoadingProgress(50)) {
            return false;
        }
        ce = new Image[28];
        int imageIndex = 0;
        while (imageIndex < 28) {
            int[] iArr;
            int i2;
            ce[imageIndex] = Resources.getImage(dB[imageIndex]);
            if (ce[imageIndex] != null) {
                i = (Math.max(ce[imageIndex].getHeight(), ce[imageIndex].getWidth()) * 256) / 32;
                iArr = dy;
                i2 = imageIndex;
            } else {
                i2 = imageIndex;
                iArr = dy;
                i = 1;
            }
            iArr[i2] = i;
            if (imageIndex == 5 || imageIndex == 11) {
                int[] iArr2 = dy;
                iArr2[imageIndex] = iArr2[imageIndex] / 2;
            }
            imageIndex++;
        }
        if (!House.updateLoadingProgress(60)) {
            return false;
        }
        ai = Resources.getImage(37);
        if (!House.updateLoadingProgress(70)) {
            return false;
        }
        ao = ae.getHeight();
        ap = ae.getWidth();
        aq = ag.getWidth();
        ar = af.getWidth();
        as = ah.getWidth();
        ak = Resources.getImage(41);
        al = Resources.getImage(38);
        am = Resources.getImage(39);
        if (!House.updateLoadingProgress(80)) {
            return false;
        }
        an = Resources.getImage(40);
        System.gc();
        resetTiming();
        return House.updateLoadingProgress(100);
    }

    private static void w() {
        cy = null;
        cz = null;
        cB = null;
        cA = null;
        cC = null;
        cD = null;
        cE = null;
        bI = (byte[][]) null;
        bH = (short[][]) null;
        bJ = null;
        U = null;
        Y = null;
        Z = null;
        V = null;
        X = null;
        W = null;
        ae = null;
        af = null;
        ag = null;
        ah = null;
        ai = null;
        System.gc();
        an = null;
    }

    private static void w(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 2) {
            int[] iArr = db[i2];
            iArr[2] = iArr[2] + ((((iArr[1] * iArr[4]) / 2) / H) * i);
            for (int i4 = 0; i4 < iArr[0]; i4++) {
                int[] iArr3 = dc[i4 + i3];
                if (iArr3[0] == 1) {
                    int i5 = ((aW * 256) >> 8) + 384;
                    iArr3[1] = iArr3[1] + (iArr3[5] * i);
                    iArr3[2] = iArr3[2] + (iArr3[6] * i);
                    switch (dv) {
                        case 2:
                            iArr3[4] = iArr3[4] + ((((3 - iArr3[3]) * 90) + 90) * i);
                            iArr3[4] = iArr3[4] % 368640;
                            iArr3[1] = iArr3[1] + (((iArr3[3] * 64) * House.j(iArr3[4] >> 10)) >> 15);
                            break;
                    }
                    if (iArr3[2] > H) {
                        iArr3[0] = 0;
                    } else if (i5 < 1024 && iArr3[2] > (i5 * H) / 1024) {
                        iArr3[0] = 0;
                    } else if (iArr3[1] < 0) {
                        iArr3[1] = G;
                    } else if (iArr3[1] > G) {
                        iArr3[1] = 0;
                    }
                } else if (iArr[2] >= 1024) {
                    int i5 = iArr[5] + 1;
                    int i7 = iArr[6] + 1;
                    iArr3[0] = 1;
                    iArr3[1] = House.random(G);
                    iArr3[2] = 0;
                    iArr3[3] = House.random(4);
                    iArr3[4] = House.random(368640);
                    iArr3[5] = ((iArr[3] + House.random(i5)) - (i5 / 2)) / (4 - iArr3[3]);
                    iArr3[6] = ((iArr[4] + House.random(i7)) - (i7 / 2)) / (4 - iArr3[3]);
                    iArr[2] = iArr[2] - 1024;
                }
            }
            i2++;
            i3 += iArr[0];
        }
    }

    private void x() {
        int i;
        aT = screenWidth >> 1;
        aU = (screenHeight >> 1) + 0;
        bd = (screenWidth * 256) / 32;
        be = (screenHeight * 256) / 32;
        cw = Resources.getImage(9);
        aj = Resources.getImage(0);
        if (aa == null) {
            aa = Resources.getImage(12);
        }
        if (ab == null) {
            ab = Resources.getImage(13);
        }
        if (ac == null) {
            ac = Resources.getImage(69);
        }
        if (ad == null) {
            ad = Resources.getImage(70);
        }
        if (ce == null) {
            ce = new Image[28];
        }
        if (ce[3] == null) {
            ce[3] = Resources.getImage(45);
        }
        cp = -1;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i <= 6; i++) {
            bG[i] = i2;
            bF[i] = i3;
            i2 += i + 5;
            i3 += 5 - i;
        }
        cn = screenWidth / 20;
        co = screenHeight / 15;
        this.t = 24;
        this.t = 13;
        aG = (short[][]) Array.newInstance(Short.TYPE, new int[]{2, this.t});
        for (i = 0; i < this.t; i++) {
            short s = (short) ((i / 5) * 45);
            aG[0][i] = (short) ((i % 5) * 45);
            aG[1][i] = s;
        }
        cI = 128;
        for (i = screenHeight; i > 48; i = (int) (Renderer3D.projected[1] - (0.5f * ((float) screenHeight)))) {
            cI += 100;
            Renderer3D.lookAt(0.0f, 0.0f, (float) cI, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
            Renderer3D.projected[0] = 0.0f;
            Renderer3D.projected[1] = -384.0f;
            Renderer3D.projected[2] = 128.0f;
            Renderer3D.projected[3] = 1.0f;
            Renderer3D.project(Renderer3D.projected);
        }
        cd = Math.max(be, 2048);
        l = new int[]{70, 250, 550, 1000};
        onLoad();
        if (House.a(3)) {
            q = true;
        }
        if (House.a(1)) {
            p = true;
        }
    }

    private static void x(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i3 <= 28) {
            if (cp >= dz[0][i3] && cp < dz[1][i3] && ((dx[i3] > 0 || dx[i3] == -1) && House.random(100) < dz[2][i3])) {
                i2 = i3;
                break;
            }
            i3++;
        }
        if (i2 == 0) {
            dA[i][0] = i2;
            dA[i][3] = (cg + 1000) + House.random(2500);
            return;
        }
        int[] iArr;
        int i4;
        int[] iArr2 = dx;
        iArr2[i2] = iArr2[i2] - 1;
        if (dz[3][i2] == 0 || House.random(2) == 0) {
            dA[i][1] = House.random(bd);
            iArr = dA[i];
            i4 = dy[i2 - 1] + ((aW * 3) / 4);
            i3 = 512;
        } else {
            if (dz[3][i2] > 0) {
                i3 = 0 - dy[i2 - 1];
            } else {
                i3 = bd + dy[i2 - 1];
            }
            dA[i][1] = i3;
            iArr = dA[i];
            i4 = (((aW * 3) / 4) - (be >> 1)) - 512;
            i3 = 1024;
        }
        iArr[2] = House.random(i3) + i4;
        dA[i][0] = i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /**
     * Eases the camera height aW towards its target aX and applies the landing shake
     * (private static void y()). Reconstructed from bytecode (decompiler failure).
     */
    private static void y() {
        if (aW < aX) {
            aW = Math.min(aX, aX + ((((cg - aY) - 500) * 256) / 500));
            if (bk != 1 && bk != 4) {
                aJ = aW + 1792 + 128;
            }
        } else if (aW > aX) {
            aW = Math.max(aX, aX - ((((cg - aY) - 500) * 256) / 500));
            dH = 0;
            if (bk != 1 && bk != 4) {
                aJ = aW + 1792 + 128;
            }
        }
        if (cg - cM < 800) {
            aW = (aW + 32) - House.random(64);
        }
    }

    private static void y(int i) {
        int i2;
        if (bk == 1) {
            i2 = bt + i;
        } else if (i > 0) {
            if (bA > 0) {
                bB += bz * (((bs / 10) * 2) + 2);
            }
            i2 = bt + ((bs / 10) + i);
        } else {
            i2 = bt - ((bs / 10) - i);
        }
        bt = i2;
    }

    private static void z() {
        int i = bn + br;
        int max = Math.max(0, bs - 5);
        int i2 = ((max - 1) * 256) + 128;
        int i3 = 0;
        int i4 = 0;
        while (max < bs) {
            int i5;
            int i6;
            int[] iArr;
            byte b = aF[max % 20];
            if (cR > 0) {
                if (b < (byte) 0) {
                    i5 = (dE * b) * cR;
                    i6 = 29491200;
                } else {
                    i6 = -((dE * b) * cR);
                    i5 = i6;
                    i6 = 58982400;
                }
            } else if (b > (byte) 0) {
                i5 = -((dE * b) * cR);
                i6 = 29491200;
            } else {
                i6 = (dE * b) * cR;
                i5 = i6;
                i6 = 58982400;
            }
            i6 = i5 / i6;
            if (max == bs - 1 && max != bg - 1) {
                i5 = cg - dD;
                if (i5 < 100) {
                    i3 = ((i3 / 8) + (b / 6)) + ((i5 * b) / 600);
                } else if (i5 < 500) {
                    i3 = ((i3 / 8) + (b / 6)) + (((400 - (i5 - 100)) * b) / 2400);
                    cS = i3;
                } else if (i5 < 800) {
                    i3 = cS - (((cS - i3) * (i5 - 500)) / 300);
                }
            }
            i3 += i6;
            int i7 = cR > 0 ? i3 >> 1 : -(i3 >> 1);
            if (max == 0) {
                i6 = -999;
                iArr = bh;
                i5 = 0;
            } else if (bk == 2 && max == bs - 1 && bq != 0) {
                iArr = bh;
                i5 = 4;
                i6 = -888;
            } else {
                i5 = i4;
                iArr = bh;
                i6 = i3;
            }
            iArr[i5] = i6;
            i += (i3 * 2) + b;
            bi[i4] = i;
            bj[i4] = (i2 + i7) + 256;
            i2 = bj[i4];
            i4++;
            max++;
        }
    }

    private static void z(int i) {
        int abs;
        int i2;
        int i3 = 128;
        if (i < 0) {
            abs = Math.abs(aF[(bs - 1) % 20]);
            abs = abs < 25 ? -4 : abs < 50 ? -3 : abs < 80 ? -2 : -1;
            House.y(abs);
        }
        if (i > 0 && bs > 4) {
            bn += aF[bx % 20];
        } else if (i < 0) {
            bm -= aF[(bs - 1) % 20];
        }
        bs += i;
        bx = Math.max(0, bs - 5);
        by = Math.min(4, bs - 1);
        if (i < 0) {
            bn -= aF[bx % 20];
        }
        dE = 0;
        for (abs = bx; abs < bs; abs++) {
            dE += Math.abs(aF[abs % 20]);
        }
        dE /= 5;
        dE = Math.min((bs * dE) / 20, 100);
        bv = Math.min((bs / 2) + (Math.abs(bm) / 20), (bs * ((bs / 2) + (Math.abs(bm) / 20))) / 6);
        if (e == 5) {
            i2 = Math.max(1, cO);
            abs = bl;
            cO = aS[abs] - ((bs * (aS[abs] - aS[abs + 2])) / k[bl - 1]);
            cP = Math.min(aQ[abs], aQ[1] + ((bs * (aQ[abs] - aQ[1])) / (bg >> 1)));
            cQ = Math.min(aR[abs], (((aR[abs] - aR[1]) * bs) / (bg >> 1)) + aR[1]);
            abs = (bs * 256) / 100;
        } else {
            i2 = Math.max(1, cO);
            cP = Math.min(aQ[bl], aQ[0] + ((bs * (aQ[bl] - aQ[0])) / 60));
            cQ = Math.min(aR[bl], aR[0] + ((bs * (aR[bl] - aR[0])) / 60));
            if (bs < 100) {
                cN = -Math.min(128, (bs * 256) / 200);
                cO = Math.max(aS[bl + 2], aS[0] - ((bs * (aS[0] - aS[bl + 2])) / 100));
                aP = (aP * cO) / Math.max(1, i2);
                if (bk != 1 && bk != 4) {
                    House.o(i * 256);
                    return;
                }
            }
            cO = aS[bl + 1] - (((bs - 100) * 100) / 150);
            i3 = 256;
            abs = (((bs - 100) * 256) / 300) + 128;
        }
        cN = -Math.min(i3, abs);
        aP = (aP * cO) / Math.max(1, i2);
        if (bk != 1 && bk != 4) {
            House.o(i * 256);
        }
    }

    protected final void onInit() {
        House.L();
        String[] strArr = new String[]{this.canvas.getKeyName(52), this.canvas.getKeyName(54), this.canvas.getKeyName(50), this.canvas.getKeyName(56), this.canvas.getKeyName(53)};
    }

    public final void update(int i, int i2) {
        if (this.O == 0) {
            int i3 = 0;
            if (f == 7) {
                if (q || p) {
                    cy = null;
                    cz = null;
                    cB = null;
                    cA = null;
                    cC = null;
                    cD = null;
                    cE = null;
                }
                if (aZ == 2) {
                    House.updateLoadingProgress(5);
                    if (cJ) {
                        cJ = false;
                        if (!cx) {
                            CityMode.init();
                            House.updateLoadingProgress(10);
                            cx = true;
                        }
                        if (!CityMode.d) {
                            CityMode.enterCity();
                        }
                    }
                    House.w();
                    House.updateLoadingProgress(15);
                    if (CityMode.loadAssets()) {
                        resetTiming();
                        this.sound.play(-2147483566, -1);
                        f = aZ;
                        h = false;
                        g = false;
                    } else {
                        return;
                    }
                }
                if (aZ != 2) {
                CityMode.unloadAssets();
                if (v()) {
                    cp = -1;
                    resetTiming();
                    if (!(h && g)) {
                        this.sound.play(-2147483567, -1);
                    }
                    if (e == 6) {
                        if (g) {
                            if (!bf) {
                                b.play(-2147483564, 1);
                            }
                            if (bf) {
                                b.play(-2147483563, 1);
                            }
                        }
                        if (cj[1] == 0) {
                            House.showPrompt(Resources.getString(34, new String[]{"3"}), null, null);
                            i3 = 4;
                        } else {
                            i3 = aZ;
                        }
                    } else {
                        if (h) {
                            SoundPlayer oVar;
                            if (bq == 0) {
                                oVar = b;
                                i3 = -2147483565;
                            } else if (bq == 2) {
                                oVar = b;
                                i3 = -2147483563;
                            } else {
                                oVar = b;
                                i3 = -2147483564;
                            }
                            oVar.play(i3, 1);
                        }
                        if (cj[0] == 0) {
                            House.showPrompt(Resources.getString(55, new String[]{"3"}), null, null);
                            i3 = 4;
                            bk = 4;
                        } else {
                            i3 = aZ;
                        }
                    }
                    f = i3;
                } else {
                    return;
                }
                }
            }
            i3 = i > 150 ? 150 : i;
            if (f == 2) {
                cg += i3;
                CityMode.update(i3, i);
                if (CityMode.consumeHighScoreFlag() && !CityMode.isCheatActive()) {
                    this.highScores.submitScore(0, new int[]{CityMode.getPopulation(), 0}, "-");
                }
                if (CityMode.isCityModeActive()) {
                    boolean z;
                    bl = CityMode.getBuildingTowerType();
                    if (bl > 4) {
                        bl -= 4;
                        z = true;
                    } else {
                        z = false;
                    }
                    bD = z;
                    bg = k[bl - 1];
                    House.J();
                    f = 7;
                    aZ = 1;
                    return;
                }
                return;
            }
            I();
            if (bk == 2 && cg - aE > 2000) {
                if (e == 5) {
                    h = true;
                    b.stopAll();
                    if (bq == 0) {
                        b.play(-2147483565, 1);
                        House.showPrompt(Resources.getString(56), null, null);
                        f = 5;
                    } else if (bq == 2) {
                        b.play(-2147483563, 1);
                        House.showPrompt(Resources.getString(57), null, null);
                        f = 5;
                    } else {
                        b.play(-2147483564, 1);
                        a(false);
                    }
                } else {
                    g = true;
                    b.stopAll();
                    a(true);
                    if (!bf) {
                        b.play(-2147483564, 1);
                    }
                    if (bf) {
                        b.play(-2147483563, 1);
                    }
                }
                bk = 3;
            }
            this.cL = i3 + this.cL;
            if (this.cL >= 25) {
                cg += this.cL;
                if (f == 4 || f == 6 || f == 5 || f == 8) {
                    this.cL = 0;
                    return;
                }
                House.y();
                House.u(this.cL);
                House.D();
                House.p(this.cL);
                House.s(this.cL);
                House.q(this.cL);
                House.z();
                House.A();
                if (bA > 0) {
                    bA = (bA - this.cL) - (((bz - 1) * this.cL) / 6);
                    if (bA <= 0) {
                        House.G();
                    }
                } else if (bA > -2000) {
                    bA -= this.cL;
                }
                this.cL = 0;
            }
        }
    }

    public final void commandAction(Command command) {
        if (command == this.P) {
            if (f == 7) {
                n = true;
            }
            this.O = 2;
            this.R = false;
        }
    }

    protected final void paintLoading(Graphics graphics) {
        if (this.N) {
            Image image = null;
            if (this.I == 0) {
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                graphics.setColor(-1);
                graphics.fillRect(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                if (this.L != null) {
                    image = this.L;
                }
                this.N = false;
            }
            if (this.I == 1) {
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                graphics.setColor(10143978);
                graphics.fillRect(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                if (ac == null) {
                    ac = Resources.getImage(69);
                }
                if (ad == null) {
                    ad = Resources.getImage(70);
                }
                if (aa == null) {
                    aa = Resources.getImage(12);
                }
                if (ab == null) {
                    ab = Resources.getImage(13);
                }
                if (ce == null) {
                    ce = new Image[28];
                    if (ce[3] == null) {
                        ce[3] = Resources.getImage(45);
                    }
                }
                House.a(graphics, screenWidth >> 1, 30, ce[3].getWidth(), ce[3].getHeight());
                graphics.drawImage(ce[3], screenWidth >> 1, 30, 20);
                House.a(graphics, 0, 0, screenWidth, screenHeight);
                House.a(graphics, 10, 40, ad.getWidth(), ad.getHeight());
                graphics.drawImage(ad, 10, 40, 20);
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.a(graphics, screenWidth - 80, 60, ac.getWidth(), ac.getHeight());
                graphics.drawImage(ac, screenWidth - 80, 60, 20);
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.b(graphics, 20, screenHeight - 40, 4, 1, 1);
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.b(graphics, 80, screenHeight - 50, 1, -1, 0);
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.b(graphics, 20, 50, 2, -1, 0);
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.a(graphics, screenWidth - 60, screenHeight - 40, ad.getWidth(), ad.getHeight());
                graphics.drawImage(ad, screenWidth - 60, screenHeight - 40, 20);
                House.a(graphics, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                if (this.M != null) {
                    image = this.M;
                }
            }
            this.N = false;
            if (image != null) {
                graphics.drawImage(image, GameMIDlet.screenWidth >> 1, GameMIDlet.screenHeight >> 1, 3);
            }
            this.N = false;
        }
    }

    public final void paint(Graphics graphics, boolean z) {
        if (this.O == 0) {
            if (f == 7) {
                House.g(graphics);
                return;
            }
            graphics.setFont(o);
            if (f == 2) {
                if (CityMode.assetsLoaded) {
                    CityMode.paint(graphics);
                } else if (cw != null) {
                    House.g(graphics);
                } else {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, screenWidth, screenHeight);
                }
                return;
            }
            if (bI == null || bH == null || dF == null || dA == null) {
                // Tower resources are released while loading city mode: show loading bar instead
                if (cw != null) {
                    House.g(graphics);
                } else {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, screenWidth, screenHeight);
                }
                return;
            }
            House.i(graphics);
            House.b(graphics, false);
            if (bk == 1) {
                ax[0] = 888;
            }
            if (bk == 4) {
                ax[0] = 999;
            }
            f(graphics);
            House.b(graphics, true);
            House.h(graphics);
            House.k(graphics);
            if (bs > 5) {
                for (int i = 0; i < 5; i++) {
                    if (cg - cm[1][i] < 300) {
                        int i2 = cm[0][i];
                        int i3 = (cg - cm[1][i]) / 100;
                        House.a(graphics, i2 - 11, screenHeight - 34, 23, 34);
                        graphics.drawImage(an, (i2 - 11) - (i3 * 23), screenHeight - 34, 20);
                    }
                }
            }
            House.j(graphics);
            if (f == 4 || f == 6 || f == 5 || f == 8) {
                House.paintTutorialModal(graphics);
            }
        }
    }

    protected final void onPause() {
        n = true;
        this.O = 2;
    }

    public final void b(int i) {
        int i2 = 0;
        if (i == 3) {
            i2 = 8;
        } else if (i == 1) {
            i2 = 9;
            saveSettings();
        }
        Storage.setMenuFlag(i2, 1);
        Storage.setSetting(10, 0);
        saveSettings();
    }

    public final void keyPressed(int i, int i2) {
        if (f == 2) {
            CityMode.handleKeyPressed(i, i2);
            return;
        }
        if (i == 35 && CityMode.isCheatActive()) {
            bk = 2;
        }
        if (this.I != -1) {
            this.K = true;
        } else if (f != 7) {
            int e = House.e(i, i2);
            if (e == 0) {
                at = true;
            } else if (e == 1) {
                au = true;
            } else if (e == 2) {
                av = true;
            }
        }
    }

    protected final void paintBackground(Graphics graphics) {
        int i;
        int i2;
        int i3;
        if (ce[3] == null) {
            ce[3] = Resources.getImage(45);
        }
        if (cv == null) {
            cv = new int[20];
            for (i = 0; i < cv.length / 5; i++) {
                cv[(i * 5) + 1] = be;
            }
        }
        House.a(graphics, (cr * 32) >> 8, true, false);
        for (i = 0; i < cu.length / 5; i++) {
            if (cu[(i * 5) + 4] == 0) {
                i2 = (cu[(i * 5) + 0] * 32) >> 8;
                i3 = (cu[(i * 5) + 1] * 32) >> 8;
                House.a(graphics, i2, i3, ad.getWidth(), ad.getHeight());
                graphics.drawImage(ad, i2, i3, 20);
            }
        }
        for (i = 0; i < cu.length / 5; i++) {
            if (cu[(i * 5) + 4] == 1) {
                i2 = (cu[(i * 5) + 0] * 32) >> 8;
                i3 = (cu[(i * 5) + 1] * 32) >> 8;
                House.a(graphics, i2, i3, ac.getWidth(), ac.getHeight());
                graphics.drawImage(ac, i2, i3, 20);
            }
        }
        for (int i4 = 0; i4 < cs.length / 6; i4++) {
            i2 = (cs[(i4 * 6) + 0] * 32) >> 8;
            i3 = (cs[(i4 * 6) + 1] * 32) >> 8;
            int i5 = cs[(i4 * 6) + 2] + 1;
            if (i5 > 5) {
                i5 = 5 - (i5 - 5);
            }
            House.b(graphics, i2, i3, i5, cs[(i4 * 6) + 3] < 0 ? -1 : 1, cs[(i4 * 6) + 5]);
        }
        for (i = 0; i < cv.length / 5; i++) {
            i2 = (cv[(i * 5) + 0] * 32) >> 8;
            i3 = (screenHeight - (cv[(i * 5) + 1] * 32)) >> 8;
            House.a(graphics, i2, i3, ce[3].getWidth(), ce[3].getHeight());
            graphics.drawImage(ce[3], i2, i3, 20);
            House.a(graphics, 0, 0, screenWidth, screenHeight);
        }
    }

    protected final void saveGame() {
        saveSettings();
        if (e == 2) {
            if (!CityMode.isCheatActive()) {
                this.highScores.submitScore(1, new int[]{bt, bs}, MenuController.getPlayerName());
            }
        } else if (e == 5) {
            CityMode.saveState();
            House.saveTowerCityMode();
        } else {
            House.saveTowerQuickMode();
        }
    }

    public final void c(int i) {
        int i2 = 9;
        if (i == 3) {
            Storage.setMenuFlag(8, 0);
        } else {
            if (i == 1) {
                Storage.setMenuFlag(9, 0);
                i2 = 10;
            }
            saveSettings();
        }
        Storage.setSetting(i2, 1);
        saveSettings();
    }

    protected final void saveSettings() {
        if (!CityMode.isCheatActive()) {
            try {
                DataOutputStream b = Storage.openWrite("towermode");
                for (int i = 0; i < 6; i++) {
                    b.writeInt(cj[i]);
                }
                b.writeBoolean(House.a(1));
                b.writeBoolean(House.a(3));
                Storage.close();
            } catch (Exception e) {
            }
        }
    }

    protected final boolean loadStep(int i) {
        boolean z = false;
        if (this.I == -1) {
            this.I = 1;
            this.J = i + 3000;
            this.N = true;
        }
        if (this.I != 1) {
            return true;
        }
        if (this.J < i || this.K) {
            x();
            this.I = -1;
            this.K = false;
        } else {
            z = true;
        }
        if (this.R && !this.S) {
            this.sound.play(-2147483568, -1);
            this.S = true;
        }
        this.N = true;
        return z;
    }

    protected final void onLoad() {
        int i = 1;
        cj = new int[6];
        Storage.setMenuFlag(9, 1);
        Storage.setSetting(10, 0);
        Storage.setMenuFlag(8, 1);
        Storage.setSetting(9, 0);
        try {
            int i2;
            int i3;
            int i4;
            DataInputStream a = Storage.openRead("towermode");
            for (i2 = 0; i2 < 6; i2++) {
                cj[i2] = a.readInt();
            }
            if (a.readBoolean()) {
                Storage.setMenuFlag(9, 0);
                i2 = 10;
                i3 = 1;
            } else {
                Storage.setMenuFlag(9, 1);
                i2 = 10;
                i3 = 0;
            }
            Storage.setSetting(i2, i3);
            if (a.readBoolean()) {
                Storage.setMenuFlag(8, 0);
                i4 = 9;
            } else {
                Storage.setMenuFlag(8, 1);
                i = 0;
                i4 = 9;
            }
            Storage.setSetting(i4, i);
            Storage.close();
        } catch (Exception e) {
        }
    }

    public final void keyReleasedRaw(int i) {
        super.keyReleasedRaw(i);
    }

    public final String formatNumber(int i) {
        return new StringBuffer().append("").append(i).toString();
    }

    protected final void loadTowerQuickMode() {
        int i = 0;
        try {
            int i2;
            int i3;
            DataInputStream a = Storage.openRead("quickModeRS");
            e = a.readInt();
            f = a.readInt();
            bk = a.readInt();
            cg = a.readInt();
            aT = a.readInt();
            aU = a.readInt();
            aV = a.readInt();
            aW = a.readInt();
            cI = a.readInt();
            aX = a.readInt();
            aY = a.readInt();
            aI = a.readInt();
            aJ = a.readInt();
            aK = a.readInt();
            aL = a.readInt();
            aM = a.readInt();
            aP = a.readInt();
            cN = a.readInt();
            aH = a.readInt();
            cO = a.readInt();
            aN = a.readInt();
            aO = a.readInt();
            cP = a.readInt();
            cQ = a.readInt();
            cp = a.readInt();
            ao = a.readInt();
            ap = a.readInt();
            ar = a.readInt();
            aq = a.readInt();
            cd = a.readInt();
            bs = a.readInt();
            bg = a.readInt();
            bl = a.readInt();
            bo = a.readInt();
            bp = a.readInt();
            bn = a.readInt();
            bm = a.readInt();
            by = a.readInt();
            bu = a.readInt();
            bv = a.readInt();
            bw = a.readInt();
            for (i2 = 0; i2 < bi.length; i2++) {
                bi[i2] = a.readInt();
            }
            for (i2 = 0; i2 < bj.length; i2++) {
                bj[i2] = a.readInt();
            }
            for (i2 = 0; i2 < cF.length; i2++) {
                cF[i2] = a.readInt();
            }
            for (i2 = 0; i2 < bh.length; i2++) {
                bh[i2] = a.readInt();
            }
            bD = a.readBoolean();
            dC = a.readInt();
            bq = a.readInt();
            for (i2 = 0; i2 < az.length; i2++) {
                az[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aA.length; i2++) {
                aA[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aw.length; i2++) {
                aw[i2] = a.readInt();
            }
            for (i2 = 0; i2 < cG.length; i2++) {
                cG[i2] = a.readInt();
            }
            for (i2 = 0; i2 < ax.length; i2++) {
                ax[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aB.length; i2++) {
                aB[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aC.length; i2++) {
                aC[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aD.length; i2++) {
                aD[i2] = a.readInt();
            }
            for (i2 = 0; i2 < ay.length; i2++) {
                ay[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aF.length; i2++) {
                aF[i2] = a.readByte();
            }
            for (i2 = 0; i2 < cH.length; i2++) {
                cH[i2] = a.readInt();
            }
            for (i3 = 0; i3 < 8; i3++) {
                for (i2 = 0; i2 < 8; i2++) {
                    bE[i3][i2] = a.readInt();
                }
            }
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 5; i2++) {
                    cm[i3][i2] = a.readInt();
                }
            }
            cn = a.readInt();
            co = a.readInt();
            bt = a.readInt();
            ba = a.readInt();
            bb = a.readInt();
            bc = a.readInt();
            ch = a.readInt();
            bA = a.readInt();
            bB = a.readInt();
            bz = a.readInt();
            dD = a.readInt();
            dH = a.readInt();
            for (i3 = 0; i3 < 12; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    dF[i3][i2] = a.readInt();
                }
            }
            for (i3 = 0; i3 < 9; i3++) {
                for (i2 = 0; i2 < 6; i2++) {
                    dA[i3][i2] = a.readInt();
                }
            }
            for (i2 = 0; i2 < cj.length; i2++) {
                cj[i2] = a.readInt();
            }
            g = a.readBoolean();
            bC = a.readInt();
            j = a.readBoolean();
            bf = a.readBoolean();
            cW = a.readBoolean();
            cX = a.readBoolean();
            cY = a.readBoolean();
            cZ = a.readBoolean();
            da = a.readBoolean();
            dd = a.readBoolean();
            de = a.readBoolean();
            df = a.readInt();
            dg = a.readInt();
            dh = a.readInt();
            di = a.readInt();
            dj = a.readInt();
            dk = a.readInt();
            dl = a.readInt();
            dm = a.readInt();
            dn = a.readInt();
            do_ = a.readInt();
            dp = a.readInt();
            dq = a.readInt();
            dr = a.readInt();
            ds = a.readInt();
            dt = a.readInt();
            du = a.readInt();
            dv = a.readInt();
            dw = a.readInt();
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    db[i3][i2] = a.readInt();
                }
            }
            while (i < 2) {
                int[] iArr = db[i];
                iArr[0] = db[i][0];
                iArr[1] = db[i][1];
                iArr[2] = db[i][2];
                iArr[3] = db[i][3];
                iArr[4] = db[i][4];
                iArr[5] = db[i][5];
                iArr[6] = db[i][6];
                i++;
            }
            cM = a.readInt();
            Storage.close();
            if (aw[0] == 4) {
                aH = 1664;
                if (bk != 2) {
                    aw[0] = 1;
                    az[0] = aK;
                    aA[0] = aL;
                    cH[0] = 0;
                    cG[0] = 0;
                    dC = Math.max(0, Math.min(4, bs - 1));
                }
            }
            if (g) {
                a(true);
                bk = 3;
            }
            if (cj[2] == 0 && cj[1] != 0 && j) {
                House.showPrompt(Resources.getString(35), null, null);
                f = 8;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in loadTowerQuickMode(), e:").append(e.getMessage()).toString());
        }
    }

    protected final void updateBackground(int i) {
        int i2;
        int i3 = 0;
        if (cu == null) {
            cu = new int[40];
            for (i2 = 0; i2 < cu.length / 5; i2++) {
                cu[(i2 * 5) + 1] = be;
            }
        }
        if (cs == null) {
            cs = new int[18];
            for (i2 = 0; i2 < cs.length / 6; i2++) {
                cs[(i2 * 6) + 1] = be;
            }
        }
        if (ce[3] == null) {
            ce[3] = Resources.getImage(45);
        }
        if (cv == null) {
            cv = new int[20];
            for (i2 = 0; i2 < cv.length / 5; i2++) {
                cv[(i2 * 5) + 1] = be;
            }
        }
        if (i > 150) {
            i = 150;
        }
        cq += i;
        if (cq >= 25) {
            int i4;
            int i5;
            int[] iArr;
            int i6;
            int[] iArr2;
            int i7;
            int i8 = cq;
            cq = 0;
            cr += i8 >> 1;
            for (i2 = 0; i2 < cu.length / 5; i2++) {
                i4 = cu[(i2 * 5) + 2];
                i5 = cu[(i2 * 5) + 3];
                iArr = cu;
                i6 = (i2 * 5) + 0;
                iArr[i6] = ((i4 * i8) >> 6) + iArr[i6];
                iArr2 = cu;
                i7 = (i2 * 5) + 1;
                iArr2[i7] = ((i5 * i8) >> 6) + iArr2[i7];
                if (cu[(i2 * 5) + 1] >= be) {
                    cu[(i2 * 5) + 0] = ((-((ac.getWidth() >> 1) << 8)) / 32) + House.random(bd);
                    cu[(i2 * 5) + 1] = ((-(ac.getHeight() << 8)) / 32) - House.random(be);
                    i4 = House.random(2);
                    cu[(i2 * 5) + 4] = i4;
                    cu[(i2 * 5) + 2] = (i4 * 0) + 0;
                    cu[(i2 * 5) + 3] = (i4 * 3) + 6;
                }
            }
            ct -= i8;
            if (ct < 0) {
                ct = 300;
                for (i2 = 0; i2 < cs.length / 6; i2++) {
                    iArr2 = cs;
                    i5 = (i2 * 6) + 2;
                    iArr = cs;
                    i6 = (i2 * 6) + 2;
                    int i9 = iArr[i6] + 1;
                    iArr[i6] = i9;
                    iArr2[i5] = i9 % 8;
                }
            }
            for (i2 = 0; i2 < cs.length / 6; i2++) {
                i4 = cs[(i2 * 6) + 3];
                i5 = cs[(i2 * 6) + 4];
                iArr = cs;
                i6 = (i2 * 6) + 0;
                iArr[i6] = ((i4 * i8) >> 6) + iArr[i6];
                iArr2 = cs;
                i7 = (i2 * 6) + 1;
                iArr2[i7] = ((i5 * i8) >> 6) + iArr2[i7];
                if (cs[(i2 * 6) + 1] >= be) {
                    cs[(i2 * 6) + 0] = House.random(bd);
                    cs[(i2 * 6) + 1] = -224 - House.random(be);
                    cs[(i2 * 6) + 2] = House.random(8);
                    cs[(i2 * 6) + 3] = House.random(16) - 8;
                    cs[(i2 * 6) + 4] = House.random(10) + 10;
                    cs[(i2 * 6) + 5] = House.random(2);
                }
            }
            while (i3 < cv.length / 5) {
                i2 = cv[(i3 * 5) + 2];
                i4 = cv[(i3 * 5) + 3];
                int[] iArr3 = cv;
                i7 = (i3 * 5) + 0;
                iArr3[i7] = ((i2 * i8) >> 6) + iArr3[i7];
                int[] iArr4 = cv;
                i5 = (i3 * 5) + 1;
                iArr4[i5] = ((i4 * i8) >> 6) + iArr4[i5];
                if (cv[(i3 * 5) + 1] >= be) {
                    cv[(i3 * 5) + 0] = (((-((ce[3].getWidth() >> 1) << 8)) / 32) + House.random(bd)) - bd;
                    cv[(i3 * 5) + 1] = ((-(ce[3].getHeight() << 8)) / 32) - House.random(be);
                    i2 = House.random(2);
                    cv[(i3 * 5) + 2] = (i2 * 6) + 6;
                    cv[(i3 * 5) + 3] = (i2 * 3) + 6;
                }
                i3++;
            }
            Storage.dirty = true;
        }
    }

    protected final void loadTowerInfoCityMode() {
        int i = 0;
        System.out.println("loadTowerInfoCityMode()");
        try {
            int i2;
            int i3;
            DataInputStream a = Storage.openRead("cityModeRS");
            e = a.readInt();
            f = a.readInt();
            bk = a.readInt();
            cg = a.readInt();
            aT = a.readInt();
            aU = a.readInt();
            aV = a.readInt();
            aW = a.readInt();
            cI = a.readInt();
            aX = a.readInt();
            aY = a.readInt();
            aI = a.readInt();
            aJ = a.readInt();
            aK = a.readInt();
            aL = a.readInt();
            aM = a.readInt();
            aP = a.readInt();
            cN = a.readInt();
            aH = a.readInt();
            cO = a.readInt();
            aN = a.readInt();
            aO = a.readInt();
            cP = a.readInt();
            cQ = a.readInt();
            cp = a.readInt();
            ao = a.readInt();
            ap = a.readInt();
            ar = a.readInt();
            aq = a.readInt();
            cd = a.readInt();
            bs = a.readInt();
            bg = a.readInt();
            bl = a.readInt();
            bo = a.readInt();
            bp = a.readInt();
            bn = a.readInt();
            bm = a.readInt();
            by = a.readInt();
            bu = a.readInt();
            bv = a.readInt();
            bw = a.readInt();
            for (i2 = 0; i2 < bi.length; i2++) {
                bi[i2] = a.readInt();
            }
            for (i2 = 0; i2 < bj.length; i2++) {
                bj[i2] = a.readInt();
            }
            for (i2 = 0; i2 < cF.length; i2++) {
                cF[i2] = a.readInt();
            }
            for (i2 = 0; i2 < bh.length; i2++) {
                bh[i2] = a.readInt();
            }
            bD = a.readBoolean();
            dC = a.readInt();
            bq = a.readInt();
            for (i2 = 0; i2 < az.length; i2++) {
                az[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aA.length; i2++) {
                aA[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aw.length; i2++) {
                aw[i2] = a.readInt();
            }
            for (i2 = 0; i2 < cG.length; i2++) {
                cG[i2] = a.readInt();
            }
            for (i2 = 0; i2 < ax.length; i2++) {
                ax[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aB.length; i2++) {
                aB[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aC.length; i2++) {
                aC[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aD.length; i2++) {
                aD[i2] = a.readInt();
            }
            for (i2 = 0; i2 < ay.length; i2++) {
                ay[i2] = a.readInt();
            }
            for (i2 = 0; i2 < aF.length; i2++) {
                aF[i2] = a.readByte();
            }
            for (i2 = 0; i2 < cH.length; i2++) {
                cH[i2] = a.readInt();
            }
            for (i3 = 0; i3 < 8; i3++) {
                for (i2 = 0; i2 < 8; i2++) {
                    bE[i3][i2] = a.readInt();
                }
            }
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 5; i2++) {
                    cm[i3][i2] = a.readInt();
                }
            }
            cn = a.readInt();
            co = a.readInt();
            bt = a.readInt();
            ba = a.readInt();
            bb = a.readInt();
            bc = a.readInt();
            ch = a.readInt();
            bA = a.readInt();
            bB = a.readInt();
            bz = a.readInt();
            dD = a.readInt();
            dH = a.readInt();
            for (i3 = 0; i3 < 12; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    dF[i3][i2] = a.readInt();
                }
            }
            for (i3 = 0; i3 < 9; i3++) {
                for (i2 = 0; i2 < 6; i2++) {
                    dA[i3][i2] = a.readInt();
                }
            }
            for (i2 = 0; i2 < cj.length; i2++) {
                cj[i2] = a.readInt();
            }
            h = a.readBoolean();
            bC = a.readInt();
            House.i = a.readBoolean();
            bf = a.readBoolean();
            cW = a.readBoolean();
            cX = a.readBoolean();
            cY = a.readBoolean();
            cZ = a.readBoolean();
            da = a.readBoolean();
            dd = a.readBoolean();
            de = a.readBoolean();
            df = a.readInt();
            dg = a.readInt();
            dh = a.readInt();
            di = a.readInt();
            dj = a.readInt();
            dk = a.readInt();
            dl = a.readInt();
            dm = a.readInt();
            dn = a.readInt();
            do_ = a.readInt();
            dp = a.readInt();
            dq = a.readInt();
            dr = a.readInt();
            ds = a.readInt();
            dt = a.readInt();
            du = a.readInt();
            dv = a.readInt();
            dw = a.readInt();
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    db[i3][i2] = a.readInt();
                }
            }
            while (i < 2) {
                int[] iArr = db[i];
                iArr[0] = db[i][0];
                iArr[1] = db[i][1];
                iArr[2] = db[i][2];
                iArr[3] = db[i][3];
                iArr[4] = db[i][4];
                iArr[5] = db[i][5];
                iArr[6] = db[i][6];
                i++;
            }
            cM = a.readInt();
            Storage.close();
            if (aw[0] == 4) {
                aH = 1664;
                if (bk != 2) {
                    aw[0] = 1;
                    az[0] = aK;
                    aA[0] = aL;
                    cH[0] = 0;
                    cG[0] = 0;
                    dC = Math.max(0, Math.min(4, bs - 1));
                }
            }
            if (h) {
                if (bq == 0) {
                    House.showPrompt(Resources.getString(56), null, null);
                    f = 5;
                } else if (bq == 2) {
                    House.showPrompt(Resources.getString(57), null, null);
                    f = 5;
                } else {
                    a(false);
                }
                bk = 3;
            }
            if (cj[2] == 0 && cj[0] != 0 && House.i) {
                House.showPrompt(Resources.getString(35), null, null);
                f = 8;
            }
        } catch (Exception e) {
        }
    }

    protected final void onLoadBegin() {
        this.L = Resources.getImage(-1);
        this.M = Resources.getImage(11);
    }

    protected final void onLoadEnd() {
        this.L = null;
        this.M = null;
    }

    protected final void onMenuTick() {
        boolean z;
        House house;
        boolean z2 = true;
        boolean z3 = this.R;
        if (Storage.getSetting(3) == 1) {
            z = true;
            house = this;
        } else {
            z = false;
            house = this;
        }
        house.R = z;
        this.sound.setEnabled(this.R);
        if (!z3 && this.R) {
            this.sound.play(-2147483568, -1);
        }
        if (this.vibra != null) {
            if (Storage.getSetting(0) != 1) {
                z2 = false;
            }
            T = z2;
            this.vibra.setEnabled(T);
        }
    }

    public final void onEnter() {
        int i = 2;
        int i2 = 1;
        this.P = new Command("", 2, 1);
        bT = screenWidth / 44;
        bU = Math.max(bT, ((screenHeight - ((o.getHeight() + 2) * 5)) - 48) >> 1);
        bR = 24;
        bS = screenWidth / 14;
        n = false;
        this.O = 0;
        this.canvas.setCommand(this.P, this.Q);
        if (Storage.getSetting(12) == 3) {
            if (!q) {
                q = true;
            }
            CityMode.init();
            CityMode.loadState();
            if (CityMode.d) {
                House.J();
                loadTowerInfoCityMode();
                House.C();
            }
            CityMode.clearInputState();
            if (f != 7) {
                i = f;
            } else if (CityMode.d) {
                i = 3;
            }
            aZ = i;
            f = 7;
        } else if (Storage.getSetting(12) == 4) {
            if (!p) {
                p = true;
            }
            bl = 4;
            bg = k[bl - 1];
            House.J();
            loadTowerQuickMode();
            House.C();
            aZ = f != 7 ? f : 1;
            f = 7;
        } else {
            e = Storage.getSetting(12);
            if (e == 5) {
                cJ = true;
                if (q) {
                    int i3;
                    if (CityMode.isCityModeActive()) {
                        CityMode.d = false;
                    }
                    if (cx) {
                        cx = false;
                    }
                    CityMode.init();
                    if (bD) {
                        bD = false;
                    }
                    if (CityMode.n > 0) {
                        CityMode.n = 0;
                    }
                    if (CityMode.level > 0) {
                        CityMode.level = 0;
                    }
                    if (CityMode.population > 0) {
                        CityMode.population = 0;
                    }
                    if (CityMode.grid == null || CityMode.r == null || CityMode.b == null) {
                        CityMode.grid = new int[75];
                        CityMode.r = new boolean[46];
                        CityMode.b = new int[25];
                    }
                    for (i3 = 0; i3 < CityMode.grid.length; i3++) {
                        CityMode.grid[i3] = 0;
                    }
                    for (i3 = 0; i3 < CityMode.r.length; i3++) {
                        CityMode.r[i3] = false;
                    }
                    CityMode.f = 0;
                    CityMode.cursorCol = 2;
                    CityMode.cursorRow = 2;
                    CityMode.population = 0;
                    CityMode.t = 0;
                    CityMode.m = -1;
                    CityMode.o = false;
                    CityMode.q = true;
                    CityMode.k = 0;
                    CityMode.l = false;
                    CityMode.s = 0;
                    CityMode.u = 0;
                    CityMode.v = false;
                    CityMode.x = -1;
                    CityMode.y = -1;
                    CityMode.updateUnlocks();
                    CityMode.recalculateSynergies();
                    CityMode.e = 0;
                    if (!CityMode.r[0]) {
                        House.showPrompt(Resources.getString(36), null, null);
                        CityMode.r[0] = true;
                        CityMode.o = true;
                    }
                }
                h = false;
                f = 7;
            } else {
                bl = 4;
                bg = k[bl - 1];
                House.J();
                g = false;
                f = 7;
                i = 1;
            }
            aZ = i;
            if (e != 6) {
                i2 = 3;
            }
            c(i2);
        }
    }

    public final void onModeChange() {
        switch (this.O) {
            case 1:
                House.w();
                if (cx) {
                    CityMode.unloadAssets();
                }
                cp = -1;
                if (this.P != null) {
                    this.canvas.removeCommand(this.P);
                }
                this.menu.a(1);
                break;
            case 2:
                PrintStream printStream;
                String str;
                House.w();
                if (cx) {
                    CityMode.unloadAssets();
                }
                cp = -1;
                if (this.P != null) {
                    this.canvas.removeCommand(this.P);
                }
                if (e != 6) {
                    if (e == 5) {
                        c(3);
                        q = true;
                        CityMode.saveState();
                        House.saveTowerCityMode();
                        printStream = System.out;
                        str = "saveToweInfoCityMode() in MODE_CHANGE_BACK";
                    }
                    this.menu.a(2);
                    break;
                }
                c(1);
                p = true;
                House.saveTowerQuickMode();
                printStream = System.out;
                str = "saveToweInfoQuickMode() in MODE_CHANGE_BACK";
                printStream.println(str);
                this.menu.a(2);
                break;
            case 3:
                Storage.setScreenId(2);
                e = Storage.getScreenId();
                break;
        }
        this.O = 0;
    }

    public final int[][] getHighScoreLayout() {
        return a;
    }

    public static int getMode() {
        return f;
    }

    public static void dropBlock() {
        if (f == 2) {
            CityMode.handleClick(0, 0);
        } else if (f == 4 || f == 5 || f == 6 || f == 8) {
            House.handleModalAction(0);
        } else if (f != 7) {
            at = true;
        }
    }
}
