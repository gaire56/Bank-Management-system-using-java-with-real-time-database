import java.sql.*;
import java.util.Scanner;

public class Pin {
    private String pin;
    private Scanner scanner;

    public Pin(String pin) {
        this.pin = pin;
        scanner = new Scanner(System.in);
        changePin();
    }

    private void changePin() {
        System.out.println("====================================");
        System.out.println("         CHANGE YOUR PIN            ");
        System.out.println("====================================");

        System.out.print("Enter New PIN: ");
        String newPin = scanner.nextLine();

        System.out.print("Re-Enter New PIN: ");
        String confirmPin = scanner.nextLine();

        if (newPin.isEmpty() || confirmPin.isEmpty()) {
            System.out.println("Error: PIN fields cannot be empty.");
            changePin(); // Retry if pin field empty
        }

        if (!newPin.equals(confirmPin)) {
            System.out.println("Error: Entered PINs do not match.");
            changePin(); // Retry if PINs don't match
        } else {
            updatePin(newPin);
        }

    }

    private void updatePin(String newPin) {
        try {
            Connn c = new Connn();

            // Update queries
            String q1 = "UPDATE bank SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
            String q2 = "UPDATE login SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
            String q3 = "UPDATE signupthree SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";

            c.statement.executeUpdate(q1);
            c.statement.executeUpdate(q2);
            c.statement.executeUpdate(q3);

            System.out.println("PIN changed successfully!");
            new Main(newPin); // Redirect to the main menu
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unable to update PIN. Please try again.");
        }
    }

    public static void main(String[] args) {
        new Pin("");
    }
}
