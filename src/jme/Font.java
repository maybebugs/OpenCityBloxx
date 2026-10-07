package jme;

import java.awt.Canvas;
import java.awt.FontMetrics;

/** Font wrapper (replacement for lcdui Font). Face/style/size use MIDP constants. */
public final class Font {
    public static final int STYLE_PLAIN = 0, STYLE_BOLD = 1, STYLE_ITALIC = 2;
    public static final int SIZE_MEDIUM = 0, SIZE_SMALL = 8, SIZE_LARGE = 16;
    private static final Canvas METRICS_HOST = new Canvas();
    private static final java.util.HashMap<Integer, Font> CACHE = new java.util.HashMap<Integer, Font>();

    public final java.awt.Font awt;
    private final FontMetrics fm;

    private Font(java.awt.Font f) {
        this.awt = f;
        this.fm = METRICS_HOST.getFontMetrics(f);
    }

    public static synchronized Font getFont(int face, int style, int size) {
        int key = (face << 16) | (style << 8) | size;
        Font f = CACHE.get(key);
        if (f == null) {
            int px = size == SIZE_SMALL ? 11 : (size == SIZE_LARGE ? 17 : 13);
            int st = ((style & STYLE_BOLD) != 0 ? java.awt.Font.BOLD : 0) | ((style & STYLE_ITALIC) != 0 ? java.awt.Font.ITALIC : 0);
            String name = face == 32 ? java.awt.Font.MONOSPACED : java.awt.Font.SANS_SERIF;
            f = new Font(new java.awt.Font(name, st, px));
            CACHE.put(key, f);
        }
        return f;
    }

    public int getHeight() { return fm.getHeight(); }
    public int getBaselinePosition() { return fm.getAscent(); }
    public int stringWidth(String s) { return fm.stringWidth(s); }
    public int substringWidth(String s, int off, int len) { return fm.stringWidth(s.substring(off, off + len)); }
    public int charWidth(char c) { return fm.charWidth(c); }
    public FontMetrics metrics() { return fm; }
}
