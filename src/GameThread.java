import Grid.Grid;
import nl.saxion.app.SaxionApp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GridDraw gridDraw;
    static boolean draw;

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
                System.out.println(nextBlockId);


                while (gridDraw.moveBlockDown()) {
                    SaxionApp.clear();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                if(gridDraw.isBlockOutOfBounds()) {
                    System.out.println("Game Over");
                    break;
                }

                gridDraw.moveBlockToBackground();
                gridDraw.clearLines();
            }
        }
    }
}