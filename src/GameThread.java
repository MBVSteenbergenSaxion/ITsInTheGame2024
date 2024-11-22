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

    // TODO Straks bepalen hoe we de blokjes resetten en koppelen aan de backend via GridDraw
    // Bounds zijn er ook niet!

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
            }
        }
    }
}



//Moet een while loop hebben die elke keer de frame verversd (geen idee hoe)
