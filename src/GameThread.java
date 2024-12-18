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

    @Override
    public void run() {

        GameBackend.setNextPiece();

        while (running) {
            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }

            GameBackend.spawnBlock();
            nextBlockId = GameBackend.nextBlockId;

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

    public synchronized void pauseGame() {
        paused = true;
    }

    public synchronized void resumeGame() {
        paused = false;
        notify();
    }

    public void stopGame() {
        running = false;
        interrupt();
    }

    private static void setLevel() {
        int lvl = scoreCounterThread / scorePerLevel + 1;

        if (level < 6) {
            if (lvl > level) {

                level = lvl;
                GameBackend.updateLevel(level);
                gameSpeed -= speedUpPerLevel;
            }
        }
    }

    private static void ifGameOver() {
        if(gridDraw.isBlockOutOfBounds()) {
            draw = false;
            GameBackend.gd = null;
            GameBackend.gt = null;
            Canvas.switchToScreen(new GameOver(scoreCounterThread));
        }
    }
}
