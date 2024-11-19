import buttons.*;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Main implements GameLoop{

    public Main() {
    }

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new Main(), Settings.width, Settings.height, Settings.ms);

    }

    /***
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     *
     */


    buttons.MyButton gameButton = new MyButton();
    buttons.MyButton leaderBoardButton = new MyButton();
    buttons.MyButton quitButton = new MyButton();


    @Override
    public void init() {
        gameButton.x = Settings.width / 3;
        gameButton.y = (int) (Settings.height * 0.3 - Settings.height * 0.15);
        gameButton.width = Settings.buttonWidth;
        gameButton.height = Settings.buttonHeight;

        leaderBoardButton.x = Settings.width / 3;
        leaderBoardButton.y = (int) (Settings.height * 0.6 - Settings.height * 0.15);
        leaderBoardButton.width = Settings.buttonWidth;
        leaderBoardButton.height = Settings.buttonHeight;

        quitButton.x = Settings.width / 3;
        quitButton.y = (int) (Settings.height * 0.9 - Settings.height * 0.15);
        quitButton.width = Settings.buttonWidth;
        quitButton.height = Settings.buttonHeight;


    }

    @Override
    public void loop() {

        SaxionApp.clear();

        MyButton.drawButton(gameButton.x, gameButton.y, gameButton.width, gameButton.height, Settings.fontSize, "Start Game");
        MyButton.drawButton(leaderBoardButton.x, leaderBoardButton.y, leaderBoardButton.width, leaderBoardButton.height, Settings.fontSize, "Leaderboard");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize, "Quit Game");


    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

        /***
         * Main menu button listeners
         *
         * 18-11-2024, quit button is self-explanatory
         *
         */


        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (MyButton.checkBounds(x, y,
                    gameButton.x, gameButton.y, gameButton.width, gameButton.height)) {


            }

            if (MyButton.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height)) {

                System.exit(0);

            }

            if (MyButton.checkBounds(x, y,
                    leaderBoardButton.x, leaderBoardButton.y, leaderBoardButton.width, leaderBoardButton.height)) {

            }

        }

    }

}





