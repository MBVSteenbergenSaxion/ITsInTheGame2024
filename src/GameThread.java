import nl.saxion.app.SaxionApp;

public class GameThread extends Thread {

    public GridDraw gridDraw;
    public volatile boolean running = true;
    public volatile boolean paused = false;

    private static boolean draw = true;

    private int scoreCounterThread;
    private int level = 1;
    private final int scorePerLevel = 2;

    private int gameSpeed = 1000;
    private final int speedUpPerLevel = 150;

    public int nextBlockId;

    public GameThread(GridDraw gridDraw) {
        this.gridDraw = gridDraw;
    }

    @Override
    public void run() {
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

            gridDraw.setNextPiece();
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

            if (gridDraw.isBlockOutOfBounds()) {
                stopGame();
                Canvas.switchToScreen(new GameOver(scoreCounterThread));
                return;
            }

            gridDraw.moveBlockToBackground();
            scoreCounterThread += gridDraw.clearLines();

            Game.updateScore(scoreCounterThread);
            int newLevel = scoreCounterThread / scorePerLevel + 1;

            if (newLevel > level && level < 6) {
                level = newLevel;
                Game.updateLevel(level);
                gameSpeed -= speedUpPerLevel;
            }
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
}
