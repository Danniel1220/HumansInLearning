package com.crystal.employees;

public class Technician implements Employee {
    private String name;
    private float salary;

    public Technician(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public float getSalary() {
        return salary;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
