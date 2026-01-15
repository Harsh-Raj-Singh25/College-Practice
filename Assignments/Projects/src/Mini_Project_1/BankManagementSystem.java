package Mini_Project_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class BankManagementSystem {
	private static final String FILE_NAME = "bank_data.txt";
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println("\n--- Bank Management Menu ---");
			System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Balance Enquiry\n5. Exit");
			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> createAccount();
			case 2 -> performTransaction("deposit");
			case 3 -> performTransaction("withdraw");
			case 4 -> performTransaction("enquiry");
			case 5 -> System.exit(0);
			}
		}
	}

	private static void createAccount() {
		System.out.print("Enter Name: ");
		String name = sc.next();
		System.out.print("Enter Acc Number: ");
		String num = sc.next();
		System.out.print("Type (1. Savings / 2. Current): ");
		int type = sc.nextInt();

		// 6. RUN-TIME POLYMORPHISM: Parent reference holding child object
		Account acc = (type == 1) ? new SavingsAccount(name, num, 1000) : new CurrentAccount(name, num, 1000);
		saveToFile(acc);
	}

	//  FILE HANDLING: Storing and retrieving data
	private static void saveToFile(Account acc) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(acc);
			System.out.println("Account data stored in file.");
		} catch (IOException e) {
			System.out.println("File Error: " + e.getMessage());
		}
	}

	private static void performTransaction(String type) {
		// In a real project, we would read the list of accounts.
		// For this console demo, we simulate loading the account from the file.
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			Account acc = (Account) ois.readObject();

			if (type.equals("deposit")) {
				acc.deposit(500, "Monthly Savings");
			} else if (type.equals("withdraw")) {
				acc.withdraw(200);
			}
			acc.displayDetails();
			saveToFile(acc); // Update file after transaction
		} catch (Exception e) {
			System.out.println("No account found in file. Create one first.");
		}
	}
}