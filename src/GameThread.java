import nl.saxion.app.SaxionApp;

public class GameThread extends Thread {

    public static int gameSpeed;
    private static Game game;

    public GameThread(Game game) {
        this.game = game;

        gameSpeed = 1000;

    }


    public static void main(String[] args) {
    }

    @Override
    public void run() {
        game.restart();

        while(true) {
            updateGame();
            try {
                Thread.sleep(gameSpeed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void updateGame() {
        game.spawnShape();
    }

}





/*


    private static GridDraw gridDraw;
    static boolean draw;
    private static int scoreCounterThread, level, scorePerLevel, speedUpPerLevel;
    public int nextBlockId;

    @Override
    public void run() {
        gridDraw.setNextPiece();

        while(true){

            if(draw){

                gridDraw.spawnBlock();
                nextBlockId = GridDraw.randomBlock;


                while (gridDraw.moveBlockDown()) {
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
 *

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
        /*if(gridDraw.isBlockOutOfBounds()) {
            draw = false;
            Game.gd = null;
            Game.gt = null;
            Canvas.switchToScreen(new GameOver());
        }
}*/