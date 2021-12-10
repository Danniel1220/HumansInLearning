package com.crystal.employees;

import java.util.ArrayList;

public class Manager extends Employee {
    private ArrayList<Employee> subordinates = new ArrayList<>();

    public Manager(String name, float salary, ArrayList<Employee> subordinates) {
        this.name = name;
        this.salary = salary;
        this.subordinates = subordinates;
    }

    public float calculateSalary() {
        float subordinatesSalaryBonus = 0;
        for (Employee e : subordinates) {
            subordinatesSalaryBonus = subordinatesSalaryBonus + e.calculateSalary();
        }
        subordinatesSalaryBonus = subordinatesSalaryBonus / 200;
        return salary + subordinatesSalaryBonus;
    }

    public String getName() {
        return this.name;
    }
}
