package com.crystal.queue;

import java.util.Arrays;

public class IntegerQueueInterface {
    private final int DEFAULT_ARRAY_SIZE = 10;
    private int[] array;
    private int arrayIndex;

    public IntegerQueueInterface() {
        array = new int[DEFAULT_ARRAY_SIZE];
        arrayIndex = 0;
    }

    public IntegerQueueInterface(int size) {
        array = new int[size];
        arrayIndex = 0;
    }

    public void add(int integer) {
        // If the array is full
        if (arrayIndex == array.length) {
            // Create a new array, twice the size of the original array
            int[] newArray = new int[array.length*2];

            // Copy the smaller array's elements into the new one
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }

            // The array field becomes the new bigger array
            array = newArray;

            // Add the new element
            array[arrayIndex] = integer;
            arrayIndex++;
        }
        else {
            array[arrayIndex] = integer;
            arrayIndex++;
        }
    }

    public int remove() {
        int removedItem = array[0];
        //array = Arrays.copyOfRange(array, 1, array.length);

        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i+1];
        }

        arrayIndex--;
        return removedItem;
    }

    public boolean isEmpty() {
        return arrayIndex == 0;
    }

    @Override
    public String toString() {
        return "IntegerQueueInterface{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
