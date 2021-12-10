package com.crystal.employees;

public class Technician extends Employee {

    public Technician(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public float calculateSalary() {
        return salary;
    }

    public String getName() {
        return this.name;
    }
}
