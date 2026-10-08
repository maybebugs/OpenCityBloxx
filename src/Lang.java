
import jme.Assets;
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
    public static synchronized String getString(int index, String[] formatArgs) {
        String result;
        synchronized (Lang.class) {
            try {
                if (instance == null) {
                    instance = new Lang();
                }
                if (langStream == null) {
                    InputStream inputStream = Assets.open("lang/lang." + locale);
                    if (inputStream == null) {
                        int dash = locale.indexOf('-');
                        if (dash > 0) inputStream = Assets.open("lang/lang." + locale.substring(0, dash));
                    }
                    if (inputStream == null) {
                        inputStream = Assets.open("lang/lang.en-US");
                    }
                    if (inputStream == null) {
                        inputStream = Assets.open("lang/lang.xx");
                    }
                    if (inputStream == null) {
                        return "X";
                    }
                    langStream = new DataInputStream(inputStream);
                    langStream.mark(512);
                }
                langStream.skipBytes(index * 2);
                langStream.skipBytes((langStream.readUnsignedShort() - (index * 2)) - 2);
                result = langStream.readUTF();
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
                if (formatArgs != null) {
                    if (formatArgs.length == 1) {
                        result = replaceAll(result, "%U", formatArgs[0]);
                    } else {
                        for (int i = 0; i < formatArgs.length; i++) {
                            result = replaceAll(result, "%" + i + "U", formatArgs[i]);
                        }
                    }
                }
            } catch (IOException e) {
                langStream = null;
                result = "E";
            }
        }
        return result;
    }

    private static String replaceAll(String text, String targetKey, String replacement) {
        int idx;
        do {
            idx = text.indexOf(targetKey);
            if (idx >= 0) {
                text = text.substring(0, idx) + replacement + text.substring(targetKey.length() + idx);
            }
        } while (idx >= 0);
        return text;
    }
}
