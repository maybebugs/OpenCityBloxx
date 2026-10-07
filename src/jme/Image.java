package jme;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Image wrapper (replacement for lcdui Image). */
public final class Image {
    public final BufferedImage img;

    private Image(BufferedImage b) { this.img = b; }

    public static Image createImage(byte[] data, int off, int len) throws IOException {
        BufferedImage b = ImageIO.read(new ByteArrayInputStream(data, off, len));
        if (b == null) throw new IOException("bad image");
        return new Image(toArgb(b));
    }

    public static Image createImage(int w, int h) {
        return new Image(new BufferedImage(Math.max(1, w), Math.max(1, h), BufferedImage.TYPE_INT_ARGB));
    }

    public static Image createRGBImage(int[] rgb, int w, int h, boolean alpha) {
        BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int[] px = new int[w * h];
        for (int i = 0; i < px.length && i < rgb.length; i++) px[i] = alpha ? rgb[i] : (rgb[i] | 0xFF000000);
        b.setRGB(0, 0, w, h, px, 0, w);
        return new Image(b);
    }

    private static BufferedImage toArgb(BufferedImage src) {
        if (src.getType() == BufferedImage.TYPE_INT_ARGB) return src;
        BufferedImage b = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = b.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.dispose();
        return b;
    }

    public int getWidth() { return img.getWidth(); }
    public int getHeight() { return img.getHeight(); }

    public Graphics getGraphics() {
        return new Graphics(img.createGraphics(), img.getWidth(), img.getHeight());
    }
}
