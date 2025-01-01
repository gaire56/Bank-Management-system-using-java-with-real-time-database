import java.util.Date;
import java.util.Scanner;

public class Deposit {

    private String pin;

    Deposit(String pin) {
        this.pin = pin;
        processDeposit();
    }

    private void processDeposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount you want to deposit: ");
        String amount = scanner.nextLine();

        if (amount.isEmpty()) {
            System.out.println("Please enter a valid amount to deposit.");
        } else {
            try {
                Date date = new Date();
                Connn c = new Connn();
                ((Object) c.getStatement()).executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')");

                System.out.println("Rs. " + amount + " deposited successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
        new Deposit("");
    }
}
