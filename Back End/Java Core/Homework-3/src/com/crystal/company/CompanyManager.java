package com.crystal.company;

import com.crystal.employees.Employee;

import java.util.ArrayList;

public class CompanyManager {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public Employee getEmployeeByName(String name) {
        for (Employee e : employeeList) {
            if (e.getName() == name) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeList() {
        return  employeeList;
    }
}
