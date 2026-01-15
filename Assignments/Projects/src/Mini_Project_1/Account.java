package Mini_Project_1;

import java.io.*;
import java.util.*;

// . ABSTRACTION: Abstract class cannot be instantiated
abstract class Account implements Serializable {
	// ENCAPSULATION: Private fields with public getters/setters
	private String accHolder;
	private String accNumber;
	protected double balance; // Protected so child classes can access

	public Account(String accHolder, String accNumber, double balance) {
		this.accHolder = accHolder;
		this.accNumber = accNumber;
		this.balance = balance;
	}

	//  ABSTRACT METHODS: Must be implemented by children
	public abstract void withdraw(double amount);

	// Final method: Common to all
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Successfully deposited: " + amount);
		}
	}

	// Overloaded method (Compile-time Polymorphism)
	public void deposit(double amount, String note) {
		deposit(amount);
		System.out.println("Transaction Note: " + note);
	}

	public void displayDetails() {
		System.out.println("Holder: " + accHolder + " | Acc No: " + accNumber + " | Balance: " + balance);
	}

	public String getAccNumber() {
		return accNumber;
	}
}


