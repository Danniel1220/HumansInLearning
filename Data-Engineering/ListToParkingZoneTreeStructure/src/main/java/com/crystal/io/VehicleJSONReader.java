package com.crystal.io;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class VehicleJSONReader {

    public JSONArray readVehicles(String inputFilePath) {
        StringBuilder jsonData = new StringBuilder();

        try {
            File jsonFile = new File(inputFilePath);
            Scanner reader = new Scanner(jsonFile);
            while (reader.hasNextLine()) {
                jsonData.append(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new JSONArray(jsonData.toString());
    }
}
