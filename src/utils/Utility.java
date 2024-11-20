package utils;

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

    public static boolean checkBounds(int x, int y, int objectX, int objectY, int width, int height){
        return (x - 4 >= objectX && x - 8  <= objectX + width) && (y + 4 >= objectY && y + 2 <= objectY + height);
    }

}
