import java.io.Console;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {

    private Scanner scanner;

    public Login() {
        scanner = new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("Welcome to Gaire G Bank");
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume1 newline character

        switch (choice) {
            case 1:
                signIn();
                break;
            case 2:
                signUp();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMenu();
                break;
        }
    }

    private void signIn() {
        System.out.print("Enter Account No: ");
        String cardNo = scanner.nextLine();

        Console console = System.console();
        String pin = null;

        if (console != null) {
            pin = new String(console.readPassword("Enter Passcode: "));
        } else {
            // If no console is available, use Scanner (for IDE environments)
            System.out.print("Enter Passcode: ");
            pin = scanner.nextLine();
        }

        try {
            Connn c = new Connn();
            String query = "SELECT * FROM login WHERE card_number = '" + cardNo + "' AND pin = '" + pin + "'";
            ResultSet resultSet = c.statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Login Successful!");
                // After successful login, redirect to the main menu
                new Main(pin);
            } else {
                System.out.println("Incorrect Account Number or PIN.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        System.out.println("Redirecting to Sign Up...");
        new Signup(); // Assuming Signup is another class you have implemented for user registration
    }

    public static void main(String[] args) {
        new Login();
    }
}
