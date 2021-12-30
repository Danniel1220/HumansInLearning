package Run;

import IO.CLI;

import java.sql.*;

public class Run {
    final static String DATABASE_CONNECT_URL = "jdbc:mysql://localhost:3306/blogdb";
    final static String DATABASE_USER = "root";
    final static String DATABASE_PASSWORD = "rootpassword";

    public static void main(String[] args) {
        checkDatabase();

        // Start the CLI.
        CLI CLI = new CLI();
    }

    public static void checkDatabase() {
        // Connecting to database
        try {
            Connection connection = DriverManager.getConnection(DATABASE_CONNECT_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from articles");

            // Printing database's title row
            while (resultSet.next()) {
                System.out.println(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
