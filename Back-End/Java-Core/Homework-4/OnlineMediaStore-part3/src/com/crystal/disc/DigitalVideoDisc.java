package com.crystal.disc;

public class DigitalVideoDisc {
    private String title;
    private String category;
    private double cost;
    private String director;
    private double length;

    public DigitalVideoDisc(String title, String category, double cost, String director, double length) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.director = director;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    public String getDirector() {
        return director;
    }

    public double getLength() {
        return length;
    }

    public String toString() {
        return "Title: " + this.title + "\nCategory: " + this.category + "\nCost: " + this.cost +
                "\nDirector: " + this.director + "\nLength: " + this.length + "\n\n";
    }
}
