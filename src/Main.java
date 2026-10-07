/** Desktop entry point: runs City Bloxx on a plain JDK (no J2ME / Nokia libraries needed). */
public final class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "false");
        House game = new House();
        game.startFromLauncher();
    }
}
