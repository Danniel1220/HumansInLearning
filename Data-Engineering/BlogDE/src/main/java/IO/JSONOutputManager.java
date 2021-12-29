package IO;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class JSONOutputManager {
    public <T> String convertListToJSON(List<T> list) {
        String json = new Gson().toJson(list);
        return json;
    }

    public void writeToFile(String path, String text) {
        // Creates a file to the target path
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred... Could not create the file.");
            System.out.println("Perhaps the path is invalid?");
            e.printStackTrace();
        }

        // Writes to target file
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to file.");
        } catch (IOException e) {
            System.out.println("An error occured... Could not write to file.");
            e.printStackTrace();
        }
    }
}
