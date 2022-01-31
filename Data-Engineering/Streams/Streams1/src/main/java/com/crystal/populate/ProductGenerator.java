package com.crystal.populate;

import com.crystal.dao.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductGenerator {
    public List<Product> generateProducts () {
        List<Product> productList = new ArrayList<>();
        List<Integer> alreadyGeneratedIds = new ArrayList<>();

        generateProductCategory(productList, alreadyGeneratedIds, "Book", "Books");
        generateProductCategory(productList, alreadyGeneratedIds, "Baby Product", "Baby");
        generateProductCategory(productList, alreadyGeneratedIds, "Toy", "Toys");

        return productList;
    }

    // make this return a list
    private void generateProductCategory(List<Product> productList, List<Integer> alreadyGeneratedIds,
                          String name, String category) {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int id = random.nextInt(99999 - 10000) + 10000;
            while (alreadyGeneratedIds.contains(id)) {
                id = random.nextInt(99999 - 10000) + 10000;
            }
            productList.add(new Product(
                    id,
                    name + i,
                    category,
                    random.nextInt(999 - 100) + 100));
            alreadyGeneratedIds.add(id);
        }
    }
}
