package com.crystal.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Product {
    private long id;
    private String name;
    private String category;
    private double price;
}
