package infrastructure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopulateDB {
    public static void main(String[] args) throws SQLException, java.text.ParseException {
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
            String jsonDate = (String) arrObject.get("date");
            long millisDate = parseDate(jsonDate);
            String imgUrl = (String) arrObject.get("imgUrl");
            String content = (String) arrObject.get("content");

            String sql = "INSERT INTO `blogdb`.`articles` (`title`, `tag`, `author`, `date`, `dateTimeStamp`, `imgUrl`, `content`) VALUES (" +
                    "'" + title + "', " +
                    "'" + tag + "', " +
                    "'" + author + "', " +
                    "'" + jsonDate + "', " +
                    "'" + millisDate + "', " +
                    "'" + imgUrl + "', " +
                    "'" + content + "');";

            statement.executeUpdate(sql);
            System.out.println("Executed update on db.");
        }


    }

    public static long parseDate(String inputDate) throws java.text.ParseException {
        String[] dateParts = inputDate.split(" ");
        int year = Integer.parseInt(dateParts[2]);
        int month;
        int day = Integer.parseInt(dateParts[1].substring(0, dateParts[1].length() - 1));

        switch (dateParts[0]) {
            case "January":
                month = 1;
                break;
            case "February":
                month = 2;
                break;
            case "March":
                month = 3;
                break;
            case "April":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "June":
                month = 6;
                break;
            case "July":
                month = 7;
                break;
            case "August":
                month = 8;
                break;
            case "September":
                month = 9;
                break;
            case "October":
                month = 10;
                break;
            case "November":
                month = 11;
                break;
            case "December":
                month = 12;
                break;
            default:
                System.out.println("Month name is invalid, defaulting to January...");
                month = 1;
        }

        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        String dateString = year + "/" + month + "/" + day + " 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateString);

        System.out.println(date.getTime());
        System.out.println();

        return date.getTime();
    }
}
