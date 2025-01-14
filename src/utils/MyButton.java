package utils;

import nl.saxion.app.SaxionApp;

import java.awt.*;

import static utils.Utility.checkBounds;

/** Class MyButton
 * A button class that draws the buttons.
 * The actions for these buttons are made else where.
 * */
public class MyButton {

    /** Integers for the x-coordinate, y-coordinate, the width of the button and the height of the button.
     * */
    public int x, y, width, height;

    /** Specifies the border color of the buttons.
     * */
    static Color borderColor = Color.lightGray;

    /** static void drawButton(x, y, width, height, fontSize, buttonText, buttonColor)
     * @param x the given start x coordinate of the button
     * @param y the given start y coordinate of the button
     * @param width the given width of the button
     * @param height the given height of the button
     * @param fontSize the size of the text within the button
     * @param buttonText the text that the button has
     * @param buttonColor the color that the button has
     * ------------------------------------------------
     * It gets the mouse location on the frame through another class, which is here used for the hover.
     * It sets the size of the border around the button, and then checks if the button isn't clicked and hovering on the location of the button.
     * IF so, the border gets a brighter border to say that the player is hovering on the button.
     * ------------------------------------------------
     * After that, the buttons get filled with a color, and a rectangle is drawn with the given x, y, width and height.
     * It makes a shadow from the top to a little bit underneath the button-text.
     * The text is drawn with a specific text given in the method and a specific location, together with the fontsize and the color white.
     * */
    public static void drawButton(int x, int y, int width, int height, int fontSize, String buttonText, Color buttonColor) {
        int[] mouseLocation = Utility.getPointerOnFrame();

        SaxionApp.setBorderSize(4);

        if(checkBounds(mouseLocation[0], mouseLocation[1], x, y, width, height, false)){
            SaxionApp.setBorderColor(borderColor.brighter());
        }else{
            SaxionApp.setBorderColor(borderColor);
        }

        SaxionApp.setFill(buttonColor);

        SaxionApp.drawRectangle(x, y, width, height);

        SaxionApp.setBorderColor(buttonColor.darker());
        for(int i = 4; i < width - 4; i++) {
            SaxionApp.drawLine(x + i,y + 4,
                    x + i, y + height/3*2+5);
        }

        SaxionApp.setBorderColor(Color.WHITE);
        SaxionApp.drawText(buttonText, x + (int)(width * 0.25), y + (int)(height * 0.4), fontSize);

    }


}
