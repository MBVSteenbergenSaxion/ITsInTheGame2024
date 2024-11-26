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
import java.util.Set;

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

    utils.MyButton menuButton = new MyButton();


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


        menuButton.x = Settings.width / 3;
        menuButton.y = (int) (Settings.height * 0.8 - Settings.height * 0.15);
        menuButton.width = Settings.buttonWidth;
        menuButton.height = Settings.buttonHeight;



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
                    menuButton.x, menuButton.y, menuButton.width, menuButton.height)) {

                switchToScreen(new Main());
            }



        }

    }

    private void draw() {
        SaxionApp.drawText("Leaderboard", Settings.width/3, Settings.height/7 - Settings.height/10, 50);
        for (int i = 0; i < scores.size() -1; i++) {
            Score currentScore = scores.get(i);

            SaxionApp.drawText(currentScore.name, Settings.width/6, Settings.height/5+i*50, 50);
            SaxionApp.drawText(String.valueOf(currentScore.highScore),(Settings.width/6)*5 - 50,Settings.height/5+i*50,50);
        }

            MyButton.drawButton(menuButton.x,menuButton.y, menuButton.width, menuButton.height, Settings.fontSize, "Main Menu");
    }

}



