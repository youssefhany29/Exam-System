package UI.admin;

import UI.common.MenuPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Admin;

public class AdminFrame extends JFrame {

    private final Admin admin;
    private final MenuPanel menu;
    private final JPanel contentPanel;

    private final AdminDashboard adminDashboard;
    private final Students students;
    private final Quizzes quizzes;
    private final AddQuizzes addQuiz;
    private final Questions questions;

    public AdminFrame(Admin admin) {
        this.admin = admin;
        this.menu = new MenuPanel(
                "Dashboard",
                "Students",
                "Quizzes",
                "Add Quiz",
                "Logout",
                "Questions",
                true
        );

        this.contentPanel = new JPanel(new CardLayout());

        this.adminDashboard = new AdminDashboard(admin);
        this.students = new Students(admin);
        this.quizzes = new Quizzes(admin);
        this.addQuiz = new AddQuizzes(admin);
        this.questions = new Questions(admin);

        initFrame();
        addPagesToContentPanel();
        addActionListeners();

    }

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

    private void showCard(String name) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, name);
    }

    private void addPagesToContentPanel() {
        contentPanel.add(adminDashboard, "dashboard");
        contentPanel.add(students, "students");
        contentPanel.add(quizzes, "quizzes");
        contentPanel.add(addQuiz, "addQuiz");
        contentPanel.add(questions, "questions");
    }

    private void addActionListeners() {
        menu.dashBoardBtn.addActionListener(e -> showCard("dashboard"));
        menu.quizzesBtn.addActionListener(e -> showCard("students"));
        menu.quizScoreBtn.addActionListener(e -> showCard("quizzes"));
        menu.addQuizBtn.addActionListener(e -> showCard("addQuiz"));
        menu.extraBtn.addActionListener(e -> showCard("questions"));
        menu.logoutBtn.addActionListener(e -> dispose());
    }

}
