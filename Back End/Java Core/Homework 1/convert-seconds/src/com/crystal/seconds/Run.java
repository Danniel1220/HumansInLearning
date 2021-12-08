package com.crystal.seconds;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        TimeConverter converter = new TimeConverter();
        converter.convertTime(3680);
        converter.getTime();

        Scanner keyboard = new Scanner(System.in);

        while (true)
        {
            System.out.println("Enter an integer (seconds), or 0 to exit: ");
            int seconds = keyboard.nextInt();
            if (seconds == 0) break;
            else {
                converter.convertTime(seconds);
                converter.getTime();
            }
        }
    }
}
