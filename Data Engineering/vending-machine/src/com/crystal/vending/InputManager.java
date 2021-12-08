package com.crystal.vending;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager {
    private File file;
    private Scanner scanner;
    private List<String> inputData = new ArrayList<String>();
    private int[] coinTypes;
    private float inputMoney;
    private float price;

    // Get input data when the object is created;
    public InputManager(String filePath) {
        try {
            file = new File(filePath);
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                inputData.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file. File not found.");
            e.printStackTrace();
        }
    }

    public void parseData() {
        // For each line of data in the cfg file:
        for (String s : inputData) {
            // Split the string by '=' to get the name of the data and the value.
            String[] splitString = s.split("=");

            // If the length is greater than one that means the data was split correctly.
            if (splitString.length > 1) {
                // Checking the data's name to know where to assign it.
                switch (splitString[0]) {
                    case "coins":
                        String[] individualCoinValues = splitString[1].split(",");
                        coinTypes = new int[individualCoinValues.length];
                        for (int i = 0; i < coinTypes.length; i++) {
                            coinTypes[i] = Integer.parseInt(individualCoinValues[i]);
                        }
                        break;
                    case "inputMoney":
                        inputMoney = Float.parseFloat(splitString[1]);
                        break;
                    case "price":
                        price = Float.parseFloat(splitString[1]);
                        break;
                }
            }

        }
    }

    public int[] getCoinTypes() {
        return coinTypes;
    }

    public float getInputMoney() {
        return inputMoney;
    }

    public float getPrice() {
        return price;
    }
}
