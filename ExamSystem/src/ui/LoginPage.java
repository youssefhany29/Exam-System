package UI;

import UI.common.RoundedButton;
import UI.common.UserChecker;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Admin;
import model.Student;

public abstract class LoginPage extends JFrame implements ActionListener {

    private final UserChecker checker = new UserChecker(ADMIN_FILE, STUDENT_FILE);

    private static final Path ADMIN_FILE = Path.of("src/data/admins.txt");
    private static final Path STUDENT_FILE = Path.of("src/data/students.txt");

    private final JPanel upper_login_panel = new JPanel(null);
    private final JLabel wlcmTxt = new JLabel("Welcome to ExamHub");
    private final JLabel emoji = new JLabel("👋");
    private final JLabel smlTxt = new JLabel("Login to start taking your quizzes.");

    private final JPanel down_login_panel = new JPanel(null);
    private final JLabel usrIdTxt = new JLabel("User ID:");
    private final JTextField usrField = new JTextField();

    private final JLabel pswrdTxt = new JLabel("Password:");
    private final JPasswordField pass = new JPasswordField();

    private final RoundedButton login = new RoundedButton("Login", 30);
    private final JLabel troubleTxt = new JLabel("Trouble logging in? Contact your instructor.");

    protected LoginPage(String title) {
        this.setTitle(title);
        this.setLayout(null);
        this.setSize(600, 435);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBoundsForComponents();
        setDesign();
        setFont();
        setPanel();
    }

    private void setBoundsForComponents() {
        upper_login_panel.setBounds(0, 0, 600, 145);
        wlcmTxt.setBounds(140, 41, 320, 30);
        emoji.setBounds(430, 41, 30, 30);
        smlTxt.setBounds(171, 77, 257, 30);

        down_login_panel.setBounds(0, 145, 600, 255);
        usrIdTxt.setBounds(26, 48, 109, 21);
        usrField.setBounds(135, 41, 330, 30);

        pswrdTxt.setBounds(26, 95, 109, 21);
        pass.setBounds(135, 88, 330, 30);

        login.setBounds(200, 165, 200, 40);
        troubleTxt.setBounds(165, 211, 327, 30);
    }

    private void setDesign() {
        upper_login_panel.setBackground(new Color(255, 255, 255));
        wlcmTxt.setForeground(Color.black);
        emoji.setForeground(new Color(248, 201, 87));
        smlTxt.setForeground(Color.black);

        down_login_panel.setBackground(new Color(75, 173, 220));
        usrIdTxt.setForeground(Color.WHITE);
        pswrdTxt.setForeground(Color.WHITE);
        troubleTxt.setForeground(Color.WHITE);

        login.setForeground(Color.WHITE);
        login.setBackground(new Color(44, 44, 44));
    }

    private void setFont() {
        Font f28 = new Font("Inter", Font.BOLD, 28);
        wlcmTxt.setFont(f28);

        Font emoji_ = new Font("Inter", Font.BOLD, 28);
        emoji.setFont(emoji_);

        Font f20 = new Font("Times New Roman", Font.ROMAN_BASELINE, 20);
        usrIdTxt.setFont(f20);
        pswrdTxt.setFont(f20);
        login.setFont(f20);

        Font f16 = new Font("Times New Roman", Font.ROMAN_BASELINE, 16);
        smlTxt.setFont(f16);
        troubleTxt.setFont(f16);
    }

    private void setPanel() {
        upper_login_panel.add(wlcmTxt);
        upper_login_panel.add(smlTxt);
        upper_login_panel.add(emoji);
        this.add(upper_login_panel);

        down_login_panel.add(usrIdTxt);
        down_login_panel.add(usrField);
        down_login_panel.add(pswrdTxt);
        down_login_panel.add(pass);
        down_login_panel.add(login);
        down_login_panel.add(troubleTxt);
        this.add(down_login_panel);

        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent clicked) {
        if (clicked.getSource() == login) {
            checkLogin();
        }
    }

    protected abstract void openAdminPage(String userId);
    protected abstract void openStudentPage(String userId);

    protected void checkLogin() {
        String userId = usrField.getText().trim();
        String password = new String(pass.getPassword()).trim();

        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both User ID and Password.",
                    "Missing Data",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Admin admin = checker.findAdmin(userId, password);
            if (admin != null) {
                openAdminPage(admin.getId());
                return;
            }

            Student student = checker.findStudent(userId, password);
            if (student != null) {
                openStudentPage(student.getId());
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Invalid ID or Password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error reading user files:\n" + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
