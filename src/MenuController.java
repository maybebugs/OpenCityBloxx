

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
    private static int[] palette;
    private static int[][] entries;
    private static String[] optionTexts;
    private static int[] valueData;
    private static int entryCount;
    private static int nextMenuId;
    private static int backMenuId;
    private static int menuType;
    private static DataInputStream menuData;
    private static boolean needsParse;
    private static boolean selectPressed;
    private static boolean backPressed;
    private static int moveDir;
    private static int sideDir;
    private static ICanvas canvas;
    private static Command[] commands;
    private static boolean screenChangePending;
    private static int targetScreenId;
    private static TextField nameField;
    Command deleteCommand = new Command(Resources.getString(150), 2, 0);
    private StringBuffer inputBuffer;
    private int textSlot;
    private Form nameForm;
    private TextField nameInput;
    private int caretPos;

    public MenuController(ICanvas canvasArg) {
        int k = 0;
        try {
            canvas = canvasArg;
            menuData = new DataInputStream(getClass().getResourceAsStream("m"));
            palette = new int[9];
            int d = MenuController.readValue(2);
            MenuController.readValue((-d) * 2);
            while (k < 9) {
                palette[k] = MenuController.readValue(4);
                k++;
            }
            menuData.close();
            menuData = null;
            Storage.menuCursors = new int[d];
            showMenu(6);
        } catch (Exception e) {
        }
    }

    static int setCaretPos(MenuController mc, int value) {
        mc.caretPos = value;
        return value;
    }

    static Form setNameForm(MenuController mc, Form form) {
        mc.nameForm = form;
        return form;
    }

    static TextField getNameInput(MenuController mc) {
        return mc.nameInput;
    }

    static TextField setNameInput(MenuController mc, TextField field) {
        mc.nameInput = field;
        return field;
    }

    public static int[] getMenuHierarchy() {
        return palette;
    }

    static int getCaretPos(MenuController mc) {
        return mc.caretPos;
    }

    static int setTextSlot(MenuController mc, int value) {
        mc.textSlot = value;
        return value;
    }

    public static String getPlayerName() {
        return nameField.getString().trim().replace('\n', ' ');
    }

    private void showMenu(int menuId) {
        int k = 0;
        if (menuId == 19) {
            executeActions(2);
            return;
        }
        menuData = new DataInputStream(getClass().getResourceAsStream("m"));
        MenuController.readValue(-((menuId * 2) - 1));
        MenuController.readValue(-MenuController.readValue(3));
        int type = MenuController.readValue(2);
        MenuController.menuType = type;
        if (type == 7) {
            MenuController.readValue(2);
            MenuController.readValue(-Storage.getSetting(MenuController.readValue(2)));
            showMenu(MenuController.readValue(2));
        } else {
            nextMenuId = MenuController.readValue(2);
            backMenuId = MenuController.readValue(2);
            switch (type) {
                case 4:
                    targetScreenId = 1;
                    break;
                case 5:
                    targetScreenId = 2;
                    break;
                case 6:
                    targetScreenId = 3;
                    break;
                default:
                    targetScreenId = 0;
                    needsParse = true;
                    break;
            }
            if (Storage.getScreenId() != targetScreenId) {
                screenChangePending = true;
            }
            Storage.setMenuId(menuId);
        }
        if (commands != null) {
            while (k < commands.length) {
                if (commands[k] != null) {
                    canvas.removeCommand(commands[k]);
                }
                k++;
            }
        }
        commands = null;
    }

    static int getTextSlot(MenuController mc) {
        return mc.textSlot;
    }

    static ICanvas getCanvas() {
        return canvas;
    }

    private void executeActions(int actionFlags) {
        int bit = 7;
        int remaining = actionFlags;
        while (bit >= 0) {
            int row;
            int mask = 1;
            for (row = 0; row < bit; row++) {
                mask <<= 1;
            }
            if (remaining >= mask) {
                row = entries[Storage.cursor][1];
                switch (bit) {
                    case 0:
                        Storage.menuCursors[row - 1] = 0;
                        showMenu(row);
                        break;
                    case 1:
                        targetScreenId = 4;
                        screenChangePending = true;
                        break;
                    case 2:
                        row = (Storage.getSetting(entries[Storage.cursor][0]) + sideDir) % entries[Storage.cursor][3];
                        if (row < 0) {
                            row = entries[Storage.cursor][3] - 1;
                        }
                        Storage.setSetting(entries[Storage.cursor][0], row);
                        Ui.setMenuItem(Storage.cursor, null, optionTexts[row + entries[Storage.cursor][4]], 0);
                        Ui.update(0);
                        break;
                    case 5:
                        int count = entries[Storage.cursor][5];
                        int start = entries[Storage.cursor][6];
                        for (row = 0; row < count; row += 2) {
                            Storage.setSetting(valueData[start + row], valueData[(start + row) + 1]);
                        }
                        break;
                }
                row = remaining - mask;
            } else {
                row = remaining;
            }
            bit--;
            remaining = row;
        }
    }

    /** Reads a value from the menu data stream: 1=byte, 2=ubyte, 3=short, 4=int, negative n = skip -n bytes. */
    private static int readValue(int byteCount) {
        int value = 0;
        try {
            switch (byteCount) {
                case 1:
                    value = menuData.readByte();
                    break;
                case 2:
                    value = menuData.readUnsignedByte();
                    break;
                case 3:
                    value = menuData.readShort();
                    break;
                case 4:
                    value = menuData.readInt();
                    break;
                default:
                    menuData.skipBytes(-byteCount);
                    break;
            }
        } catch (Exception e) {
        }
        return value;
    }

    /** Parses one menu definition from the "m" data stream. */
    private void parseMenu() {
        int style = MenuController.readValue(2);
        int titleId = MenuController.readValue(3);
        entries = (int[][]) Array.newInstance(Integer.TYPE, new int[]{1, 8});
        if (menuType == 2) {
            // message box menu
            Ui.openTextPanel(20, 0, GameMIDlet.screenWidth - 40, GameMIDlet.screenHeight - 0, 255, style, new int[]{-1, -1, -1});
            Ui.setText(Resources.getString(titleId));
            return;
        }
        int iconId = MenuController.readValue(4);
        Image icon = Resources.getImage(iconId);
        String title = Resources.getString(titleId);
        MenuController.readValue(1);
        int commandCount = MenuController.readValue(2);
        commands = new Command[commandCount];
        for (int k2 = 0; k2 < commandCount; k2++) {
            commands[k2] = new Command(Resources.getString(MenuController.readValue(3)), MenuController.readValue(2), 1);
        }
        if (menuType == 0) {
            // list menu
            entryCount = 0;
            if (Storage.getMenuId() != 18) {
                int total = MenuController.readValue(2);
                Ui.initMenuList(total, icon, title, style, 0, 0, -1, -1, null);
                entries = (int[][]) Array.newInstance(Integer.TYPE, new int[]{total, 8});
                optionTexts = new String[MenuController.readValue(2)];
                valueData = new int[MenuController.readValue(2)];
                int optionIndex = 0;
                int valueIndex = 0;
                for (int n = 0; n < total; n++) {
                    int itemId = MenuController.readValue(2);
                    int state = MenuController.readValue(2);
                    if (state != 0) {
                        state = Storage.getMenuFlag(itemId);
                    }
                    entries[entryCount][7] = state;
                    if (state != 1) {
                        entries[entryCount][0] = itemId;
                        String text = Resources.getString(MenuController.readValue(3));
                        Image itemIcon = Resources.getImage(MenuController.readValue(4));
                        entries[entryCount][1] = MenuController.readValue(2);
                        entries[entryCount][2] = MenuController.readValue(2);
                        int optionCount = MenuController.readValue(2);
                        entries[entryCount][3] = optionCount;
                        entries[entryCount][4] = optionIndex;
                        if (optionCount > 0) {
                            entries[entryCount][0] = MenuController.readValue(2); // setting index
                            int selected = optionIndex + Storage.getSetting(entries[entryCount][0]);
                            for (int o2 = 0; o2 < optionCount; o2++) {
                                optionTexts[optionIndex] = new StringBuffer().append(text).append(" [").append(Resources.getString(MenuController.readValue(3))).append("]").toString();
                                optionIndex++;
                            }
                            text = optionTexts[selected];
                        }
                        int valueCount = MenuController.readValue(2);
                        entries[entryCount][5] = valueCount;
                        entries[entryCount][6] = valueIndex;
                        if (valueCount > 0) {
                            for (int v2 = 0; v2 < valueCount; v2++) {
                                valueData[valueIndex] = MenuController.readValue(2);
                                valueIndex++;
                            }
                        }
                        Ui.setMenuItem(entryCount, itemIcon, text, state);
                        entryCount++;
                    } else {
                        // hidden entry: skip its data
                        MenuController.readValue(-8);
                        int skipCount = MenuController.readValue(2);
                        MenuController.readValue(-(Math.min(1, skipCount) + (skipCount * 2)));
                        int skipLen = MenuController.readValue(2);
                        MenuController.readValue(-skipLen);
                    }
                }
            }
        } else {
            // text / box menu
            String text = null;
            if (menuType == 1) {
                text = Resources.getString(MenuController.readValue(3));
            }
            entries[0][7] = 0;
            entries[0][1] = nextMenuId;
            entries[0][2] = MenuController.readValue(2);
            int valueCount = MenuController.readValue(2);
            entries[0][5] = valueCount;
            valueData = new int[valueCount * 2];
            for (int n = 0; n < valueCount; n++) {
                valueData[n] = MenuController.readValue(2);
            }
            Ui.openDialog(icon, title, style, 0, 0, -1, -1, null);
            Ui.setText(text);
        }
        try {
            menuData.close();
        } catch (Exception ex) {
        }
        menuData = null;
        Storage.cursor = Storage.menuCursors[Storage.getMenuId() - 1];
        this.installCommands();
        Ui.update(0);
    }

    private void installCommands() {
        if (menuType != 3) {
            int i = 0;
            while (i < commands.length) {
                canvas.removeCommand(commands[i]);
                if (i != 3 || this.inputBuffer.length() == 0 || commands[i].getCommandType() != 2) {
                    canvas.setCommand(commands[i], null);
                }
                i++;
            }
        }
    }

    public final void gotoMenu(int which) {
        int targetMenuId;
        Storage.setScreenId(0);
        switch (which) {
            case 1:
                targetMenuId = nextMenuId;
                break;
            case 2:
                targetMenuId = backMenuId;
                break;
            default:
                return;
        }
        showMenu(targetMenuId);
    }

    public final void update(int delta, int time) {
        if (needsParse) {
            parseMenu();
            needsParse = false;
            moveDir = 0;
            sideDir = 0;
            selectPressed = false;
        }
        if (moveDir != 0) {
            if (menuType == 0) {
                if (Storage.cursor + moveDir >= 0 && Storage.cursor + moveDir < entryCount) {
                    Storage.cursor += moveDir;
                    Ui.update(moveDir);
                    Storage.menuCursors[Storage.getMenuId() - 1] = Storage.cursor;
                }
            } else if (menuType == 2 && moveDir == 1 && Ui.isOnLastPage()) {
                Ui.clearDialog();
                showMenu(backMenuId);
            } else {
                Ui.update(moveDir);
            }
            moveDir = 0;
        }
        if (sideDir != 0) {
            if (menuType == 0 && Storage.cursor < entries.length && entries[Storage.cursor][2] == 4 && entries[Storage.cursor][7] == 0) {
                executeActions(4);
            }
            sideDir = 0;
        }
        if (selectPressed) {
            selectPressed = false;
            if (menuType == 2) {
                Ui.clearDialog();
                showMenu(backMenuId);
            } else if (entries[Storage.cursor][7] == 0) {
                if (entries[Storage.cursor][2] == 4) {
                    sideDir = 1;
                    executeActions(entries[Storage.cursor][2]);
                    sideDir = 0;
                } else {
                    executeActions(entries[Storage.cursor][2]);
                }
            }
        }
        if (backPressed) {
            showMenu(backMenuId);
            backPressed = false;
        }
    }

    public final void enterSubMenu(int menuType, int menuParam, Command[] cmds) {
        int inputMode = 3;
        menuType = menuType;
        entryCount = menuParam;
        Storage.cursor = 0;
        if (menuType == 3) {
            TextField field;
            MenuController self;
            switch (menuParam) {
                case 1:
                    break;
                default:
                    inputMode = 0;
                    break;
            }
            this.nameForm = new Form(Resources.getString(146));
            if (Storage.getText(0).equals("-")) {
                field = new TextField("", "", 10, inputMode);
                self = this;
            } else {
                field = new TextField("", Storage.getText(0), 10, inputMode);
                self = this;
            }
            self.nameInput = field;
            this.nameForm.append(this.nameInput);
            for (Command addCommand : cmds) {
                this.nameForm.addCommand(addCommand);
            }
            this.nameForm.addCommand(this.deleteCommand);
            this.nameForm.setCommandListener(new CommandListener() {
                public void commandAction(Command command, Displayable displayable) {
                    if (command == MenuController.this.deleteCommand) {
                        // delete command: remove the character before the caret
                        MenuController.this.caretPos = MenuController.this.nameInput.getCaretPosition();
                        if (MenuController.this.caretPos != 0 && MenuController.this.nameInput.size() != 0) {
                            MenuController.this.nameInput.delete(MenuController.this.caretPos - 1, 1);
                            return;
                        }
                        return;
                    }
                    // OK command: store the entered name and return to the game
                    MenuController.this.textSlot = 0;
                    Storage.setText(MenuController.this.textSlot, MenuController.this.nameInput.getString().trim().replace('\n', ' '));
                    MenuController.this.nameForm = null;
                    MenuController.this.nameInput = null;
                    MenuController.getCanvas().setActive(true);
                    GameMIDlet.getInstance().handleCommand(command);
                }
            });
            nameField = this.nameInput;
            canvas.setActive(false);
            Display.getDisplay(GameMIDlet.getInstance()).setCurrent(this.nameForm);
        }
        commands = cmds;
        if (cmds != null) {
            installCommands();
        }
    }

    public final void commandAction(Command command) {
        switch (command.getCommandType()) {
            case 2:
                if (menuType == 0 || menuType == 1 || menuType == 3) {
                    backPressed = true;
                    return;
                }
                return;
            case 4:
                selectPressed = true;
                return;
            case 7:
                executeActions(2);
                return;
            default:
                return;
        }
    }

    public final void paint(Graphics g, boolean fullRedraw) {
        Ui.paint(g);
    }

    public final void keyPressed(int keyCode, int gameAction) {
        int unused = -1;
        if (keyCode == 50) {
            moveDir = -1;
        } else if (keyCode == 56) {
            moveDir = 1;
        } else if (keyCode == 53) {
            selectPressed = true;
        } else {
            if (keyCode != 52) {
                if (keyCode != 54) {
                    if ((keyCode < 48 || keyCode > 57) && keyCode != 35 && keyCode != 42) {
                        if (gameAction == 1) {
                            moveDir = -1;
                            return;
                        } else if (gameAction == 6) {
                            moveDir = 1;
                            return;
                        } else if (gameAction == 8) {
                            selectPressed = true;
                            return;
                        } else if (gameAction != 2) {
                            if (gameAction != 5) {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
                unused = 1;
            }
            sideDir = unused;
        }
    }

    public final void onEnter() {
    }

    public void selectItem(int index) {
        if (index >= 0 && index < entryCount) {
            Storage.cursor = index;
            if (Storage.getMenuId() > 0 && Storage.getMenuId() - 1 < Storage.menuCursors.length) {
                Storage.menuCursors[Storage.getMenuId() - 1] = index;
            }
            Ui.setCursor(index);
            selectPressed = true;
        }
    }

    public final void onModeChange() {
        if (screenChangePending) {
            Ui.clearDialog();
            Storage.setScreenId(targetScreenId);
            screenChangePending = false;
        }
    }
}
