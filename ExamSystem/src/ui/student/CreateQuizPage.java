package UI.student;

import data.QuizFileManager;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import model.Quiz;
import model.Student;

public class CreateQuizPage extends JPanel {

    private static final Font TITLE_FONT  = new Font("Inter", Font.BOLD, 24);
    private static final Font SMALL_FONT  = new Font("Inter", Font.PLAIN, 12);
    private static final Color BG_COLOR   = Color.WHITE;

    private final Student student;

    private final JLabel titleLabel    = new JLabel("Add Quiz");
    private final JLabel subtitleLabel = new JLabel("Select a quiz created by your instructor to add it to your list.");
    private final JSeparator divider   = new JSeparator();

    
    private JTable availableQuizzesTable;
    private final JButton addQuizButton = new JButton("Add Quiz");

    private List<Quiz> adminQuizzes;

    public CreateQuizPage(Student student) {
        this.student = student;

        initPanel();
        initStyles();
        loadAdminQuizzes();
        buildTable();
        setComponentsBounds();
        addComponentsToPanel();
        addListeners();
    }

    private void initPanel() {
        setLayout(null);
        setBackground(BG_COLOR);
        divider.setForeground(new Color(204, 204, 204));
    }

    private void initStyles() {
        titleLabel.setFont(TITLE_FONT);
        subtitleLabel.setFont(SMALL_FONT);
    }

    private void loadAdminQuizzes() {
        adminQuizzes = QuizFileManager.loadAdminQuizzes();
    }

    private void buildTable() {
        String[] columns = { "Quiz Name", "Questions", "Time (min)" };

        Object[][] data = new Object[adminQuizzes.size()][3];
        for (int i = 0; i < adminQuizzes.size(); i++) {
            Quiz q = adminQuizzes.get(i);
            data[i][0] = q.getName();
            data[i][1] = q.getQuestionCount();
            data[i][2] = q.getTimeMinutes();
        }

        availableQuizzesTable = new JTable(data, columns);
    }

    private void setComponentsBounds() {
        titleLabel.setBounds(303, 33, 150, 30);
        subtitleLabel.setBounds(23, 93, 400, 20);
        divider.setBounds(55, 116, 600, 1);

        addQuizButton.setBounds(560, 487, 130, 38);
    }

    private void addComponentsToPanel() {
        add(titleLabel);
        add(subtitleLabel);
        add(addQuizButton);
        add(divider);
        
        JScrollPane scrollPane = new JScrollPane(availableQuizzesTable);
        scrollPane.setBounds(30, 140, 640, 350);
        add(scrollPane);
    }

    private void addListeners() {
        addQuizButton.addActionListener(e -> {
            int selectedRow = availableQuizzesTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a quiz first.");
                return;
            }

            Quiz selectedQuiz = adminQuizzes.get(selectedRow);

            // نضيف الكويز لفايل الطالب
            QuizFileManager.addQuizForStudent(student.getId(), selectedQuiz);

            JOptionPane.showMessageDialog(this,
                    "Quiz \"" + selectedQuiz.getName() + "\" added to your quizzes.");
        });
    }
}
