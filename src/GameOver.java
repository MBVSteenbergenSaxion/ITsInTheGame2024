import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import utils.MyButton;
import utils.TextBox;
import utils.Utility;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.TextBox.charLimit;

public class GameOver extends Canvas{

    /***
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     *
     */

    public GameOver(){
        super();
    }
    ArrayList<Character> keyboardInput = new ArrayList<>();
    TextBox usernameInput = new TextBox();

    /**
     * Initialize method to initialize:
     * - Game, leaderboard and quit button with dynamic positioning and dimensions
     * - It calculates the height and width
     * - It calculates the x and y coordinates relative to the Settings class
     * */
    @Override
    public void init() {
        usernameInput.x = Settings.width / 3;
        usernameInput.y = (int) (Settings.height * 0.3 - Settings.height * 0.15);
        usernameInput.fontSize = 25;
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

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

    }

    /**
     * Draws the game, leaderboard and quit button with dynamic width and height based on the Settings class and MyButton class
     * */
    private void draw(){
        TextBox.drawTextBox(usernameInput.x, usernameInput.y, usernameInput.fontSize, keyboardInput);
    }


}





