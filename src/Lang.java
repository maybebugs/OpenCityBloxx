
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Localisation + platform lock.  (obfuscated name: com.nokia.mid.appl.bloxx.a)
 *
 * - lang.<locale> is a table of big-endian ushort offsets followed by modified-UTF
 *   strings; entry i is located by skipping to offset-table slot i.
 * - On class load, the MANIFEST "Nokia-Platform:" attribute (a '@' separated list of
 *   wildcard patterns) is matched against microedition.platform; no match => exit.
 */
public final class Lang {
    public static final String locale = java.util.Locale.getDefault().toLanguageTag();
    private static Lang instance = null;
    private static DataInputStream langStream = null;


    private Lang() {
    }

    /** Returns language string #index with %U / %0U %1U ... replaced by args. */
    public static synchronized String getString(int index, String[] args) {
        String str;
        synchronized (Lang.class) {
            try {
                if (instance == null) {
                    instance = new Lang();
                }
                if (langStream == null) {
                    InputStream is = instance.getClass().getResourceAsStream(new StringBuffer().append("/lang.").append(locale).toString());
                    if (is == null) {
                        int dash = locale.indexOf('-');
                        if (dash > 0) is = instance.getClass().getResourceAsStream("/lang." + locale.substring(0, dash));
                    }
                    if (is == null) {
                        is = instance.getClass().getResourceAsStream("/lang.en-US");
                    }
                    if (is == null) {
                        is = instance.getClass().getResourceAsStream("/lang.xx");
                    }
                    if (is == null) {
                        return "X";
                    }
                    langStream = new DataInputStream(is);
                    langStream.mark(512);
                }
                langStream.skipBytes(index * 2);
                langStream.skipBytes((langStream.readUnsignedShort() - (index * 2)) - 2);
                str = langStream.readUTF();
                if (langStream.markSupported()) {
                    try {
                        langStream.reset();
                    } catch (IOException e) {
                        langStream.close();
                        langStream = null;
                    }
                } else {
                    langStream.close();
                    langStream = null;
                }
                if (args != null) {
                    if (args.length == 1) {
                        str = replaceAll(str, "%U", args[0]);
                    } else {
                        for (int i = 0; i < args.length; i++) {
                            str = replaceAll(str, new StringBuffer().append("%").append(i).append("U").toString(), args[i]);
                        }
                    }
                }
            } catch (IOException e2) {
                langStream = null;
                str = "E";
            }
        }
        return str;
    }

    private static String replaceAll(String str, String key, String value) {
        int idx;
        do {
            idx = str.indexOf(key);
            if (idx >= 0) {
                str = new StringBuffer().append(str.substring(0, idx)).append(value).append(str.substring(key.length() + idx)).toString();
            }
        } while (idx >= 0);
        return str;
    }
}
