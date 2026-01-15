import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Validation Menu ---");
            System.out.println("1. Mobile 2. Email 3. Username 4. Password 5. Exit");
            int choice = sc.nextInt();
            if (choice == 5) break;

            System.out.print("Enter input to validate: ");
            String input = sc.next();
            String pattern = "";

            switch (choice) {
                case 1: // Mobile: Starts with 6-9 and total 10 digits
                    pattern = "^[6-9]\\d{9}$"; 
                    break;
                case 2: // Email: standard alphanumeric + @ + domain
                    pattern = "^[A-Za-z0-9+_.-]+@(.+)$"; 
                    break;
                case 3: // Username: alphanumeric, 5 to 12 characters
                    pattern = "^[a-zA-Z0-9]{5,12}$"; 
                    break;
                case 4: // Password: Min 8 chars, at least one digit and one lowercase
                    pattern = "^(?=.*[0-9])(?=.*[a-z]).{8,}$"; 
                    break;
            }

            // Using Pattern.matches to check if input matches our regex
            if (Pattern.matches(pattern, input)) {
                System.out.println(">> Success: Validation Passed! Welcome.");
            } else {
                System.out.println(">> Error: Invalid input format.");
            }
        }
    }
}