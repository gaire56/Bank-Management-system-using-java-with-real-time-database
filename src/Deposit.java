import java.util.Date;
import java.util.Scanner;

public class Deposit {
    private final String pin;

    public Deposit(String pin) {
        this.pin = pin;
        processDeposit();
    }

    private void processDeposit() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=====================================");
            System.out.println("          DEPOSIT MENU               ");
            System.out.println("=====================================");
            System.out.println("1. Deposit Amount");
            System.out.println("2. Back to Main Menu");
            System.out.println("Enter your choice (1-2): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> depositAmount(scanner);
                case 2 -> {
                    System.out.println("Returning to Main Menu...");
                    new Main(pin); // Assuming `Main` is your main menu class
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void depositAmount(Scanner scanner) {
        System.out.println("Enter the amount you want to deposit: ");
        String amount = scanner.nextLine();

        if (amount.isEmpty() || !amount.matches("\\d+")) {
            System.out.println("Invalid input. Please enter a valid numeric amount.");
            return;
        }

        try {
            Connn connn = new Connn();
            Date date = new Date();
            String query = "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')";
            connn.statement.executeUpdate(query);
            System.out.println("Rs. " + amount + " deposited successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred. Please try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        new Deposit(pin);
    }
}
