package Mini_Project_1;

//4. HIERARCHICAL INHERITANCE: Savings Account
class SavingsAccount extends Account {
	private final double MIN_BALANCE = 500.0;

	public SavingsAccount(String name, String num, double bal) {
		super(name, num, bal);
	}

	// 5. METHOD OVERRIDING (Run-time Polymorphism)
	@Override
	public void withdraw(double amount) {
		if (balance - amount >= MIN_BALANCE) {
			balance -= amount;
			System.out.println("Withdrawal successful from Savings.");
		} else {
			System.out.println("Error: Minimum balance of 500 must be maintained.");
		}
	}
}