import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Signup {
    public Signup() {
        Scanner scanner = new Scanner(System.in);
        Random ran = new Random();
        long first4 = (ran.nextLong() % 9000L) + 1000L;
        String formno = "" + Math.abs(first4);

        System.out.println("APPLICATION FORM NO. " + formno);
        System.out.println("Personal Details");

        // Name
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        // Father's Name
        System.out.print("Enter your Father's Name: ");
        String fname = scanner.nextLine();

        // Date of Birth
        String dob = getValidDob(scanner);

        // Gender selection
        String gender = getGenderChoice(scanner);

        // Email
        String email = getValidEmail(scanner);

        // Marital Status selection
        String marital = getMaritalStatusChoice(scanner);

        // Address
        System.out.print("Enter your Address: ");
        String address = scanner.nextLine();

        // City
        System.out.print("Enter your City: ");
        String city = scanner.nextLine();

        // Pin Code
        System.out.print("Enter your Pin Code: ");
        String pincode = scanner.nextLine();

        // State
        System.out.print("Enter your State: ");
        String state = scanner.nextLine();

        // Verify all fields are filled
        if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || gender.isEmpty() || email.isEmpty() ||
                marital.isEmpty() || address.isEmpty() || city.isEmpty() || pincode.isEmpty() || state.isEmpty()) {
            System.out.println("Error: All fields are required. Please try again.");
            return;
        }

        // Save the information to the database
        try {
            Connn c = new Connn();
            String query = "INSERT INTO signup VALUES('" + formno + "', '" + name + "', '" + fname + "', '" + dob +
                    "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city +
                    "', '" + pincode + "', '" + state + "')";
            c.statement.executeUpdate(query);
            System.out.println("Your details have been successfully submitted. Proceeding to the next step...");
            // After submission, you can proceed to a second signup step or welcome message
            new Signup2(formno);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving your details. Please try again.");
        }

        scanner.close();
    }

    // Method to choose gender
    private static String getGenderChoice(Scanner scanner) {
        System.out.println("Select your Gender:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.print("Enter choice (1 or 2): ");
        int genderChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (genderChoice == 1) {
            return "Male";
        } else if (genderChoice == 2) {
            return "Female";
        } else {
            System.out.println("Invalid choice. Please try again.");
            return getGenderChoice(scanner); // Recursively call to get correct input
        }
    }

    // Method to choose marital status
    private static String getMaritalStatusChoice(Scanner scanner) {
        System.out.println("Select your Marital Status:");
        System.out.println("1. Unmarried");
        System.out.println("2. Married");
        System.out.println("3. Other");
        System.out.print("Enter choice (1, 2, or 3): ");
        int maritalChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (maritalChoice == 1) {
            return "Unmarried";
        } else if (maritalChoice == 2) {
            return "Married";
        } else if (maritalChoice == 3) {
            return "Other";
        } else {
            System.out.println("Invalid choice. Please try again.");
            return getMaritalStatusChoice(scanner); // Recursively call to get correct input
        }
    }

    // Method to validate the Date of Birth (DOB)
    private static String getValidDob(Scanner scanner) {
        String dob;
        while (true) {
            System.out.print("Enter your Date of Birth (YYYY-MM-DD): ");
            dob = scanner.nextLine();
            if (isValidDate(dob)) {
                break;
            } else {
                System.out.println("Invalid Date format. Please use the format YYYY-MM-DD.");
            }
        }
        return dob;
    }

    // Method to check if the Date format is valid
    private static boolean isValidDate(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dob);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Method to validate the Email format
    private static String getValidEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.print("Enter your Email Address: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid Email format. Please enter a valid email address.");
            }
        }
        return email;
    }

    // Method to check if the Email format is valid using regex
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
