import java.io.Console;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {

    private Scanner scanner;

    public Login() {
        scanner = new Scanner(System.in);
        displayMenu();
    }

    // Main menu for user options
    private void displayMenu() {
        while (true) {
            System.out.println("Welcome to Gaire G Bank");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.print("Enter your choice: ");

            // Handle input choice
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Read user input
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a valid number.");
                continue; // Prompt the menu again if invalid choice
            }

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Sign In logic
    private void signIn() {
        System.out.print("Enter Account No: ");
        String cardNo = scanner.nextLine();

        // Use Console for password input (for better security) if available
        Console console = System.console();
        String pin = null;

        if (console != null) {
            pin = new String(console.readPassword("Enter Passcode: "));
        } else {
            // If console is not available (e.g., in an IDE), fall back to Scanner
            System.out.print("Enter Passcode: ");
            pin = scanner.nextLine();
        }

        // Attempt to validate the user credentials
        try {
            Connn c = new Connn();
            String query = "SELECT * FROM login WHERE cardNumber = '" + cardNo + "' AND pin = '" + pin + "'";
            ResultSet resultSet = c.statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Login Successful!");
                new Main(pin); // Redirect to Main menu with pin (assuming Main class exists)
            } else {
                System.out.println("Incorrect Account Number or PIN.");
            }

            // Closing the ResultSet resource to prevent memory leaks
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sign Up logic (redirect to signup class)
    private void signUp() {
        System.out.println("Redirecting to Sign Up...");
        // Initialize Signup class (will call Signup.main() if implemented correctly)
        Signup signup = new Signup(); // Create an instance of Signup (this will run the main method)
    }

    public static void main(String[] args) {
        new Login();
    }
}
