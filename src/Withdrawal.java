import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

public class Withdrawal {

    private final String pin;

    public Withdrawal(String pin) {
        this.pin = pin;
        run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("         WITHDRAWAL SYSTEM          ");
        System.out.println("========================================");
        System.out.println("Maximum Withdrawal Amount: Rs. 10,000");
        System.out.print("Please enter the amount you want to withdraw: ");

        String amount = scanner.nextLine();

        if (amount.isEmpty()) {
            System.out.println("Error: Please enter a valid amount.");
            return;
        }

        try {
            int withdrawalAmount = Integer.parseInt(amount);

            if (withdrawalAmount > 10000) {
                System.out.println("Error: Maximum withdrawal limit is Rs. 10,000.");
                return;
            }

            Connn conn = new Connn();
            ResultSet resultSet = conn.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

            int balance = 0;

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int transactionAmount = Integer.parseInt(resultSet.getString("amount"));

                if ("Deposit".equals(type)) {
                    balance += transactionAmount;
                } else if ("Withdrawal".equals(type)) {
                    balance -= transactionAmount;
                }
            }

            if (balance < withdrawalAmount) {
                System.out.println("Error: Insufficient balance.");
                return;
            }

            Date date = new Date();
            conn.statement.executeUpdate(
                    "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawal', '" + withdrawalAmount + "')");

            System.out.println("Success: Rs. " + withdrawalAmount + " debited successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid amount entered. Please enter a numeric value.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("========================================");
        System.out.println("1. Back to Main Menu");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            new Main(pin);
        } else {
            System.out.println("Thank you for using the ATM. Goodbye!");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Withdrawal("1234");
    }
}
