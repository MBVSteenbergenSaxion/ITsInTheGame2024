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

public class Canvas implements GameLoop {
    public static Canvas activeCanvas;
    private static Clip backgroundMusic;
    private static long lastMouseEventTime = 0;

    public static Color backgroundColor = SaxionApp.createColor(0, 0, 128);

    public Canvas() {
    }

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

    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }

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

    public static void switchToScreen(Canvas newScreen) {
        SaxionApp.clear();

        activeCanvas = newScreen;
        activeCanvas.init();
        activeCanvas.loop();
    }

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

    @Override
    public void loop() {
        if (activeCanvas != null) {
            activeCanvas.loop();
        }
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (activeCanvas != null) {
            activeCanvas.keyboardEvent(keyboardEvent);
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastMouseEventTime > 500) {
            lastMouseEventTime = currentTime;
            if (activeCanvas != null) {
                System.out.println("test");
                activeCanvas.mouseEvent(mouseEvent);
            }
        }
    }

    public static Color getColor() {
        return backgroundColor;
    }
}