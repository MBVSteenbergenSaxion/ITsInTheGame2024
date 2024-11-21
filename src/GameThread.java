// Thread Initialization Example
import nl.saxion.app.SaxionApp;
public class GameThread implements Runnable {

    @Override
    public void run() {

        Game.spawnBlock();
        Game.block.moveDown();
        SaxionApp.sleep(1);

    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new GameThread());
        myThread.start();
    }
}



//Moet een while loop hebben die elke keer de frame verversd (geen idee hoe)
