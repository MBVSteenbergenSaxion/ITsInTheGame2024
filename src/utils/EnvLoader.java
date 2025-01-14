package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {

    /***
     * Searches for a file with the specified file path.
     * If a file is found, lines in it get set in the HashMap with the following principle:
     * Key: variable name
     * Value: variable
     *
     */

    public static Map<String, String> loadEnv(String filePath) throws IOException {
        Map<String, String> env = new HashMap<>();

        Files.lines(Paths.get(filePath)).forEach(line -> {
            String trimmedLine = line.trim();
            if (!trimmedLine.startsWith("#") && trimmedLine.contains("=")) {
                String[] parts = trimmedLine.split("=", 2);
                env.put(parts[0].trim(), parts[1].trim());
            }
        });

        return env;
    }
}
