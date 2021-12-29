package Run;

import IO.CLI;

import java.sql.*;

public class Run {
    public static void main(String[] args) {
        final String DATABASE_CONNECT_URL = "jdbc:mysql://localhost:3306/blogdb";
        final String DATABASE_USER = "root";
        final String DATABASE_PASSWORD = "rootpassword";

        // Connecting to database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "rootpassword");
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

        // Start the CLI.
        CLI cli = new CLI();
    }
}
