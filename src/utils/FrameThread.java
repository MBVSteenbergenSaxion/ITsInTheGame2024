package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Class FrameThread
 * Thread for an extra feature, a sort of animation in the top-bar of the application.
 * This lets two images and two strings flicker every two seconds.
 * */
public class FrameThread extends Thread{

    /** Makes a Frame array which we can call use to change the state of a frame, the frames we get from another class
     * */
    private Frame[] frames;

    /** An image variable we use and get from another class
     * */
    private BufferedImage customImage, customImage2;

    /** FrameThread(frames, customImage, customeImage2), constructor class
     * @param frames the state of a frame and how this frame looks like
     * @param customImage images we use in the top-bar
     * */
    public FrameThread(Frame[] frames, BufferedImage customImage, BufferedImage customImage2) {
        this.frames = frames;
        this.customImage = customImage;
        this.customImage2 = customImage2;
    }

    /** Goes in the while loop which never ends.
     * Goes in the for loop with all the frames.
     * IF there is a frame showing it sets it first to the navy eagle text and custom image 2 and stays there for two seconds.
     * After two seconds it sets the text to Tetris and to the tetris logo and stays there for two seconds.
     * It does this loop all over again.
     * */
    @Override
    public void run() {
        while(true) {
            for (Frame frame : frames) {
                if (frame.isShowing()) {
                    frame.setIconImage(customImage2);
                    frame.setTitle("Navy Eagle");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    frame.setIconImage(customImage);
                    frame.setTitle("Tetris");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }



        }
    }

}
