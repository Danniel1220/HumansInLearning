package com.crystal.service;

import com.crystal.dao.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RequestServiceTest {
    RequestService requestService;

    @Before
    public void init() {
        requestService = new RequestService();
    }

    @Test
    public void shouldReturnExpensiveBooks () {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(
                0,
                "Book 1",
                "Books",
                100
        ));
        productList.add(new Product(
                0,
                "Book 2",
                "Books",
                250
        ));
        productList.add(new Product(
                0,
                "Book 3",
                "Books",
                300
        ));
        productList.add(new Product(
                0,
                "Toy 1",
                "Toys",
                100
        ));

        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(new Product(
                0,
                "Book 2",
                "Books",
                250
        ));
        expectedProductList.add(new Product(
                0,
                "Book 3",
                "Books",
                300
        ));

        List<Product> serviceFilteredList = requestService.getExpensiveBooks(productList);

        System.out.println(serviceFilteredList);
        System.out.println(expectedProductList);

        Assert.assertEquals(expectedProductList, serviceFilteredList);
    }
}
