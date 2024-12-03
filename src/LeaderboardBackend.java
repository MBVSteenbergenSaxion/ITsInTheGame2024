import java.io.FileNotFoundException;
import java.util.ArrayList;

import Leaderboard.*;
import nl.saxion.app.CsvReader;

public class LeaderboardBackend {

    public static ArrayList<Score> getScores(String CsvFile) throws FileNotFoundException {
        ArrayList<Score> scores = new ArrayList<>();
        CsvReader csvreader = new CsvReader(CsvFile);
        csvreader.skipRow();
        csvreader.setSeparator(',');
        while (csvreader.loadRow()) {
            Score score = new Score();
            score.name = csvreader.getString(0);
            score.highScore = csvreader.getInt(1);
            scores.add(score);
        }
        sortScores(scores);
        return scores;
    }
private static void sortScores(ArrayList<Score> scoreSort) {
    int n = scoreSort.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            // Compare the ages of adjacent students
            if (scoreSort.get(j).highScore < scoreSort.get(j + 1).highScore) {
                // Swap the students if they're in the wrong order
                Score temp = scoreSort.get(j);
                scoreSort.set(j, scoreSort.get(j + 1));
                scoreSort.set(j + 1, temp);
            }
        }
    }
}
}