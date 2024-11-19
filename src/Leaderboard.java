import nl.saxion.app.SaxionApp;

public class Leaderboard implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Leaderboard(), 1024, 768);
    }

    public void run() {
        SaxionApp.printLine("Hello World!");

    }

}





