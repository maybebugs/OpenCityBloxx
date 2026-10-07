package jme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Full-screen game canvas hosted in a Swing window. The logical screen is fixed
 * (240x320) and scaled to the window. Replacement for Nokia FullCanvas / lcdui Canvas.
 */
public abstract class Canvas extends Displayable {
    public static final int LOGICAL_W = 240, LOGICAL_H = 320;
    public static final int UP = 1, LEFT = 2, RIGHT = 5, DOWN = 6, FIRE = 8, GAME_A = 9, GAME_B = 10, GAME_C = 11, GAME_D = 12;

    private static JFrame frame;
    private static Canvas current;

    private final BufferedImage screen = new BufferedImage(LOGICAL_W, LOGICAL_H, BufferedImage.TYPE_INT_RGB);
    private final BufferedImage shown = new BufferedImage(LOGICAL_W, LOGICAL_H, BufferedImage.TYPE_INT_RGB);
    private final int[] screenPx = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
    private final int[] shownPx = ((DataBufferInt) shown.getRaster().getDataBuffer()).getData();
    private volatile boolean repaintRequested = true;
    private JPanel panel;
    private final Set<Integer> down = new HashSet<Integer>();
    private Runnable closeHandler;

    protected Canvas() {
        if (!java.awt.GraphicsEnvironment.isHeadless()) {
            try {
                SwingUtilities.invokeAndWait(new Runnable() { public void run() { buildWindow(); } });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        current = this;
    }

    public static java.awt.Window window() { return frame; }

    /** Saves the last presented frame as PNG (used for automated testing). */
    public final void saveFrame(java.io.File f) {
        try { synchronized (shown) { javax.imageio.ImageIO.write(shown, "png", f); } } catch (Exception e) { e.printStackTrace(); }
    }

    /** Simulates a key press/release (used for automated testing). */
    public final void injectKey(int code) {
        dispatchPressed(code);
        try { keyReleased(code); } catch (Throwable t) { }
    }
    public void setCloseHandler(Runnable r) { closeHandler = r; }

    private void buildWindow() {
        panel = new JPanel() {
            @Override protected void paintComponent(java.awt.Graphics g0) {
                java.awt.Graphics2D g = (java.awt.Graphics2D) g0;
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                double s = Math.min(getWidth() / (double) LOGICAL_W, getHeight() / (double) LOGICAL_H);
                int dw = (int) Math.round(LOGICAL_W * s), dh = (int) Math.round(LOGICAL_H * s);
                int dx = (getWidth() - dw) / 2, dy = (getHeight() - dh) / 2;
                boolean integer = Math.abs(s - Math.round(s)) < 0.001;
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, integer
                        ? RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR : RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                synchronized (shown) { g.drawImage(shown, dx, dy, dw, dh, null); }
            }
        };
        panel.setPreferredSize(new Dimension(LOGICAL_W, LOGICAL_H));
        panel.setFocusable(true);
        panel.setBackground(Color.BLACK);
        panel.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int code = mapKey(e);
                if (code == 0) return;
                boolean repeat = !down.add(e.getKeyCode());
                if (repeat) { try { Canvas.this.keyRepeated(code); } catch (Throwable t) { } }
                else dispatchPressed(code);
            }
            public void keyReleased(KeyEvent e) {
                int code = mapKey(e);
                down.remove(e.getKeyCode());
                if (code != 0) { try { Canvas.this.keyReleased(code); } catch (Throwable t) { } }
            }
            public void keyTyped(KeyEvent e) { }
        });
        panel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            @Override public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
                int rot = e.getWheelRotation();
                if (rot < 0) {
                    dispatchPressed(-1);
                    try { Canvas.this.keyReleased(-1); } catch (Throwable t) { }
                } else if (rot > 0) {
                    dispatchPressed(-2);
                    try { Canvas.this.keyReleased(-2); } catch (Throwable t) { }
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                double s = Math.min(panel.getWidth() / (double) LOGICAL_W, panel.getHeight() / (double) LOGICAL_H);
                int dx = (int) ((panel.getWidth() - LOGICAL_W * s) / 2), dy = (int) ((panel.getHeight() - LOGICAL_H * s) / 2);
                int x = (int) ((e.getX() - dx) / s), y = (int) ((e.getY() - dy) / s);
                if (x >= 0 && x < LOGICAL_W && y >= 0 && y < LOGICAL_H) {
                    dispatchPointerPressed(x, y);
                }
                panel.requestFocusInWindow();
            }
            @Override public void mouseReleased(MouseEvent e) {
                double s = Math.min(panel.getWidth() / (double) LOGICAL_W, panel.getHeight() / (double) LOGICAL_H);
                int dx = (int) ((panel.getWidth() - LOGICAL_W * s) / 2), dy = (int) ((panel.getHeight() - LOGICAL_H * s) / 2);
                int x = (int) ((e.getX() - dx) / s), y = (int) ((e.getY() - dy) / s);
                dispatchPointerReleased(x, y);
            }
        });
        frame = new JFrame("City Bloxx");
        try {
            java.io.InputStream in = Canvas.class.getResourceAsStream("/icon.png");
            if (in != null) frame.setIconImage(javax.imageio.ImageIO.read(in));
        } catch (Exception e) { }
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                if (closeHandler != null) closeHandler.run(); else System.exit(0);
            }
        });
        frame.setContentPane(panel);
        frame.pack();
        frame.setMinimumSize(new Dimension(LOGICAL_W, LOGICAL_H));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    private void dispatchPressed(int code) {
        try { keyPressed(code); } catch (Throwable t) { t.printStackTrace(); }
    }

    private static int mapKey(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP: return -1;
            case KeyEvent.VK_DOWN: return -2;
            case KeyEvent.VK_LEFT: return -3;
            case KeyEvent.VK_RIGHT: return -4;
            case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE: return -5;
            case KeyEvent.VK_F1: case KeyEvent.VK_Z: return -6;
            case KeyEvent.VK_F2: case KeyEvent.VK_X: case KeyEvent.VK_ESCAPE: return -7;
            case KeyEvent.VK_BACK_SPACE: case KeyEvent.VK_DELETE: return -8;
            case KeyEvent.VK_W: return 50;
            case KeyEvent.VK_A: return 52;
            case KeyEvent.VK_S: return 56;
            case KeyEvent.VK_D: return 54;
            case KeyEvent.VK_MULTIPLY: return 42;
            case KeyEvent.VK_ASTERISK: return 42;
            case KeyEvent.VK_NUMBER_SIGN: return 35;
            default: break;
        }
        if (k >= KeyEvent.VK_0 && k <= KeyEvent.VK_9) return 48 + (k - KeyEvent.VK_0);
        if (k >= KeyEvent.VK_NUMPAD0 && k <= KeyEvent.VK_NUMPAD9) return 48 + (k - KeyEvent.VK_NUMPAD0);
        char c = e.getKeyChar();
        if (c == '*') return 42;
        if (c == '#') return 35;
        return 0;
    }

    void focus() {
        if (panel != null) panel.requestFocusInWindow();
        current = this;
        showNotify();
    }

    void unfocus() { hideNotify(); }

    public final int getWidth() { return LOGICAL_W; }
    public final int getHeight() { return LOGICAL_H; }

    public int getGameAction(int k) {
        switch (k) {
            case -1: case 50: return UP;
            case -2: case 56: return DOWN;
            case -3: case 52: return LEFT;
            case -4: case 54: return RIGHT;
            case -5: case 53: return FIRE;
            case 49: return GAME_A;
            case 51: return GAME_B;
            case 55: return GAME_C;
            case 57: return GAME_D;
            default: return 0;
        }
    }

    public String getKeyName(int k) {
        if (k >= 48 && k <= 57) return String.valueOf((char) k);
        if (k == 42) return "*";
        if (k == 35) return "#";
        switch (k) {
            case -1: return "Up";
            case -2: return "Down";
            case -3: return "Left";
            case -4: return "Right";
            case -5: return "Select";
            case -6: return "Left soft key";
            case -7: return "Right soft key";
            default: return "Key " + k;
        }
    }

    public final void repaint() { repaintRequested = true; }

    /** Paints synchronously on the caller's thread and presents the frame. */
    public final void serviceRepaints() {
        if (!repaintRequested) return;
        repaintRequested = false;
        java.awt.Graphics2D g2 = screen.createGraphics();
        Graphics g = new Graphics(g2, LOGICAL_W, LOGICAL_H);
        try {
            paint(g);
            Graphics3D.flushPending();
        } finally {
            g2.dispose();
        }
        synchronized (shown) { System.arraycopy(screenPx, 0, shownPx, 0, screenPx.length); }
        if (panel != null) panel.repaint();
    }

    protected abstract void paint(Graphics g);
    protected void showNotify() { }
    protected void hideNotify() { }
    protected void keyPressed(int keyCode) { }
    protected void keyReleased(int keyCode) { }
    protected void keyRepeated(int keyCode) { }
    protected void pointerPressed(int x, int y) { }
    protected void pointerReleased(int x, int y) { }

    private void dispatchPointerPressed(int x, int y) {
        try { pointerPressed(x, y); } catch (Throwable t) { t.printStackTrace(); }
    }
    private void dispatchPointerReleased(int x, int y) {
        try { pointerReleased(x, y); } catch (Throwable t) { t.printStackTrace(); }
    }
}
