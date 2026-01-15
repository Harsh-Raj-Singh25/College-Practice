package Mini_Project_3;

// Student Class to hold data for sorting/collections
class Student {
	int eno;
	String name, branch;
	double per;
	int sem;

	public Student(int eno, String name, String branch, double per, int sem) {
		this.eno = eno;
		this.name = name;
		this.branch = branch;
		this.per = per;
		this.sem = sem;
	}

	@Override
	public String toString() {
		return String.format("Eno: %d | Name: %-10s | Branch: %-5s | Per: %.2f | Sem: %d", eno, name, branch, per, sem);
	}
}
