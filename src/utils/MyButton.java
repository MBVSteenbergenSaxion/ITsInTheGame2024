package utils;

import nl.saxion.app.SaxionApp;

import java.awt.*;

import static utils.Utility.checkBounds;

public class MyButton {
    public int x, y, width, height;
    static Color buttonBlue = SaxionApp.createColor(65,105,225);
    static Color hoverBlue = SaxionApp.createColor(45,85,255);

    public static void drawButton(int x, int y, int width, int height, int fontSize, String buttonText) {

        int[] mouseLocation = Utility.getPointerOnFrame();

        if(checkBounds(mouseLocation[0], mouseLocation[1], x, y, width, height, false)){
            SaxionApp.setFill(hoverBlue);
        }else{
            SaxionApp.setFill(buttonBlue);
        }

        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawRectangle(x, y, width, height);
        SaxionApp.drawText(buttonText, x + (int)(width * 0.25), y + (int)(height * 0.4), fontSize);

    }
}
