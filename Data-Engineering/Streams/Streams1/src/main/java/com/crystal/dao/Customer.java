package com.crystal.dao;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Customer {
    private long id;
    private String name;
    private int tier;
}
