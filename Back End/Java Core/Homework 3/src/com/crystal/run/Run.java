package com.crystal.run;

import com.crystal.company.CompanyManager;
import com.crystal.employees.Employee;
import com.crystal.employees.Manager;
import com.crystal.employees.Seller;
import com.crystal.employees.Technician;

import java.util.ArrayList;
import java.util.Arrays;

public class Run {
    public static void main(String[] args) {
        CompanyManager company = new CompanyManager();

        company.addEmployee(new Seller("Sam", 500, 120, 15));
        company.addEmployee(new Technician("Teresa", 1400));
        company.addEmployee(new Manager("Matt", 1500, new ArrayList<Employee>(Arrays.asList(
                company.getEmployeeByName("Sam"),
                company.getEmployeeByName("Teresa")
        ))));
        company.addEmployee(new Seller("Susan", 500, 300, 17));
        company.addEmployee(new Manager("Maria", 1500, new ArrayList<Employee>( Arrays.asList(
                company.getEmployeeByName("Matt"),
                company.getEmployeeByName("Susan")
        ))));

        for (Employee e : company.getEmployeeList()) {
            System.out.println(e.getName() + "'s salary is " + e.getSalary());
        }
    }
}
