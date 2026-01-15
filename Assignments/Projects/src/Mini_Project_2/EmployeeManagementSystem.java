package Mini_Project_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EmployeeManagementSystem {
	private static final String FILE_NAME = "employees.dat";
	private static List<Employee> employeeList = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (login()) {
			loadFromFile();
			menu();
		} else {
			System.out.println("Invalid Login. Exiting...");
		}
	}

	// --- 1. Login System ---
	private static boolean login() {
		System.out.println("=== Admin Login ===");
		System.out.print("Username: ");
		String user = sc.next();
		System.out.print("Password: ");
		String pass = sc.next();
		return user.equals("admin") && pass.equals("admin123");
	}

	// --- 2. Menu Logic ---
	private static void menu() {
		while (true) {
			System.out.println("\n--- Employee Management Menu ---");
			System.out.println("1. Add 2. Display All 3. Search 4. Update Salary");
			System.out.println("5. Delete 6. Sort by Salary 7. View Departments 8. Exit");
			try {
				int choice = sc.nextInt();
				switch (choice) {
				case 1 -> addEmployee();
				case 2 -> displayAll();
				case 3 -> searchEmployee();
				case 4 -> updateSalary();
				case 5 -> deleteEmployee();
				case 6 -> sortEmployees();
				case 7 -> displayDepartments();
				case 8 -> {
					saveToFile();
					System.exit(0);
				}
				default -> System.out.println("Invalid choice!");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				sc.nextLine(); // clear buffer
			}
		}
	}

	// --- 3. CRUD Operations ---
	private static void addEmployee() throws EmployeeException {
		System.out.print("Enter ID: ");
		int id = sc.nextInt();
		// ID Uniqueness Validation
		for (Employee e : employeeList)
			if (e.id == id)
				throw new EmployeeException("ID already exists!");

		System.out.print("Enter Name: ");
		String name = sc.next();
		System.out.print("Enter Dept: ");
		String dept = sc.next();
		if (dept.isEmpty())
			throw new EmployeeException("Department cannot be empty!");

		System.out.print("Enter Salary: ");
		double sal = sc.nextDouble();
		if (sal <= 0)
			throw new EmployeeException("Salary must be positive!");

		employeeList.add(new Employee(id, name, dept, sal));
		saveToFile(); // Sync with file
	}

	private static void displayAll() {
		if (employeeList.isEmpty())
			System.out.println("No records found.");
		else
			employeeList.forEach(System.out::println);
	}

	private static void searchEmployee() {
		System.out.print("Enter ID to search: ");
		int id = sc.nextInt();
		employeeList.stream().filter(e -> e.id == id).findFirst().ifPresentOrElse(System.out::println,
				() -> System.out.println("Not found."));
	}

	private static void updateSalary() {
		System.out.print("Enter ID: ");
		int id = sc.nextInt();
		for (Employee e : employeeList) {
			if (e.id == id) {
				System.out.print("Enter new salary: ");
				double newSal = sc.nextDouble();
				if (newSal > 0)
					e.salary = newSal;
				saveToFile();
				System.out.println("Updated!");
				return;
			}
		}
		System.out.println("Employee ID not found.");
	}

	private static void deleteEmployee() {
		System.out.print("Enter ID to delete: ");
		int id = sc.nextInt();
		boolean removed = employeeList.removeIf(e -> e.id == id);
		if (removed) {
			saveToFile();
			System.out.println("Employee deleted.");
		} else
			System.out.println("ID not found.");
	}

	private static void sortEmployees() {
		employeeList.sort(Comparator.comparingDouble(e -> e.salary));
		System.out.println("Sorted by Salary (Low to High):");
		displayAll();
	}

	private static void displayDepartments() {
		Set<String> depts = new HashSet<>();
		for (Employee e : employeeList)
			depts.add(e.department);
		System.out.println("Unique Departments: " + depts);
	}

	// --- 4. File Handling (Persistence) ---
	private static void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(employeeList);
		} catch (IOException e) {
			System.out.println("Save Error: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadFromFile() {
		File file = new File(FILE_NAME);
		if (!file.exists())
			return;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			employeeList = (List<Employee>) ois.readObject();
		} catch (Exception e) {
			System.out.println("Load Error: " + e.getMessage());
		}
	}
}
