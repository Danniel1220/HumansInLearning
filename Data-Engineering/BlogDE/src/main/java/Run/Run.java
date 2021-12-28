package Run;

import java.sql.*;

public class Run {
    public static void main(String[] args) {
        final String DATABASE_CONNECT_URL = "jdbc:mysql://localhost:3306/blogdb";
        final String DATABASE_USER = "root";
        final String DATABASE_PASSWORD = "rootpassword";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "rootpassword");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from articles");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
