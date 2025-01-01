import java.sql.ResultSet;
import java.util.Scanner;

public class Mini {

    String pin;

    public Mini(String pin) {
        this.pin = pin;
        displayStatement();
    }

    private void displayStatement() {
        System.out.println("********* Mini Statement *********");
        System.out.println("Bank Name: TechCoder A.V");

        try {
            Connn c = new Connn();

            // Fetch card number
            ResultSet rsCard = c.statement.executeQuery("select * from login where pin = '" + pin + "'");
            if (rsCard.next()) {
                String cardNumber = rsCard.getString("card_number");
                System.out.println("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }

            // Fetch transaction history and calculate balance
            int balance = 0;
            ResultSet rsTransactions = c.statement.executeQuery("select * from bank where pin = '" + pin + "'");
            System.out.println("\nDate\t\t\tType\t\tAmount");
            System.out.println("---------------------------------------------");
            while (rsTransactions.next()) {
                String date = rsTransactions.getString("date");
                String type = rsTransactions.getString("type");
                String amount = rsTransactions.getString("amount");

                System.out.println(date + "\t" + type + "\t\tRs. " + amount);

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
            System.out.println("---------------------------------------------");
            System.out.println("Your Total Balance is Rs. " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        exit();
    }

    private void exit() {
        System.out.println("\nPress Enter to Exit...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for user input
        System.out.println("Exiting Mini Statement. Thank you!");
    }

    public static void main(String[] args) {
        new Mini(""); // Replace with actual PIN for testing
    }
}
