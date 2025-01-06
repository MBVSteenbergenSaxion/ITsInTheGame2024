import nl.saxion.app.SaxionApp;

/** Class GameThread extends Thread, makes a new gameloop for the game.
 * The gameloop of the SaxionApp we use for the animation/hover.
 * This thread we use for the game itself which gets refreshed way less than the gameloop of the SaxionApp.
 * */
public class GameThread extends Thread {

    /** Variable for using the GridDraw
     * */
    private static GridDraw gd;

    /** volatile?
     * boolean running, true or false if the game is started or ended
     * */
    public volatile boolean running;

    /** boolean draw, true or false if the game still needs to draw or not
     * */
    static boolean draw;

    /** integer for score counter, level counter, the score it takes to level up,
     * the game speed and the number of milliseconds it will go faster every level up.
     * */
    private static int scoreCounterThread, levelCounterThread, scorePerLevel, gameSpeed, speedUpPerLevel;


    /** Constructor method
     * @param gd sets the grid draw method with given class when the method is called.
     * Sets the starting settings.
     * */
    public GameThread(GridDraw gd) {
        GameThread.gd = gd;
        running = true;
        draw = true;
        scoreCounterThread = 0;
        scorePerLevel = 2;
        levelCounterThread = 1;
        gameSpeed = 1000;
        speedUpPerLevel = 150;
    }

    /** Void run(), the run method for this Thread. This thread just does the run method over and over again until the while loop is set to false.
     * It sets the next piece,
     * it spawns a block which is the already created next piece, and it makes a new next piece.
     * It moves the shape down and clears the screen every loop. It sleeps every amount of game speed so it doesn't fall down immediately at once.
     * It checks if the player isn't game over.
     * It moves the shape to the background of the game grid.
     * It updates the score counter, and it sets the level.
     * */
    @Override
    public void run() {

        GameBackend.setNextPiece();

        while (running) {


            GameBackend.spawnBlock();

            while (GameBackend.moveBlockDown()) {
                SaxionApp.clear();
                try {
                    Thread.sleep(gameSpeed);
                } catch (InterruptedException e) {
                    return;
                }
            }

            ifGameOver();

            gd.moveBlockToBackground();
            scoreCounterThread += gd.clearLineCheck();

            setLevel();
        }
    }

    /** Static void setLevel(), levels up the game and the counter if the player is level up.
     * The game has a maximum of five levels.
     * The gamespeed is updated with the current speed subtracted with the speedup speed per level.
     * */
    private static void setLevel() {
        int level = scoreCounterThread / scorePerLevel + 1;

        if (levelCounterThread < 6) {
            if (level > levelCounterThread) {

                levelCounterThread = level;
                GameBackend.updateLevel(levelCounterThread);
                gameSpeed -= speedUpPerLevel;
            }
        }
    }

    /** Static void ifGameOver(), checks if the player is gameover.
     * If the player is gameover then there can’t be drawn anymore, the called classes are set to null,
     * and it switches the screen to the gameover screen.
     * */
    private static void ifGameOver() {
        if(gd.isBlockOutOfBounds()) {
            draw = false;
            GameBackend.gd = null;
            GameBackend.gt = null;
            Canvas.switchToScreen(new GameOver(scoreCounterThread));
        }
    }
    public static void resetScore() {
        scoreCounterThread = 0;
        levelCounterThread = 0;
        GameBackend.levelCount = 0;
        GameBackend.scoreCount = 0;
    }
}
