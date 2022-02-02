package com.crystal.populate;

import com.crystal.dao.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomerGeneratorTest {
    CustomerGenerator customerGenerator;

    List<String> firstNames;
    List<String> lastNames;

    @Before
    public void init() {
        firstNames = Arrays.asList("John", "David", "Elisabeth", "Daniel", "Andrew", "Maria", "Diana", "Nicholas", "Sarah", "Miley");
        lastNames = Arrays.asList("Cooper", "Smith", "Ackerman", "Jaeger", "Williams", "Brown", "Garcia", "Miller");

        customerGenerator = new CustomerGenerator();
    }

    @Test
    public void shouldHaveRandomNameFromLists() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            customerList.add(customerGenerator.generateCustomer());
        }

        // Checking if customer's name contains any of the known first names
        customerList.forEach(customer -> {
            AtomicBoolean contains = new AtomicBoolean(false);
            firstNames.forEach(name -> {
                if (customer.getName().contains(name)) {
                    contains.set(true);
                }
            });
            Assert.assertTrue("Customer has unknown first name", contains.get());
        });

        // Checking if customer's name contains any of the known last names
        customerList.forEach(customer -> {
            AtomicBoolean contains = new AtomicBoolean(false);
            lastNames.forEach(name -> {
                if (customer.getName().contains(name)) {
                    contains.set(true);
                }
            });
            Assert.assertTrue("Customer has unknown first name", contains.get());
        });
    }

    @Test
    public void shouldHaveValidId() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            customerList.add(customerGenerator.generateCustomer());
        }
        customerList.forEach(c -> Assert.assertTrue("Customer id is invalid.", c.getId() >= 0 && c.getId() < 1000));
    }

    @Test
    public void shouldHaveValidCustomerTier() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            customerList.add(customerGenerator.generateCustomer());
        }
        customerList.forEach(c -> Assert.assertTrue("Customer tier is invalid.", c.getTier() >= 1 && c.getTier() <= 3));
    }
}
