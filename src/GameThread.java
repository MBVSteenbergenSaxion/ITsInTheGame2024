import nl.saxion.app.SaxionApp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GridDraw gridDraw;

    public GameThread(GridDraw gridDraw) {
        this.gridDraw = gridDraw;
    }

    public static void main(String[] args) {

    }

    // TODO Straks bepalen hoe we de blokjes resetten en koppelen aan de backend via GridDraw
    // Bounds zijn er ook niet!

    @Override
    public void run() {

        while(true){
            gridDraw.spawnBlock();

            while (gridDraw.moveBlockDown()) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE,null, e);
                }
            }

        }

    }


}



//Moet een while loop hebben die elke keer de frame verversd (geen idee hoe)
