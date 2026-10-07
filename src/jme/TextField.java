package jme;

/** Simple text buffer used by the name-entry dialog. */
public class TextField {
    private StringBuilder text;
    private final int maxSize;
    public int caret;

    public TextField(String label, String text, int maxSize, int constraints) {
        this.maxSize = maxSize;
        this.text = new StringBuilder(text == null ? "" : text);
        if (this.text.length() > maxSize) this.text.setLength(maxSize);
        this.caret = this.text.length();
    }

    public String getString() { return text.toString(); }
    public void setString(String s) {
        text = new StringBuilder(s == null ? "" : s);
        if (text.length() > maxSize) text.setLength(maxSize);
        caret = text.length();
    }
    public int getCaretPosition() { return caret; }
    public int size() { return text.length(); }
    public int getMaxSize() { return maxSize; }
    public void delete(int offset, int length) {
        if (offset >= 0 && offset + length <= text.length()) text.delete(offset, offset + length);
        if (caret > text.length()) caret = text.length();
    }
}
