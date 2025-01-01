import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Pin {

    String currentPin;

    public Pin(String pin) {
        this.currentPin = pin;
        changePin();
    }

    private void changePin() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("********** Change PIN **********");
            System.out.print("Enter New PIN: ");
            String newPin = scanner.nextLine();

            System.out.print("Re-enter New PIN: ");
            String confirmPin = scanner.nextLine();

            if (!newPin.equals(confirmPin)) {
                System.out.println("Error: Entered PINs do not match. Please try again.");
                return;
            }

            if (newPin.isEmpty() || confirmPin.isEmpty()) {
                System.out.println("Error: PIN fields cannot be empty.");
                return;
            }

            updatePinInDatabase(newPin);
            System.out.println("PIN changed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Exiting Change PIN process.");
            scanner.close();
        }
    }

    private void updatePinInDatabase(String newPin) throws Exception {
        Connn c = new Connn();
        String q1 = "UPDATE bank SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'";
        String q2 = "UPDATE login SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'";
        String q3 = "UPDATE signupthree SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'";

        c.statement.executeUpdate(q1);
        c.statement.executeUpdate(q2);
        c.statement.executeUpdate(q3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Current PIN: ");
        String currentPin = scanner.nextLine();

        new Pin(currentPin);
    }
}
