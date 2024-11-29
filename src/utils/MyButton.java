package utils;

import nl.saxion.app.SaxionApp;

import java.awt.*;

import static utils.Utility.checkBounds;

public class MyButton {
    public int x, y, width, height;

    public static void drawButton(int x, int y, int width, int height, int fontSize, String buttonText) {

        int[] mouseLocation = Utility.getPointerOnFrame();

        if(checkBounds(mouseLocation[0], mouseLocation[1], x, y, width, height, false)){
            SaxionApp.setFill(Color.DARK_GRAY);
        }else{
            SaxionApp.setFill(SaxionApp.DEFAULT_BACKGROUND_COLOR);
        }

        SaxionApp.setBorderColor(Color.LIGHT_GRAY);
        SaxionApp.drawRectangle(x, y, width, height);
        SaxionApp.drawText(buttonText, x + (int)(width * 0.25), y + (int)(height * 0.4), fontSize);

    }
}
