package com.crystal.populate;

import com.crystal.dao.Product;

import java.util.List;
import java.util.Random;

public class ProductGenerator {
    public void generateProducts (List<Product> productList) {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            productList.add(new Product(
                    random.nextInt(99999 - 10000) + 10000,
                    "Product " + i,
                    "Category 1",
                    random.nextInt(999 - 100) + 100));
        }

        for (int i = 0; i < 100; i++) {
            productList.add(new Product(
                    random.nextInt(99999 - 10000) + 10000,
                    "Product " + i + 100,
                    "Category 2",
                    random.nextInt(999 - 100) + 100));
        }
    }
}
