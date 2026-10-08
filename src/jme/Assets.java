package jme;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Single entry point for reading game data from the assets/ folder.
 *
 * Lookup order for the assets root:
 *   1. -Dbloxx.assets=&lt;dir&gt;   (or env BLOXX_ASSETS)
 *   2. ./assets                 (current working directory)
 *   3. &lt;folder of the jar / class files&gt;/assets, then its parent's /assets
 *   4. classpath resource /assets/... (the build scripts also bundle assets/ into the jar)
 * A root is accepted only if it contains manifest.txt.
 * All paths are relative to the assets root and use '/' separators, e.g. "images/ui/arrow.png".
 */
public final class Assets {
    private static File root;
    private static boolean resolved;

    private Assets() {
    }

    private static boolean valid(File dir) {
        return dir != null && new File(dir, "manifest.txt").isFile();
    }

    private static synchronized File root() {
        if (resolved) return root;
        resolved = true;
        String prop = System.getProperty("bloxx.assets");
        if (prop == null) prop = System.getenv("BLOXX_ASSETS");
        File[] candidates = new File[4];
        if (prop != null) candidates[0] = new File(prop);
        candidates[1] = new File("assets");
        try {
            File code = new File(Assets.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File base = code.isDirectory() ? code : code.getParentFile();
            if (base != null) {
                candidates[2] = new File(base, "assets");
                if (base.getParentFile() != null) candidates[3] = new File(base.getParentFile(), "assets");
            }
        } catch (Exception e) {
            // ignore: fall through to other candidates
        }
        for (File c : candidates) {
            if (valid(c)) {
                root = c;
                break;
            }
        }
        return root;
    }

    /** Opens an asset; returns null if it does not exist. */
    public static InputStream open(String path) {
        if (path == null) return null;
        String clean = path.replace('\\', '/');
        while (clean.startsWith("/")) clean = clean.substring(1);
        if (clean.contains("..")) return null;
        File r = root();
        try {
            if (r != null) {
                File f = new File(r, clean);
                return f.isFile() ? new FileInputStream(f) : null;
            }
        } catch (IOException e) {
            return null;
        }
        return Assets.class.getResourceAsStream("/assets/" + clean);
    }

    /** Reads a whole asset; returns null if it is missing or unreadable. */
    public static byte[] readBytes(String path) {
        InputStream in = open(path);
        if (in == null) return null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) bo.write(buf, 0, n);
            return bo.toByteArray();
        } catch (IOException e) {
            return null;
        } finally {
            try { in.close(); } catch (IOException ignored) { }
        }
    }

    /** Directory part of an asset path ("models/a.obj" -> "models/"), or "" for top level. */
    public static String dirOf(String path) {
        int i = path.lastIndexOf('/');
        return i < 0 ? "" : path.substring(0, i + 1);
    }
}
