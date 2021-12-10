package com.crystal.vending;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        InputManager inputManager = new InputManager("input.cfg");
        InputValidator inputValidator = new InputValidator();
        inputManager.parseData();

        // Validating input.
        if( inputValidator.checkCoins(inputManager.getCoinTypes()) &&
            inputValidator.checkPositive(inputManager.getInputMoney()) &&
            inputValidator.checkPositive(inputManager.getPrice()) &&
            inputValidator.checkInputMoney(inputManager.getInputMoney(), inputManager.getPrice())) {
                System.out.println("Validation passed.");

                // Printing the coin types before getting the change for easier debugging/testing.
                for (int c : inputManager.getCoinTypes()) {
                    if (c < 100) System.out.print(c + "c ");
                    else System.out.print((float)c / 100 + "$ ");
                }
                System.out.println();

                System.out.println(Arrays.toString(vendingMachine.getChange(
                        inputManager.getInputMoney(),
                        inputManager.getPrice(),
                        inputManager.getCoinTypes())));
        }
        else System.out.println("Failed validation.");




    }
}

// 1c (0), 5c (1), 10c (2), 25c (3), 50c (4), 1$ (5)
// (5, 0.99)
