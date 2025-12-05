package UI.student;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import model.Student;

public class ScoresPage extends JPanel {

    // ========================
    //       CONSTANTS
    // ========================
    private static final Font TITLE_FONT  = new Font("Inter", Font.BOLD, 24);
    private static final Font SMALL_FONT  = new Font("Inter", Font.PLAIN, 12);
    private static final Color BG_COLOR   = Color.WHITE;
    
    private final JSeparator divider   = new JSeparator();

    // ========================
    //        FIELDS
    // ========================
    private final Student student;
    private JTable scoresTable;

    private final JLabel titleLabel    = new JLabel("Quiz Scores");
    private final JLabel subtitleLabel = new JLabel("Here you can see your quiz results");

    // ========================
    //      CONSTRUCTOR
    // ========================
    public ScoresPage(Student student) {
        this.student = student;

        initPanel();
        initStyles();
        buildTable();
        setComponentsBounds();
        addComponentsToPanel();
    }

    // ========================
    //      INIT METHODS
    // ========================
    private void initPanel() {
        setLayout(null);
        setBackground(BG_COLOR);
        divider.setForeground(new Color(204, 204, 204));

    }

    private void initStyles() {
        titleLabel.setFont(TITLE_FONT);
        subtitleLabel.setFont(SMALL_FONT);
    }

    private void buildTable() {
        String[] columns = { "Quiz Name", "Score", "Total", "Date" };
        Object[][] data = {
            // TODO: replace with real data later
            { "OOP", "8", "10", "2024-12-01" },
            { "Operating System", "7", "10", "2024-12-02" }
        };

        scoresTable = new JTable(data, columns);
    }

    private void setComponentsBounds() {
        titleLabel.setBounds(293, 33, 150, 30);
        subtitleLabel.setBounds(23, 93, 300, 20);
        divider.setBounds(55, 116, 600, 1);
    }

    private void addComponentsToPanel() {
        add(titleLabel);
        add(subtitleLabel);
        add(divider);
        
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.setBounds(30, 130, 640, 350);
        add(scrollPane);
    }
}
