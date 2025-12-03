package UI.common;

import UI.LoginPage;
import javax.swing.JOptionPane;

public class MainLogin extends LoginPage {

    public MainLogin() {
        super("ExamHub - Login");
    }

    public static void main(String[] args) {
        MainLogin page = new MainLogin();
        page.setVisible(true);
    }

    @Override
    protected void openAdminPage(String userId) {
        JOptionPane.showMessageDialog(
                this,
                "Admin logged in: " + userId,
                "Admin Login",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    protected void openStudentPage(String userId) {
        JOptionPane.showMessageDialog(
                this,
                "Student logged in: " + userId,
                "Student Login",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}

    

