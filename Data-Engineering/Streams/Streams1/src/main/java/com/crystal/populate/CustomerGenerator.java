package com.crystal.populate;

import com.crystal.dao.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomerGenerator {
    List<String> firstNames;
    List<String> lastNames;

    public CustomerGenerator() {
        firstNames = Arrays.asList("John", "David", "Elisabeth", "Daniel", "Andrew", "Maria", "Diana", "Nicholas", "Sarah", "Miley");
        lastNames = Arrays.asList("Cooper", "Smith", "Ackerman", "Jaeger", "Williams", "Brown", "Garcia", "Miller");
    }

    public Customer generateCustomer() {
        Random random = new Random();
        return new Customer(
                random.nextInt(1000),
                firstNames.get(random.nextInt(firstNames.size())) + " " + lastNames.get(random.nextInt(firstNames.size())),
                random.nextInt(3));
    }
}
