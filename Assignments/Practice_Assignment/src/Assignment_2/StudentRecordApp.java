import java.util.*;
import java.util.regex.Pattern;

/**
 * created a class named Student Record Management System that
 * Manages student records using ArrayList, Vector, and List.
 * Supports add, display, search, and remove operations.
 * Uses REGEX for input validation and handles exceptions.
 */

public class StudentRecordApp {

    /* ================= STUDENT CLASS ============================ */

    static class Student {
        private int rollNumber;
        private String name;

        public Student(int rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public int getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Roll No: " + rollNumber + ", Name: " + name;
        }
    }

    /* ================= REGEX PATTERNS ================= */

    private static final String ROLL_REGEX = "^[0-9]+$";
    private static final String NAME_REGEX = "^[A-Za-z ]+$";

    /* ================= COLLECTIONS ==================== */

    // List interface reference
    private static List<Student> studentList = new  ArrayList<>();

    // Vector collection (legacy synchronized)
    private static Vector<Student> studentVector = new  Vector<>();

    /* ================= VALIDATION METHODS ======================== */

    public static boolean isValidRoll(String roll) {
        return Pattern.matches(ROLL_REGEX, roll);
    }

    public static boolean isValidName(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    /* ================= STUDENT OPERATIONS ================= */

    public static void addStudent(int roll, String name) {
        Student student = new Student(roll, name);
        studentList.add(student);
        studentVector.add(student);
        System.out.println("Student added successfully");
    }

    public static void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found");
            return;
        }

        System.out.println("\n--- Student Records ---");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public static void searchStudent(int roll) {
        for (Student s : studentList) {
            if (s.getRollNumber() == roll) {
                System.out.println("Student Found: " + s);
                return;
            }
        }
        System.out.println("Student not found");
    }

    public static void removeStudent(int roll) {
        Iterator<Student> iterator = studentList.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber() == roll) {
                iterator.remove();
                studentVector.remove(s);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Student removed successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    /* ================= MAIN METHOD ================= */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            while (true) {

                System.out.println("\n--- STUDENT RECORD MENU ---");
                System.out.println("1. Add Student");
                System.out.println("2. Display All Students");
                System.out.println("3. Search Student by Roll Number");
                System.out.println("4. Remove Student by Roll Number");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {

                    case 1:
                        System.out.print("Enter Roll Number: ");
                        String rollInput = sc.nextLine();

                        if (!isValidRoll(rollInput)) {
                            System.out.println(" Invalid roll number");
                            break;
                        }

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        if (!isValidName(name)) {
                            System.out.println(" Invalid name");
                            break;
                        }

                        addStudent(Integer.parseInt(rollInput), name);
                        break;

                    case 2:
                        displayStudents();
                        break;

                    case 3:
                        System.out.print("Enter Roll Number to Search: ");
                        int searchRoll = sc.nextInt();
                        searchStudent(searchRoll);
                        break;

                    case 4:
                        System.out.print("Enter Roll Number to Remove: ");
                        int removeRoll = sc.nextInt();
                        removeStudent(removeRoll);
                        break;

                    case 5:
                        System.out.println("Exiting program");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: Invalid input");
            sc.close();
        }
    }
}