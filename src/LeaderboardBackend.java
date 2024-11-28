import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import Leaderboard.*;
import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

public class LeaderboardBackend {

    public static ArrayList<Score> getScores(String CsvFile) throws FileNotFoundException {
        ArrayList<Score> scores = new ArrayList<>();
        CsvReader csvreader = new CsvReader(CsvFile);
        csvreader.skipRow();
        csvreader.setSeparator(',');
        while (csvreader.loadRow()){
            Score score = new Score();
            score.name = csvreader.getString(0);
            score.highScore = csvreader.getInt(1);
            scores.add(score);
        }
        return scores;
    }

}
