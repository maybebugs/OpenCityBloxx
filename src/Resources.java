
import java.io.DataInputStream;
import java.io.InputStream;
import jme.Image;

/**
 * Resource manager.  (obfuscated name: g)
 *
 * Resource ids are packed ints:
 *   bit 31      : standalone file (file name = decimal of low 15 bits, e.g. "80")
 *   bits 16..30 : archive number (archive file "r<n>"; only "r0" exists in this jar)
 *   bits 0..14  : index into the offset table stored at the head of "r0"
 * Offsets table: 92 ints. Entries [0..90] are resource offsets (negative value for a
 * standalone resource = -length), entry [91 + archive] is the archive size.
 */
public final class Resources {
    private static Resources instance;
    private static int[] offsets;        // b
    private static int[] langInts;       // c  (153 ints read from the "l<N>" file)
    private static int streamPos = -2;   // d  (read position inside archiveStream, -2 = none)
    private static DataInputStream archiveStream; // e
    private static String archiveName;   // f
    private static DataInputStream langStream;    // g

    private Resources() {
    }

    /** Looks up string id and substitutes args. Never throws. (a(int,String[])) */
    public static synchronized String getString(int id, String[] args) {
        String str;
        synchronized (Resources.class) {
            str = null;
            try {
                str = lookupString(id, args);
            } catch (Exception e) {
            }
        }
        return str;
    }

    /** Opens the "r0" index and reads the offset table. */
    public static void init() {
        if (instance == null) {
            instance = new Resources();
        }
        try {
            archiveName = "r0";
            archiveStream = new DataInputStream(instance.getClass().getResourceAsStream(archiveName));
            offsets = new int[92];
            for (int i = 0; i < offsets.length; i++) {
                offsets[i] = archiveStream.readInt();
            }
            streamPos = offsets.length * 4;
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

    /** Reads the raw bytes of resource 'id' (sequential reads reuse the open stream). */
    public static byte[] getBytes(int id) {
        byte[] data = null;
        if (id != -1) {
            int archive = (Integer.MAX_VALUE & id) >> 16;
            try {
                DataInputStream stream;
                if (streamPos == -2 || offsets[id & 32767] < streamPos
                        || !archiveName.equals(new StringBuffer().append("r").append(archive).toString())
                        || (id & Integer.MIN_VALUE) != 0) {
                    if (archiveStream != null) {
                        archiveStream.close();
                        archiveStream = null;
                    }
                    archiveName = new StringBuffer().append("r").append(archive).toString();
                    stream = openStream(id);
                } else {
                    archiveStream.skipBytes(offsets[id & 32767] - streamPos);
                    stream = archiveStream;
                }
                data = new byte[sizeOf(id)];
                stream.read(data);
                if ((id & Integer.MIN_VALUE) == 0) {
                    streamPos = offsets[id & 32767] + data.length;
                    archiveStream = stream;
                } else {
                    streamPos = -2;
                    stream.close();
                }
            } catch (Exception e) {
            }
        }
        return data;
    }

    /** Opens a stream positioned at the start of resource 'id'. */
    public static DataInputStream openStream(int id) {
        DataInputStream stream;
        if (id != -1) {
            StringBuffer name;
            int number;
            if ((id & Integer.MIN_VALUE) != 0) {
                try {
                    name = new StringBuffer().append("");
                    number = id & 32767;
                } catch (Exception e) {
                    stream = null;
                    streamPos = -2;
                    return stream;
                }
            } else {
                name = new StringBuffer().append("r");
                number = (Integer.MAX_VALUE & id) >> 16;
            }
            stream = new DataInputStream(instance.getClass().getResourceAsStream(name.append(number).toString()));
            if ((id & Integer.MIN_VALUE) == 0) {
                try {
                    stream.skipBytes(offsets[id & 32767]);
                } catch (Exception e2) {
                }
            }
        } else {
            stream = null;
        }
        streamPos = -2;
        return stream;
    }

    /**
     * String-id -> language-table index (+ argument array).  Reconstructed from the
     * bytecode of g.b(int,String[]) (the decompiler could not handle it).
     * Behaviour: ids with no entry map to index -1.  Some ids pass the caller's args
     * through, some build "key name" arguments (%U = soft key / '5' key label) when the
     * caller gave none, and ids 34/55 prepend the '5' key name to args[0].
     */
    private static String lookupString(int id, String[] args) {
        int langIndex = -1;
        String[] langArgs = null;
        ICanvas canvas = GameMIDlet.getInstance().canvas;
        switch (id) {
            case 0:
                langIndex = 30;
                break;
            case 2:
                langIndex = 1;
                if (args == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 3:
                langIndex = 0;
                if (args == null) {
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
                if (args != null) {
                    langArgs = new String[]{canvas.getKeyName(53), args[0]};
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
                if (args == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 39:
                langIndex = 19;
                if (args == null) {
                    langArgs = new String[]{canvas.getKeyName(52), canvas.getKeyName(54), canvas.getKeyName(50), canvas.getKeyName(56), canvas.getKeyName(53)};
                }
                break;
            case 40:
                langIndex = 18;
                break;
            case 41:
                langIndex = 8;
                langArgs = args;
                break;
            case 42:
                langIndex = 9;
                break;
            case 43:
                langIndex = 67;
                langArgs = args;
                break;
            case 44:
                langIndex = 68;
                langArgs = args;
                break;
            case 45:
                langIndex = 69;
                break;
            case 46:
                langIndex = 6;
                break;
            case 47:
                langIndex = 14;
                if (args == null) {
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
                langArgs = args;
                break;
            case 52:
                langIndex = 13;
                break;
            case 53:
                langIndex = 66;
                langArgs = args;
                break;
            case 54:
                langIndex = 3;
                langArgs = args;
                break;
            case 55:
                langIndex = 21;
                if (args != null) {
                    langArgs = new String[]{canvas.getKeyName(53), args[0]};
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
                langArgs = args;
                break;
            case 59:
                langIndex = 11;
                langArgs = args;
                break;
            case 60:
                langIndex = 12;
                break;
            case 62:
                langIndex = 54;
                langArgs = args;
                break;
            case 63:
                langIndex = 55;
                langArgs = args;
                break;
            case 64:
                langIndex = 50;
                if (args == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 65:
                langIndex = 51;
                if (args == null) {
                    langArgs = new String[]{canvas.getKeyName(53)};
                }
                break;
            case 66:
                langIndex = 52;
                break;
            case 67:
                langIndex = 59;
                langArgs = args;
                break;
            case 68:
                langIndex = 60;
                break;
            case 69:
                langIndex = 58;
                langArgs = args;
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
                langArgs = args;
                break;
            case 94:
                langIndex = 81;
                langArgs = args;
                break;
            case 95:
                langIndex = 80;
                langArgs = args;
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
            if (archiveStream != null) {
                streamPos = -2;
                archiveStream.close();
                archiveStream = null;
            }
            if (langStream != null) {
                langStream.close();
                langStream = null;
            }
        } catch (Exception e) {
        }
    }

    public static Image getImage(int id) {
        Image image = null;
        if (id != -1) {
            byte[] data = getBytes(id);
            try {
                image = Image.createImage(data, 0, data.length);
            } catch (Exception e) {
            }
        }
        return image;
    }

    public static synchronized String getString(int id) {
        String str;
        synchronized (Resources.class) {
            str = getString(id, null);
        }
        return str;
    }

    /** Size in bytes of resource 'id'. */
    private static int sizeOf(int id) {
        if (id == -1) {
            return 0;
        }
        int index = id & 32767;
        if ((Integer.MIN_VALUE & id) != 0) {
            return -offsets[index];
        }
        int next = index + 1;
        while (offsets[next] < 0) {
            next++;
        }
        return (next >= 91 || offsets[next] <= offsets[index])
                ? offsets[((Integer.MAX_VALUE & id) >> 16) + 91] - offsets[index]
                : offsets[next] - offsets[index];
    }

    /** Opens file "l<n>" (header: 7 bytes, UTF string, then 153 ints). */
    private static void loadLangFile(int n) {
        try {
            InputStream is = instance.getClass().getResourceAsStream(new StringBuffer().append("l").append(n).toString());
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
