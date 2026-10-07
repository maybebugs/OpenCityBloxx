package jme;

import java.io.InputStream;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/** MIDI player on javax.sound.midi (replacement for JSR-135 Player). Fails silently if no audio device. */
public final class Player implements MetaEventListener {
    public interface Listener { void playerUpdate(Player p, String event, Object data); }

    private Sequencer seq;
    private Sequence sequence;
    private int loops = 1;
    private Listener listener;
    private boolean closed;

    public Player(InputStream in) {
        try {
            sequence = MidiSystem.getSequence(in);
        } catch (Throwable t) {
            sequence = null;
        }
    }

    public void realize() { }
    public void setLoopCount(int n) { loops = n; }
    public void addPlayerListener(Listener l) { listener = l; }
    public int getLevel() { return 100; }
    public void setLevel(int l) { }

    public void prefetch() {
        if (sequence == null || seq != null || closed) return;
        try {
            seq = MidiSystem.getSequencer();
            seq.open();
            seq.setSequence(sequence);
            seq.addMetaEventListener(this);
        } catch (Throwable t) {
            seq = null;
        }
    }

    public void start() {
        if (closed) return;
        prefetch();
        if (seq == null) return;
        try {
            int count = loops == -1 ? Sequencer.LOOP_CONTINUOUSLY : Math.max(0, loops - 1);
            seq.setLoopCount(count);
            seq.setTickPosition(0);
            seq.start();
        } catch (Throwable t) { }
    }

    public void stop() {
        try { if (seq != null && seq.isRunning()) seq.stop(); } catch (Throwable t) { }
    }

    public void close() {
        closed = true;
        try {
            if (seq != null) { seq.stop(); seq.close(); }
        } catch (Throwable t) { }
        seq = null;
    }

    @Override
    public void meta(MetaMessage m) {
        if (m.getType() == 47 && listener != null) listener.playerUpdate(this, "endOfMedia", null);
    }
}
