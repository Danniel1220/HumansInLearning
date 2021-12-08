package com.crystal.seconds;

public class TimeConverter {
    private int seconds;
    private int minutes;
    private int hours;

    public void convertTime(int seconds) {
        minutes = seconds / 60;
        this.seconds = seconds % 60;
        hours = minutes / 60;
        minutes = minutes % 60;
    }

    public void getTime() {
        System.out.println(hours + " hour(s), " + minutes + " minute(s) and " + seconds + " second(s)");
    }
}
