import jme.Command;
import jme.Image;

/** Canvas abstraction used by GameMIDlet. (obfuscated: m) */
public interface ICanvas {
    void setMidlet(GameMIDlet midlet);             // a(GameMIDlet)
    void removeCommand(Command command);           // a(Command)
    void setCommand(Command command, Image image); // a(Command,Image)
    void setActive(boolean active);                // a(boolean)
    int getGameAction(int keyCode);
    int getHeight();
    String getKeyName(int keyCode);
    int getWidth();
}
