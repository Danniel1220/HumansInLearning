package com.crystal.run;

import com.crystal.dao.Order;
import com.crystal.dao.Product;
import com.crystal.populate.OrderGenerator;
import com.crystal.populate.ProductGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        ProductGenerator productGenerator = new ProductGenerator();
        List<Product> productList = new ArrayList<>();
        productGenerator.generateProducts(productList);

        OrderGenerator orderGenerator = new OrderGenerator(productList);
        List<Order> orderList = orderGenerator.generateOrders();

        System.out.println(productList);
        System.out.println(productList.size() + "\n");
        System.out.println(orderList);
        System.out.println(orderList.size() + "\n");

        printExpensiveBooks(productList);
        printBabyProducts(productList);
        printDiscountedToys(productList);
    }

    public static void printExpensiveBooks(List<Product> productList) {
        List<Product> expensiveBooks =  productList.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() > 200)
                .collect(Collectors.toList());

        System.out.println("- get a list of products belongs to category “Books” with price > 200");
        System.out.println(expensiveBooks);
        System.out.println("Size: " + expensiveBooks.size() + "\n");
    }

    public static void printBabyProducts(List<Product> productList) {
        List<Product> expensiveBooks =  productList.stream()
                .filter(p -> p.getCategory().equals("Baby"))
                .collect(Collectors.toList());

        System.out.println("- get a list of order with products belong to category “Baby”");
        System.out.println(expensiveBooks);
        System.out.println("Size: " + expensiveBooks.size() + "\n");
    }

    public static void printDiscountedToys(List<Product> productList) {
        List<Product> expensiveBooks =  productList.stream()
                .filter(p -> p.getCategory().equals("Toys"))
                // evading weird floating point arithmetic, this is just multiplying by 0.9 to get a 10% discount
                .map(p -> p = new Product(p.getId(), p.getName(), p.getCategory(), ((p.getPrice() * 9) / 10)))
                .collect(Collectors.toList());

        System.out.println("- get a list of products with category = “Toys” and then apply 10% discount");
        System.out.println(expensiveBooks);
        System.out.println("Size: " + expensiveBooks.size() + "\n");
    }
}
