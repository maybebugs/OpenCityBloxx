import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import jme.RecordStore;

/**
 * RMS persistence helper + shared UI/menu state.  (obfuscated name: f)
 * Every record store holds exactly one record (id 1) containing the whole blob.
 */
public final class Storage {
    public static int cursor = 0;            // a: selected item in the current menu
    public static int[] menuCursors;         // b: remembered cursor per menu
    public static boolean dirty;             // c: menu/UI needs repaint
    public static boolean unused = false;    // d
    private static int menuId = 0;           // e: current menu id
    private static int screenId = 5;         // f: which Screen is active (see GameMIDlet.getScreen)
    private static String recordName = null; // g
    private static ByteArrayOutputStream writeBuffer = null; // h
    private static DataOutputStream out = null;              // i
    private static DataInputStream in = null;                // j
    private static int[] settings = new int[14];  // k
    private static int[] menuFlags = new int[12]; // l
    private static String[] texts = new String[1]; // m (texts[0] = player name)

    public static int getMenuId() {
        return menuId;
    }

    public static int getSetting(int i) {
        return settings[i];
    }

    /** Opens record store 'name' (must exist) and returns a stream over record 1, or null. */
    public static DataInputStream openRead(String name) {
        try {
            RecordStore rs = RecordStore.openRecordStore(name, false);
            in = new DataInputStream(new ByteArrayInputStream(rs.getRecord(1)));
            rs.closeRecordStore();
        } catch (Exception e) {
            in = null;
        }
        return in;
    }

    public static void setSetting(int i, int value) {
        settings[i] = value;
    }

    public static void setText(int i, String str) {
        texts[i] = str;
    }

    public static int getScreenId() {
        return screenId;
    }

    public static int getMenuFlag(int i) {
        return menuFlags[i];
    }

    /** Starts buffering a write to record store 'name'; flushed by close(). */
    public static DataOutputStream openWrite(String name) {
        try {
            recordName = name;
            writeBuffer = new ByteArrayOutputStream();
            out = new DataOutputStream(writeBuffer);
        } catch (Exception e) {
        }
        return out;
    }

    public static void setMenuFlag(int i, int value) {
        menuFlags[i] = value;
    }

    public static String getText(int i) {
        return texts[i];
    }

    /** Closes any open streams; a pending write is committed to RMS. */
    public static void close() {
        try {
            if (writeBuffer != null) {
                flush();
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                in.close();
                in = null;
            }
        } catch (Exception e) {
        }
    }

    private static void flush() {
        try {
            byte[] data = writeBuffer.toByteArray();
            writeBuffer.close();
            writeBuffer = null;
            RecordStore rs = RecordStore.openRecordStore(recordName, true);
            if (rs.getNumRecords() > 0) {
                rs.setRecord(1, data, 0, data.length);
            } else {
                rs.addRecord(data, 0, data.length);
            }
            rs.closeRecordStore();
        } catch (Exception e) {
        }
    }

    public static void setMenuId(int i) {
        menuId = i;
    }

    public static void setScreenId(int i) {
        screenId = i;
    }
}
