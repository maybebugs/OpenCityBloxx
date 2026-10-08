

import java.io.ByteArrayInputStream;
import java.util.Hashtable;
import jme.Player;

public final class SoundPlayer implements Player.Listener {
    private Player player = null;
    private Player loopPlayer = null;
    private int loopCount = 0;
    private Hashtable preloaded = new Hashtable();
    private Player tempPlayer;
    private boolean enabled = true;
    private boolean ownsPlayer = false;
    private boolean loopStopped = false;

    private void stopLoopIfNeeded() {
        if (this.loopPlayer != null && !this.loopStopped) {
            stopLoopPlayer();
        }
    }

    private void startPlayer(int resourceId, int loopCount) {
        if (this.enabled && resourceId != -1) {
            try {
                this.player = new Player(new ByteArrayInputStream(Resources.getBytes(resourceId)));
                this.player.setLoopCount(loopCount);
                this.player.addPlayerListener(this);
                this.player.prefetch();
                this.player.start();
            } catch (Exception e) {
            }
        }
    }

    private void closeIfOwned() {
        if (this.player != null && this.ownsPlayer) {
            closePlayer();
        }
        this.ownsPlayer = false;
    }

    private void closePlayer() {
        try {
            this.player.close();
            this.player = null;
        } catch (Exception e) {
        }
    }

    private void restartPlayer() {
        try {
            this.player.start();
        } catch (Exception e) {
        }
    }

    private void stopLoopPlayer() {
        try {
            this.loopPlayer.stop();
        } catch (Exception e) {
        }
    }

    public final void stopAll() {
        closeIfOwned();
        stopLoopIfNeeded();
    }

    public final void play(int resourceId, int loopCount) {
        if (this.enabled && resourceId != -1) {
            closeIfOwned();
            this.ownsPlayer = true;
            startPlayer(resourceId, loopCount);
        }
    }

    public final void preload(int resourceId, boolean load) {
        if (!this.preloaded.containsKey(Integer.valueOf(resourceId)) && load) {
            try {
                this.tempPlayer = new Player(new ByteArrayInputStream(Resources.getBytes(resourceId)));
                this.tempPlayer.realize();
                this.tempPlayer.prefetch();
                this.preloaded.put(Integer.valueOf(resourceId), this.tempPlayer);
            } catch (Exception e) {
            }
        }
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (!this.enabled) {
            stopAll();
        }
    }

    public final void playerUpdate(Player source, String event, Object data) {
        if (event != "endOfMedia" || source != this.loopPlayer) {
            return;
        }
        if (this.loopCount == 0) {
            restartPlayer();
            return;
        }
        if (this.loopCount != -1) {
            this.loopCount--;
        }
        try {
            this.loopPlayer.start();
        } catch (Exception e) {
        }
    }
}
