package com.crystal.dao;

public class DigitalVideoDisc extends Media implements Playable {
    private final String director;
    private final double length;

    public DigitalVideoDisc(String title, String category, double cost, String director, double length) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public double getLength() {
        return length;
    }

    public String toString() {
        return super.toString() + "\nDirector: " + this.director + "\nLength: " + this.length + "\n";
    }

    public void play() {
        System.out.println("Movie " + super.getTitle() + " plays!");
    }
}
