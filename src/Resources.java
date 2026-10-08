
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import jme.Assets;
import jme.Image;

/**
 * Resource manager.  (obfuscated name: g)
 *
 * Resource ids are the original packed ints; only the low 15 bits identify a resource.
 * assets/manifest.txt maps that number to a file under assets/ (images, audio, data), so the
 * old r0 archive and numbered loose files are gone.  Also does the string table lookup.
 */
public final class Resources {
    private static Resources instance;
    private static Map<Integer, String> manifest;   // resource id -> asset path
    private static int[] langInts;       // c  (153 ints read from the "l<N>" file)
    private static DataInputStream langStream;    // g

    private Resources() {
    }

    /** Looks up string id and substitutes args. Never throws. */
    public static synchronized String getString(int resourceId, String[] formatArgs) {
        String result;
        synchronized (Resources.class) {
            result = null;
            try {
                result = lookupString(resourceId, formatArgs);
            } catch (Exception e) {
            }
        }
        return result;
    }

    /** Loads the resource manifest (assets/manifest.txt: resource id -> file) and the language table. */
    public static void init() {
        if (instance == null) {
            instance = new Resources();
        }
        try {
            manifest = new HashMap<Integer, String>();
            BufferedReader r = new BufferedReader(new InputStreamReader(Assets.open("manifest.txt"), "UTF-8"));
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0 || line.charAt(0) == '#') continue;
                String[] p = line.split("\\s+", 2);
                if (p.length == 2) manifest.put(Integer.valueOf(p[0]), p[1]);
            }
            r.close();
            langInts = new int[153];
            loadLangFile(Storage.getSetting(2));
            if (getString(152).compareTo("0") == 0) {
                Storage.setSetting(4, 1);
            } else {
                Storage.setSetting(4, 0);
            }
        } catch (Exception e) {
        }
    }

    /** Asset path (relative to assets/) of resource 'id', or null. */
    private static String pathOf(int resourceId) {
        if (resourceId == -1 || manifest == null) return null;
        return manifest.get(Integer.valueOf(resourceId & 32767));
    }

    /** Reads the raw bytes of resource 'id' from the assets folder (null if missing). */
    public static byte[] getBytes(int resourceId) {
        String path = pathOf(resourceId);
        return path == null ? null : Assets.readBytes(path);
    }

    /** Opens a stream for resource 'id' (null if missing). */
    public static DataInputStream openStream(int resourceId) {
        String path = pathOf(resourceId);
        if (path == null) return null;
        InputStream is = Assets.open(path);
        return is == null ? null : new DataInputStream(is);
    }

    /**
     * String-id -> language-table index (+ argument array).  Reconstructed from the
     * bytecode of g.b(int,String[]) (the decompiler could not handle it).
     * Behaviour: ids with no entry map to index -1.  Some ids pass the caller's args
     * through, some build "key name" arguments (%U = soft key / '5' key label) when the
     * caller gave none, and ids 34/55 prepend the '5' key name to args[0].
     */
    private static String lookupString(int resourceId, String[] formatArgs) {
        int langIndex = -1;
        String[] langArgs = null;
        ICanvas canvas = GameMIDlet.getInstance().canvas;
        switch (resourceId) {
            case 0:
                langIndex = 30;
                break;
            case 2:
                langIndex = 1;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 3:
                langIndex = 0;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 4:
                langIndex = 43;
                break;
            case 5:
                langIndex = 39;
                break;
            case 6:
                langIndex = 37;
                break;
            case 7:
                langIndex = 31;
                break;
            case 8:
                langIndex = 32;
                break;
            case 9:
                langIndex = 44;
                break;
            case 10:
                langIndex = 38;
                break;
            case 15:
                langIndex = 41;
                break;
            case 17:
                langIndex = 40;
                break;
            case 19:
                langIndex = 36;
                break;
            case 22:
                langIndex = 34;
                break;
            case 34:
                langIndex = 23;
                if (formatArgs != null) {
                    langArgs = new String[]{canvas.getKeyName(53), formatArgs[0]};
                }
                break;
            case 35:
                langIndex = 24;
                break;
            case 36:
                langIndex = 4;
                break;
            case 37:
                langIndex = 5;
                break;
            case 38:
                langIndex = 7;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 39:
                langIndex = 19;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(52), canvas.getKeyName(54), canvas.getKeyName(50), canvas.getKeyName(56), canvas.getKeyName(53)};
                }
                break;
            case 40:
                langIndex = 18;
                break;
            case 41:
                langIndex = 8;
                langArgs = formatArgs;
                break;
            case 42:
                langIndex = 9;
                break;
            case 43:
                langIndex = 67;
                langArgs = formatArgs;
                break;
            case 44:
                langIndex = 68;
                langArgs = formatArgs;
                break;
            case 45:
                langIndex = 69;
                break;
            case 46:
                langIndex = 6;
                break;
            case 47:
                langIndex = 14;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(50), canvas.getKeyName(56)};
                }
                break;
            case 48:
                langIndex = 15;
                break;
            case 49:
                langIndex = 16;
                break;
            case 50:
                langIndex = 17;
                break;
            case 51:
                langIndex = 20;
                langArgs = formatArgs;
                break;
            case 52:
                langIndex = 13;
                break;
            case 53:
                langIndex = 66;
                langArgs = formatArgs;
                break;
            case 54:
                langIndex = 3;
                langArgs = formatArgs;
                break;
            case 55:
                langIndex = 21;
                if (formatArgs != null) {
                    langArgs = new String[]{canvas.getKeyName(53), formatArgs[0]};
                }
                break;
            case 56:
                langIndex = 70;
                break;
            case 57:
                langIndex = 22;
                break;
            case 58:
                langIndex = 10;
                langArgs = formatArgs;
                break;
            case 59:
                langIndex = 11;
                langArgs = formatArgs;
                break;
            case 60:
                langIndex = 12;
                break;
            case 62:
                langIndex = 54;
                langArgs = formatArgs;
                break;
            case 63:
                langIndex = 55;
                langArgs = formatArgs;
                break;
            case 64:
                langIndex = 50;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 65:
                langIndex = 51;
                if (formatArgs == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 66:
                langIndex = 52;
                break;
            case 67:
                langIndex = 59;
                langArgs = formatArgs;
                break;
            case 68:
                langIndex = 60;
                break;
            case 69:
                langIndex = 58;
                langArgs = formatArgs;
                break;
            case 70:
                langIndex = 56;
                break;
            case 71:
                langIndex = 71;
                break;
            case 72:
                langIndex = 72;
                break;
            case 73:
                langIndex = 73;
                break;
            case 74:
                langIndex = 74;
                break;
            case 75:
                langIndex = 75;
                break;
            case 76:
                langIndex = 76;
                break;
            case 77:
                langIndex = 77;
                break;
            case 78:
                langIndex = 78;
                break;
            case 79:
                langIndex = 79;
                break;
            case 80:
                langIndex = 45;
                break;
            case 81:
                langIndex = 28;
                break;
            case 82:
                langIndex = 65;
                break;
            case 83:
                langIndex = 91;
                break;
            case 84:
                langIndex = 85;
                break;
            case 85:
                langIndex = 87;
                break;
            case 86:
                langIndex = 89;
                break;
            case 87:
                langIndex = 92;
                break;
            case 88:
                langIndex = 86;
                break;
            case 89:
                langIndex = 88;
                break;
            case 90:
                langIndex = 90;
                break;
            case 91:
                langIndex = 64;
                break;
            case 92:
                langIndex = 63;
                break;
            case 93:
                langIndex = 83;
                langArgs = formatArgs;
                break;
            case 94:
                langIndex = 81;
                langArgs = formatArgs;
                break;
            case 95:
                langIndex = 80;
                langArgs = formatArgs;
                break;
            case 96:
                langIndex = 82;
                break;
            case 98:
                langIndex = 25;
                break;
            case 99:
                langIndex = 53;
                break;
            case 100:
                langIndex = 61;
                break;
            case 101:
                langIndex = 57;
                break;
            case 102:
                langIndex = 62;
                break;
            case 118:
                langIndex = 2;
                break;
            case 146:
                langIndex = 84;
                break;
            case 150:
                langIndex = 33;
                break;
            case 151:
                langIndex = 35;
                break;
            case 152:
                langIndex = 93;
                break;
            case 153:
                langIndex = 42;
                break;
            case 154:
                langIndex = 46;
                break;
            case 155:
                langIndex = 47;
                break;
            case 156:
                langIndex = 48;
                break;
            case 157:
                langIndex = 49;
                break;
            case 158:
                langIndex = 27;
                break;
            case 159:
                langIndex = 26;
                break;
            case 160:
                langIndex = 29;
                break;
            default:
                break;
        }
        return Lang.getString(langIndex, langArgs);
    }

    /** Unlocks/releases the open streams. */
    public static void close() {
        try {
            if (langStream != null) {
                langStream.close();
                langStream = null;
            }
        } catch (Exception e) {
        }
    }

    public static Image getImage(int resourceId) {
        Image image = null;
        if (resourceId != -1) {
            byte[] data = getBytes(resourceId);
            try {
                image = Image.createImage(data, 0, data.length);
            } catch (Exception e) {
            }
        }
        return image;
    }

    public static synchronized String getString(int resourceId) {
        String str;
        synchronized (Resources.class) {
            str = getString(resourceId, null);
        }
        return str;
    }

    /** Opens file "l<n>" (header: 7 bytes, UTF string, then 153 ints). */
    private static void loadLangFile(int langIndex) {
        try {
            InputStream is = Assets.open("lang/l" + langIndex);
            if (is != null) {
                langStream = new DataInputStream(is);
                langStream.skipBytes(7);
                langStream.readUTF();
                for (int i = 0; i < langInts.length; i++) {
                    langInts[i] = langStream.readInt();
                }
            }
        } catch (Exception e) {
        }
    }
}
