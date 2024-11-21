import nl.saxion.app.SaxionApp;
public class GameThread extends Thread {

    public static void main(String[] args) {

    }

    // TODO Straks bepalen hoe we de blokjes resetten en koppelen aan de backend via GridDraw
    // Bounds zijn er ook niet!

    @Override
    public void run() {
        while(true){
            GridDraw.moveBlockDown();
            SaxionApp.sleep(1);
        }

    }


}



//Moet een while loop hebben die elke keer de frame verversd (geen idee hoe)
