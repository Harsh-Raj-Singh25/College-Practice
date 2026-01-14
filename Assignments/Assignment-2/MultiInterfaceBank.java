package assignment2;

interface BankingOperations {
    void deposit(double amount);
    void withdraw(double amount) throws Exception;
}

interface CustomerDetails {
    void displayCustomerInfo();
}

class SavingsAccount implements BankingOperations, CustomerDetails {
    String name;
    double balance;

    SavingsAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount > balance) throw new Exception("Insufficient Funds!");
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    public void displayCustomerInfo() {
        System.out.println("Customer: " + name + " | Current Balance: " + balance);
    }
}

public class MultiInterfaceBank {
    public static void main(String[] args) {
        SavingsAccount acc = new SavingsAccount("Harsh", 1000.0);
        acc.displayCustomerInfo();
        try {
            acc.deposit(500);
            acc.withdraw(2000); // This will trigger exception
        } catch (Exception e) {
            System.out.println("Alert: " + e.getMessage());
        }
    }
}