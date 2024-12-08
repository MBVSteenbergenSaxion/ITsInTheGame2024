import nl.saxion.app.SaxionApp;

public class GameThread extends Thread {

    private static GridDraw gridDraw;
    static boolean draw;
    private static int scoreCounterThread, level, scorePerLevel, speedUpPerLevel;
    public static int gameSpeed;
    public int nextBlockId;

    public GameThread(GridDraw gridDraw) {
        GameThread.gridDraw = gridDraw;

        draw = true;
        scorePerLevel = 2;
        level = 1;
        gameSpeed = 1000;
        speedUpPerLevel = 150;
    }


    public static void main(String[] args) {
    }

    /*
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
*/
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

<<<<<<< HEAD
    private void update() {
        gridDraw.addPiece(currentShape, currentColumn, currentRow, currentRotation);
        spawnShape();
    }

    private void spawnShape() {
        this.currentShape = shapes[0];
        this.currentColumn = currentShape.getSpawnColumn();
        this.currentRow = currentShape.getSpawnRow();
        this.currentRotation = 0;
    }


    public Shape getCurrentShape() {
        return currentShape;
    }

    public int getShapeColumns() {
        return currentColumn;
    }

    public int getShapeRows() {
        return currentRow;
    }

    public int getCurrentRotation() {
        return currentRotation;
=======
    private static void ifGameOver() {
        /*if(gridDraw.isBlockOutOfBounds()) {
            draw = false;
            Game.gd = null;
            Game.gt = null;
            Canvas.switchToScreen(new GameOver());
        }*/
>>>>>>> parent of d48318e ([Done to much] Cannot find the problem, classes stay null)
    }
}
