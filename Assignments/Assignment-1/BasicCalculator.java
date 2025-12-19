import java.util.Scanner;
public class BasicCalculator {
     // Defining the Methods:-
    public static double add(double a, double b) {
	 return a + b; 
    }
    public static double subtract(double a, double b) {
	 return a - b;
    }
    public static double multiply(double a, double b) {
	 return a * b; 
    }
    public static double divide(double a, double b) {
	 return (b != 0) ? a / b : 0; 
    }
    public static double remainder(double a, double b) { 
	return a % b; 
    }
    public static double square(double a) { 
	return a * a; 
    }
    public static double cube(double a) { 
	return a * a * a; 
    }
    public static double absolute(double a) { 
	return Math.abs(a); 
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        System.out.println("Addition: " + add(a, b));
        System.out.println("Subtraction: " + subtract(a, b));
        System.out.println("Multiplication: " + multiply(a, b));
        System.out.println("Division: " + divide(a, b));
        System.out.println("Remainder: " + remainder(a, b));
        System.out.println("Square of first number: " + square(a));
        System.out.println("Cube of first number: " + cube(a));
        System.out.println("Absolute value of first: " + absolute(a));
    }

    
}