import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) { super(msg); }
}

public class BankManagementSystem {
    private String name;
    private double balance;

    public BankSystem(String name, double initialDeposit) {
        this.name = name;
        this.balance = initialDeposit;
    }

    public void deposit(double amt) { balance += amt; }

    public void withdraw(double amt) throws InsufficientBalanceException {
        if (amt > balance) throw new InsufficientBalanceException("Not enough money!");
        balance -= amt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem account = new BankSystem("Student User", 500.0);

        try {
            System.out.println("Current Balance: " + account.balance);
            account.withdraw(600.0); // This will trigger exception
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}