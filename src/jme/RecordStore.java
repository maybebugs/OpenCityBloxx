package jme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/** File-backed single-record store. Saves live in the "saves" folder next to the game (or ~/.citybloxx). */
public final class RecordStore {
    private static File dir;
    private final File file;
    private byte[] data;

    private static synchronized File dir() {
        if (dir == null) {
            File d = new File("saves");
            if (!(d.isDirectory() || d.mkdirs()) || !d.canWrite()) {
                d = new File(System.getProperty("user.home"), ".citybloxx");
                d.mkdirs();
            }
            dir = d;
        }
        return dir;
    }

    private RecordStore(File f, byte[] d) { this.file = f; this.data = d; }

    public static RecordStore openRecordStore(String name, boolean create) throws RecordStoreException {
        File f = new File(dir(), name.replaceAll("[^A-Za-z0-9_.-]", "_") + ".rms");
        if (f.isFile()) {
            try { return new RecordStore(f, Files.readAllBytes(f.toPath())); }
            catch (IOException e) { throw new RecordStoreException(e.toString()); }
        }
        if (!create) throw new RecordStoreException("not found: " + name);
        return new RecordStore(f, null);
    }

    public int getNumRecords() { return data == null ? 0 : 1; }

    public byte[] getRecord(int id) throws RecordStoreException {
        if (data == null) throw new RecordStoreException("empty");
        return data.clone();
    }

    public int addRecord(byte[] d, int off, int len) throws RecordStoreException {
        setRecord(1, d, off, len);
        return 1;
    }

    public void setRecord(int id, byte[] d, int off, int len) throws RecordStoreException {
        byte[] c = new byte[len];
        System.arraycopy(d, off, c, 0, len);
        data = c;
        try { Files.write(file.toPath(), c); }
        catch (IOException e) { throw new RecordStoreException(e.toString()); }
    }

    public void closeRecordStore() { }
}
