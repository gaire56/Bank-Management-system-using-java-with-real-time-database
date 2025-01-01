import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

public class FastCash {

    private String pin;

    FastCash(String pin) {
        this.pin = pin;
        processFastCash();
    }

    private void processFastCash() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select withdrawal amount:");
        System.out.println("1. Rs. 100");
        System.out.println("2. Rs. 500");
        System.out.println("3. Rs. 1000");
        System.out.println("4. Rs. 2000");
        System.out.println("5. Rs. 5000");
        System.out.println("6. Rs. 10000");
        System.out.println("7. Back");

        int choice = scanner.nextInt();
        String amount = "";

        switch (choice) {
            case 1:
                amount = "100";
                break;
            case 2:
                amount = "500";
                break;
            case 3:
                amount = "1000";
                break;
            case 4:
                amount = "2000";
                break;
            case 5:
                amount = "5000";
                break;
            case 6:
                amount = "10000";
                break;
            case 7:
                goBack();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                processFastCash();
                return;
        }

        try {
            Connn c = new Connn();
            ResultSet resultSet = c.getStatement().executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            int balance = 0;

            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            if (balance < Integer.parseInt(amount)) {
                System.out.println("Insufficient Balance.");
                return;
            }

            Date date = new Date();
            c.getStatement().executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'withdrawal', '" + amount + "')");
            System.out.println("Rs. " + amount + " debited successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        goBack();
    }

    private void goBack() {
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
        new FastCash("");
    }
}
