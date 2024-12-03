package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameThread extends Thread{
    private Frame[] frames;
    private BufferedImage customImage;
    private BufferedImage customImage2;

    public FrameThread(Frame[] frames, BufferedImage customImage, BufferedImage customImage2) {
        this.frames = frames;
        this.customImage = customImage;
        this.customImage2 = customImage2;
    }

    public static void main(String[] args) {

    }

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
