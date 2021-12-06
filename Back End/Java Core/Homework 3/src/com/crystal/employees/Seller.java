package com.crystal.employees;

import java.util.Map;

public class Seller implements Employee {
    private String name;
    private float salary;
    private int amountOfItemsSold;
    private int sellingPrice;

    public Seller(String name, float salary, int amountOfItemsSold, int sellingPrice) {
        this.name = name;
        this.salary = salary;
        this.amountOfItemsSold = amountOfItemsSold;
        this.sellingPrice = sellingPrice;
    }

    @Override
    public float getSalary() {
        return salary + ((amountOfItemsSold * sellingPrice) / 10);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
