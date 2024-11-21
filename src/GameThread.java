// Thread Initialization Example
import nl.saxion.app.SaxionApp;
public class GameThread extends Thread {

    public GameThread(){
        super();
    }

    public static void main(String[] args) {

    }

    @Override
    public void run() {
        int i = 0;

        while(true){

            SaxionApp.drawCircle(10, 10 + i * 10, 10);

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            i++;

            System.out.println("Thread ran");
        }
    }
}



//Moet een while loop hebben die elke keer de frame verversd (geen idee hoe)
