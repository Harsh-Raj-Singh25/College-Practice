package Assignment_2;

import java.util.*;
import java.util.regex.Pattern;

//  Define an Interface for standard operations
interface EmployeeOperations {
	void addEmployee(int id, String name);

	void displayAll();

	void searchEmployee(int id);

	void removeEmployee(int id);
}

// Custom Exception for handling business logic errors
class EmployeeNotFoundException extends Exception {
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}

public class EmployeeManagementSystem implements EmployeeOperations {

	// HashMap for general storage (allows nulls)
	private Map<Integer, String> hashMap = new HashMap<>();

	// TreeMap for sorted storage (sorted by ID)
	private Map<Integer, String> treeMap = new TreeMap<>();

	// Hashtable for legacy/synchronized storage (no nulls allowed)
	private Map<Integer, String> hashTable = new Hashtable<>();

	// Regex pattern for Name validation (Only letters and spaces, 2-20 chars)
	private static final String NAME_REGEX = "^[a-zA-Z\\s]{2,20}$";

	@Override
	public void addEmployee(int id, String name) {
		// Validate name using REGEX before adding
		if (Pattern.matches(NAME_REGEX, name)) {
			hashMap.put(id, name);
			treeMap.put(id, name);
			hashTable.put(id, name);
			System.out.println("Employee " + name + " added successfully.");
		} else {
			System.out.println("Invalid Name: " + name + ". (Use only letters, 2-20 chars)");
		}
	}

	@Override
	public void displayAll() {
		System.out.println("\n--- All Employees (Sorted by ID using TreeMap) ---");
		treeMap.forEach((id, name) -> System.out.println("ID: " + id + " | Name: " + name));
	}

	@Override
	public void searchEmployee(int id) {
		try {
			if (!hashMap.containsKey(id)) {
				throw new EmployeeNotFoundException("ID " + id + " does not exist in our records.");
			}
			System.out.println("Search Result -> ID: " + id + ", Name: " + hashMap.get(id));
		} catch (EmployeeNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void removeEmployee(int id) {
		if (hashMap.containsKey(id)) {
			String removed = hashMap.remove(id);
			treeMap.remove(id);
			hashTable.remove(id);
			System.out.println("Successfully removed: " + removed);
		} else {
			System.out.println("Cannot remove. ID " + id + " not found.");
		}
	}

	// Method to demonstrate the technical differences in Null support
	public void demonstrateNullSupport() {
		System.out.println("\n--- Testing Null Support ---");

		// HashMap supports null keys and values
		try {
			hashMap.put(null, "NullUser");
			System.out.println("1. HashMap: Success adding null key.");
		} catch (Exception e) {
			System.out.println("1. HashMap: Failed adding null key.");
		}

		// Hashtable does NOT support null keys or values
		try {
			hashTable.put(null, "NoNulls");
		} catch (NullPointerException e) {
			System.out.println("2. Hashtable: Correctly threw NullPointerException for null key.");
		}
	}

	public static void main(String[] args) {
		EmployeeManagementSystem ems = new EmployeeManagementSystem();

		// Adding records
		ems.addEmployee(105, "Harsh");
		ems.addEmployee(101, "Anand");
		ems.addEmployee(103, "Vikas");
		ems.addEmployee(102, "123BadName"); // This will fail the Regex check

		// Displaying records (TreeMap ensures these are printed as 101, 103, 105)
		ems.displayAll();

		// Searching
		ems.searchEmployee(103);
		ems.searchEmployee(999); // Will trigger our custom exception logic

		// Removal
		ems.removeEmployee(101);

		// Demonstrating null differences
		ems.demonstrateNullSupport();

		System.out.println("\nFinal Record Count: " + ems.hashMap.size());
	}
}