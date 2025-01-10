import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import utils.MyButton;
import utils.TextBox;
import utils.Utility;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.TextBox.charLimit;

public class GameOver extends Canvas{

    public GameOver(int highscore){
        super();
        finalHighscore = highscore;
    }
    /***
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     *
     */
    private static ArrayList<Character> keyboardInput = new ArrayList<>();
    private static int finalHighscore;

    TextBox usernameInput = new TextBox();
    utils.MyButton menuButton = new MyButton();
    utils.MyButton submitButton = new MyButton();


    /**
     * Initialize method to initialize:
     * - Game, leaderboard and quit button with dynamic positioning and dimensions
     * - It calculates the height and width
     * - It calculates the x and y coordinates relative to the Settings class
     * */
    @Override
    public void init() {

        Canvas.stopBackgroundMusic();

        usernameInput.x = Settings.width / 2 - Settings.fontSize*6;
        usernameInput.y = (int) (Settings.height * 0.4);
        usernameInput.fontSize = 25;

        menuButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        menuButton.y = (int) (Settings.height * 0.8 - Settings.height * 0.15);
        menuButton.width = Settings.buttonWidth;
        menuButton.height = Settings.buttonHeight;

        submitButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        submitButton.y = (int) (Settings.height * 0.8);
        submitButton.width = Settings.buttonWidth;
        submitButton.height = Settings.buttonHeight;

    }
    /**
     * Calls the Draw method every frame
     * */
    @Override
    public void loop() {
        draw();
    }

    /**
     * reads the keyboard input for writing a name down, length is capped to 8 characters
     *
     */
    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.isKeyPressed()){
            String keyChar = KeyEvent.getKeyText(keyboardEvent.getKeyCode());

            if(keyboardEvent.getKeyCode() == 8 && !keyboardInput.isEmpty()){
                keyboardInput.removeLast();
                return;
            }

            if (Character.isLetter(keyChar.charAt(0))
                    && keyboardInput.size() < charLimit
                    && keyboardEvent.getKeyCode() != 8) {
                keyboardInput.add(keyChar.charAt(0));

            }

        }

    }

    /**
     * listens to if there was a button pressed.
     */

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (utils.Utility.checkBounds(x, y,
                    menuButton.x, menuButton.y, menuButton.width, menuButton.height, true)) {
                Canvas.stopBackgroundMusic();
                SaxionApp.setBackgroundColor(backgroundColor);
                switchToScreen(new Main());
                GameThread.resetScore();
            }
            if (utils.Utility.checkBounds(x, y,
                    submitButton.x, submitButton.y, submitButton.width, submitButton.height, true)) {

                Canvas.stopBackgroundMusic();
                SaxionApp.setBackgroundColor(backgroundColor);
                try {
                    submitScores();
                } catch (IOException | JSchException | SftpException e) {
                    throw new RuntimeException(e);
                }
                GameThread.resetScore();
                switchToScreen(new Main());
            }

        }
    }


    /**
     * Draws the game, leaderboard and quit button with dynamic width and height based on the Settings class and MyButton class
     * */
    private void draw(){
        SaxionApp.setTextDrawingColor(Color.red);
        SaxionApp.drawText("GAME OVER", usernameInput.x-usernameInput.x/3,usernameInput.y/2-usernameInput.y/6,100);
        SaxionApp.drawText("YOUR SCORE: "+finalHighscore,usernameInput.x+usernameInput.x/5,usernameInput.y/2+usernameInput.y/4,25);
        SaxionApp.setTextDrawingColor(Color.white);
        TextBox.drawTextBox(usernameInput.x, usernameInput.y, usernameInput.fontSize, keyboardInput);
        MyButton.drawButton(menuButton.x,menuButton.y, menuButton.width, menuButton.height, Settings.fontSize, "Main Menu", Color.RED);
        MyButton.drawButton(submitButton.x, submitButton.y, submitButton.width, submitButton.height, Settings.fontSize, "Submit Score", Color.GREEN);
    }
    /**
     * capitalizes whatever username has been entered before sending it to SFTP.
     */
private void submitScores() throws IOException, JSchException, SftpException {


    String userName = new String();
    for (int i = 0; i < keyboardInput.size(); i++){
        userName += keyboardInput.get(i);
    }

    if(userName.isEmpty()) userName = "username";

    userName.toLowerCase();
    userName = userName.substring(0,1).toUpperCase() + userName.substring(1).toLowerCase();
    String[] array = new String[]{userName, String.valueOf(finalHighscore)};

    if (Main.env == null){
        LeaderboardBackend.writeToCSVOffline(array);
    } else {
        LeaderboardBackend.writeToCSVOnline(array);

    }
}
}





