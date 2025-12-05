package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

public class QuizAttemptWriter {

    private static final Path ATTEMPTS_FILE = Path.of("src/data/quiz_attempts.txt");

    public static void saveAttempt(String studentId,
                                   String quizName,
                                   int questionCount,
                                   int timeMinutes,
                                   int score) {

        String line = String.join(";",
                studentId,
                quizName,
                String.valueOf(questionCount),
                String.valueOf(timeMinutes),
                String.valueOf(score),
                LocalDateTime.now().toString()
        );

        try (BufferedWriter bw = Files.newBufferedWriter(
                ATTEMPTS_FILE,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
