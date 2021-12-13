package com.crystal.employees;

import java.util.Map;

public class Seller extends Employee {
    private int amountOfItemsSold;
    private int sellingPrice;

    public Seller(String name, float salary, int amountOfItemsSold, int sellingPrice) {
        this.name = name;
        this.salary = salary;
        this.amountOfItemsSold = amountOfItemsSold;
        this.sellingPrice = sellingPrice;
    }

    public float calculateSalary() {
        return salary + ((amountOfItemsSold * sellingPrice) / 10);
    }

    public String getName() {
        return this.name;
    }
}
