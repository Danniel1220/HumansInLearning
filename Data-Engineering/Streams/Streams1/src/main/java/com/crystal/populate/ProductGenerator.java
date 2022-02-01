package com.crystal.populate;

import com.crystal.dao.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductGenerator {
    public List<Product> generateProducts () {
        List<Product> productList = new ArrayList<>();
        List<Integer> alreadyGeneratedIds = new ArrayList<>();
        Random random = new Random();

        productList.addAll(generateProductCategory(alreadyGeneratedIds, "Book", "Books", 100, random));
        productList.addAll(generateProductCategory(alreadyGeneratedIds, "Baby Product", "Baby", 100, random));
        productList.addAll(generateProductCategory(alreadyGeneratedIds, "Toy", "Toys", 100, random));

        return productList;
    }

    public List<Product> generateProductCategory(List<Integer> alreadyGeneratedIds, String name, String category,
                                                 int productAmount, Random random) {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < productAmount; i++) {
            int id = random.nextInt(99999 - 10000) + 10000;
            while (alreadyGeneratedIds.contains(id)) {
                id = random.nextInt(99999 - 10000) + 10000;
            }
            products.add(new Product(
                    id,
                    name + i,
                    category,
                    random.nextInt(999 - 100) + 100));

            alreadyGeneratedIds.add(id);
        }

        return products;
    }
}
