package Script;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PopulateDB {
    public static void main(String[] args) throws SQLException {
        final String DATABASE_JSON_FILEPATH = "src/main/resources/db.json";
        final String DATABASE_CONNECT_URL = "jdbc:mysql://localhost:3306/blogdb";
        final String DATABASE_USER = "root";
        final String DATABASE_PASSWORD = "rootpassword";


        // Read JSON file.
        Object jsonFile = new Object();
        try {
            jsonFile = new JSONParser().parse(new FileReader(DATABASE_JSON_FILEPATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find JSON file...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO exception occurred...");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Could not parse JSON file...");
        }

        // Turn JSON data into an array that is usable.
        JSONObject jsonObject = (JSONObject) jsonFile;
        JSONArray jsonDBArray = (JSONArray) jsonObject.get("articles");

        // Connect to database
        Connection connection = DriverManager.getConnection(DATABASE_CONNECT_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from articles");
        // Add each array item to the database as a new row.

        // For each object in array:
        for (Object o : jsonDBArray) {
            JSONObject arrObject = (JSONObject) o;

            //Long id = (Long) arrObject.get("id");
            String title = (String) arrObject.get("title");
            String tag = (String) arrObject.get("tag");
            String author = (String) arrObject.get("author");
            String date = (String) arrObject.get("date");
            String imgUrl = (String) arrObject.get("imgUrl");
            String content = (String) arrObject.get("content");

//            String sql = "INSERT INTO `blogdb`.`articles` (`id`, `title`, `tag`, `author`, `dateTimeStamp`, `imgUrl`, `content`) VALUES (" +
//                    "'" + id + "', " +
//                    "'" + title + "', " +
//                    "'" + tag + "', " +
//                    "'" + author + "', " +
//                    "'" + date + "', " +
//                    "'" + imgUrl + "', " +
//                    "'" + content + "');";

            String sql = "INSERT INTO `blogdb`.`articles` (`title`, `tag`, `author`, `dateTimeStamp`, `imgUrl`, `content`) VALUES (" +
                    "'" + title + "', " +
                    "'" + tag + "', " +
                    "'" + author + "', " +
                    "'" + date + "', " +
                    "'" + imgUrl + "', " +
                    "'" + content + "');";

            statement.executeUpdate(sql);
            System.out.println("Executed update on db.");
        }


    }
}
