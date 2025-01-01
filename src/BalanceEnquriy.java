import java.sql.ResultSet;
import java.util.Scanner;

public class BalanceEnquriy {

    private String pin;

    BalanceEnquriy(String pin) {
        this.pin = pin;

        int balance = 0;

        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Your Current Balance is Rs " + balance);
        showBackOption();
    }

    private void showBackOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress 1 to go back to the main menu.");
        int choice = scanner.nextInt();

        if (choice == 1) {
            new Main(pin);
        } else {
            System.out.println("Invalid choice. Returning to the main menu.");
            new Main(pin);
        }
    }

    public static void main(String[] args) {
        new BalanceEnquriy("");
    }
}
