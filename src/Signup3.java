import java.util.Scanner;
import java.util.Random;

public class Signup3 {
    private final String formno;

    public Signup3(String formno) {
        this.formno = formno;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("            Account Details          ");
        System.out.println("=====================================");

        // Account Type Selection
        String accountType = getAccountType(scanner);

        // Generate Card Number and PIN
        Random random = new Random();
        long first7 = (random.nextLong() % 90000000L) + 1409963000000000L;
        String cardNumber = String.valueOf(Math.abs(first7));

        long first3 = (random.nextLong() % 9000L) + 1000L;
        String pin = String.valueOf(Math.abs(first3));

        // Services Required
        String facilities = getFacilities(scanner);

        // Confirm and Save Details
        if (accountType.isEmpty() || facilities.isEmpty()) {
            System.out.println("Error: Please fill in all the fields.");
            return;
        }

        try {
            Connn c1 = new Connn();
            String query1 = "INSERT INTO signupthree VALUES('" + formno + "', '" + accountType + "', '" + cardNumber
                    + "', '" + pin + "', '" + facilities + "')";
            String query2 = "INSERT INTO login VALUES('" + formno + "', '" + cardNumber + "', '" + pin + "')";
            c1.statement.executeUpdate(query1);
            c1.statement.executeUpdate(query2);

            System.out.println("=====================================");
            System.out.println("  Account Created Successfully!      ");
            System.out.println("  Card Number: " + cardNumber);
            System.out.println("  PIN: " + pin);
            System.out.println("=====================================");

            // Proceed to Deposit
            new Deposit(pin); // Assuming Deposit is another CLI class
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred. Please try again.");
        }

        scanner.close();
    }

    private String getAccountType(Scanner scanner) {
        System.out.println("Select Account Type:");
        System.out.println("1. Saving Account");
        System.out.println("2. Fixed Deposit Account");
        System.out.println("3. Current Account");
        System.out.println("4. Recurring Deposit Account");
        System.out.print("Enter choice (1-4): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return switch (choice) {
            case 1 -> "Saving Account";
            case 2 -> "Fixed Deposit Account";
            case 3 -> "Current Account";
            case 4 -> "Recurring Deposit Account";
            default -> {
                System.out.println("Invalid choice. Please try again.");
                yield getAccountType(scanner);
            }
        };
    }

    private String getFacilities(Scanner scanner) {
        System.out.println("Select Services Required (comma-separated choices):");
        System.out.println("1. ATM CARD");
        System.out.println("2. Internet Banking");
        System.out.println("3. Mobile Banking");
        System.out.println("4. EMAIL Alerts");
        System.out.println("5. Cheque Book");
        System.out.println("6. E-Statement");
        System.out.print("Enter choices (e.g., 1,3,5): ");

        String[] choices = scanner.nextLine().split(",");
        StringBuilder facilities = new StringBuilder();

        for (String choice : choices) {
            switch (choice.trim()) {
                case "1" -> facilities.append("ATM CARD ");
                case "2" -> facilities.append("Internet Banking ");
                case "3" -> facilities.append("Mobile Banking ");
                case "4" -> facilities.append("EMAIL Alerts ");
                case "5" -> facilities.append("Cheque Book ");
                case "6" -> facilities.append("E-Statement ");
                default -> System.out.println("Invalid choice: " + choice + ". Skipping.");
            }
        }

        return facilities.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Form Number: ");
        String formno = scanner.nextLine();

        Signup3 signup3 = new Signup3(formno);
        signup3.start();
    }
}
