package jme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/** MIDP-style Graphics on top of Graphics2D. */
public final class Graphics {
    public static final int HCENTER = 1, VCENTER = 2, LEFT = 4, RIGHT = 8, TOP = 16, BOTTOM = 32, BASELINE = 64;
    public static final int SOLID = 0, DOTTED = 1;

    final java.awt.Graphics2D g2;
    private final int w, h;
    private Font font = Font.getFont(0, 0, 0);
    private int clipX, clipY, clipW, clipH;

    public Graphics(java.awt.Graphics2D g2, int w, int h) {
        this.g2 = g2;
        this.w = w;
        this.h = h;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setFont(font.awt);
        g2.setColor(Color.BLACK);
        setClip(0, 0, w, h);
    }

    private void sync() {
        if (Graphics3D.pendingTarget == this) Graphics3D.flushPending();
    }

    private int lastRgb = -1;
    private Color lastColor;
    public void setColor(int rgb) {
        rgb &= 0xFFFFFF;
        if (rgb != lastRgb || lastColor == null) { lastRgb = rgb; lastColor = new Color(rgb); }
        g2.setColor(lastColor);
    }
    public void setColor(int r, int g, int b) { setColor(((r & 255) << 16) | ((g & 255) << 8) | (b & 255)); }
    public void setFont(Font f) { if (f != null) { font = f; g2.setFont(f.awt); } }
    public Font getFont() { return font; }

    public void setClip(int x, int y, int cw, int ch) {
        sync();
        clipX = x; clipY = y; clipW = cw; clipH = ch;
        g2.setClip(x, y, Math.max(0, cw), Math.max(0, ch));
    }
    public int getClipX() { return clipX; }
    public int getClipY() { return clipY; }
    public int getClipWidth() { return clipW; }
    public int getClipHeight() { return clipH; }

    public void setStrokeStyle(int style) {
        if (style == DOTTED) g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, new float[]{1f, 1f}, 0f));
        else g2.setStroke(new BasicStroke(1f));
    }

    public void fillRect(int x, int y, int rw, int rh) { sync(); if (rw > 0 && rh > 0) g2.fillRect(x, y, rw, rh); }
    public void drawRect(int x, int y, int rw, int rh) { sync(); if (rw >= 0 && rh >= 0) g2.drawRect(x, y, rw, rh); }
    public void drawLine(int x1, int y1, int x2, int y2) {
        sync();
        // inclusive end pixels like MIDP
        g2.drawLine(x1, y1, x2, y2);
        if (x1 == x2 && y1 == y2) g2.fillRect(x1, y1, 1, 1);
    }

    public void drawString(String s, int x, int y, int anchor) {
        if (s == null || s.length() == 0) return;
        sync();
        java.awt.FontMetrics fm = font.metrics();
        int sw = fm.stringWidth(s);
        int px = x;
        if ((anchor & HCENTER) != 0) px = x - sw / 2;
        else if ((anchor & RIGHT) != 0) px = x - sw;
        int py = y;
        if ((anchor & BASELINE) != 0) py = y;
        else if ((anchor & BOTTOM) != 0) py = y - fm.getDescent();
        else py = y + fm.getAscent();
        g2.drawString(s, px, py);
    }

    public void drawImage(Image im, int x, int y, int anchor) { drawImageImpl(im, x, y, anchor, false); }

    /** Replacement for Nokia DirectGraphics.drawImage(..., FLIP_HORIZONTAL). */
    public void drawImageFlipped(Image im, int x, int y, int anchor) { drawImageImpl(im, x, y, anchor, true); }

    private void drawImageImpl(Image im, int x, int y, int anchor, boolean flipH) {
        if (im == null) return;
        sync();
        int iw = im.getWidth(), ih = im.getHeight();
        int px = x, py = y;
        if ((anchor & HCENTER) != 0) px = x - iw / 2;
        else if ((anchor & RIGHT) != 0) px = x - iw;
        if ((anchor & VCENTER) != 0) py = y - ih / 2;
        else if ((anchor & BOTTOM) != 0) py = y - ih;
        if (flipH) g2.drawImage(im.img, px + iw, py, -iw, ih, null);
        else g2.drawImage(im.img, px, py, null);
    }

    public void dispose() { g2.dispose(); }
}
