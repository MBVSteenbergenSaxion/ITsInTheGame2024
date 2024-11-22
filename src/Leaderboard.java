import nl.saxion.app.SaxionApp;
import utils.*;
import Leaderboard.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import javax.security.sasl.SaslClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Leaderboard extends Canvas {

    public Leaderboard() {
        super();
    }

    /***
     * Initialize buttons and add their attributes (see MyButton class for the drawing methods)
     *
     */

    private int highscore = 1000;
    private String username = "name";
    ArrayList<Score> scores = new ArrayList<>();

    utils.MyButton gameButton = new MyButton();
    utils.MyButton leaderBoardButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    @Override
    public void init() {
        String filename = "resources/Leaderboard/scores.csv";
        File CsvFile = new File(filename);

        if (CsvFile.exists() && !CsvFile.isDirectory()) {
            try {

                scores = LeaderboardBackend.getScores(filename);
                for (int i = 0; i < scores.size() -1; i++) {
                    //Score currentScore = scores.get(i);
                    //System.out.println(currentScore.name);
                    //System.out.println(currentScore.highScore);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


        }


        gameButton.x = Settings.width / 3;
        gameButton.y = (int) (Settings.height * 0.3 - Settings.height * 0.15);
        gameButton.width = Settings.buttonWidth;
        gameButton.height = Settings.buttonHeight;

        leaderBoardButton.x = Settings.width / 3;
        leaderBoardButton.y = (int) (Settings.height * 0.6 - Settings.height * 0.15);
        leaderBoardButton.width = Settings.buttonWidth;
        leaderBoardButton.height = Settings.buttonHeight;

        quitButton.x = Settings.width / 3;
        quitButton.y = (int) (Settings.height * 0.9 - Settings.height * 0.15);
        quitButton.width = Settings.buttonWidth;
        quitButton.height = Settings.buttonHeight;


    }

    @Override
    public void loop() {
        draw();

    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

        /***
         * Main menu button listeners
         *
         * 18-11-2024, quit button is self-explanatory
         *
         */


        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (utils.Utility.checkBounds(x, y,
                    gameButton.x, gameButton.y, gameButton.width, gameButton.height)) {


            }

            if (utils.Utility.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height)) {

                System.exit(0);

            }

            if (utils.Utility.checkBounds(x, y,
                    leaderBoardButton.x, leaderBoardButton.y, leaderBoardButton.width, leaderBoardButton.height)) {

            }

        }

    }

    private void draw() {
        for (int i = 0; i < scores.size() -1; i++) {
           Score currentScore = scores.get(i);
            SaxionApp.drawText(currentScore.name, 100, 100, 10);
        }


    }

}



