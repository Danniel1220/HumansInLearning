package com.crystal.dao;

import java.util.List;

public class Book extends Media {
    List<String> authors;

    public Book (String title, String category, double cost, List<String> authors) {
        super(title, category, cost);
        this.authors = authors;
    }


}
