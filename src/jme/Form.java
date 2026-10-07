package jme;

import java.util.ArrayList;
import java.util.List;

public class Form extends Displayable {
    public final String title;
    public final List<Command> commands = new ArrayList<Command>();
    public TextField field;
    public CommandListener listener;

    public Form(String title) { this.title = title == null ? "" : title; }
    public int append(TextField f) { this.field = f; return 0; }
    public void addCommand(Command c) { commands.add(c); }
    public void setCommandListener(CommandListener l) { listener = l; }
}
