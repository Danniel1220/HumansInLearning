package com.crystal.vending;

public class VendingMachine {
    public int[] getChange(float inputValue, float price) {
        if (inputValue >= price) {
            inputValue *= 100;
            price *= 100;

            int[] coinArr = new int[6];
            int change = (int)(inputValue - price);

            coinArr[5] = change / 100;
            change = change - coinArr[5]*100;
            coinArr[4] = (change / 50);
            change = change - coinArr[4]*50;
            coinArr[3] = (change / 25);
            change = change - coinArr[3]*25;
            coinArr[2] = (change / 10);
            change = change - coinArr[2]*10;
            coinArr[1] = (change / 5);
            change = change - coinArr[1]*5;
            coinArr[0] = (change / 1);

            return coinArr;
        }
        else {

        }

    }
}

// 1c (0), 5c (1), 10c (2), 25c (3), 50c (4), 1$ (5)
// (5, 0.99)