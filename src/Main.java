import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;

import javax.sound.sampled.Clip;

public class Main extends Canvas{

    /***
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     *
     */

    public Main(){
        super();
    }

    utils.MyButton gameButton = new MyButton();
    utils.MyButton leaderBoardButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    private String filePath = "resources/GameMusic/TetrisTheme.wav";

    /**
     * Initialize method to initialize:
     * - Game, leaderboard and quit button with dynamic positioning and dimensions
     * - It calculates the height and width
     * - It calculates the x and y coordinates relative to the Settings class
     * */
    @Override
    public void init() {
        SaxionApp.drawImage("resources/Images/eagle_left.jpg", 0, 0, Settings.width / 3, Settings.height);
        SaxionApp.drawImage("resources/Images/eagle_right.jpg", Settings.width - Settings.width / 3, 0, Settings.width / 3, Settings.height);
        SaxionApp.drawImage("resources/Images/Tetris_Logo.png", Settings.width / 3, 0, Settings.width / 3, Settings.height / 3);
        SaxionApp.drawImage("resources/Images/USA.png", Settings.width / 3 + Settings.width / 9, Settings.height / 5, Settings.width / 9, Settings.height / 9);

        gameButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        gameButton.y = (int) (Settings.height * 0.50 - Settings.height * 0.15);
        gameButton.width = Settings.buttonWidth;
        gameButton.height = Settings.buttonHeight;
        Canvas.playBackgroundMusic(filePath);

        leaderBoardButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        leaderBoardButton.y = (int) (Settings.height * 0.70 - Settings.height * 0.15);
        leaderBoardButton.width = Settings.buttonWidth;
        leaderBoardButton.height = Settings.buttonHeight;

        quitButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        quitButton.y = (int) (Settings.height * 0.9 - Settings.height * 0.15);
        quitButton.width = Settings.buttonWidth;
        quitButton.height = Settings.buttonHeight;


    }
    /**
     * Calls the Draw method every frame
     * */
    @Override
    public void loop() {
        draw();

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

            if (utils.Utility.checkBounds(x, y,
                    gameButton.x, gameButton.y, gameButton.width, gameButton.height,
                    true)) {
                Canvas.stopBackgroundMusic();
                switchToScreen(new Game());

            }

            if (utils.Utility.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height, true)) {

                System.exit(0);

            }

            if (utils.Utility.checkBounds(x, y,
                    leaderBoardButton.x, leaderBoardButton.y, leaderBoardButton.width,
                    leaderBoardButton.height, true)) {

                switchToScreen(new Leaderboard());
            }

        }

    }

    /**
     * Draws the game, leaderboard and quit button with dynamic width and height based on the Settings class and MyButton class
     * */
    private void draw(){

        MyButton.drawButton(gameButton.x, gameButton.y, gameButton.width, gameButton.height, Settings.fontSize, "Start Game");
        MyButton.drawButton(leaderBoardButton.x, leaderBoardButton.y, leaderBoardButton.width, leaderBoardButton.height, Settings.fontSize, "Leaderboard");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize, "Quit Game");

    }


}





