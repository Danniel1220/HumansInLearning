package com.crystal.run;

import com.crystal.dao.Product;
import com.crystal.populate.ProductGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        ProductGenerator productGenerator = new ProductGenerator();
        List<Product> productList = new ArrayList<>();
        productGenerator.generateProducts(productList);

        System.out.println(productList);
        System.out.println(productList.size());

        printExpensiveBooks(productList);
    }

    public static void printExpensiveBooks(List<Product> productList) {
        List<Product> expensiveBooks =  productList.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() > 200)
                .collect(Collectors.toList());

        System.out.println(expensiveBooks);
        System.out.println(expensiveBooks.size());
    }
}
