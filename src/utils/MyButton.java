package utils;

import nl.saxion.app.SaxionApp;

import java.awt.*;

import static utils.Utility.checkBounds;

public class MyButton {
    public int x, y, width, height;
    static Color borderColor = Color.lightGray;


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
