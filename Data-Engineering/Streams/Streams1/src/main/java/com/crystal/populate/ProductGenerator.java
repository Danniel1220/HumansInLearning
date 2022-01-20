package com.crystal.populate;

import com.crystal.dao.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductGenerator {
    public List<Product> generateProducts () {
        List<Product> productList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            productList.add(new Product(
                    random.nextInt(99999 - 10000) + 10000,
                    "Book " + i,
                    "Books",
                    random.nextInt(999 - 100) + 100));
        }

        for (int i = 0; i < 100; i++) {
            productList.add(new Product(
                    random.nextInt(99999 - 10000) + 10000,
                    "Baby Product " + i,
                    "Baby",
                    random.nextInt(999 - 100) + 100));
        }


        for (int i = 0; i < 100; i++) {
            productList.add(new Product(
                    random.nextInt(99999 - 10000) + 10000,
                    "Toy " + i,
                    "Toys",
                    random.nextInt(999 - 100) + 100));
        }

        return productList;
    }
}
