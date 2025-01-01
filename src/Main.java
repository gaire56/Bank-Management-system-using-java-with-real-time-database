import java.util.Scanner;

public class Main {

    private String pin;

    Main(String pin) {
        this.pin = pin;
        showMenu();
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please Select Your Transaction:");
            System.out.println("1. DEPOSIT");
            System.out.println("2. CASH WITHDRAWAL");
            System.out.println("3. FAST CASH");
            System.out.println("4. MINI STATEMENT");
            System.out.println("5. PIN CHANGE");
            System.out.println("6. BALANCE ENQUIRY");
            System.out.println("7. EXIT");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new Deposit(pin);
                    break;
                case 2:
                    new Withdrawal(pin);
                    break;
                case 3:
                    new FastCash(pin);
                    break;
                case 4:
                    new Mini(pin);
                    break;
                case 5:
                    new Pin(pin);
                    break;
                case 6:
                    new BalanceEnquriy(pin);
                    break;
                case 7:
                    System.out.println("Thank you for using our services. See you soon!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        new Main("");
    }
}
