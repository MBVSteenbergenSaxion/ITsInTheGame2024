package buttons;

import nl.saxion.app.SaxionApp;

import java.awt.*;

public class MyButton {
    public int x, y, width, height;
    public String buttonText = "";

    public static void drawButton(int x, int y, int width, int height, int fontSize, String buttonText) {

        PointerInfo pointerInfo = MouseInfo.getPointerInfo();

        if(checkBounds((int) pointerInfo.getLocation().getX(), (int) pointerInfo.getLocation().getY(), x, y, width, height)){
            SaxionApp.setFill(Color.DARK_GRAY);
        }else{
            SaxionApp.setFill(SaxionApp.DEFAULT_BACKGROUND_COLOR);
        }

        SaxionApp.drawRectangle(x, y, width, height);
        SaxionApp.drawText(buttonText, x + (int)(width * 0.3), y + (int)(height * 0.4), fontSize);

    }

    public static boolean checkBounds(int x, int y, int buttonX, int buttonY, int width, int height){
        return (x >= buttonX && x <= buttonX + width) && (y >= buttonY && y <= buttonY + height);
    }
}
