import Grid.GridSettings;
import nl.saxion.app.SaxionApp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GridDraw gridDraw;
    static boolean draw;

    public GameThread(GridDraw gridDraw) {
        this.gridDraw = gridDraw;
        draw = true;
    }

    public static void main(String[] args) {

    }

    @Override
    public void run() {

        while(true){

            if(draw){
                gridDraw.spawnBlock();

                while (gridDraw.moveBlockDown()) {
                    SaxionApp.clear();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                if(gridDraw.isBlockOutOfBounds()) {
                    Game.gt = new GameThread(new GridDraw(GridSettings.width));
                    Game.gd = new GridDraw(GridSettings.width);
                    Canvas.switchToScreen(new GameOver());
                }

                gridDraw.moveBlockToBackground();
                gridDraw.clearLines();
            }
        }
    }
}