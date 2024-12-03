package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameThread extends Thread{
    private Frame[] frames;
    private BufferedImage customImage;

    public FrameThread(Frame[] frames, BufferedImage customImage) {
        this.frames = frames;
        this.customImage = customImage;
    }

    public static void main(String[] args) {

    }

    @Override
    public void run() {
        while(true) {
            frames[0].setIconImage(customImage);
            frames[0].setTitle("Navy Eagle");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            frames[0].setTitle("Tetris");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
