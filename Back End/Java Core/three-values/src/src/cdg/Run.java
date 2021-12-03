package src.cdg;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        int x1;
        int x2;
        int x3;

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter x1:");
        x1 = keyboard.nextInt();

        System.out.println("Enter x2:");
        x2 = keyboard.nextInt();

        System.out.println("Enter x3:");
        x3 = keyboard.nextInt();

        float mean = (x1 + x2 + x3) / 3;
        double varianceSquared = (Math.pow((x1 - mean), 2) + Math.pow((x2 - mean), 2) + Math.pow((x3 - mean), 2)) / 3;
        double standardDeviation = Math.sqrt(varianceSquared);

        System.out.println("Mean = " + mean);
        System.out.println("Variance^2 = " + varianceSquared);
        System.out.println("Standard Deviation = " + standardDeviation);
    }
}
