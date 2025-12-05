package UI.common;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class MenuPanel extends JPanel {

    private final JLabel exmHub = new JLabel("ExamHub");
    private final JSeparator divider = new JSeparator();

    public RoundedButton dashBoardBtn;
    public RoundedButton quizzesBtn;
    public RoundedButton quizScoreBtn;
    public RoundedButton addQuizBtn;
    public RoundedButton logoutBtn;

    public RoundedButton extraBtn;
    private final boolean showExtra;

    public MenuPanel(String dashboardText,
            String quizzesText,
            String quizScoreText,
            String addQuizText,
            String logoutText,
            String extraText, // نص الزرار الإضافي (لو فاضي مش مهم)
            boolean showExtra) {

        setLayout(null);
        setBackground(new Color(75, 173, 220));
        setBounds(0, 0, 240, 600);

        this.dashBoardBtn = new RoundedButton(dashboardText, 30);
        this.quizzesBtn = new RoundedButton(quizzesText, 30);
        this.quizScoreBtn = new RoundedButton(quizScoreText, 30);
        this.addQuizBtn = new RoundedButton(addQuizText, 30);
        this.logoutBtn = new RoundedButton(logoutText, 30);

        this.extraBtn = new RoundedButton(extraText, 30);
        this.showExtra = showExtra;

        setBoundsForComponents();
    }

    private void setBoundsForComponents() {
        exmHub.setBounds(43, 50, 155, 38);
        exmHub.setFont(new Font("Inter", Font.BOLD, 32));
        exmHub.setForeground(Color.WHITE);
        add(exmHub);

        divider.setBounds(20, 116, 200, 1);
        divider.setForeground(Color.WHITE);
        add(divider);

        dashBoardBtn.setBounds(30, 143, 180, 30);
        quizzesBtn.setBounds(30, 193, 180, 30);
        quizScoreBtn.setBounds(30, 243, 180, 30);
        addQuizBtn.setBounds(30, 293, 180, 30);
        logoutBtn.setBounds(30, 492, 180, 30);

        add(dashBoardBtn);
        add(quizzesBtn);
        add(quizScoreBtn);
        add(addQuizBtn);

        if (showExtra) {
            extraBtn.setBounds(30, 343, 180, 30);
            add(extraBtn);
            logoutBtn.setBounds(30, 492, 180, 30);
        } else {
            logoutBtn.setBounds(30, 492, 180, 30);
        }

        add(logoutBtn);
    }

}
