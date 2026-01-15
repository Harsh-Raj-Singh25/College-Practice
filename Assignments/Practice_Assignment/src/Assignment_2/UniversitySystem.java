package Assignment_2;

import java.util.*;
import java.util.regex.Pattern;

// 1. Interface for abstraction
interface UniversityActions {
	void addStudentRecord(int id, String name, String course, int marks);

	void displayAllStudents();

	void removeStudent(int id);

	void searchStudent(int id);

	void sortStudentsByMarks();

	void displayUniqueCourses();
}

public class UniversitySystem implements UniversityActions {

	// Using various collections as per requirements
	private List<String> studentList = new ArrayList<>(); // To store formatted strings
	private Vector<Integer> studentIds = new Vector<>(); // Thread-safe ID storage
	private Stack<String> actionHistory = new Stack<>(); // To track last added student
	private Set<String> uniqueCourses = new HashSet<>(); // To prevent duplicate courses
	private Map<Integer, Integer> studentMarks = new HashMap<>(); // ID to Marks mapping
	private Map<Integer, String> studentData = new HashMap<>(); // ID to Name mapping

	// Regex: Name should be 2-30 alphabets only
	private static final String NAME_PATTERN = "^[a-zA-Z\\s]{2,30}$";

	@Override
	public void addStudentRecord(int id, String name, String course, int marks) {
		try {
			// Check for duplicate ID entry
			if (studentIds.contains(id)) {
				System.out.println("Error: Student ID " + id + " already exists!");
				return;
			}

			// Validate Name using REGEX
			if (!Pattern.matches(NAME_PATTERN, name)) {
				throw new IllegalArgumentException("Invalid Name format!");
			}

			// Adding to various collections
			studentIds.add(id);
			studentData.put(id, name);
			studentMarks.put(id, marks);
			uniqueCourses.add(course.toUpperCase()); // Store unique course names
			studentList.add("ID: " + id + " | Name: " + name + " | Course: " + course);
			actionHistory.push(name); // Stack keeps track of the latest entry

			System.out.println("Student " + name + " added successfully.");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	@Override
	public void displayAllStudents() {
		System.out.println("\n--- All Student Records ---");
		for (String record : studentList) {
			System.out.println(record);
		}
	}

	@Override
	public void searchStudent(int id) {
		if (studentData.containsKey(id)) {
			System.out.println("Found: " + studentData.get(id) + " | Marks: " + studentMarks.get(id));
		} else {
			System.out.println("Student ID " + id + " not found.");
		}
	}

	@Override
	public void removeStudent(int id) {
		if (studentData.containsKey(id)) {
			String name = studentData.remove(id);
			studentMarks.remove(id);
			studentIds.remove(Integer.valueOf(id));
			// Remove from list view as well
			studentList.removeIf(s -> s.contains("ID: " + id));
			System.out.println("Removed Student: " + name);
		} else {
			System.out.println("ID not found. Cannot remove.");
		}
	}

	@Override
	public void sortStudentsByMarks() {
		System.out.println("\n--- Students Sorted by Marks (Ascending) ---");
		// Convert Map entries to List for sorting
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(studentMarks.entrySet());
		list.sort(Map.Entry.comparingByValue());

		for (Map.Entry<Integer, Integer> entry : list) {
			System.out.println("Student ID: " + entry.getKey() + " | Marks: " + entry.getValue());
		}
	}

	// Convert HashMap to TreeMap
	public void convertAndDisplayTreeMap() {
		System.out.println("\n--- HashMap converted to TreeMap (Sorted by ID) ---");
		TreeMap<Integer, String> treeMap = new TreeMap<>(studentData);
		treeMap.forEach((id, name) -> System.out.println("ID: " + id + " -> Name: " + name));
	}

	@Override
	public void displayUniqueCourses() {
		System.out.println("\n--- Unique University Courses ---");
		System.out.println(uniqueCourses);
	}

	public void countStudentsCourseWise(String targetCourse) {
		// Simple logic to count based on the list
		long count = studentList.stream().filter(s -> s.toUpperCase().contains(targetCourse.toUpperCase())).count();
		System.out.println("Total students in " + targetCourse + ": " + count);
	}

	public static void main(String[] args) {
		UniversitySystem us = new UniversitySystem();

		// 1. Add Records
		us.addStudentRecord(101, "Harsh", "Java", 88);
		us.addStudentRecord(105, "Namrata", "Python", 95);
		us.addStudentRecord(103, "Anand", "Java", 72);
		us.addStudentRecord(102, "Riya", "C++", 88);

		// 2. Display all
		us.displayAllStudents();

		// 3. Unique courses (Set demonstration)
		us.displayUniqueCourses();

		// 4. Search and Remove
		us.searchStudent(105);
		us.removeStudent(102);

		// 5. Sorting by Marks (Map conversion logic)
		us.sortStudentsByMarks();

		// 6. Convert HashMap to TreeMap
		us.convertAndDisplayTreeMap();

		// 7. Course-wise count
		us.countStudentsCourseWise("Java");

		System.out.println("\nLast added student (from Stack): " + us.actionHistory.peek());
	}
}
