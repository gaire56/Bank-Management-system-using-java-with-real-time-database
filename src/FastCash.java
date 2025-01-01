import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

public class FastCash {
    private String pin;
    private Scanner scanner;

    public FastCash(String pin) {
        this.pin = pin;
        scanner = new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("====================================");
        System.out.println("        SELECT WITHDRAWAL AMOUNT    ");
        System.out.println("====================================");
        System.out.println("1. Rs. 100");
        System.out.println("2. Rs. 500");
        System.out.println("3. Rs. 1000");
        System.out.println("4. Rs. 2000");
        System.out.println("5. Rs. 5000");
        System.out.println("6. Rs. 10000");
        System.out.println("7. BACK");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        handleChoice(choice);
    }

    private void handleChoice(int choice) {
        int amount = 0;

        switch (choice) {
            case 1:
                amount = 100;
                break;
            case 2:
                amount = 500;
                break;
            case 3:
                amount = 1000;
                break;
            case 4:
                amount = 2000;
                break;
            case 5:
                amount = 5000;
                break;
            case 6:
                amount = 10000;
                break;
            case 7:
                System.out.println("Going back to the main menu...");
                new Main(pin); // Assuming Main is your main menu class
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMenu();
                return;
        }

        processWithdrawal(amount);
    }

    private void processWithdrawal(int amount) {
        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            int balance = 0;

            // Calculate current balance
            while (resultSet.next()) {
                if (resultSet.getString("type").equalsIgnoreCase("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else if (resultSet.getString("type").equalsIgnoreCase("Withdrawl")) {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            // Check if sufficient balance exists
            if (balance < amount) {
                System.out.println("Insufficient Balance.");
                displayMenu();
                return;
            }

            // Perform withdrawal
            Date date = new Date();
            c.statement.executeUpdate(
                    "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "')");
            System.out.println("Rs. " + amount + " Debited Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while processing the transaction.");
        }

        // Return to the main menu
        new Main(pin);
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
