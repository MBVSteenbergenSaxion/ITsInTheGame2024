package utils;

import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

/** class TextBox
 * This is the class that makes a textbox with an input that gets on the screen.
 * */
public class TextBox {

    /** Integer charLimit, the maximum number of characters that are in the textBox.
     * */
    public static final int charLimit = 8;

    /** Integer space, the space between the coordinates of every character.
     * */
    private static final int space = 10;

    /** Integers for the x-coordinate, y-coordinate and the amount of the text size.
     * */
    public int x, y, fontSize;

    /** static void drawTextBox(x, y, fontSize, input)
     * @param x the starting x-coordinate of the text-box.
     * @param y the starting y-coordinate of the text-box.
     * @param fontSize the text size within the text box.
     * @param input an ArrayList with characters as input, the input array that's given with calling this method.
     * --------------------------------------------------
     * It fills the text box with the default background color of the SaxionApp.
     * It draws a rectangle with the given x, the given y, as width the maximum characters times the fontSize
     * and the space between the coordinates of the characters,
     * and as last a sixteenth of the screenHeight.
     * --------------------------------------------------
     * It calls the drawInput method with the given variables to draw the text within the input box
     * */
    public static void drawTextBox(int x, int y, int fontSize, ArrayList<Character> input) {
        SaxionApp.setFill(SaxionApp.DEFAULT_BACKGROUND_COLOR);
        SaxionApp.drawRectangle(x, y - 15, charLimit * (fontSize + space), SaxionApp.getHeight() / 16);
        drawInput(input, x, y, fontSize);
    }

    /** static void drawInput(input, startX, startY, fontSize)
     * @param input an ArrayList with characters as input, the input array that's given with calling this method.
     * @param startX the starting x-coordinate of the text.
     * @param startY the starting y-coordinate of the text.
     * @param fontSize the text size of the text.
     * --------------------------------------------------
     * It gives a for loop that loops to the input.
     * They get the character on the place of the current loop number,
     * and it draws a character on the start with an every time bigger position so that its spaced enough,
     * on the startY and with the given fontsize.
     * */
    private static void drawInput(ArrayList<Character> input, int startX, int startY, int fontSize) {
        for (int i = 0; i < input.size(); i++) {
            char currentChar = input.get(i);
            SaxionApp.drawText(String.valueOf(currentChar), (int)(startX + i * space * 1.5), startY, fontSize);
        }
    }
}
