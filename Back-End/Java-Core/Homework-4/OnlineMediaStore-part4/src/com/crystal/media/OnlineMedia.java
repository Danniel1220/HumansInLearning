package com.crystal.media;


import com.crystal.dao.DigitalVideoDisc;
import com.crystal.dao.Media;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;

public class OnlineMedia {
    public static void main(String[] args) throws Exception {
        // Read JSON file.
        Object jsonFile = new JSONParser().parse(new FileReader("media.json"));

        // Object with everything in the file.
        JSONObject jsonFileObject = (JSONObject) jsonFile;

        // Object containing just the discs.
        JSONArray jsonDiscsArray = (JSONArray) jsonFileObject.get("discs");

        // Create the order.
        Order order = new Order();

        // Iterate through the JSON array and add all the dvds inside to the order.
        for (Object o : jsonDiscsArray) {
            JSONObject arrayElement = (JSONObject) o;

            DigitalVideoDisc dvd = new DigitalVideoDisc(
                    (String) arrayElement.get("title"),
                    (String) arrayElement.get("category"),
                    (double) arrayElement.get("cost"),
                    (String) arrayElement.get("director"),
                    (double) arrayElement.get("length")
            );

            order.addMedia(dvd);
        }

        // Iterate through order and print all discs in it.
        for (Media m : order.getOrderArr()) {
            System.out.println(m);
        }
    }
}
