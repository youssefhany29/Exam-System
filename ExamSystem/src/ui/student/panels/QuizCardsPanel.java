package UI.student.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Quiz;

public class QuizCardsPanel extends JPanel {

    // ====== CONSTANTS ======
    private static final Font CARD_TITLE_FONT = new Font("Inter", Font.BOLD, 14);
    private static final Font CARD_INFO_FONT  = new Font("Inter", Font.PLAIN, 10);
    private static final Font BUTTON_FONT     = new Font("Inter", Font.PLAIN, 10);

    private static final Color CARD_BG_COLOR  = new Color(75, 173, 220);
    private static final Color CARD_BTN_BG    = new Color(51, 51, 51);
    private static final Color CARD_BTN_FG    = Color.WHITE;

    // ====== FIELDS ======
    private List<Quiz> quizzes;

    // ====== CONSTRUCTOR ======
    public QuizCardsPanel(List<Quiz> quizzes) {
        this.quizzes = quizzes;
        setOpaque(false); // نخليها شفافة من تحت
        rebuildCards();
    }

    // تقدر تستدعيها لما الليست تتغيّر
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
        rebuildCards();
    }

    // ====== INTERNAL BUILD ======
    private void rebuildCards() {
        removeAll();

        if (quizzes == null || quizzes.isEmpty()) {
            setLayout(new GridLayout(1, 1));
            JLabel empty = new JLabel("No quizzes available.", SwingConstants.CENTER);
            add(empty);
            revalidate();
            repaint();
            return;
        }

        int cols = 4;
        int rows = (int) Math.ceil(quizzes.size() / (double) cols);
        if (rows == 0) rows = 1;

        setLayout(new GridLayout(rows, cols, 25, 25));

        for (Quiz quiz : quizzes) {
            JPanel card = createQuizCard(quiz);
            add(card);
        }

        revalidate();
        repaint();
    }

    private JPanel createQuizCard(Quiz quiz) {
        JPanel card = new JPanel(null);
        card.setBackground(CARD_BG_COLOR);

        JLabel quizNameLabel = new JLabel(quiz.getName(), SwingConstants.CENTER);
        quizNameLabel.setFont(CARD_TITLE_FONT);
        quizNameLabel.setForeground(Color.WHITE);
        quizNameLabel.setBounds(10, 10, 140, 20);

        String infoText = "Questions: " + quiz.getQuestionCount()
                + " • Time: " + quiz.getTimeMinutes() + " min";
        JLabel infoLabel = new JLabel(infoText, SwingConstants.CENTER);
        infoLabel.setFont(CARD_INFO_FONT);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBounds(10, 35, 140, 20);

        JButton detailsBtn = new JButton("View details");
        detailsBtn.setFont(BUTTON_FONT);
        detailsBtn.setBackground(CARD_BTN_BG);
        detailsBtn.setForeground(CARD_BTN_FG);
        detailsBtn.setFocusPainted(false);
        detailsBtn.setBorderPainted(false);
        detailsBtn.setBounds(35, 70, 90, 25);

        // TODO: هنا بعدين تربطه بفتح صفحة تفاصيل أو بدء الامتحان
        detailsBtn.addActionListener(e -> {
            System.out.println("View details for quiz: " + quiz.getName());
        });

        card.add(quizNameLabel);
        card.add(infoLabel);
        card.add(detailsBtn);

        return card;
    }
}
