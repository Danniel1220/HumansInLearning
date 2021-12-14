package com.crystal.media;


import com.crystal.dao.CompactDisc;
import com.crystal.dao.DigitalVideoDisc;
import com.crystal.dao.Media;
import com.crystal.dao.Track;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OnlineMedia {
    public static void main(String[] args) throws Exception {
        // Read JSON file.
        Object jsonFile = new JSONParser().parse(new FileReader("media.json"));

        // Object with everything in the file.
        JSONObject jsonFileObject = (JSONObject) jsonFile;

        // Object containing just the discs/tracks.
        JSONArray jsonDVDsArray = (JSONArray) jsonFileObject.get("dvds");
        JSONArray jsonCDsArray = (JSONArray) jsonFileObject.get("cds");

        // Create the order.
        Order order = new Order();

        // Iterate the DVD array, create and add DVDs to order.
        for (Object o : jsonDVDsArray) {
            JSONObject dvdInArray = (JSONObject) o;

            DigitalVideoDisc dvd = new DigitalVideoDisc(
                    (String) dvdInArray.get("title"),
                    (String) dvdInArray.get("category"),
                    (double) dvdInArray.get("cost"),
                    (String) dvdInArray.get("director"),
                    (double) dvdInArray.get("length")
            );

            dvd.play();
            order.addMedia(dvd);
        }

        // Iterate the CD array, create and add CDs to order.
        for (Object o : jsonCDsArray) {
            JSONObject cdInArray = (JSONObject) o;

            // Get track list from cd.
            ArrayList<Track> tracks = new ArrayList<Track>();
            JSONArray tracksArray = (JSONArray) cdInArray.get("tracks");
            for (Object t : tracksArray) {
                JSONObject trackInArray = (JSONObject) t;
                Track track = new Track((String) trackInArray.get("title"), (long) trackInArray.get("length"));

                track.play();
                tracks.add(track);
            }

            CompactDisc cd = new CompactDisc(
                    (String) cdInArray.get("title"),
                    (String) cdInArray.get("category"),
                    (double) cdInArray.get("cost"),
                    (String) cdInArray.get("artist"),
                    tracks
            );

            cd.play();
            order.addMedia(cd);
        }

        System.out.println();

        // Iterate through order and print everything in it.
        for (Media m : order.getOrderArr()) {
            System.out.println(m);
            System.out.println();
        }

        // Sort the tracks from the CD since it implements Comparable.
        CompactDisc cd = (CompactDisc) order.getOrderArr().get(3);
        System.out.println(cd.getTracks());
        Collections.sort(cd.getTracks());
        System.out.println(cd.getTracks());
    }
}