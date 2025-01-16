package utils;

import nl.saxion.app.SaxionApp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utility {

    /***
     * Getter for the pointer location. Compensates for the dynamic nature of the game window.
     *
     */

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
     * Getter for active frames on the screen (JFrame).
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

    /***
     * Checks if the pointer is in the given bounds at the current moment.
     * Servers two functionalities:
     * 1) changing the fill color of a button on hover
     * 2) playing a sound when a given object is clicked
     *
     */

    public static boolean checkBounds(int x, int y, int objectX, int objectY, int width, int height, boolean isClicked ) {

        if ((x >= objectX && x <= objectX + width) && (y >= objectY && y <= objectY + height) && isClicked) {
            SaxionApp.playSound("resources/gameSounds/buttons.wav");
            return true;
        }

        return (x >= objectX && x <= objectX + width) && (y >= objectY && y <= objectY + height);
    }

    /***
     * Takes an array object and multiple integers.
     * Iterates through the whole thing to check if the given indexes are out of bounds.
     * Possibly deprecated, given the use of the newest checks in the GameThread.
     *
     */

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

    /***
     * Takes the active frame and makes two custom images.
     * These get used to style the window in the FrameThread.
     *
     */

    public static void customizeScreen() throws IOException, InterruptedException {

        Frame[] frames = Frame.getFrames();
        BufferedImage customImage = ImageIO.read(new File("resources/images/Tetris_Logo.png"));
        BufferedImage customImage2 = ImageIO.read(new File("resources/images/NavyEagleLogo.png"));

        frames[0].setResizable(true);

        FrameThread frameThread = new FrameThread(frames, customImage, customImage2);
        frameThread.start();


    }

}
