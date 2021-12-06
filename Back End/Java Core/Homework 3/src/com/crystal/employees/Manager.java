package com.crystal.employees;

import java.util.ArrayList;

public class Manager implements Employee {
    private String name;
    private float salary;
    private ArrayList<Employee> subordinates = new ArrayList<>();

    public Manager(String name, float salary, ArrayList<Employee> subordinates) {
        this.name = name;
        this.salary = salary;
        this.subordinates = subordinates;
    }

    @Override
    public float getSalary() {
        float subordinatesSalaryBonus = 0;
        for (Employee e : subordinates) {
            subordinatesSalaryBonus = subordinatesSalaryBonus + e.getSalary();
        }
        subordinatesSalaryBonus = subordinatesSalaryBonus / 200;
        return salary + subordinatesSalaryBonus;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
