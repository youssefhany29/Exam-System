package UI.student;

import UI.student.panels.QuizCardsPanel;
import data.QuizFileManager;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import model.Quiz;
import model.Student;

public class QuizPage extends JPanel {

    private static final Font TITLE_FONT    = new Font("Inter", Font.BOLD, 24);
    private static final Font WELCOME_FONT  = new Font("Inter", Font.BOLD, 20);
    private static final Font SMALL_FONT    = new Font("Inter", Font.PLAIN, 12);
    private static final Color BG_COLOR     = Color.WHITE;
    private static final Color EMOJI_COLOR  = new Color(248, 201, 87);
    private static final Color DIVIDER_COLOR= new Color(204, 204, 204);

    private final Student student;

    private final JLabel titleLabel      = new JLabel("Quizzes");
    private final JLabel welcomeLabel;
    private final JLabel emojiLabel      = new JLabel("👋");
    private final JLabel subtitleLabel   = new JLabel("Choose a quiz and start practicing now.");
    private final JLabel availableLabel  = new JLabel("Available quizzes");
    private final JSeparator divider     = new JSeparator();

    private final JButton randomQuizBtn  = new JButton("Start Random Quiz");

    private List<Quiz> studentQuizzes;
    private final QuizCardsPanel cardsPanel;   // ← البانل الجديدة

    public QuizPage(Student student) {
        this.student = student;

        String name = student.getFname();
        this.welcomeLabel = new JLabel("Hi, " + name);

        // 1) load quizzes from file
        loadStudentQuizzes();

        // 2) create cards panel with list
        this.cardsPanel = new QuizCardsPanel(studentQuizzes);

        initPanel();
        initStyles();
        setComponentsBounds();
        addComponentsToPanel();
        addListeners();
    }

    private void initPanel() {
        setLayout(null);
        setBackground(BG_COLOR);
    }

    private void initStyles() {
        titleLabel.setFont(TITLE_FONT);
        welcomeLabel.setFont(WELCOME_FONT);
        subtitleLabel.setFont(SMALL_FONT);
        availableLabel.setFont(SMALL_FONT);

        emojiLabel.setFont(TITLE_FONT);
        emojiLabel.setForeground(EMOJI_COLOR);

        divider.setForeground(DIVIDER_COLOR);

        randomQuizBtn.setFont(SMALL_FONT);
    }

    private void setComponentsBounds() {
        titleLabel.setBounds(309, 33, 200, 30);
        welcomeLabel.setBounds(23, 62, 200, 25);
        emojiLabel.setBounds(132, 57, 35, 35);
        subtitleLabel.setBounds(23, 93, 400, 20);
        divider.setBounds(55, 116, 600, 1);

        availableLabel.setBounds(23, 130, 200, 20);

        cardsPanel.setBounds(23, 160, 650, 280);

        randomQuizBtn.setBounds(510, 460, 160, 30);
    }

    private void addComponentsToPanel() {
        add(titleLabel);
        add(welcomeLabel);
        add(emojiLabel);
        add(subtitleLabel);
        add(divider);
        add(availableLabel);

        add(cardsPanel);
        add(randomQuizBtn);
    }

    private void addListeners() {
        randomQuizBtn.addActionListener(e -> {
            if (studentQuizzes == null || studentQuizzes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You don't have any quizzes yet.");
                return;
            }
            int idx = (int) (Math.random() * studentQuizzes.size());
            Quiz randomQuiz = studentQuizzes.get(idx);
            System.out.println("Start random quiz: " + randomQuiz.getName());
            // TODO: فتح صفحة الامتحان
        });
    }

    private void loadStudentQuizzes() {
        studentQuizzes = QuizFileManager.loadStudentQuizzes(student.getId());
    }

    // لو في أي وقت زاد عدد الكويزات وعايز تحدث البانل:
    public void refreshQuizzes() {
        loadStudentQuizzes();
        cardsPanel.setQuizzes(studentQuizzes);
    }
}
