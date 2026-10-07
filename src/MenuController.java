

import java.io.DataInputStream;
import java.lang.reflect.Array;
import jme.Command;
import jme.CommandListener;
import jme.Displayable;
import jme.Display;
import jme.Form;
import jme.Graphics;
import jme.Image;
import jme.TextField;

public final class MenuController implements Screen {
    private static int[] b;
    private static int[][] c;
    private static String[] d;
    private static int[] e;
    private static int f;
    private static int g;
    private static int h;
    private static int i;
    private static DataInputStream j;
    private static boolean k;
    private static boolean l;
    private static boolean m;
    private static int n;
    private static int o;
    private static ICanvas p;
    private static Command[] q;
    private static boolean r;
    private static int s;
    private static TextField x;
    Command a = new Command(Resources.getString(150), 2, 0);
    private StringBuffer t;
    private int u;
    private Form v;
    private TextField w;
    private int y;

    public MenuController(ICanvas mVar) {
        int i = 0;
        try {
            p = mVar;
            j = new DataInputStream(getClass().getResourceAsStream("m"));
            b = new int[9];
            int d = MenuController.d(2);
            MenuController.d((-d) * 2);
            while (i < 9) {
                b[i] = MenuController.d(4);
                i++;
            }
            j.close();
            j = null;
            Storage.menuCursors = new int[d];
            b(6);
        } catch (Exception e) {
        }
    }

    static int a(MenuController jVar, int i) {
        jVar.y = i;
        return i;
    }

    static Form a(MenuController jVar, Form form) {
        jVar.v = form;
        return form;
    }

    static TextField a(MenuController jVar) {
        return jVar.w;
    }

    static TextField a(MenuController jVar, TextField textField) {
        jVar.w = textField;
        return textField;
    }

    public static int[] getMenuHierarchy() {
        return b;
    }

    static int b(MenuController jVar) {
        return jVar.y;
    }

    static int b(MenuController jVar, int i) {
        jVar.u = i;
        return i;
    }

    public static String getPlayerName() {
        return x.getString().trim().replace('\n', ' ');
    }

    private void b(int menuId) {
        int i2 = 0;
        if (menuId == 19) {
            c(2);
            return;
        }
        j = new DataInputStream(getClass().getResourceAsStream("m"));
        MenuController.d(-((menuId * 2) - 1));
        MenuController.d(-MenuController.d(3));
        int type = MenuController.d(2);
        MenuController.i = type;
        if (type == 7) {
            MenuController.d(2);
            MenuController.d(-Storage.getSetting(MenuController.d(2)));
            b(MenuController.d(2));
        } else {
            g = MenuController.d(2);
            h = MenuController.d(2);
            switch (type) {
                case 4:
                    s = 1;
                    break;
                case 5:
                    s = 2;
                    break;
                case 6:
                    s = 3;
                    break;
                default:
                    s = 0;
                    k = true;
                    break;
            }
            if (Storage.getScreenId() != s) {
                r = true;
            }
            Storage.setMenuId(menuId);
        }
        if (q != null) {
            while (i2 < q.length) {
                if (q[i2] != null) {
                    p.removeCommand(q[i2]);
                }
                i2++;
            }
        }
        q = null;
    }

    static int c(MenuController jVar) {
        return jVar.u;
    }

    static ICanvas c() {
        return p;
    }

    private void c(int i) {
        int i2 = 7;
        int i3 = i;
        while (i2 >= 0) {
            int i4;
            int i5 = 1;
            for (i4 = 0; i4 < i2; i4++) {
                i5 <<= 1;
            }
            if (i3 >= i5) {
                i4 = c[Storage.cursor][1];
                switch (i2) {
                    case 0:
                        Storage.menuCursors[i4 - 1] = 0;
                        b(i4);
                        break;
                    case 1:
                        s = 4;
                        r = true;
                        break;
                    case 2:
                        i4 = (Storage.getSetting(c[Storage.cursor][0]) + o) % c[Storage.cursor][3];
                        if (i4 < 0) {
                            i4 = c[Storage.cursor][3] - 1;
                        }
                        Storage.setSetting(c[Storage.cursor][0], i4);
                        Ui.setMenuItem(Storage.cursor, null, d[i4 + c[Storage.cursor][4]], 0);
                        Ui.update(0);
                        break;
                    case 5:
                        int i6 = c[Storage.cursor][5];
                        int i7 = c[Storage.cursor][6];
                        for (i4 = 0; i4 < i6; i4 += 2) {
                            Storage.setSetting(e[i7 + i4], e[(i7 + i4) + 1]);
                        }
                        break;
                }
                i4 = i3 - i5;
            } else {
                i4 = i3;
            }
            i2--;
            i3 = i4;
        }
    }

    /** Reads a value from the menu data stream: 1=byte, 2=ubyte, 3=short, 4=int, negative n = skip -n bytes. */
    private static int d(int size) {
        int value = 0;
        try {
            switch (size) {
                case 1:
                    value = j.readByte();
                    break;
                case 2:
                    value = j.readUnsignedByte();
                    break;
                case 3:
                    value = j.readShort();
                    break;
                case 4:
                    value = j.readInt();
                    break;
                default:
                    j.skipBytes(-size);
                    break;
            }
        } catch (Exception e) {
        }
        return value;
    }

    /** Parses one menu definition from the "m" data stream (private void d()). */
    private void d() {
        int style = MenuController.d(2);
        int titleId = MenuController.d(3);
        c = (int[][]) Array.newInstance(Integer.TYPE, new int[]{1, 8});
        if (i == 2) {
            // message box menu
            Ui.setLayout(20, 0, GameMIDlet.screenWidth - 40, GameMIDlet.screenHeight - 0, 255, style, new int[]{-1, -1, -1});
            Ui.setTitle(Resources.getString(titleId));
            return;
        }
        int iconId = MenuController.d(4);
        Image icon = Resources.getImage(iconId);
        String title = Resources.getString(titleId);
        MenuController.d(1);
        int commandCount = MenuController.d(2);
        q = new Command[commandCount];
        for (int k2 = 0; k2 < commandCount; k2++) {
            q[k2] = new Command(Resources.getString(MenuController.d(3)), MenuController.d(2), 1);
        }
        if (i == 0) {
            // list menu
            f = 0;
            if (Storage.getMenuId() != 18) {
                int entryCount = MenuController.d(2);
                Ui.initMenuList(entryCount, icon, title, style, 0, 0, -1, -1, null);
                c = (int[][]) Array.newInstance(Integer.TYPE, new int[]{entryCount, 8});
                d = new String[MenuController.d(2)];
                e = new int[MenuController.d(2)];
                int optionIndex = 0;
                int valueIndex = 0;
                for (int n = 0; n < entryCount; n++) {
                    int itemId = MenuController.d(2);
                    int state = MenuController.d(2);
                    if (state != 0) {
                        state = Storage.getMenuFlag(itemId);
                    }
                    c[f][7] = state;
                    if (state != 1) {
                        c[f][0] = itemId;
                        String text = Resources.getString(MenuController.d(3));
                        Image itemIcon = Resources.getImage(MenuController.d(4));
                        c[f][1] = MenuController.d(2);
                        c[f][2] = MenuController.d(2);
                        int optionCount = MenuController.d(2);
                        c[f][3] = optionCount;
                        c[f][4] = optionIndex;
                        if (optionCount > 0) {
                            c[f][0] = MenuController.d(2); // setting index
                            int selected = optionIndex + Storage.getSetting(c[f][0]);
                            for (int o2 = 0; o2 < optionCount; o2++) {
                                d[optionIndex] = new StringBuffer().append(text).append(" [").append(Resources.getString(MenuController.d(3))).append("]").toString();
                                optionIndex++;
                            }
                            text = d[selected];
                        }
                        int valueCount = MenuController.d(2);
                        c[f][5] = valueCount;
                        c[f][6] = valueIndex;
                        if (valueCount > 0) {
                            for (int v2 = 0; v2 < valueCount; v2++) {
                                e[valueIndex] = MenuController.d(2);
                                valueIndex++;
                            }
                        }
                        Ui.setMenuItem(f, itemIcon, text, state);
                        f++;
                    } else {
                        // hidden entry: skip its data
                        MenuController.d(-8);
                        int skipCount = MenuController.d(2);
                        MenuController.d(-(Math.min(1, skipCount) + (skipCount * 2)));
                        int skipLen = MenuController.d(2);
                        MenuController.d(-skipLen);
                    }
                }
            }
        } else {
            // text / box menu
            String text = null;
            if (i == 1) {
                text = Resources.getString(MenuController.d(3));
            }
            c[0][7] = 0;
            c[0][1] = g;
            c[0][2] = MenuController.d(2);
            int valueCount = MenuController.d(2);
            c[0][5] = valueCount;
            e = new int[valueCount * 2];
            for (int n = 0; n < valueCount; n++) {
                e[n] = MenuController.d(2);
            }
            Ui.openDialog(icon, title, style, 0, 0, -1, -1, null);
            Ui.setTitle(text);
        }
        try {
            j.close();
        } catch (Exception ex) {
        }
        j = null;
        Storage.cursor = Storage.menuCursors[Storage.getMenuId() - 1];
        this.e();
        Ui.update(0);
    }

    private void e() {
        if (i != 3) {
            int i = 0;
            while (i < q.length) {
                p.removeCommand(q[i]);
                if (i != 3 || this.t.length() == 0 || q[i].getCommandType() != 2) {
                    p.setCommand(q[i], null);
                }
                i++;
            }
        }
    }

    public final void a(int i) {
        int i2;
        Storage.setScreenId(0);
        switch (i) {
            case 1:
                i2 = g;
                break;
            case 2:
                i2 = h;
                break;
            default:
                return;
        }
        b(i2);
    }

    public final void update(int dt, int time) {
        if (k) {
            d();
            k = false;
            n = 0;
            o = 0;
            l = false;
        }
        if (n != 0) {
            if (i == 0) {
                if (Storage.cursor + n >= 0 && Storage.cursor + n < f) {
                    Storage.cursor += n;
                    Ui.update(n);
                    Storage.menuCursors[Storage.getMenuId() - 1] = Storage.cursor;
                }
            } else if (i == 2 && n == 1 && Ui.isDialogActive()) {
                Ui.clearDialog();
                b(h);
            } else {
                Ui.update(n);
            }
            n = 0;
        }
        if (o != 0) {
            if (i == 0 && Storage.cursor < c.length && c[Storage.cursor][2] == 4 && c[Storage.cursor][7] == 0) {
                c(4);
            }
            o = 0;
        }
        if (l) {
            l = false;
            if (i == 2) {
                Ui.clearDialog();
                b(h);
            } else if (c[Storage.cursor][7] == 0) {
                if (c[Storage.cursor][2] == 4) {
                    o = 1;
                    c(c[Storage.cursor][2]);
                    o = 0;
                } else {
                    c(c[Storage.cursor][2]);
                }
            }
        }
        if (m) {
            b(h);
            m = false;
        }
    }

    public final void a(int menuType, int i2, Command[] commandArr) {
        int i3 = 3;
        i = menuType;
        f = i2;
        Storage.cursor = 0;
        if (i == 3) {
            TextField textField;
            MenuController jVar;
            switch (i2) {
                case 1:
                    break;
                default:
                    i3 = 0;
                    break;
            }
            this.v = new Form(Resources.getString(146));
            if (Storage.getText(0).equals("-")) {
                textField = new TextField("", "", 10, i3);
                jVar = this;
            } else {
                textField = new TextField("", Storage.getText(0), 10, i3);
                jVar = this;
            }
            jVar.w = textField;
            this.v.append(this.w);
            for (Command addCommand : commandArr) {
                this.v.addCommand(addCommand);
            }
            this.v.addCommand(this.a);
            this.v.setCommandListener(new CommandListener() {
                public void commandAction(Command command, Displayable displayable) {
                    if (command == MenuController.this.a) {
                        // delete command: remove the character before the caret
                        MenuController.this.y = MenuController.this.w.getCaretPosition();
                        if (MenuController.this.y != 0 && MenuController.this.w.size() != 0) {
                            MenuController.this.w.delete(MenuController.this.y - 1, 1);
                            return;
                        }
                        return;
                    }
                    // OK command: store the entered name and return to the game
                    MenuController.this.u = 0;
                    Storage.setText(MenuController.this.u, MenuController.this.w.getString().trim().replace('\n', ' '));
                    MenuController.this.v = null;
                    MenuController.this.w = null;
                    MenuController.c().setActive(true);
                    GameMIDlet.getInstance().handleCommand(command);
                }
            });
            x = this.w;
            p.setActive(false);
            Display.getDisplay(GameMIDlet.getInstance()).setCurrent(this.v);
        }
        q = commandArr;
        if (commandArr != null) {
            e();
        }
    }

    public final void commandAction(Command command) {
        switch (command.getCommandType()) {
            case 2:
                if (i == 0 || i == 1 || i == 3) {
                    m = true;
                    return;
                }
                return;
            case 4:
                l = true;
                return;
            case 7:
                c(2);
                return;
            default:
                return;
        }
    }

    public final void paint(Graphics graphics, boolean z) {
        Ui.paint(graphics);
    }

    public final void keyPressed(int i, int i2) {
        int i3 = -1;
        if (i == 50) {
            n = -1;
        } else if (i == 56) {
            n = 1;
        } else if (i == 53) {
            l = true;
        } else {
            if (i != 52) {
                if (i != 54) {
                    if ((i < 48 || i > 57) && i != 35 && i != 42) {
                        if (i2 == 1) {
                            n = -1;
                            return;
                        } else if (i2 == 6) {
                            n = 1;
                            return;
                        } else if (i2 == 8) {
                            l = true;
                            return;
                        } else if (i2 != 2) {
                            if (i2 != 5) {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
                i3 = 1;
            }
            o = i3;
        }
    }

    public final void onEnter() {
    }

    public void selectItem(int index) {
        if (index >= 0 && index < f) {
            Storage.cursor = index;
            if (Storage.getMenuId() > 0 && Storage.getMenuId() - 1 < Storage.menuCursors.length) {
                Storage.menuCursors[Storage.getMenuId() - 1] = index;
            }
            Ui.setCursor(index);
            l = true;
        }
    }

    public final void onModeChange() {
        if (r) {
            Ui.clearDialog();
            Storage.setScreenId(s);
            r = false;
        }
    }
}
