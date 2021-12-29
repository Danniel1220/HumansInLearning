package IO;

import DAO.Article;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessManager {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private final String DATABASE_CONNECT_URL = "jdbc:mysql://localhost:3306/blogdb";
    private final String DATABASE_USER = "root";
    private final String DATABASE_PASSWORD = "rootpassword";

    DatabaseAccessManager() {
        // Connecting to database
        try {
            connection = DriverManager.getConnection(DATABASE_CONNECT_URL, DATABASE_USER, DATABASE_PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Could not secure a connection to the database...");
            e.printStackTrace();
        }
    }

    // Fetches data from articles table.
    public ArrayList<Article> selectArticlesTable (int index) throws SQLException {
        // If index less than or equal to 0 we fetch all the data inside the table.
        if (index <= 0) {
            resultSet = statement.executeQuery("select * from articles;");
        }
        // Else the index is valid and we fetch only that entry.
        else {
            resultSet = statement.executeQuery("select * from articles where id=" + index + ";");
        }

        ArrayList<Article> articleList = new ArrayList<>();

        // Create each Article object as we read from the resultSet and add it to the list.
        while (resultSet.next()) {
            Article article = Article.builder()
                    .id(Integer.parseInt(resultSet.getString("id")))
                    .title(resultSet.getString("title"))
                    .tag(resultSet.getString("tag"))
                    .author(resultSet.getString("author"))
                    .dateTimeStamp(resultSet.getString("dateTimeStamp"))
                    .imgUrl(resultSet.getString("imgUrl"))
                    .content(resultSet.getString("content"))
                    .build();

            articleList.add(article);
        }

        return articleList;
    }
}
