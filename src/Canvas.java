/**
 * Import necessary packages
 */

import Grid.GridSettings;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import SideDraw.*;

import javax.sound.sampled.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas implements GameLoop {

    /**
     * Initializes activeCavas, backgroundMusic, mouse event time tracker and backgroundColor
     */

    public static Canvas activeCanvas;
    private static Clip backgroundMusic;
    public static Color backgroundColor = SaxionApp.DEFAULT_BACKGROUND_COLOR;

    int pressedTimes = 0;

    public Canvas() {
    }

    /**
     * playBackgroundMusic method to start playing music
     */

    public static void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Audio Line not supported");
                return;
            }

            backgroundMusic = (Clip) AudioSystem.getLine(info);
            backgroundMusic.open(audioStream);

            // Loop the background music
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

            // Start playing
            backgroundMusic.start();

        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * stopBackgroundMusic method to stop the music
     */

    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }


    /**
     * - Sets up a canvas
     * - Initializes the activeCanvas as Main()
     * - Starts the game loop with info from the settings Class
     * - Adds a component listener to monitor window changes
     * - Resizes window on changes
     */

    public static void main(String[] args) {
        Canvas mainApp = new Canvas();
        activeCanvas = new Main();

        SaxionApp.startGameLoop(mainApp, Settings.width, Settings.height, Settings.ms);

        Frame[] frames = Frame.getFrames();

        frames[0].addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Settings.width = frames[0].getWidth();
                Settings.height = frames[0].getHeight();
                GridSettings.updateScreenDimensions(frames[0].getWidth(), frames[0].getHeight());
                SideSettings.updateScreenDimensions(frames[0].getWidth(), frames[0].getHeight());

                SaxionApp.clear();
                activeCanvas.init();
            }
        });
    }

    /**
     * switchToScreen method to switch to a different screen / game scene
     */

    public static void switchToScreen(Canvas newScreen) {
        SaxionApp.clear();

        activeCanvas = newScreen;
        activeCanvas.init();
        activeCanvas.loop();
    }

    /**
     * Calls customizeScreen method, initializes errors and sets backgroundColor
     */

    @Override
    public void init() {
        try {
            utils.Utility.customizeScreen();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (activeCanvas != null) {
            activeCanvas.init();
        }
        SaxionApp.setBackgroundColor(backgroundColor);
    }

    /**
     * Loops active canvas
     */

    @Override
    public void loop() {
        if (activeCanvas != null) {
            activeCanvas.loop();
        }
    }

    /**
     * Tracks keyboard events and passes them to the active canvas
     */

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (activeCanvas != null) {
            activeCanvas.keyboardEvent(keyboardEvent);
        }
    }

    /**
     * Tracks mouse events and passes them to the active canvas
     */

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

        pressedTimes++;

        if (activeCanvas != null && pressedTimes == 1) {
            activeCanvas.mouseEvent(mouseEvent);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pressedTimes = 0;
                }
            }, 100);

        }


    }

    /**
     * gets backgroundColor (for usage in other classes)
     */

    public static Color getColor() {
        return backgroundColor;
    }
}