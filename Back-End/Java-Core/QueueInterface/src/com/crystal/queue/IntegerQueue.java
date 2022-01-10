package com.crystal.queue;

import java.util.Arrays;

public class IntegerQueue {
    private final int DEFAULT_ARRAY_SIZE = 10;
    private int[] array;
    private int totalNumberOfElements;

    public IntegerQueue() {
        array = new int[DEFAULT_ARRAY_SIZE];
    }

    public IntegerQueue(int size) {
        array = new int[size];
    }

    public void add(int newNumericValue) {
        // If the array is full
        if (totalNumberOfElements == array.length) {
            int[] newArray = new int[array.length*2];

            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }

            System.out.println("Size increased from " + array.length + " to " + newArray.length);

            array = newArray;

            array[totalNumberOfElements] = newNumericValue;
            totalNumberOfElements++;
        }
        else {
            array[totalNumberOfElements] = newNumericValue;
            totalNumberOfElements++;
        }
    }

    public int remove() {
        int removedItem = array[0];

        // If the size of the new array after removing an item is the same size as
        // half the array, we can cut the size of the array in half.
        // Or better yet, create the new array with the element already removed.
        if ((totalNumberOfElements - 1 == array.length / 2) && totalNumberOfElements > DEFAULT_ARRAY_SIZE ) {
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
            array[totalNumberOfElements - 1] = 0;
        }
        totalNumberOfElements--;
        return removedItem;
    }

    public boolean isEmpty() {
        return totalNumberOfElements == 0;
    }

    @Override
    public String toString() {
        return "IntegerQueueInterface{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
