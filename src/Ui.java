

import java.util.Vector;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class Ui {
    private static int A = -1;
    private static int[] B = new int[3];
    private static Font C;
    private static Font D;
    private static Font E;
    private static boolean F;
    protected static int a = -1;
    public static Image b;
    protected static int c = 0;
    private static String[] d;
    private static int[] e;
    private static int f;
    private static int g = 0;
    private static boolean h;
    private static int i;
    private static Image j;
    private static boolean k;
    private static int l = 0;
    private static long m;
    private static Image[] n;
    private static String[][] o;
    private static int[] p;
    private static int q;
    private static int r;
    private static String[][] s;
    private static int t = -1;
    private static int u = -1;
    private static int v = -1;
    private static int w = -1;
    private static int x = -1;
    private static int y = -1;
    private static int z = -1;

    public static void clearDialog() {
        Ui.dismissDialog();
        Storage.dirty = true;
    }

    /** Scrolls the paged text by delta pages (static void a(int)). */
    public static void update(int delta) {
        boolean atEnd = false;
        if (h) {
            i += delta;
            i = Math.max(0, i);
            i = Math.min(s.length - 1, i);
            if (i == s.length - 1) {
                atEnd = true;
            }
            k = atEnd;
        }
        Storage.dirty = true;
        c = Storage.cursor;
    }

    public static void setLayout(int i, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        Ui.dismissDialog();
        a = 3;
        A = i5;
        g = i6;
        u = i;
        v = i2;
        w = i3;
        w = Math.min(w, GameMIDlet.screenWidth - u);
        x = i4;
        x = Math.min(x, GameMIDlet.screenHeight - v);
        t = (w - 16) + 0;
        if (iArr != null) {
            for (int i7 = 0; i7 < 3; i7++) {
                B[i7] = iArr[i7];
            }
        }
        Storage.dirty = true;
    }

    public static void setMenuItem(int i, Image image, String str, int i2) {
        if (a == 1) {
            if (image != null) {
                q = Math.max(q, image.getHeight());
                r = Math.max(r, image.getWidth());
            }
            if (i + 1 > l) {
                l++;
            }
            n[i] = image;
            o[i] = Ui.a(str, (w - 24) - r, D);
            p[i] = i2;
        }
    }

    public static void initMenuList(int i, Image image, String str, int i2, int i3, int i4, int i5, int i6, String str2) {
        l = 0;
        Ui.dismissDialog();
        n = new Image[i];
        o = new String[i][];
        p = new int[i];
        u = i3;
        v = i4;
        w = i5;
        x = i6;
        a = 1;
        g = i2;
        Ui.a(image, str);
        m = 0;
        Storage.dirty = true;
    }

    public static void setTitle(String str) {
        s = Ui.a(str, (x - 36) - 16, t, D);
        D.getHeight();
        if (s.length > 1) {
            h = true;
            i = 0;
        } else {
            k = true;
        }
        Storage.dirty = true;
    }

    public static void paint(Graphics graphics) {
        switch (a) {
            case 1:
                Ui.b(graphics);
                break;
            case 2:
                Ui.paintDialog(graphics);
                break;
            case 3:
                Ui.paintScrollIndicator(graphics);
                break;
        }
        if (F || d == null || d.length == 1) {
            Storage.dirty = false;
        }
    }

    public static void paintArrowIndicator(Graphics graphics, int i, int i2, boolean z) {
        graphics.setClip(i, i2, 18, 18);
        graphics.drawImage(j, i, i2, 20);
        if (z) {
            graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        }
    }

    /** Paints the paged text popup body (private static void a(Graphics,boolean)). */
    private static void a(Graphics graphics, boolean z) {
        int left = u;
        int right = u + w;
        int centerX = u + (w / 2);
        int top = v;
        int bottom = v + x;
        int y = top + 8;
        int fontHeight = D.getHeight();
        int textX = left + 8;
        if (Storage.getSetting(4) == 1) {
            textX = right - 8;
        }
        if (a == 2) {
            textX = Storage.getSetting(4) == 0 ? textX + 8 : textX - 8;
        }
        graphics.setColor(B[0] == -1 ? e[2] : B[0]);
        graphics.setFont(D);
        if (h && i > 0) {
            Ui.paintArrowIndicator(graphics, centerX - 9, top + 2, false);
        }
        y += 18;
        graphics.setClip(left, top, right, bottom);
        for (int line = 0; line < s[i].length; line++) {
            if (g == 1) {
                graphics.drawString(s[i][line], centerX, y, 17);
            } else {
                int anchor = 24;
                if (Storage.getSetting(4) == 0) {
                    anchor = 20;
                }
                graphics.drawString(s[i][line], textX, y, anchor);
            }
            y += fontHeight;
        }
        if (k) {
            if (z) {
                Ui.paintProgressBar(graphics, centerX - 9, (bottom - 18) - 1, true);
            }
        } else if (h) {
            Ui.paintProgressBar(graphics, centerX - 9, (bottom - 18) - 1, false);
        }
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
    }

    private static void a(Image image, String str) {
        F = false;
        b = image;
        if (str == null) {
            d = new String[]{""};
            F = true;
        } else {
            int i = GameMIDlet.screenWidth - 6;
            d = Ui.a(str, b != null ? i - 30 : i - 8, C);
        }
        z = 0;
        y = 26;
        if (b != null) {
            y = Math.max(y, (b.getHeight() + 12) + C.getHeight());
        }
        v = Math.max(v, z + y);
        if (w == -1) {
            w = GameMIDlet.screenWidth;
        }
        w = Math.min(w, GameMIDlet.screenWidth - u);
        if (x == -1) {
            x = GameMIDlet.screenHeight;
        }
        x = Math.min((GameMIDlet.screenHeight - v) - f, x);
        Storage.dirty = true;
    }

    public static void openDialog(Image image, String str, int i, int i2, int i3, int i4, int i5, String str2) {
        Ui.dismissDialog();
        a = 2;
        u = i2;
        v = i3;
        w = i4;
        x = i5;
        A = 255;
        g = i;
        Ui.a(image, str);
        t = (w - 32) + 0;
        Storage.dirty = true;
    }

    public static void setColorPalette(int[] iArr) {
        C = Font.getFont(32, 1, 0);
        D = Font.getFont(32, 0, 8);
        E = Font.getFont(32, 1, 8);
        f = Font.getFont(32, 1, 8).getHeight() + 4;
        e = iArr;
        j = Resources.getImage(0);
        Storage.dirty = true;
    }

    private static String[] a(String str, int i, Font font) {
        return Ui.a(str, -1, i, font)[0];
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /**
     * Word-wraps text into pages of lines (private static String[][] a(String,int,int,Font)).
     * Reconstructed from bytecode (decompiler failure).
     *
     * @param text      source text; '\n' = line break, "\\p" = page break
     * @param maxHeight page height in pixels, or -1 for unlimited
     * @param maxWidth  line width in pixels
     */
    private static String[][] a(String text, int maxHeight, int maxWidth, Font font) {
        System.currentTimeMillis();
        int len = text.length();
        Vector pages = new Vector();
        Vector page = null;
        int pos = 0;
        int pageUsed = 0;
        int lineWidth = 0;
        String line = null;
        int segStart;
        int segEnd = 0;
        int fontHeight = font.getHeight();
        int nextNewline = 0;
        int nextTag = 0;
        int nextPos;
        do {
            boolean pageBreak = false;
            boolean lineBreak = false;
            boolean tooLong = false;
            if (page == null) {
                page = new Vector();
                pageUsed = 0;
            }
            if (nextNewline <= pos) {
                nextNewline = text.indexOf(10, pos);
                if (nextNewline == -1) {
                    nextNewline = len;
                }
            }
            if (nextTag <= pos) {
                nextTag = text.indexOf("\\p", pos);
                if (nextTag == -1) {
                    nextTag = len;
                }
            }
            if (nextTag < nextNewline) {
                segStart = pos;
                segEnd = nextTag;
                nextPos = nextTag + "\\p".length();
                pageBreak = true;
                lineBreak = true;
            } else if (nextNewline < nextTag) {
                segStart = pos;
                segEnd = nextNewline;
                nextPos = nextNewline + 1;
                lineBreak = true;
            } else {
                segStart = pos;
                segEnd = len;
                nextPos = len;
            }
            // first break candidate (space or '.') at/after segStart
            int space = text.indexOf(32, segStart);
            int dot = text.indexOf(46, segStart);
            int brk = nextBreak(text, len, space, dot);
            brk = Math.min(brk, segEnd);
            int width = lineWidth + font.substringWidth(text, pos, brk - pos);
            int lastGood = -1;
            while (width < maxWidth && brk != -1) {
                lastGood = brk;
                if (brk == segEnd) {
                    break;
                }
                space = text.indexOf(32, brk + 1);
                dot = text.indexOf(46, brk + 1);
                brk = nextBreak(text, len, space, dot);
                if (brk > segEnd || brk == -1) {
                    brk = segEnd;
                }
                width = lineWidth + font.substringWidth(text, pos, brk - pos);
            }
            if (lastGood == -1) {
                tooLong = true;
            } else if (lastGood < segEnd) {
                segEnd = lastGood;
                nextPos = text.charAt(lastGood) == ' ' ? lastGood + 1 : lastGood;
                lineBreak = true;
                pageBreak = false;
            }
            width = lineWidth + font.substringWidth(text, pos, segEnd - pos);
            line = text.substring(pos, segEnd);
            if (tooLong) {
                // no break point fits: cut inside the word
                nextPos = pos;
                do {
                    nextPos++;
                    String sub = text.substring(pos, nextPos);
                    if (font.stringWidth(sub) < maxWidth) {
                        line = sub;
                    } else {
                        nextPos--;
                        break;
                    }
                } while (nextPos < len);
                lineBreak = true;
            }
            lineWidth = width;
            if (lineBreak) {
                if (maxHeight != -1 && pageUsed + fontHeight > maxHeight) {
                    pages.addElement(page);
                    page = new Vector();
                    pageUsed = 0;
                }
                page.addElement(line);
                pageUsed += fontHeight;
                line = null;
                lineWidth = 0;
            }
            if (pageBreak) {
                pages.addElement(page);
                page = null;
            }
            pos = nextPos;
        } while (pos < len);
        if (page != null) {
            if (line != null) {
                if (maxHeight != -1 && pageUsed + fontHeight > maxHeight) {
                    pages.addElement(page);
                    page = new Vector();
                }
                page.addElement(line);
            }
            pages.addElement(page);
        }
        String[][] result = new String[pages.size()][];
        for (int i = 0; i < pages.size(); i++) {
            Vector p = (Vector) pages.elementAt(i);
            int n = p.size();
            result[i] = new String[n];
            for (int j = 0; j < n; j++) {
                result[i][j] = (String) p.elementAt(j);
            }
        }
        return result;
    }

    /** Picks the next wrap position from the next space / '.' (shared tail of the wrap loop). */
    private static int nextBreak(String text, int len, int space, int dot) {
        int brk;
        if (space == -1 && dot == -1) {
            return len;
        }
        if (space == -1) {
            brk = dot + 1;
            if (brk < len) {
                if (text.charAt(brk) == '.' || text.charAt(brk) == ' ') {
                    return len;
                }
                return brk;
            }
            return len;
        }
        if (dot != -1 && dot < space) {
            brk = dot + 1;
            if (brk < len) {
                if (text.charAt(brk) == '.' || text.charAt(brk) == ' ') {
                    return space;
                }
                return brk;
            }
            return space;
        }
        return space;
    }

    /** Paints the scrolling list menu (private static void b(Graphics)). Rewritten from bytecode. */
    private static void b(Graphics graphics) {
        Ui.paintSelectionCursor(graphics);
        if (Storage.dirty) {
            int rowHeight = Math.max(D.getHeight(), q + 2);
            graphics.setFont(D);
            int textX = Storage.getSetting(4) == 1 ? ((u + w) - 16) - r : (u + 16) + r;
            int iconX = Storage.getSetting(4) == 1 ? (u + w) - 8 : u + 8;
            if (g == 1) {
                textX = u + (w >> 1);
            }
            int listTop = v + 8;
            int y = listTop;
            int listHeight = x - 16;
            int visibleRows = listHeight / (rowHeight + 0);
            if (visibleRows < l) {
                // arrows needed: leave room for the scroll arrow at the top
                listTop = (v + 18) + 8;
                y = listTop;
                listHeight = x - 52;
                visibleRows = listHeight / (rowHeight + 0);
            }
            int halfRows = (visibleRows - 1) >> 1;
            int iconYOffset = (rowHeight - q) >> 1;
            int textYOffset = (rowHeight - D.getHeight()) >> 1;
            int selectedY = rowHeight;
            int totalHeight = 0;
            for (int k = 0; k < l; k++) {
                String[] item = o[k];
                if (k == c) {
                    selectedY = totalHeight;
                }
                int itemHeight = rowHeight + (D.getHeight() * (item.length - 1));
                totalHeight += itemHeight;
            }
            int scroll = selectedY - (halfRows * rowHeight);
            boolean moreAbove = false;
            boolean moreBelow = false;
            scroll = Math.min(scroll, totalHeight - (visibleRows * rowHeight));
            scroll = Math.max(0, scroll);
            y -= scroll;
            for (int k = 0; k < l; k++) {
                String[] item = o[k];
                if (k == c) {
                    graphics.setColor(e[6]);
                    if (Storage.getSetting(4) == 1) {
                        int hiliteWidth = ((D.stringWidth(o[k][0]) + 16) + r) + 3;
                        graphics.fillRect(((u + w) - 4) - hiliteWidth, y - 1, hiliteWidth, rowHeight + (D.getHeight() * (item.length - 1)));
                    } else {
                        graphics.fillRect(u + 8, y - 1, w - 16, rowHeight + (D.getHeight() * (item.length - 1)));
                    }
                }
                if (p[k] == 2) {
                    graphics.setColor(e[5]);
                } else {
                    graphics.setColor(e[2]);
                    graphics.setFont(D);
                    if (GameMIDlet.getInstance().loadState == 1) {
                        graphics.setColor(0);
                    }
                    if (k == c) {
                        graphics.setColor(e[7]);
                        if (GameMIDlet.getInstance().loadState == 1) {
                            graphics.setColor(16777215);
                        }
                        graphics.setFont(E);
                    }
                }
                int anchor = 17;
                if (g == 0) {
                    anchor = Storage.getSetting(4) == 0 ? 20 : 24;
                }
                for (int j = 0; j < item.length; j++) {
                    if (y >= listTop && y + rowHeight <= listTop + listHeight) {
                        if (n[k] != null && j == 0) {
                            int offset = 0;
                            if (g == 1) {
                                int maxWidth = 0;
                                for (int m2 = 0; m2 < item.length; m2++) {
                                    maxWidth = Math.max(maxWidth, D.stringWidth(item[m2]));
                                }
                                offset = textX - ((r + 8) + (maxWidth >> 1));
                            }
                            if (k == c) {
                                offset++;
                            } else {
                                offset--;
                            }
                            if (Storage.getSetting(4) == 1) {
                                graphics.drawImage(n[k], iconX - offset, (y + iconYOffset) - 1, 24);
                            } else {
                                graphics.drawImage(n[k], iconX + offset, (y + iconYOffset) - 1, 20);
                            }
                        }
                        graphics.drawString(item[j], textX, y + textYOffset, anchor);
                    } else if (k < c) {
                        moreAbove = true;
                    } else {
                        moreBelow = true;
                    }
                    if (j < item.length - 1) {
                        y += D.getHeight();
                    }
                }
                y += rowHeight;
            }
            if (moreAbove) {
                Ui.paintArrowIndicator(graphics, (u + (w / 2)) - 9, v + 8, true);
            }
            if (moreBelow) {
                Ui.paintProgressBar(graphics, (u + (w / 2)) - 9, ((v + x) - 18) - 8, false);
            }
        }
    }

    public static int getItemAt(int px, int py) {
        if (a != 1 || o == null || l <= 0 || D == null) return -1;
        if (px < u || px > u + w) return -1;
        int rowHeight = Math.max(q, D.getHeight()) + 2;
        int listTop = v + 8;
        int y = listTop;
        int listHeight = x - 16;
        int visibleRows = listHeight / rowHeight;
        if (visibleRows < l) {
            listTop = (v + 18) + 8;
            y = listTop;
            listHeight = x - 52;
            visibleRows = listHeight / rowHeight;
        }
        int halfRows = (visibleRows - 1) >> 1;
        int selectedY = rowHeight;
        int totalHeight = 0;
        for (int k = 0; k < l; k++) {
            if (k == c) selectedY = totalHeight;
            int itemHeight = rowHeight + (D.getHeight() * (o[k].length - 1));
            totalHeight += itemHeight;
        }
        int scroll = selectedY - (halfRows * rowHeight);
        scroll = Math.min(scroll, totalHeight - (visibleRows * rowHeight));
        scroll = Math.max(0, scroll);
        y -= scroll;
        for (int k = 0; k < l; k++) {
            int itemHeight = rowHeight + (D.getHeight() * (o[k].length - 1));
            if (py >= y - 1 && py < y + itemHeight && py >= listTop && py <= listTop + listHeight) {
                return k;
            }
            y += itemHeight;
        }
        return -1;
    }

    public static void setCursor(int index) {
        c = index;
        Storage.cursor = index;
        Storage.dirty = true;
    }

    public static void paintProgressBar(Graphics graphics, int i, int i2, boolean z) {
        int i3;
        Image image;
        Graphics graphics2;
        if (z) {
            i3 = i2 - 36;
            graphics.setClip(i, i2, 18, 18);
            image = j;
            graphics2 = graphics;
        } else {
            graphics.setClip(i, i2, 18, 18);
            image = j;
            i3 = i2 - 18;
            graphics2 = graphics;
        }
        graphics2.drawImage(image, i, i3, 20);
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
    }

    public static boolean isDialogActive() {
        return k;
    }

    private static void dismissDialog() {
        a = 0;
        d = null;
        k = false;
        i = 0;
        l = 0;
        c = 0;
        b = null;
        n = null;
        q = 0;
        r = 0;
        o = (String[][]) null;
        p = null;
        s = (String[][]) null;
        t = -1;
        u = -1;
        v = -1;
        w = -1;
        x = -1;
        y = -1;
        z = -1;
        A = -1;
        for (int i = 0; i < B.length; i++) {
            B[i] = -1;
        }
        h = false;
    }

    private static void paintDialog(Graphics graphics) {
        Ui.paintSelectionCursor(graphics);
        if (Storage.dirty) {
            Ui.a(graphics, false);
        }
    }

    private static void paintScrollIndicator(Graphics graphics) {
        if (Storage.dirty) {
            int i = u + w;
            int i2 = v + x;
            graphics.setClip(u, v, i, i2);
            if (A != 0) {
                int[] iArr;
                int i3;
                Graphics graphics2;
                if (B[1] == -1) {
                    iArr = e;
                    i3 = 4;
                    graphics2 = graphics;
                } else {
                    iArr = B;
                    graphics2 = graphics;
                    i3 = 1;
                }
                graphics2.setColor(iArr[i3]);
                graphics.fillRect(u, v, w, x);
                if (B[2] == -1) {
                    iArr = e;
                    i3 = 8;
                    graphics2 = graphics;
                } else {
                    iArr = B;
                    graphics2 = graphics;
                    i3 = 2;
                }
                graphics2.setColor(iArr[i3]);
                graphics.drawLine(u, v, i, v);
                graphics.drawLine(i, v, i, i2);
                graphics.drawLine(u, i2, i, i2);
                graphics.drawLine(u, v, u, i2);
            }
            Ui.a(graphics, true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /** Paints the menu title (icon + title text, scrolling ticker when several titles). Reconstructed from bytecode. */
    private static void paintSelectionCursor(Graphics graphics) {
        graphics.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        int x = Storage.getSetting(4) == 1 ? GameMIDlet.screenWidth - 14 : 14;
        graphics.setColor(e[1]);
        if (F ? Storage.dirty : ((Storage.dirty || d.length > 1) && b != null)) {
            graphics.drawImage(b, GameMIDlet.screenWidth >> 1, 6 + z, 17);
        }
        if (d == null) {
            return;
        }
        int textY = z + ((y - C.getHeight()) >> 1);
        if (b != null) {
            textY += (b.getHeight() + C.getHeight()) >> 1;
        }
        graphics.setColor(e[0]);
        graphics.setFont(C);
        int anchor = 20;
        if (Storage.getSetting(4) == 1) {
            anchor = 24;
        }
        if (b == null && g == 1) {
            x = GameMIDlet.screenWidth >> 1;
            anchor = 17;
        }
        if (b != null && Storage.getSetting(4) == 0) {
            x = GameMIDlet.screenWidth >> 1;
            anchor = 17;
        }
        if (b != null && Storage.getSetting(4) == 1) {
            x = GameMIDlet.screenWidth >> 1;
            anchor = 17;
        }
        if (d.length > 1) {
            long now = System.currentTimeMillis();
            if (m == 0) {
                m = now;
            }
            int elapsed = (int) (now - m);
            int t = elapsed % ((1500 * d.length) + 500);
            int index = t / 1500;
            int phase = t % 1500;
            if (index < d.length) {
                phase -= 1000;
            } else {
                index = -1;
            }
            int clipX = graphics.getClipX();
            int clipY = graphics.getClipY();
            int clipW = graphics.getClipWidth();
            int clipH = graphics.getClipHeight();
            graphics.setClip(0, z, GameMIDlet.screenWidth, y);
            String current = null;
            if (index >= 0) {
                current = d[index];
            }
            if (phase < 0) {
                if (current != null) {
                    graphics.drawString(current, x, textY, anchor);
                }
            } else {
                String next = null;
                if (index + 1 < d.length) {
                    next = d[index + 1];
                }
                int shift = (y * phase) / 500;
                if (current != null) {
                    graphics.drawString(current, x, textY - shift, anchor);
                }
                if (next != null) {
                    graphics.drawString(next, x, (textY + y) - shift, anchor);
                }
            }
            graphics.setClip(clipX, clipY, clipW, clipH);
            return;
        }
        if (Storage.dirty) {
            graphics.drawString(d[0], x, textY, anchor);
        }
    }
}
