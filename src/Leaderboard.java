import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import nl.saxion.app.SaxionApp;
import org.w3c.dom.css.RGBColor;
import utils.*;
import Leaderboard.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import javax.security.sasl.SaslClient;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Leaderboard extends Canvas {

    public Leaderboard() {
        super();
    }

    /***
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     *
     */
    private String filePath = "resources/GameMusic/TetrisTheme.wav";
    private String fileName;
    private File CsvFile;

    ArrayList<Score> scores = new ArrayList<>();

    utils.MyButton menuButton = new MyButton();


    @Override
    public void init() {
/***
 *  game tries to load CSV file from SFTP server to display scores, if it cannot communicate it loads the local scoreboard
 *  defines button attributes
 */
        Canvas.stopBackgroundMusic();
        Canvas.playBackgroundMusic(filePath);

        fileName = "resources/Leaderboard/scores.csv";

        if (Main.env != null) {
            try {
                SFTP.downloadFile(Main.env.get("USER"),
                        Main.env.get("PASS"),
                        Main.env.get("IP"));
                fileName = "temp/scores.csv";

            } catch (JSchException | SftpException e) {
                throw new RuntimeException(e);
            }
        }

        CsvFile = new File(fileName);

        if (CsvFile.exists() && !CsvFile.isDirectory()) {
            try {
                scores = LeaderboardBackend.getScores(fileName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        menuButton.x = Settings.width / 2 - Settings.buttonWidth / 2;
        menuButton.y = (int) (Settings.height * 0.8 - Settings.height * 0.15);
        menuButton.width = Settings.buttonWidth;
        menuButton.height = Settings.buttonHeight;


    }

    private int r = 0;
    private int g = 0;
    private int b = 0;
    private boolean allMaxValues = false;


    /***
     * changes the background color from rgb: 0,0,0 to 255,0,0 until it hits 255,255,255 and then stats counting down
     * once at 0 again this gets repeated.
     * this is checked every frame
     */

    @Override


    public void loop() {

        SaxionApp.setBackgroundColor(new Color(r, g, b));
        if (r < 255 && !allMaxValues) {
            r++;
        } else if (g < 255 && !allMaxValues) {
            g++;
        } else if (b < 255 && !allMaxValues) {
            b++;
        }
        if (r == 255 && g == 255 && b == 255) {
            allMaxValues = true;
        }
        if (allMaxValues) {
            if (r > 0) {
                r--;
            } else if (g > 0) {
                g--;
            } else if (b > 0) {
                b--;
            } else if (r == 0 && g == 0 && b == 0) {
                allMaxValues = false;
            }
        }

        draw();

    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    /***
     * Main menu button listeners
     *
     * 18-11-2024, quit button is self-explanatory
     *
     */

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {




        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (utils.Utility.checkBounds(x, y,
                    menuButton.x, menuButton.y, menuButton.width, menuButton.height, true)) {
                Canvas.stopBackgroundMusic();
                SaxionApp.setBackgroundColor(backgroundColor);
                if (Main.env != null) {
                    if (fileName.equals("temp/scores.csv")) {
                        if (CsvFile.delete()) {
                            System.out.println("Deleted");
                        }
                    }
                }
                switchToScreen(new Main());
            }

        }

    }

    /***
     * draws the leaderboard with the top 5 scores from the loaded leaderboard
     *
     */
    private void draw() {
        SaxionApp.drawText("Leaderboard", Settings.width / 2 - Settings.buttonWidth / 2, Settings.height / 7 - Settings.height / 10, 50);
        for (int i = 0; i < 5; i++) {
            Score currentScore = scores.get(i);
            SaxionApp.drawText(currentScore.name, Settings.width / 6, Settings.height / 5 + i * 50, 50);
            SaxionApp.drawText(String.valueOf(currentScore.highScore), (Settings.width / 6) * 5, Settings.height / 5 + i * 50, 50);
        }

        MyButton.drawButton(menuButton.x, menuButton.y, menuButton.width, menuButton.height, Settings.fontSize, "Main Menu", Color.RED);
    }

}
 