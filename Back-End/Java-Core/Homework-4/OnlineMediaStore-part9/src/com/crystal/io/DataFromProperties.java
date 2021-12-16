package com.crystal.io;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class DataFromProperties {
    // Reads a JSON file and returns the file as an JSONObject.
    public JSONObject retrieveJSONData(String filePath) {
        Object jsonFile = new Object();
        try {
            jsonFile = new JSONParser().parse(new FileReader(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while reading a JSON file.");
        }
        return (JSONObject) jsonFile;
    }

    public JSONArray getJSONArray(JSONObject data, String arrayName) {
        return (JSONArray) ((JSONObject) data).get(arrayName);
    }
}
