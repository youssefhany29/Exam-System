package UI.common;

import UI.LoginPage;
import UI.admin.AdminFrame;
import UI.student.StudentFrame;
import model.Admin;
import model.Student;

public class MainLogin extends LoginPage {

    public MainLogin() {
        super("ExamHub - Login");
    }

    public static void main(String[] args) {
        MainLogin page = new MainLogin();
        page.setVisible(true);
    }

    @Override
    protected void openAdminPage(Admin admin) {
        AdminFrame dashboard = new AdminFrame(admin);
        dashboard.setVisible(true);
        this.dispose();
    }

    @Override
    protected void openStudentPage(Student student) {
        StudentFrame dashboard = new StudentFrame(student);
        dashboard.setVisible(true);
        this.dispose();
    }
}
