package com.crystal.dao;

import java.io.Serializable;

public class Media implements Serializable {
    private final String title;
    private final String category;
    private final double cost;

    public Media(String title, String category, double cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
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

    public String toString() {
        return "Title: " + getTitle() + "\nCategory: " + getCategory() + "\nCost: " + getCost();
    }
}
