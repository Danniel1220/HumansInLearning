package com.crystal.parse;


import com.crystal.dto.NearEarthObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeoJsonParserTest {

    @Test
    public void should_parse_json_to_neo_object() {
        NearEarthObject expectedNeo = new NearEarthObject(
                2465633,
                "465633 (2009 JR5)",
                225.19305f,
                503.54697f,
                true,
                "2015-Sep-08 20:28",
                18.127954f,
                0.30274788f,
                "Earth",
                false
        );

        StringBuilder jsonNeoData = new StringBuilder();

        try {
            File jsonFile = new File("src/main/resources/exampleNEO.json");
            Scanner reader = new Scanner(jsonFile);
            while (reader.hasNextLine()) {
                jsonNeoData.append(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        NearEarthObject actualNeo = NeoJsonParser.parseNeoJsonToNeoObject(jsonNeoData.toString());

        Assert.assertTrue(actualNeo.equals(expectedNeo));
    }

    @Test
    public void should_parse_json_to_neo_objects_list() {
        List<NearEarthObject> nearEarthObjectExpectedList = new ArrayList<>();

        NearEarthObject expectedNeo1 = new NearEarthObject(
                2465633,
                "465633 (2009 JR5)",
                225.1930466786f,
                503.5469604336f,
                true,
                "2015-Sep-08 20:28",
                18.1279547773f,
                0.3027478814f,
                "Earth",
                false
        );

        NearEarthObject expectedNeo2 = new NearEarthObject(
                3426410,
                "(2008 QV11)",
                143.4019234645f,
                320.6564489709f,
                false,
                "2015-Sep-08 14:31",
                19.7498128142f,
                0.2591250701f,
                "Earth",
                false
        );

        NearEarthObject expectedNeo3 = new NearEarthObject(
                2440012,
                "440012 (2002 LE27)",
                366.9061375314f,
                820.4270648822f,
                false,
                "2015-Sep-07 07:32",
                1.1630843052f,
                0.4981690972f,
                "Earth",
                false
        );

        NearEarthObject expectedNeo4 = new NearEarthObject(
                3713989,
                "(2015 FC35)",
                101.054341542f,
                225.9643771094f,
                false,
                "2015-Sep-07 20:01",
                8.763533811f,
                0.3213750442f,
                "Earth",
                false
        );

        nearEarthObjectExpectedList.add(expectedNeo1);
        nearEarthObjectExpectedList.add(expectedNeo2);
        nearEarthObjectExpectedList.add(expectedNeo3);
        nearEarthObjectExpectedList.add(expectedNeo4);

        StringBuilder jsonNeoListData = new StringBuilder();

        try {
            File jsonFile = new File("src/main/resources/exampleNeoApiResponseData.json");
            Scanner reader = new Scanner(jsonFile);
            while (reader.hasNextLine()) {
                jsonNeoListData.append(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<NearEarthObject> nearEarthObjectActualList = NeoJsonParser.parseNeoJsonToNeoList(jsonNeoListData.toString());

        Assert.assertEquals(nearEarthObjectExpectedList, nearEarthObjectActualList);
    }
}