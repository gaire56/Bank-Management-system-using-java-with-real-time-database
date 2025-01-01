import java.util.Scanner;

public class Signup2 {
    private String formno;

    public Signup2(String formno) {
        this.formno = formno;
        collectDetails();
    }

    private void collectDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("     Page 2: Additional Details      ");
        System.out.println("=====================================");

        // Get Religion
        String religion = getReligion(scanner);

        // Get Category
        String category = getCategory(scanner);

        // Get Income
        String income = getIncome(scanner);

        // Get Education
        String education = getEducation(scanner);

        // Get Occupation
        String occupation = getOccupation(scanner);

        System.out.println("Citizenship Number:");
        String cc = scanner.nextLine();

        System.out.println("Senior Citizen (Yes/No):");
        String seniorCitizen = scanner.nextLine();

        System.out.println("Existing Account (Yes/No):");
        String existingAccount = scanner.nextLine();

        // Verify all fields are filled
        if (cc.isEmpty()) {
            System.out.println("Please fill the cc Number its mandatory");
            return;
        }

        // if (cc.isEmpty()) {
        // System.out.println("Please fill all the mandatory fields.");
        // } else {
        try {
            Connn c = new Connn();
            String query = "INSERT INTO Signuptwo VALUES ('" + formno + "', '" + religion + "', '" + category
                    + "', '" + income + "', '" + education + "', '" + occupation + "', '" + cc + "', '"
                    + seniorCitizen + "', '" + existingAccount + "');";
            c.statement.executeUpdate(query);
            System.out.println("Details submitted successfully!");

            Signup3 signup3 = new Signup3(formno);
            signup3.start();
            // new Signup3(formno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();

    }

    private static String getReligion(Scanner scanner) {
        System.out.println("Select your Religion Status:");
        System.out.println("1. Hindu");
        System.out.println("2. Muslim");
        System.out.println("3. Sikh");
        System.out.println("4. Christian");
        System.out.println("5. Other");
        System.out.print("Enter choice (1, 2, 3, 4, 5): ");
        int religionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (religionChoice) {
            case 1:
                return "Hindu";
            case 2:
                return "Muslim";
            case 3:
                return "Sikh";
            case 4:
                return "Christian";
            case 5:
                return "Other";
            default:
                System.out.println("Invalid choice. Please try again.");
                return getReligion(scanner); // Recursively call to get correct input
        }
    }

    private static String getCategory(Scanner scanner) {
        System.out.println("Select your Category:");
        System.out.println("1. General");
        System.out.println("2. OBC");
        System.out.println("3. SC");
        System.out.println("4. ST");
        System.out.println("5. Other");
        System.out.print("Enter choice (1, 2, 3, 4, 5): ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (categoryChoice) {
            case 1:
                return "General";
            case 2:
                return "OBC";
            case 3:
                return "SC";
            case 4:
                return "ST";
            case 5:
                return "Other";
            default:
                System.out.println("Invalid choice. Please try again.");
                return getCategory(scanner); // Recursively call to get correct input
        }
    }

    private static String getIncome(Scanner scanner) {
        System.out.println("Select your Income Status:");
        System.out.println("1. Null");
        System.out.println("2. <1,50,000");
        System.out.println("3. <2,50,000");
        System.out.println("4. <5,00,000");
        System.out.println("5. Upto 10,00,000");
        System.out.println("6. Above 10,00,000");
        System.out.print("Enter choice (1, 2, 3, 4, 5, 6): ");
        int incomeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (incomeChoice) {
            case 1:
                return "Null";
            case 2:
                return "<1,50,000";
            case 3:
                return "<2,50,000";
            case 4:
                return "<5,00,000";
            case 5:
                return "Upto 10,00,000";
            case 6:
                return "Above 10,00,000";
            default:
                System.out.println("Invalid choice. Please try again.");
                return getIncome(scanner); // Recursively call to get correct input
        }
    }

    private static String getEducation(Scanner scanner) {
        System.out.println("Select your Education Status:");
        System.out.println("1. Non-Graduate");
        System.out.println("2. Graduate");
        System.out.println("3. Post-Graduate");
        System.out.println("4. Doctorate");
        System.out.println("5. Others");
        System.out.print("Enter choice (1, 2, 3, 4, 5): ");
        int educationChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (educationChoice) {
            case 1:
                return "Non-Graduate";
            case 2:
                return "Graduate";
            case 3:
                return "Post-Graduate";
            case 4:
                return "Doctorate";
            case 5:
                return "Others";
            default:
                System.out.println("Invalid choice. Please try again.");
                return getEducation(scanner); // Recursively call to get correct input
        }
    }

    private static String getOccupation(Scanner scanner) {
        System.out.println("Select your Occupation Status:");
        System.out.println("1. Salaried");
        System.out.println("2. Self-Employed");
        System.out.println("3. Business");
        System.out.println("4. Student");
        System.out.println("5. Retired");
        System.out.println("6. Other");
        System.out.print("Enter choice (1, 2, 3, 4, 5, 6): ");
        int occupationChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (occupationChoice) {
            case 1:
                return "Salaried";
            case 2:
                return "Self-Employed";
            case 3:
                return "Business";
            case 4:
                return "Student";
            case 5:
                return "Retired";
            case 6:
                return "Other";
            default:
                System.out.println("Invalid choice. Please try again.");
                return getOccupation(scanner); // Recursively call to get correct input
        }
    }

    public static void main(String[] args) {
        new Signup2("");
    }
}
