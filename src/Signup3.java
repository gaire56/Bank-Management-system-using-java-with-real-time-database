import java.util.*;

public class Signup3 {

    private final String formNo;

    public Signup3(String formNo) {
        this.formNo = formNo;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Page 3: Account Details");
        System.out.println("========================");

        System.out.println("Select Account Type:");
        System.out.println("1. Saving Account");
        System.out.println("2. Fixed Deposit Account");
        System.out.println("3. Current Account");
        System.out.println("4. Recurring Deposit Account");
        System.out.print("Enter your choice (1-4): ");
        int accountChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String accountType;
        switch (accountChoice) {
            case 1 -> accountType = "Saving Account";
            case 2 -> accountType = "Fixed Deposit Account";
            case 3 -> accountType = "Current Account";
            case 4 -> accountType = "Recurring Deposit Account";
            default -> {
                System.out.println("Invalid choice. Please restart the process.");
                return;
            }
        }

        Random random = new Random();
        String cardNumber = String.format("%016d", Math.abs(random.nextLong() % 1_000_000_000_000_000L));
        String pin = String.format("%04d", random.nextInt(10_000));

        System.out.println("Generated Card Number: " + cardNumber);
        System.out.println("Generated PIN: " + pin);

        System.out.println("Select Services Required:");
        System.out.println("1. ATM CARD");
        System.out.println("2. Internet Banking");
        System.out.println("3. Mobile Banking");
        System.out.println("4. EMAIL Alerts");
        System.out.println("5. Cheque Book");
        System.out.println("6. E-Statement");
        System.out.print("Enter your choices (comma-separated): ");
        String[] servicesInput = scanner.nextLine().split(",");

        StringBuilder services = new StringBuilder();
        for (String service : servicesInput) {
            switch (service.trim()) {
                case "1" -> services.append("ATM CARD ");
                case "2" -> services.append("Internet Banking ");
                case "3" -> services.append("Mobile Banking ");
                case "4" -> services.append("EMAIL Alerts ");
                case "5" -> services.append("Cheque Book ");
                case "6" -> services.append("E-Statement ");
                default -> System.out.println("Invalid service option: " + service);
            }
        }

        System.out.println("Form No: " + formNo);
        System.out.println("Account Type: " + accountType);
        System.out.println("Services Selected: " + services);

        System.out.println("Confirm submission? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            try {
                Connn connection = new Connn();
                String query1 = "INSERT INTO signupthree VALUES ('" + formNo + "', '" + accountType + "', '"
                        + cardNumber + "', '" + pin + "', '" + services + "')";
                String query2 = "INSERT INTO login VALUES ('" + formNo + "', '" + cardNumber + "', '" + pin + "')";
                connection.statement.executeUpdate(query1);
                connection.statement.executeUpdate(query2);
                System.out.println("Submission Successful!\nCard Number: " + cardNumber + "\nPIN: " + pin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Submission canceled.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the form number as an argument.");
            return;
        }

        Signup3 signup3 = new Signup3(args[0]);
        signup3.start();
    }
}
