package com.crystal.vending;

import java.util.Arrays;

public class VendingMachine {
    public int[] getChange(float inputValue, float price, int[] coinValues) {
            // Input values are multiplied by 100 to avoid decimal shenanigans.
            inputValue *= 100;
            price *= 100;

            // Create an array of same length as the input array with coin types for change.
            int[] changeArr = new int[coinValues.length];

            // Compute change.
            int change = (int)(inputValue - price);

            // Sort the array, in case coin types given are not in order.
            Arrays.sort(coinValues);

            // Iterate through all the coin types in descending value order.
            for(int i = coinValues.length - 1; i >= 0; i--) {
                // Compute amount change given of the current coin type in the iteration.
                changeArr[i] = computeCoinChange(coinValues[i], change);
                // Update the change to subtract the value given as change already.
                change = change - changeArr[i]*coinValues[i];
            }
            return changeArr;
    }

    public int computeCoinChange(int coinValue, int remainingChange) {
        return remainingChange / coinValue;
    }
}

// 1c (0), 5c (1), 10c (2), 25c (3), 50c (4), 1$ (5)
// (5, 0.99)
