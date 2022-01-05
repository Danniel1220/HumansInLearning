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

            System.out.println("Size increased from " + array.length + " to " + newArray.length);

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

        // If the size of the new array after removing an item is the same size as
        // half the array, we can cut the size of the array in half.
        // Or better yet, create the new array with the element already removed.
        if ((arrayIndex - 1 == array.length / 2) && arrayIndex > DEFAULT_ARRAY_SIZE ) {
            int[] newArray = new int[array.length / 2];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = array[i + 1];
            }
            System.out.println("Size decreased from " + array.length + " to " + newArray.length);
            array = newArray;
        }
        else {
            for (int i = 0; i < array.length - 1; i++) {
                array[i] = array[i+1];
            }
            array[arrayIndex - 1] = 0;
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
