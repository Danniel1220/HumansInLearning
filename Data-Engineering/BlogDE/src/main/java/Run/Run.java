package Run;

import IO.CLI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.*;

public class Run {
    final static String DATABASE_CONNECT_URL = "jdbc:mysql://localhost:3306/blogdb";
    final static String DATABASE_USER = "root";
    final static String DATABASE_PASSWORD = "rootpassword";

    public static void main(String[] args) {
        checkDatabase();

        // Start the CLI.
        //CLI CLI = new CLI();

//        try {
//            byteArray();
//        } catch (IOException e) {
//            System.out.println("rip");
//            e.printStackTrace();
//        }
        testByteArray();
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

    public static void byteArray() throws IOException {
        File image = new File("src/main/resources/bucegi.jpg");
        FileInputStream fileInputStream = new FileInputStream (image);


        String sql = "INSERT INTO `blogdb`.`articles` (`title`, `tag`, `author`, `dateTimeStamp`, `imgUrl`, `content`, `imgByte`) VALUES (" +
                "'title', " +
                "'tag', " +
                "'author', " +
                "'date', " +
                "'imgurl', " +
                "'content', " +
                "'" + fileInputStream + "');";

        System.out.println(fileInputStream);

        // Connecting to database
        try {
            Connection connection = DriverManager.getConnection(DATABASE_CONNECT_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testByteArray() {
        // Connecting to database
        try {
            Connection connection = DriverManager.getConnection(DATABASE_CONNECT_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "select * from articles where id=22";
            ResultSet resultSet = statement.executeQuery(sql);

            //InputStream fileInputStreamString = resultSet.getBinaryStream("byteArray");
            System.out.println(resultSet.getBinaryStream("imgByte"));
            RenderedImage image = ImageIO.read(resultSet.getBinaryStream("imgByte"));
            ImageIO.write(image, "JPG",new File("C:\\Users\\george.clim\\Desktop\\test.jpg"));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
