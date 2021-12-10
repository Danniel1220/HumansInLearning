package com.crystal.kalah;

import java.util.Scanner;

public class InputManager {
    public int getInput(int player)
    {
        int selectedCup = 0;
        Scanner keyboard = new Scanner(System.in);
        // incrementing player's value to properly display which player's turn it is
        // this is needed because the arrays used and 0 indexed but the user sees the game as 1 indexed
        player++;

        System.out.println("It's player " + player  +"'s turn.");
        System.out.println("Select cup: [value must be between 1 and 6]");

        boolean invalidInput = true;
        while(invalidInput) {
            String input;
            input = keyboard.nextLine();
            if (validInput(input)) {
                selectedCup = Integer.valueOf(input);
                if (selectedCup > 0 && selectedCup <= 6)
                {
                    invalidInput = false;
                }
                else {
                    System.out.println("Invalid input, value must be between 1 and 6.");
                }
            }
            else {
                System.out.println("Invalid input, value must be an INTEGER between 1 and 6.");

            }
        }
        // decrementing selectedCup's value so that we select the correct cup in our 0 indexed array
        selectedCup--;

        return selectedCup;
    }

    public boolean validInput(String value)
    {
        try {
            int numericValue = Integer.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
