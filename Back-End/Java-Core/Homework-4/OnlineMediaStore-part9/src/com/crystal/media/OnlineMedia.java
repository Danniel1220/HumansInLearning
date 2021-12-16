package com.crystal.media;


import com.crystal.dao.*;
import com.crystal.exceptions.PlayerException;
import com.crystal.io.DataFromProperties;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OnlineMedia {
    public static void main(String[] args) {
        DataFromProperties dataManager = new DataFromProperties();
        JSONObject jsonFileData = dataManager.retrieveJSONData("media.json");

        // Retrieving the individual arrays
        JSONArray jsonDVDsArray = dataManager.getJSONArray(jsonFileData, "dvds");
        JSONArray jsonCDsArray = dataManager.getJSONArray(jsonFileData, "cds");

        // Create an order.
        Order order = new Order();

        // Add media to order.
        addDVDsToOrder(jsonDVDsArray, order);
        addCDsToOrder(jsonCDsArray, order);

        // Sort the tracks from the CD since it implements Comparable.
        System.out.println("Sorting tracks:");
        sortCDs(((CompactDisc) order.getOrderArr().get(3)).getTracks());

        // Add and remove a track from a CD using Library methods.
        // Create a new copy of the tracks array inside the CD.
        processAndAddCDUsingLibrary(order);

        printOrder(order);
    }

    private static void printOrder(Order order) {
        // Iterate through order and print everything in it.
        for (Media m : order.getOrderArr()) {
            System.out.println(m);
            System.out.println();
        }
    }

    private static void processAndAddCDUsingLibrary(Order order) {
        ArrayList<Track> processedTrackList = ((CompactDisc) order.getOrderArr().get(3)).getTracks();

        Library<Track> trackLibrary = new Library<>();
        processedTrackList = trackLibrary.addTrack(processedTrackList, new Track("Skillet - Comatose", 231));
        processedTrackList = trackLibrary.removeTrack(processedTrackList, 0);

        // New processed CD using Library class.
        CompactDisc libraryProcessedCD = new CompactDisc(
                order.getOrderArr().get(3).getTitle(),
                order.getOrderArr().get(3).getCategory(),
                order.getOrderArr().get(3).getCost(),
                ((CompactDisc) order.getOrderArr().get(3)).getArtist(),
                processedTrackList
        );

        order.addMedia(libraryProcessedCD);
    }

    private static void sortCDs(ArrayList<Track> trackArrayList) {
        System.out.println(trackArrayList);
        Collections.sort(trackArrayList);
        System.out.println(trackArrayList + "\n");
    }

    private static void addDVDsToOrder(JSONArray mediaArray, Order order) {
        // Iterate the DVD array, create and add DVDs to order.
        for (Object o : mediaArray) {
            JSONObject dvdInArray = (JSONObject) o;

            DigitalVideoDisc dvd = new DigitalVideoDisc(
                    (String) dvdInArray.get("title"),
                    (String) dvdInArray.get("category"),
                    (double) dvdInArray.get("cost"),
                    (String) dvdInArray.get("director"),
                    (double) dvdInArray.get("length")
            );

            try {
                dvd.play();
            } catch (PlayerException e) {
                e.printStackTrace();
            }
            order.addMedia(dvd);
        }


        System.out.println();
    }

    private static void addCDsToOrder(JSONArray mediaArray, Order order) {
        // Iterate the CD array, create and add CDs to order.
        for (Object o : mediaArray) {
            JSONObject cdInArray = (JSONObject) o;

            // Get track list from cd.
            ArrayList<Track> tracks = new ArrayList<>();
            JSONArray tracksArray = (JSONArray) cdInArray.get("tracks");
            for (Object t : tracksArray) {
                JSONObject trackInArray = (JSONObject) t;
                Track track = new Track((String) trackInArray.get("title"), (long) trackInArray.get("length"));

                try {
                    track.play();
                } catch (PlayerException e) {
                    e.printStackTrace();
                }
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
    }
}