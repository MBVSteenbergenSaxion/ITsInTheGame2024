/**
 * Import necessary packages
 */

import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

/**
 * Subclass Main from superclass Canvas
 */

public class Main extends Canvas{

    /**
     * super() invokes the constructor of the Canvas class
     */

    public Main(){
        super();
    }

    public static Map<String, String> env;

    /**
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     */

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

        if(!Leaderboard.isPlaying){
            Canvas.playBackgroundMusic(filePath);
        }

        loadEnv();

        gameButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        gameButton.y = (int) (Settings.height * 0.40);
        gameButton.width = Settings.buttonWidth;
        gameButton.height = Settings.buttonHeight;

        leaderBoardButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        leaderBoardButton.y = (int) (Settings.height * 0.60);
        leaderBoardButton.width = Settings.buttonWidth;
        leaderBoardButton.height = Settings.buttonHeight;

        quitButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        quitButton.y = (int) (Settings.height * 0.8);
        quitButton.width = Settings.buttonWidth;
        quitButton.height = Settings.buttonHeight;

        SaxionApp.drawImage("resources/images/EagleMainLogo.png", Settings.width / 3, 0, Settings.width / 3, Settings.height / 3 + 40);
        SaxionApp.drawImage("resources/images/background.png", 0, 0, Settings.width, Settings.height);
    }

    /**
     * Calls the Draw method every frame
     * */

    @Override
    public void loop() {
        draw();
    }

    /**
     * (Unused) keyboardEvent method provided by Saxion template
     */

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    /**
     * mouseEvent method containing the main menu button listeners
     */

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

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

        MyButton.drawButton(gameButton.x, gameButton.y, gameButton.width, gameButton.height, Settings.fontSize, "Start Game", Color.GREEN);
        MyButton.drawButton(leaderBoardButton.x, leaderBoardButton.y, leaderBoardButton.width, leaderBoardButton.height, Settings.fontSize, "Leaderboard", Color.YELLOW);
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize, "Quit Game", Color.RED);
        }

    /**
     * loadEnv method to load the .env file which contains credentials for access to the SFTP server
     */

    private void loadEnv(){
        if(env == null){
            try {
                env = EnvLoader.loadEnv(".env");
            } catch (IOException e) {
                // Logic for if the .env file isn't found
            }
        }
    }
}