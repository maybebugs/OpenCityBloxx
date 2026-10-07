/** Creates the platform-specific objects. (obfuscated: c) */
public final class PlatformFactory {
    public static ICanvas createCanvas() {
        return new GameCanvas();
    }

    public static Vibra createVibra() {
        return new Vibra();
    }

    public static SoundPlayer createSoundPlayer() {
        return new SoundPlayer();
    }

    public static HighScores createHighScores() {
        return new HighScores();
    }
}
