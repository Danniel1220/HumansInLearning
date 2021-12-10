package com.crystal.vampire;

import java.util.Arrays;

public class VampireNumberFinder {

    public void findVampireNumbers(int digits)
    {
        if (digits % 2 == 0)
        {
            // computing the maximum number part of a vampire number can be
            double maxNumber = Math.pow(10, digits / 2) - 1;
            // computing the minimum number part of a vampire number can be
            double startPoint = Math.pow(10, digits / 2 - 1);

            System.out.println(maxNumber);
            System.out.println(startPoint);

            // looping through all possible combinations of numbers
            for(int i = (int)startPoint; i < maxNumber; i++) {
                for(int j = (int)startPoint; j < maxNumber; j++) {
                    int product = i * j;

                    // this string contains all the digits of both i and j, concatenated
                    String ijString = Integer.toString(i) + Integer.toString(j);
                    String productString = Integer.toString(product);

                    // converting both strings to arrays of characters
                    char[] ijCharArray = ijString.toCharArray();
                    char[] productCharArray = productString.toCharArray();

                    // sorting both arrays, so their characters can be compared, regardless of order
                    Arrays.sort(ijCharArray);
                    Arrays.sort(productCharArray);

                    // if the arrays are equal, that means the vampire numbers parts combined are equal to
                    // the initial number, thus making it a vampire number, so we print it
                    if (Arrays.equals(ijCharArray, productCharArray)) {
                        System.out.println(product + " = " + i + " * " + j);
                    }
                }
            }
        }
        else System.out.println("Number of digits must be even!");
    }
}
