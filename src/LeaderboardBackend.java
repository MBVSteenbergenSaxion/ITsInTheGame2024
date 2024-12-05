import java.io.*;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import utils.SFTP;
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

                if (scoreSort.get(j).highScore < scoreSort.get(j + 1).highScore) {
                    Score temp = scoreSort.get(j);
                    scoreSort.set(j, scoreSort.get(j + 1));
                    scoreSort.set(j + 1, temp);
                }
            }
        }
    }

    public static void writeToCSVOffline(String[] score) throws IOException {

        File CSVFile = new File("resources/Leaderboard/scores.csv");
        FileWriter fw = new FileWriter(CSVFile.getAbsoluteFile(), true);

        BufferedWriter bw = new BufferedWriter(fw);

        //String array for score
        bw.write("Username,1000");
        bw.close();

    }

    public static void writeToCSVOnline(String[] score) throws IOException, JSchException, SftpException {

        String username = Main.env.get("USER");
        String password = Main.env.get("PASS");
        String ip = Main.env.get("IP");

        String tempPath = "temp/scores.csv";

        SFTP.downloadFile(username, password, ip);

        File CSVFile = new File(tempPath);
        FileWriter fw = new FileWriter(CSVFile.getAbsoluteFile(), true);

        BufferedWriter bw = new BufferedWriter(fw);

        //String array for score
        bw.write("Username,1000");
        bw.close();

        SFTP.deleteFile(username, password, ip);
        SFTP.putFile(username, password, ip);

        if(CSVFile.delete()) System.out.println("temp emptied");

    }

}