package GameOver;

import Settings.Settings;
import nl.saxion.app.SaxionApp;
import utils.TextBox;

import java.util.Set;

public class GameOverSettings {
    /** screenHeight, gets the screen height with the SaxionApp
     * */
    private static int screenHeight = SaxionApp.getHeight();

    /** screenWidth, gets the screen width with the SaxionApp
     * */
    private static int screenWidth = SaxionApp.getWidth();

    public static int getMainButtonXPos() {
        return Settings.width / 2 - Settings.buttonWidth / 2;
    }

    public static int getUsernameX() {
        int xPos = getMainButtonXPos();
        return xPos;
    }



    public static int getUsernameY() {
        int heightInputField = (int) (screenHeight * 0.3 - screenHeight * 0.15);


        return heightInputField;
    }

    public static int getFontSize() {

        return Settings.fontSize;
    }

    public static int getMainButtonYpos(){
        int yPos = (int) (Settings.height * 0.8 - Settings.height * 0.15);

        return yPos;
    }

    public static int gameOverTextYpos(){
        int ypos = (int) (Settings.height * 0.8 - Settings.height * 0.1)
    }

    public static int buttonWidth() {
        return Settings.buttonWidth;
    }
    public static int butttonHeight(){
        return Settings.buttonHeight;
    }
}
