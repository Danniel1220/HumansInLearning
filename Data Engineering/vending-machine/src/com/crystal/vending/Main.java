package com.crystal.vending;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        System.out.println(Arrays.toString(vendingMachine.getChange(5, 0.99f)));
    }
}
