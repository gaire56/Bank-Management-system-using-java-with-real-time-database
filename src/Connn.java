import java.sql.*;

public class Connn {
    // Connection details
    private static final String URL = "jdbc:mysql://localhost:3306/bankSystem"; // Replace with your DB URL
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "2813"; // Replace with your DB password

    public Connection connection;
    public Statement statement;

    // Constructor
    public Connn() {
        try {
            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement(); // Create statement object for executing queries
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to connect to the database.");
        }
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
