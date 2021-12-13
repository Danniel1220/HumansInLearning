package com.crystal.dao;

public class DigitalVideoDisc extends Media {
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
        return "Title: " + super.getTitle() + "\nCategory: " + super.getCategory() + "\nCost: " + super.getCost() +
                "\nDirector: " + this.director + "\nLength: " + this.length + "\n\n";
    }
}
