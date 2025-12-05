package UI.student;

import UI.common.MenuPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Student;

public class StudentFrame extends JFrame {

    // ========================
    //        FIELDS
    // ========================
    private final Student student;
    private final MenuPanel menu;
    private final JPanel contentPanel;

    private final DashboardPage dashboardPage;
    private final QuizPage quizzesPage;
    private final ScoresPage scoresPage;
    private final CreateQuizPage createQuizPage;

    // ========================
    //      CONSTRUCTOR
    // ========================
    public StudentFrame(Student student) {
        this.student = student;

        this.menu = new MenuPanel(
                "Dashboard",
                "All Quizzes",
                "Student Scores",
                "Create Quiz",
                "Logout",
                "",
                false
        );
        this.contentPanel = new JPanel(new CardLayout());

        this.dashboardPage = new DashboardPage(student);
        this.quizzesPage = new QuizPage(student);
        this.scoresPage = new ScoresPage(student);
        this.createQuizPage = new CreateQuizPage(student);

        initFrame();
        addPagesToContentPanel();
        addActionListeners();

        showCard("dashboard");
    }

    // ========================
    //      INIT METHODS
    // ========================
    private void initFrame() {
        setLayout(null);
        setSize(950, 635);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.setBounds(0, 0, 240, 600);
        add(menu);

        contentPanel.setBounds(240, 0, 710, 600);
        add(contentPanel);
    }

    private void addPagesToContentPanel() {
        contentPanel.add(dashboardPage, "dashboard");
        contentPanel.add(quizzesPage, "quizzes");
        contentPanel.add(scoresPage, "scores");
        contentPanel.add(createQuizPage, "Add Quiz");
    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, name);
    }

    private void addActionListeners() {
        menu.dashBoardBtn.addActionListener(e -> showCard("dashboard"));
        menu.quizzesBtn.addActionListener(e -> showCard("quizzes"));
        menu.quizScoreBtn.addActionListener(e -> showCard("scores"));
        menu.addQuizBtn.addActionListener(e -> showCard("Add Quiz"));
        menu.logoutBtn.addActionListener(e -> dispose());
    }
}
