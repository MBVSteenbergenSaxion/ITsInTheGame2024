package utils;

import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Utility {

    public static int[] getPointerOnFrame(){

        int[] pointerData = new int[2];
        int x, y;

        Point locationOfFrame = getActiveFrameLocation();

        PointerInfo pointerInfo = MouseInfo.getPointerInfo();

        x = (int) (pointerInfo.getLocation().getX() - locationOfFrame.getX());
        y = (int) (pointerInfo.getLocation().getY() - locationOfFrame.getY() - 32);

        pointerData[0] = x;
        pointerData[1] = y;

        return pointerData;
    }

    /***
     * SaxionApp only allows one window to be made at one time, that's why there's only one iteration of the loop.
     *
     */


    private static Point getActiveFrameLocation() {
        Point location = new Point();
        Frame[] frames = Frame.getFrames();

        for (Frame frame : frames) {

            if (frame.isShowing()) {
                location = frame.getLocationOnScreen();

            }
        }

        return location;

    }

    public static boolean checkBounds(int x, int y, int objectX, int objectY, int width, int height, boolean isClicked){

        if(isClicked){
            SaxionApp.playSound("resources/gameSounds/buttons.wav");
        }

        return (x >= objectX && x <= objectX + width) && (y >= objectY && y <= objectY + height);
    }

}
