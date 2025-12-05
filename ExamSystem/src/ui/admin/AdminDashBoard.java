package UI.admin;

import UI.admin.panel.InfoCard;
import java.awt.Color;
import javax.swing.*;
import model.Admin;
import data.StudentFileManager;
import data.QuizFileManager;
import data.AdminFileManager;
import data.QuestionFileManager;
import java.awt.Font;

public class AdminDashboard extends JPanel {

    // ========================
    //       CONSTANTS
    // ========================
    private static final Font TITLE_FONT   = new Font("Inter", Font.BOLD, 24);
    private static final Font WELCOME_FONT = new Font("Inter", Font.BOLD, 20);
    private static final Font SMALL_FONT   = new Font("Inter", Font.PLAIN, 12);

    private static final Color BG_COLOR         = Color.WHITE;
    private static final Color EMOJI_COLOR      = new Color(248, 201, 87);
    private static final Color DIVIDER_COLOR    = new Color(204, 204, 204);
    
    // ========================
    //        FIELDS
    // ========================
    
    private final Admin admin;
    
    private final JLabel mainPageLabel = new JLabel("Dashboard");
    private final JLabel welcomeLabel;
    private final JLabel emojiLabel    = new JLabel("👋");
    private final JLabel subTextLabel  = new JLabel("Manage students, quizzes, and questions");
    private final JSeparator divider   = new JSeparator();
    
    // ========================
    //      CONSTRUCTOR
    // ========================
    
    public AdminDashboard(Admin admin) {
        this.admin = admin;
        String name = admin.getFname();
        this.welcomeLabel = new JLabel("Hi " + name);
        
        setLayout(null);
        setBackground(Color.WHITE);

        int studentCount   = StudentFileManager.getTotalStudents();
        int examCount      = QuizFileManager.getTotalAdminQuizzes();
        int questionsCount = QuestionFileManager.getTotalStoredQuestions();
        int adminsCount    = AdminFileManager.getTotalAdmins();

        InfoCard totalStudentsCard = new InfoCard(
                "Total Students",
                studentCount,
                "icons/students.png",
                new Color(75, 173, 220)
        );
        totalStudentsCard.setBounds(155, 183, 180, 140);
        add(totalStudentsCard);

        InfoCard totalExamsCard = new InfoCard(
                "Total Exams",
                examCount,
                "icons/exams.png",
                new Color(75, 173, 220)
        );
        totalExamsCard.setBounds(375, 183, 180, 140);
        add(totalExamsCard);

        InfoCard totalQuestionsCard = new InfoCard(
                "Total Questions",
                questionsCount,
                "icons/questions.png",
                new Color(75, 173, 220)
        );
        totalQuestionsCard.setBounds(375, 363, 180, 140);
        add(totalQuestionsCard);

        InfoCard totalAdminsCard = new InfoCard(
                "Total Admins",
                adminsCount,
                "icons/admins.png",
                new Color(75, 173, 220)
        );
        totalAdminsCard.setBounds(155, 363, 180, 140);
        add(totalAdminsCard);
        initPanel();
        initComponentsStyles();
        setComponentsBounds();
        addComponentsToPanel();
    }
    
    
    // ========================
    //      INIT METHODS
    // ========================

    private void initPanel() {
        setLayout(null);
        setBackground(BG_COLOR);
    }

    private void initComponentsStyles() {
        mainPageLabel.setFont(TITLE_FONT);
        emojiLabel.setFont(TITLE_FONT);

        welcomeLabel.setFont(WELCOME_FONT);
        subTextLabel.setFont(SMALL_FONT);

        emojiLabel.setForeground(EMOJI_COLOR);
        divider.setForeground(DIVIDER_COLOR);
    }

    private void setComponentsBounds() {
        mainPageLabel.setBounds(290, 33, 200, 30);
        welcomeLabel.setBounds(23, 62, 200, 25);
        emojiLabel.setBounds(132, 57, 35, 35);
        subTextLabel.setBounds(23, 93, 300, 20);
        divider.setBounds(55, 116, 600, 1);
    }

    private void addComponentsToPanel() {
        add(mainPageLabel);
        add(welcomeLabel);
        add(emojiLabel);
        add(subTextLabel);
        add(divider);

    }
}
