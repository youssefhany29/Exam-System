package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import model.Quiz;

public class QuizFileManager {

    private static final Path ADMIN_QUIZZES_FILE   = Path.of("src/data/admin_quizzes.txt");
    private static final Path STUDENT_QUIZZES_FILE = Path.of("src/data/student_quizzes.txt");

    public static List<Quiz> loadAdminQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();

        if (!Files.exists(ADMIN_QUIZZES_FILE)) {
            return quizzes;
        }

        try (BufferedReader br = Files.newBufferedReader(ADMIN_QUIZZES_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 4) continue;

                String id       = parts[0];
                String name     = parts[1];
                int questions   = Integer.parseInt(parts[2]);
                int timeMinutes = Integer.parseInt(parts[3]);

                quizzes.add(new Quiz(id, name, questions, timeMinutes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public static List<Quiz> loadStudentQuizzes(String studentId) {
        List<Quiz> result = new ArrayList<>();

        if (!Files.exists(STUDENT_QUIZZES_FILE) || !Files.exists(ADMIN_QUIZZES_FILE)) {
            return result;
        }

        List<Quiz> adminQuizzes = loadAdminQuizzes();

        try (BufferedReader br = Files.newBufferedReader(STUDENT_QUIZZES_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 2) continue;

                String sId    = parts[0];
                String quizId = parts[1];

                if (!sId.equals(studentId)) continue;

                for (Quiz q : adminQuizzes) {
                    if (q.getId().equals(quizId)) {
                        result.add(q);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void addQuizForStudent(String studentId, Quiz quiz) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                STUDENT_QUIZZES_FILE,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            bw.write(studentId + ";" + quiz.getId());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getTotalAdminQuizzes() {
        return loadAdminQuizzes().size();
    }

    public static int getTotalQuestions() {
        int total = 0;
        for (Quiz q : loadAdminQuizzes()) {
            total += q.getQuestionCount();
        }
        return total;
    }
}
