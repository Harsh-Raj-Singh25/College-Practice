package Mini_Project_3;

import java.sql.*;
import java.util.*;

public class StudentManagementSystem {
    // Database credentials 
    static final String URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASS = "Harsh@SQL12";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Step 1: Login System [cite: 5]
        if (performLogin()) {
            showMenu();
        } else {
            System.out.println("Access Denied. Incorrect username or password.");
        }
    }

    private static boolean performLogin() {
        System.out.println("--- System Login ---");
        System.out.print("Username: "); String u = sc.next();
        System.out.print("Password: "); String p = sc.next();
        return u.equals("admin") && p.equals("admin123");
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n1. Add 2. Display 3. Search 4. Update 5. Delete 6. Sort 7. Exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                switch (choice) {
                    case 1 -> addStudent(con);
                    case 2 -> displayAll(con);
                    case 3 -> searchStudent(con);
                    case 4 -> updateBranch(con);
                    case 5 -> deleteStudent(con);
                    case 6 -> sortStudents(con);
                    case 7 -> System.exit(0);
                    default -> System.out.println("Invalid Option.");
                }
            } catch (Exception e) {
                System.out.println("System Error: " + e.getMessage());
            }
        }
    }

    // Validation Rule Implementation [cite: 5]
    private static void addStudent(Connection con) throws Exception {
        System.out.print("Eno: "); int eno = sc.nextInt();
        System.out.print("Name: "); String name = sc.next();
        System.out.print("Branch: "); String branch = sc.next();
        System.out.print("Percentage: "); double per = sc.nextDouble();
        System.out.print("Sem: "); int sem = sc.nextInt();

        // Check validation rules [cite: 5]
        if (per <= 0) throw new StudentValidationException("Percentage must be positive!");
        if (branch.trim().isEmpty()) throw new StudentValidationException("Branch cannot be empty!");

        String sql = "INSERT INTO Students VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, eno);
        ps.setString(2, name);
        ps.setString(3, branch);
        ps.setDouble(4, per);
        ps.setInt(5, sem);
        ps.executeUpdate();
        System.out.println("Student added successfully!");
    }

    private static void displayAll(Connection con) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Students");
        while (rs.next()) {
            System.out.println(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
        }
    }

    private static void searchStudent(Connection con) throws SQLException {
        System.out.print("Enter Eno: "); int id = sc.nextInt();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Students WHERE eno=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) System.out.println("Found: " + rs.getString("name"));
        else System.out.println("Not found.");
    }

    private static void updateBranch(Connection con) throws SQLException {
        System.out.print("Enter Eno: "); int id = sc.nextInt();
        System.out.print("Enter New Branch: "); String b = sc.next();
        PreparedStatement ps = con.prepareStatement("UPDATE Students SET branch=? WHERE eno=?");
        ps.setString(1, b);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Branch updated.");
    }

    private static void deleteStudent(Connection con) throws SQLException {
        System.out.print("Enter Eno: "); int id = sc.nextInt();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Students WHERE eno=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Record deleted.");
    }

    // Using ArrayList and Comparator for sorting [cite: 5]
    private static void sortStudents(Connection con) throws SQLException {
        List<Student> list = new ArrayList<>();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Students");
        while (rs.next()) {
            list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
        }
        list.sort((s1, s2) -> Double.compare(s2.per, s1.per)); // Descending sort
        list.forEach(System.out::println);
    }
}