package com.crystal.media;


import com.crystal.disc.DigitalVideoDisc;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;

public class OnlineMedia {
    public static void main(String[] args) throws Exception {
        // Read JSON file.
        Object jsonFile = new JSONParser().parse(new FileReader("media.properties"));

        // Object with everything in the file.
        JSONObject jsonFileObject = (JSONObject) jsonFile;

        // Object containing just the discs.
        JSONArray jsonDiscsArray = (JSONArray) jsonFileObject.get("discs");

        // Create the order.
        Order order = new Order();

        // Iterate through the JSON array and add all the dvds inside to the order.
        for(int i = 0; i<jsonDiscsArray.size(); i++) {
            JSONObject arrayElement = (JSONObject) jsonDiscsArray.get(i);

            DigitalVideoDisc dvd = new DigitalVideoDisc(
                    (String) arrayElement.get("title"),
                    (String) arrayElement.get("category"),
                    (double) arrayElement.get("cost"),
                    (String) arrayElement.get("director"),
                    (double) arrayElement.get("length")
            );

            order.addToOrder(dvd);
        }

        // Iterate through order and print all discs in it.
        for (DigitalVideoDisc disc : order.getOrderArr()) {
            System.out.println(disc);
        }
    }
}
