package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

public class AdminFileManager {

    private static final Path ADMIN_FILE = Path.of("src/data/admins.txt");

    public static int getTotalAdmins() {
        if (!Files.exists(ADMIN_FILE)) {
            return 0;
        }

        int count = 0;

        try (BufferedReader br = Files.newBufferedReader(ADMIN_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
