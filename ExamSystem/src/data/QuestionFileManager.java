package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import model.Question;
import model.MultipleChoiceQuestion;
import model.TrueFalseQuestion;

public class QuestionFileManager {

    private static final Path QUESTIONS_FILE = Path.of("src/data/questions.txt");
    private static final Path QUIZ_QUESTIONS_FILE = Path.of("src/data/quiz_questions.txt");

    public static class StoredQuestion {

        private final String id;
        private final Question question;

        public StoredQuestion(String id, Question question) {
            this.id = id;
            this.question = question;
        }

        public String getId() {
            return id;
        }

        public Question getQuestion() {
            return question;
        }
    }

    public static List<StoredQuestion> loadAllQuestions() {
        List<StoredQuestion> list = new ArrayList<>();

        if (!Files.exists(QUESTIONS_FILE)) {
            return list;
        }

        try (BufferedReader br = Files.newBufferedReader(QUESTIONS_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                StoredQuestion sq = parseQuestionLine(line);
                if (sq != null) {
                    list.add(sq);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<StoredQuestion> loadQuestionsForQuiz(String quizId) {
        List<StoredQuestion> result = new ArrayList<>();

        if (!Files.exists(QUIZ_QUESTIONS_FILE) || !Files.exists(QUESTIONS_FILE)) {
            return result;
        }

        Set<String> questionIds = new HashSet<>();

        try (BufferedReader br = Files.newBufferedReader(QUIZ_QUESTIONS_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 2) {
                    continue;
                }

                String qzId = parts[0];
                String qId = parts[1];

                if (qzId.equals(quizId)) {
                    questionIds.add(qId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (questionIds.isEmpty()) {
            return result;
        }

        try (BufferedReader br = Files.newBufferedReader(QUESTIONS_FILE)) {
            String line;
            while ((line = br.readLine()) != null) {
                StoredQuestion sq = parseQuestionLine(line);
                if (sq != null && questionIds.contains(sq.getId())) {
                    result.add(sq);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void addQuestion(String id, Question question) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                QUESTIONS_FILE,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            String line = formatQuestionLine(id, question);
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateQuestion(String id, Question question) {
        if (!Files.exists(QUESTIONS_FILE)) {
            return;
        }

        Path tempFile = QUESTIONS_FILE.getParent().resolve("questions_tmp.txt");

        try (BufferedReader br = Files.newBufferedReader(QUESTIONS_FILE); BufferedWriter bw = Files.newBufferedWriter(
                tempFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {

            String line;
            String newLine = formatQuestionLine(id, question);

            while ((line = br.readLine()) != null) {
                if (line.startsWith(id + ";")) {
                    bw.write(newLine);
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            Files.delete(QUESTIONS_FILE);
            Files.move(tempFile, QUESTIONS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQuestion(String id) {
        if (Files.exists(QUESTIONS_FILE)) {
            Path tempFile = QUESTIONS_FILE.getParent().resolve("questions_tmp.txt");

            try (BufferedReader br = Files.newBufferedReader(QUESTIONS_FILE); BufferedWriter bw = Files.newBufferedWriter(
                    tempFile,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {

                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(id + ";")) {
                        continue;
                    }
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Files.delete(QUESTIONS_FILE);
                Files.move(tempFile, QUESTIONS_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Files.exists(QUIZ_QUESTIONS_FILE)) {
            Path tempMap = QUIZ_QUESTIONS_FILE.getParent().resolve("quiz_questions_tmp.txt");

            try (BufferedReader br = Files.newBufferedReader(QUIZ_QUESTIONS_FILE); BufferedWriter bw = Files.newBufferedWriter(
                    tempMap,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length < 2) {
                        continue;
                    }

                    String qId = parts[1];
                    if (qId.equals(id)) {
                        continue;
                    }
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Files.delete(QUIZ_QUESTIONS_FILE);
                Files.move(tempMap, QUIZ_QUESTIONS_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void linkQuestionToQuiz(String quizId, String questionId) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                QUIZ_QUESTIONS_FILE,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            bw.write(quizId + ";" + questionId);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unlinkQuestionFromQuiz(String quizId, String questionId) {
        if (!Files.exists(QUIZ_QUESTIONS_FILE)) {
            return;
        }

        Path tempFile = QUIZ_QUESTIONS_FILE.getParent().resolve("quiz_questions_tmp.txt");

        try (BufferedReader br = Files.newBufferedReader(QUIZ_QUESTIONS_FILE); BufferedWriter bw = Files.newBufferedWriter(
                tempFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 2) {
                    continue;
                }

                String qzId = parts[0];
                String qId = parts[1];

                if (qzId.equals(quizId) && qId.equals(questionId)) {
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(QUIZ_QUESTIONS_FILE);
            Files.move(tempFile, QUIZ_QUESTIONS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StoredQuestion parseQuestionLine(String line) {
        String[] parts = line.split(";");
        if (parts.length < 6) {
            return null;
        }

        String id = parts[0];
        String type = parts[1];
        String text = parts[2];
        int score;
        try {
            score = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            return null;
        }

        String optionsPart = parts[4];
        String correctPart = parts[5];

        if ("MCQ".equalsIgnoreCase(type)) {
            List<String> options = new ArrayList<>();
            if (!optionsPart.isEmpty() && !optionsPart.equals("-")) {
                options = Arrays.asList(optionsPart.split("\\|"));
            }
            Question q = new MultipleChoiceQuestion(text, score, options, correctPart);
            return new StoredQuestion(id, q);
        } else if ("TF".equalsIgnoreCase(type)) {
            boolean answer = Boolean.parseBoolean(correctPart);
            Question q = new TrueFalseQuestion(text, score, answer);
            return new StoredQuestion(id, q);
        }

        return null;
    }

    private static String formatQuestionLine(String id, Question question) {
        String type;
        String text = question.getText();
        int score = question.getScore();
        String optionsPart = "-";
        String correctPart = "";

        if (question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
            type = "MCQ";
            List<String> options = mcq.getOptions();
            optionsPart = String.join("|", options);
            correctPart = mcq.getCorrectAnswer();
        } else if (question instanceof TrueFalseQuestion) {
            TrueFalseQuestion tf = (TrueFalseQuestion) question;
            type = "TF";
            optionsPart = "-";
            correctPart = String.valueOf(tf.getAnswer());
        } else {
            type = "UNK";
        }

        return String.join(";",
                id,
                type,
                text,
                String.valueOf(score),
                optionsPart,
                correctPart
        );
    }

    public static int getTotalStoredQuestions() {
        return loadAllQuestions().size();
    }
}
