package utils;

import nl.saxion.app.SaxionApp;
import Settings.Settings;
import java.util.ArrayList;

public class TextBox {

    public static final int charLimit = 8;
    private static final int space = 10;

    public int x, y, fontSize;


    public static void drawTextBox(int x, int y, int fontSize, ArrayList<Character> input) {
        SaxionApp.setFill(SaxionApp.DEFAULT_BACKGROUND_COLOR);
        SaxionApp.drawRectangle(x, y - 15, Settings.buttonWidth, SaxionApp.getHeight() / 16);
        drawInput(input, x, y, fontSize);

    }

    private static void drawInput(ArrayList<Character> input, int startX, int startY, int fontSize) {

        for (int i = 0; i < input.size(); i++) {
            char currentChar = input.get(i);

            if (currentChar == 'i'){


            }

            SaxionApp.drawText(String.valueOf(currentChar), (int)(startX + i * space * 1.5), startY, fontSize);
        }

    }
}
