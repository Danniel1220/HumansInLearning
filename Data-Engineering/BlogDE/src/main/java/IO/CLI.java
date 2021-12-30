package IO;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class CLI {
    boolean isRunning;

    public CLI () {
        isRunning = true;
        initCLI();
    }

    private void initCLI () {
        // Accept commands until isRunning turns false.
        while (isRunning) {
            if (!isRunning) {
                break;
            }

            // Print possible commands.
            System.out.println("Input your command:");
            System.out.println("'selectall' - Selects all database content and writes into a JSON file.");
            System.out.println("'selectid' - Selects only one specific database entry and writes it to a desired location on disk.");
            System.out.println("'exit' - Ends the program.");

            String input = getStringInput();

            input = input.toLowerCase();

            DatabaseAccessManager databaseAccessManager = new DatabaseAccessManager();
            JSONOutputManager jsonOutputManager = new JSONOutputManager();

            // Default for an empty JSON array is '[]', string is defaulted to this because we check if it is empty
            // later, in case we failed to write to it.
            String json = "[]";
            String inputPath;

            switch (input) {
                case "selectall":
                    databaseAccessManager = new DatabaseAccessManager();
                    jsonOutputManager = new JSONOutputManager();
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
                    break;

                case "selectid":
                    System.out.println("Input id:");
                    int index = getIntInput();
                    if (index > 0) {
                        try {
                            json = jsonOutputManager.convertListToJSON(databaseAccessManager.selectArticlesTable(index));
                        } catch (SQLException e) {
                            System.out.println("Could not fetch articles entry for id " + index + "...");
                            e.printStackTrace();
                        }
                    }

                    if (json.equals("[]")) {
                        System.out.println("No entry exists with specified id.");
                    }
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
        return reader.nextLine();
    }
}
