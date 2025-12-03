package UI.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import model.Admin;
import model.Student;

public class UserChecker {

    private final Path adminFile;
    private final Path studentFile;

    public UserChecker(Path adminFile, Path studentFile) {
        this.adminFile = adminFile;
        this.studentFile = studentFile;
    }

    public Admin findAdmin(String id, String password) throws IOException {
        System.out.println("🔍 Looking for admin in: " + adminFile.toAbsolutePath());

        if (!Files.exists(adminFile)) {
            System.out.println("❌ Admin file NOT FOUND!");
            return null;
        }

        try (BufferedReader br = Files.newBufferedReader(adminFile)) {
            String line;
            while ((line = br.readLine()) != null) {

                System.out.println("Admin line: " + line);

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";");
                // نقبل أقل حاجة: id;password
                if (parts.length < 2) {
                    System.out.println("❗ Skipping admin line (less than 2 parts)");
                    continue;
                }

                String fileId   = parts[0].trim();
                String filePwd  = parts[1].trim();
                String fName    = (parts.length > 2) ? parts[2].trim() : "";
                String lName    = (parts.length > 3) ? parts[3].trim() : "";

                if (fileId.equals(id) && filePwd.equals(password)) {
                    System.out.println("✅ Admin matched: " + fileId);
                    return new Admin(fileId, fName, lName, filePwd);
                }
            }
        }

        System.out.println("⚠ No admin found for id=" + id);
        return null;
    }

    public Student findStudent(String id, String password) throws IOException {
        System.out.println("🔍 Looking for student in: " + studentFile.toAbsolutePath());

        if (!Files.exists(studentFile)) {
            System.out.println("❌ Student file NOT FOUND!");
            return null;
        }

        try (BufferedReader br = Files.newBufferedReader(studentFile)) {
            String line;
            while ((line = br.readLine()) != null) {

                System.out.println("Student line: " + line);

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";");
                // برضه نقبل أقل حاجة: id;password
                if (parts.length < 2) {
                    System.out.println("❗ Skipping student line (less than 2 parts)");
                    continue;
                }

                String fileId   = parts[0].trim();
                String filePwd  = parts[1].trim();
                String fName    = (parts.length > 2) ? parts[2].trim() : "";
                String lName    = (parts.length > 3) ? parts[3].trim() : "";

                if (fileId.equals(id) && filePwd.equals(password)) {
                    System.out.println("✅ Student matched: " + fileId);
                    return new Student(fileId, fName, lName, filePwd);
                }
            }
        }

        System.out.println("⚠ No student found for id=" + id);
        return null;
    }
}
