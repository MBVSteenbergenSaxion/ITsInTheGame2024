import nl.saxion.app.SaxionApp;

public class GameThread extends Thread {

    private static GridDraw gridDraw;
    public volatile boolean running;
    public volatile boolean paused;
    static boolean draw;
    private static int scoreCounterThread, level, scorePerLevel, gameSpeed, speedUpPerLevel;

    public int nextBlockId;

    public GameThread(GridDraw gridDraw) {
        GameThread.gridDraw = gridDraw;
        running = true;
        paused = false;
        draw = true;
        scorePerLevel = 2;
        level = 1;
        gameSpeed = 1000;
        speedUpPerLevel = 150;
    }


    public static void main(String[] args) {
    }

    @Override
    public void run() {
        GameBackend.setNextPiece();

        while(true){

            if(draw){

                GameBackend.spawnBlock();
                nextBlockId = GameBackend.randomBlock;


                while (GameBackend.moveBlockDown()) {
                    SaxionApp.clear();
                    try {
                        Thread.sleep(gameSpeed);
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                ifGameOver();

                gridDraw.moveBlockToBackground();
                scoreCounterThread += gridDraw.clearLineCheck();

                setLevel();
            }
            }
        }

    /** METHODS FOR READABILITY
     * - setLevel()
     * - ifGameOver()
     * */

    private static void setLevel() {
        Game.updateScore(scoreCounterThread);
        int lvl = scoreCounterThread / scorePerLevel + 1;

        if (level < 6) {
            if (lvl > level) {

                level = lvl;
                Game.updateLevel(level);
                gameSpeed -= speedUpPerLevel;
            }
        }
    }

    private static void ifGameOver() {
        if(gridDraw.isBlockOutOfBounds()) {
            draw = false;
            GameBackend.gd = null;
            GameBackend.gt = null;
            Canvas.switchToScreen(new GameOver());
        }
    }
}
