import java.sql.ResultSet;
import java.util.Scanner;

public class MiniStatement {
    private String pin;

    public MiniStatement(String pin) {
        this.pin = pin;
        displayMiniStatement();
    }

    private void displayMiniStatement() {
        System.out.println("====================================");
        System.out.println("            Gaire G Bank            ");
        System.out.println("====================================");

        // Display card number
        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM login WHERE pin = '" + pin + "'");
            if (resultSet.next()) {
                String cardNumber = resultSet.getString("cardNumber");
                System.out
                        .println("Account Number: " + cardNumber.substring(0, 4) + "XXXXXXXX"
                                + cardNumber.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error retrieving card number.");
        }

        // Display transaction history and balance
        System.out.println("\nTransaction History:");
        int balance = 0;
        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                String amount = resultSet.getString("amount");
                System.out.println(date + "\t" + type + "\tRs. " + amount);

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error retrieving transaction history.");
        }

        // Display total balance
        System.out.println("\nYour Total Balance is: Rs. " + balance);

        // Exit option
        System.out.println("\nPress Enter to Exit...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for the user to press Enter
    }

    public static void main(String[] args) {
        new MiniStatement(""); // Replace "1234" with the test PIN or fetch dynamically
    }
}
