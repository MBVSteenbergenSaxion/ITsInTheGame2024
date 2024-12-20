package utils;

import nl.saxion.app.SaxionApp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utility {

    public static int[] getPointerOnFrame() {

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

    public static boolean checkBounds(int x, int y, int objectX, int objectY, int width, int height, boolean isClicked ) {

        if ((x >= objectX && x <= objectX + width) && (y >= objectY && y <= objectY + height) && isClicked) {
            SaxionApp.playSound("resources/gameSounds/buttons.wav");
            return true;
        }

        return (x >= objectX && x <= objectX + width) && (y >= objectY && y <= objectY + height);
    }

    public static boolean isArrayInBounds(Object arrayObj, int... indexes) {
        for (int i = 0; i < indexes.length; i++) {
            if (arrayObj == null || !arrayObj.getClass().isArray()) {
                return false;
            }
            Object[] array = (Object[]) arrayObj;
            if (indexes[i] < 0 || indexes[i] >= array.length) {
                return false;
            }
            arrayObj = array[indexes[i]];
        }
        return true;
    }

    public static void customizeScreen() throws IOException, InterruptedException {

        Frame[] frames = Frame.getFrames();
        BufferedImage customImage = ImageIO.read(new File("resources/Images/Tetris_Logo.png"));
        BufferedImage customImage2 = ImageIO.read(new File("resources/Images/NavyEagleLogo.jpg"));

        frames[0].setResizable(true);

        FrameThread frameThread = new FrameThread(frames, customImage, customImage2);
        frameThread.start();


    }

}
