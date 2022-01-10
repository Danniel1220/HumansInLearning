package io;

import java.sql.SQLException;
import java.util.Scanner;

public class CLI {
    boolean isRunning;

    public CLI () {
        isRunning = true;
        runCLI();
    }

    private void runCLI () {
        // Accept commands until isRunning turns false.
        while (isRunning) {
            if (!isRunning) {
                break;
            }

            printCommandRequest();

            String input = getStringInput();

            // Choose behaviour depending on inputted command.
            switch (input) {
                case "selectall":
                    selectAllArticles();
                    break;

                case "selectid":
                    selectArticlesById();
                    break;

                case "exit":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Command not found...\n");
                    break;
            }
        }
    }

    // Prints possible commands.
    private void printCommandRequest() {
        System.out.println("Input your command:");
        System.out.println("'selectall' - Selects all database content and writes into a JSON file.");
        System.out.println("'selectid' - Selects only one specific database entry and writes it to a desired location on disk.");
        System.out.println("'exit' - Ends the program.");
    }

    private int getIntInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.print("> ");
        //reader.close();
        return reader.nextInt();
    }

    private String getStringInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.print("> ");
        //reader.close();
        return reader.nextLine().toLowerCase();
    }

    private void selectAllArticles () {
        DatabaseAccessManager databaseAccessManager = new DatabaseAccessManager();
        JSONOutputManager jsonOutputManager = new JSONOutputManager();
        String json = "[]";
        String inputPath;

        // Try connecting to database and fetching the articles table.
        try {
            json = jsonOutputManager.convertListToJSON(databaseAccessManager.selectArticlesTable(0));
        } catch (SQLException e) {
            System.out.println("Could not fetch articles table...");
            e.printStackTrace();
        }

        // Get another input from user to get a path to write to.
        System.out.println("Please input a file path to write the file to " +
                "(no input for the default resources folder, or a simple name for the root" +
                "directory of the project):");
        inputPath = getStringInput();

        // If the user simply presses enter we default to the resources folder.
        if (inputPath.equals("")) {
            jsonOutputManager.writeToFile("src/main/resources/output.json", json);
        }
        // Else we write to the specified path.
        else {
            jsonOutputManager.writeToFile(inputPath, json);
        }

        System.out.println();
    }

    public void selectArticlesById () {
        DatabaseAccessManager databaseAccessManager = new DatabaseAccessManager();
        JSONOutputManager jsonOutputManager = new JSONOutputManager();
        String json = "[]";
        String inputPath;

        // Getting the specific id input to look for it in the articles table.
        System.out.println("Input id:");
        int id = getIntInput();

        // Check if the input id is valid
        if (id > 0) {
            try {
                json = jsonOutputManager.convertListToJSON(databaseAccessManager.selectArticlesTable(id));
            } catch (SQLException e) {
                System.out.println("Could not fetch articles entry for id " + id + "...");
                e.printStackTrace();
            }
        }

        // If no json was generated, meaning no entry with that id was found, point it out to the user.
        if (json.equals("[]")) {
            System.out.println("No entry exists with specified id.");
        }
        // Json was generated so the fetch was a success.
        else {
            // Get another input from user to get a path to write to.
            System.out.println("Please input a file path to write the file to " +
                    "(no input for the default resources folder, or a simple name for the root" +
                    "directory of the project):");
            inputPath = getStringInput();

            // If the user simply presses enter we default to the resources folder.
            if (inputPath.equals("")) {
                jsonOutputManager.writeToFile("src/main/resources/output.json", json);
            }
            // Else we write to the specified path.
            else {
                jsonOutputManager.writeToFile(inputPath, json);
            }

        }

        System.out.println();
    }
}
