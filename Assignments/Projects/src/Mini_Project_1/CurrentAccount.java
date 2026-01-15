package Mini_Project_1;

// HIERARCHICAL INHERITANCE: Current Account
class CurrentAccount extends Account {
	public CurrentAccount(String name, String num, double bal) {
		super(name, num, bal);
	}

	@Override
	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
			System.out.println("Withdrawal successful from Current.");
		} else {
			System.out.println("Error: Insufficient funds.");
		}
	}
}
