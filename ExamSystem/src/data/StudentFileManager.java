package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

public class StudentFileManager {

    private static final Path STUDENT_FILE = Path.of("src/data/students.txt");

    // يرجّع إجمالي عدد الطلاب
    public static int getTotalStudents() {
        if (!Files.exists(STUDENT_FILE)) {
            return 0;
        }

        int count = 0;

        try (BufferedReader br = Files.newBufferedReader(STUDENT_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // يتأكد إن السطر مش فاضي
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
