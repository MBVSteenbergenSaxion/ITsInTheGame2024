import nl.saxion.app.SaxionApp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GridDraw gridDraw;
    static boolean draw;

    //Create integer variables for the score, level and the number of score is needed to levelup
    private int scoreCounterThread;
    private int level = 1;
    private int scorePerLevel = 2;

    //Creates integer variables with the standard speed and the speed amount that will be deleted every time you level up
    private int gameSpeed = 1000;
    private int speedUpPerLevel = 150;

    public int nextBlockId;

    public GameThread(GridDraw gridDraw) {
        this.gridDraw = gridDraw;
        draw = true;
    }

    public static void main(String[] args) {
    }

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

                if(gridDraw.isBlockOutOfBounds()) {
                    draw = false;
                    Game.gd = null;
                    Game.gt = null;
                    Canvas.switchToScreen(new GameOver());
                }

                gridDraw.moveBlockToBackground();
                scoreCounterThread += gridDraw.clearLines();

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
            }
        }
    }
