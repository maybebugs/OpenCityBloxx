import jme.Command;
import jme.Graphics;

/** A screen that GameMIDlet can route input/paint to. (obfuscated: e) */
public interface Screen {
    void update(int delta, int time);               // a(int,int)
    void commandAction(Command command);            // a(Command)
    void paint(Graphics g, boolean fullRedraw);     // a(Graphics,boolean)
    void keyPressed(int keyCode, int gameAction);   // b(int,int)
    void onEnter();                                 // m()
    void onModeChange();                              // n()
}
