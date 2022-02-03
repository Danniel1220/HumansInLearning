package com.crystal.service;

import com.crystal.dao.Customer;
import com.crystal.dao.Order;
import com.crystal.dao.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class RequestService {
    public List<Product> getExpensiveBooks(List<Product> productList) {
        return productList.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() > 200)
                .collect(Collectors.toList());
    }

    public List<Order> getBabyProductOrders(List<Order> orderList) {
        return orderList.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> "Baby".equals(p.getCategory())))
                .collect(Collectors.toList());
    }

    public List<Product> getDiscountedToys(List<Product> productList) {
        return productList.stream()
                .filter(p -> p.getCategory().equals("Toys"))
                // evading weird floating point arithmetic, this is just multiplying by 0.9 to get a 10% discount
                .map(p -> p = new Product(p.getId(), p.getName(), p.getCategory(), ((p.getPrice() * 9) / 10)))
                .collect(Collectors.toList());
    }

    public List<Order> getOrder(List<Order> orderList, LocalDate startDate, LocalDate endDate, int customerTier) {
         return orderList.stream()
                .filter(o -> o.getCustomer().getTier() == customerTier)
                .filter(o -> (o.getOrderDate().isAfter(startDate) && o.getOrderDate().isBefore(endDate)))
                .collect(Collectors.toList());
    }

    public List<Product> getCheapBooks(List<Product> productList) {
         return productList.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() < 150)
                .collect(Collectors.toList());
    }

    public List<Order> get3MostRecentOrders (List<Order> orderList) {
        return orderList.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<List<Product>> getOrder(List<Order> orderList, LocalDate orderDate) {
        List<Order> filteredOrderList = orderList.stream()
                .filter(o -> o.getOrderDate().isEqual(orderDate))
                .collect(Collectors.toList());

        List<List<Product>> ordersProductsList = new ArrayList<>();
        filteredOrderList.forEach(o -> ordersProductsList.add(o.getProducts()));

        return ordersProductsList;
    }

    public double getOrdersSumByMonth(List<Order> orderList, Month orderMonth) {
        return orderList.stream()
                .filter(o -> (o.getOrderDate().getYear() == 2021 && o.getOrderDate().getMonth() == orderMonth))
                .mapToDouble(o -> o.getProducts().stream()
                        .mapToDouble(Product::getPrice).sum())
                .sum();
    }

    public double getOrderAveragePaymentByDay(List<Order> orderList, LocalDate requestedDate) {
        return orderList.stream()
                .filter(o -> o.getOrderDate().isEqual(requestedDate))
                .mapToDouble(o -> o.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .average()
                        .orElse(0))
                .average()
                .orElse(0);

        // Since the orders generated are for the year 2021 and the exercise calls for the average
        // of payments placed yesterday, we will instead just calculate the average payment
        // for a specific day.
    }

    public DoubleSummaryStatistics getStatisticFiguresForBooks(List<Product> productList) {
        return productList.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    public Map<Long, Integer> getMapWithOrderIdAndProductCount(List<Order> orderList) {
         return orderList.stream()
                .collect(Collectors.toMap(Order::getId, o -> o.getProducts().size()));
    }

    public HashMap<Customer, List<Order>> getMapWithOrderRecordsOfCustomers(List<Order> orderList) {
        // Create a set with all customers found in orderList
        Set<Customer> customerSet = orderList.stream().map(Order::getCustomer).collect(Collectors.toSet());

        // Creating a HashMap that contains all customers as keys and empty lists as values
        HashMap<Customer, List<Order>> customerListHashMap = new HashMap<>();
        customerSet.forEach(c -> customerListHashMap.put(c, new ArrayList<>()));

        // For each order in orderList, we look for that order's customer in the HashMap and add
        // the order to the customer's order list.
        orderList.forEach(o -> {
            List<Order> customerOrderList = customerListHashMap.get(o.getCustomer());
            customerOrderList.add(o);
            customerListHashMap.put(o.getCustomer(), customerOrderList);
        });

        return customerListHashMap;
    }

    public HashMap<Order, Double> getMapWithOrderRecordsAndTotalProductSum(List<Order> orderList) {
        HashMap<Order, Double> ordersAndTheirProductSumMap = new HashMap<>();

        orderList.forEach(o -> ordersAndTheirProductSumMap.put(o, o.getProducts().stream()
                .mapToDouble(Product::getPrice).sum()));

        return ordersAndTheirProductSumMap;
    }

    public double getMostExpensiveProductByCategory(List<Product> productList, String category) {
        return productList.stream()
                .filter(p -> p.getCategory().equals(category))
                .mapToDouble(Product::getPrice)
                .max()
                .getAsDouble();
    }
}
