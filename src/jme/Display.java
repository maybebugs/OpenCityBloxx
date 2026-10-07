package jme;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/** Window host + name-entry dialog (replacement for lcdui Display). */
public final class Display {
    private static final Display INSTANCE = new Display();
    private static JDialog dialog;
    private static Canvas lastCanvas;

    public static Display getDisplay(Object midlet) { return INSTANCE; }

    public static void shutdown() {
        System.exit(0);
    }

    public void setCurrent(final Displayable d) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                closeDialog();
                if (d instanceof Form) { if (lastCanvas != null) lastCanvas.unfocus(); showForm((Form) d); }
                else if (d instanceof Canvas) { lastCanvas = (Canvas) d; lastCanvas.focus(); }
            }
        });
    }

    private static void closeDialog() {
        if (dialog != null) { dialog.dispose(); dialog = null; }
    }

    private static void showForm(final Form form) {
        if (java.awt.GraphicsEnvironment.isHeadless()) return;
        final java.awt.Window owner = Canvas.window();
        final JDialog dlg = new JDialog(owner instanceof java.awt.Frame ? (java.awt.Frame) owner : null, form.title, false);
        dialog = dlg;
        final JTextField tf = new JTextField(form.field == null ? "" : form.field.getString(), 14);
        final int max = form.field == null ? 10 : form.field.getMaxSize();
        ((AbstractDocument) tf.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override public void replace(FilterBypass fb, int off, int len, String text, AttributeSet a) throws BadLocationException {
                if (text == null) text = "";
                int newLen = fb.getDocument().getLength() - len + text.length();
                if (newLen <= max) super.replace(fb, off, len, text, a);
            }
            @Override public void insertString(FilterBypass fb, int off, String text, AttributeSet a) throws BadLocationException {
                replace(fb, off, 0, text, a);
            }
        });
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // The last command is the internal "delete" command; keyboard backspace replaces it on PC.
        int n = form.commands.size() - 1;
        JButton first = null;
        for (int i = 0; i < n; i++) {
            final Command c = form.commands.get(i);
            JButton b = new JButton(c.getLabel().length() == 0 ? "OK" : c.getLabel());
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (form.field != null) form.field.setString(tf.getText());
                    final CommandListener l = form.listener;
                    new Thread(new Runnable() { public void run() { if (l != null) l.commandAction(c, form); } }).start();
                }
            });
            if (first == null) first = b;
            buttons.add(b);
        }
        dlg.setLayout(new BorderLayout(8, 8));
        dlg.add(new JLabel(" " + form.title), BorderLayout.NORTH);
        dlg.add(tf, BorderLayout.CENTER);
        dlg.add(buttons, BorderLayout.SOUTH);
        if (first != null) dlg.getRootPane().setDefaultButton(first);
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlg.pack();
        dlg.setLocationRelativeTo(owner);
        dlg.setVisible(true);
        tf.requestFocusInWindow();
    }
}
