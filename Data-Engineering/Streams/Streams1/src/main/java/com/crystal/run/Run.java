package com.crystal.run;

import com.crystal.dao.Customer;
import com.crystal.dao.Order;
import com.crystal.dao.Product;
import com.crystal.populate.OrderGenerator;
import com.crystal.populate.ProductGenerator;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        ProductGenerator productGenerator = new ProductGenerator();
        List<Product> productList = productGenerator.generateProducts();

        OrderGenerator orderGenerator = new OrderGenerator(productList);
        List<Order> orderList = orderGenerator.generateOrders(100, 20);

        System.out.println(productList);
        System.out.println(productList.size() + "\n");
        System.out.println(orderList);
        System.out.println(orderList.size() + "\n");

        printExpensiveBooks(productList);
        printBabyProductOrders(orderList);
        printDiscountedToys(productList);

        System.out.println("- get a list of products ordered by customer of tier 2 between " +
                "01-Feb-2021 and 01-July-2021");
        printOrder(orderList,
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 7, 1), 2);

        printCheapBooks(productList);
        print3MostRecentOrders(orderList);


        System.out.println("- get a list of orders which were delivered on 15-July-2021, " +
                "log the order records to the console and then return its product list");
        printOrder(orderList, LocalDate.of(2021, 7, 15));

        printOrdersSumByMonth(orderList, Month.MARCH);
        printOrderAveragePaymentByDay(orderList, LocalDate.of(2021, 5, 17));
        printStatisticFiguresForBooks(productList);
        printMapWithOrderIdAndProductCount(orderList);
        printMapWithOrderRecordsOfCustomers(orderList);
        printMapWithOrderRecordsAndTotalProductSum(orderList);
        printMostExpensiveProductByCategory(productList, "Toys");
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

    public static void printBabyProductOrders(List<Order> orderList) {
        List<Order> babyCategoryOrders =  orderList.stream()
                        .filter(o -> o.getProducts().stream()
                                .anyMatch(p -> "Baby".equals(p.getCategory())))
                        .collect(Collectors.toList());

        System.out.println("- get a list of orders with products that belong to category “Baby”");
        System.out.println(babyCategoryOrders);
        System.out.println("Size: " + babyCategoryOrders.size() + "\n");
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

    public static void printOrder(List<Order> orderList, LocalDate startDate, LocalDate endDate, int customerTier) {
        List<Order> filteredOrderList = orderList.stream()
                .filter(o -> o.getCustomer().getTier() == customerTier)
                .filter(o -> (o.getOrderDate().isAfter(startDate) && o.getOrderDate().isBefore(endDate)))
                .collect(Collectors.toList());

        System.out.println(filteredOrderList);
        System.out.println("Size: " + filteredOrderList.size() + "\n");
    }

    public static void printCheapBooks(List<Product> productList) {
        List<Product> cheapBooks =  productList.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() < 150)
                .collect(Collectors.toList());

        System.out.println("- get the cheapest product(s) of “Books” category");
        System.out.println(cheapBooks);
        System.out.println("Size: " + cheapBooks.size() + "\n");
    }

    public static void print3MostRecentOrders (List<Order> orderList) {
        List<Order> threeMostRecentOrders = orderList.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("- get the 3 most recent placed order");
        System.out.println(threeMostRecentOrders);
        System.out.println("Size: " + threeMostRecentOrders.size() + "\n");
    }

    public static List<List<Product>> printOrder(List<Order> orderList, LocalDate orderDate) {
        List<Order> filteredOrderList = orderList.stream()
                .filter(o -> o.getOrderDate().isEqual(orderDate))
                .collect(Collectors.toList());

        List<List<Product>> ordersProductsList = new ArrayList<>();
        filteredOrderList.forEach(o -> ordersProductsList.add(o.getProducts()));

        System.out.println(filteredOrderList);
        System.out.println("Size: " + filteredOrderList.size() + "\n");

        return ordersProductsList;
    }

    public static void printOrdersSumByMonth(List<Order> orderList, Month orderMonth) {
        double totalSum = orderList.stream()
                .filter(o -> (o.getOrderDate().getYear() == 2021 && o.getOrderDate().getMonth() == orderMonth))
                .mapToDouble(o -> o.getProducts().stream()
                    .mapToDouble(Product::getPrice).sum())
                .sum();


        System.out.println("- calculate total sum of all orders placed in March 2021");
        System.out.println(totalSum + "\n");
    }

    public static void printOrderAveragePaymentByDay(List<Order> orderList, LocalDate requestedDate) {
        double totalPaymentAverage = orderList.stream()
                .filter(o -> o.getOrderDate().equals(requestedDate))
                .mapToDouble(o -> o.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .average()
                        .orElse(0))
                .average()
                .orElse(0);

        // Since the orders generated are for the year 2021 and the exercise calls for the average
        // of payments placed yesterday, we will instead just calculate the average payment
        // for a specific day.
        System.out.println("- calculate order average payment placed yesterday (actually checks for 17-May-2021");
        System.out.println(totalPaymentAverage + "\n");
    }

    public static void printStatisticFiguresForBooks(List<Product> productList) {
        DoubleSummaryStatistics statistics = productList.stream()
                        .filter(p -> p.getCategory().equals("Books"))
                        .mapToDouble(Product::getPrice)
                        .summaryStatistics();

        System.out.println("- get a collection of statistic figures (i.e. sum, average, " +
                "max, min, count) for all products of category “Books”");
        System.out.println("sum: " + statistics.getSum() +
                "\naverage: " + statistics.getAverage() +
                "\nmax: " + statistics.getMax() +
                "\nmin: " + statistics.getMin() +
                "\ncount: " + statistics.getCount() + "\n");
    }

    public static void printMapWithOrderIdAndProductCount(List<Order> orderList) {
        Map<Long, Integer> orderIdAndProductCountMap = orderList.stream()
                .collect(Collectors.toMap(Order::getId, o -> o.getProducts().size()));

        System.out.println("- get a map with order id and order’s product count");
        System.out.println(orderIdAndProductCountMap);
        System.out.println("Size: " + orderIdAndProductCountMap.size() + "\n");
    }

    public static void printMapWithOrderRecordsOfCustomers(List<Order> orderList) {
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

        System.out.println("- get a map with order records grouped by customer");
        System.out.println(customerListHashMap);
        System.out.println("Size: " + customerListHashMap.size() + "\n");
    }

    public static void printMapWithOrderRecordsAndTotalProductSum(List<Order> orderList) {
        HashMap<Order, Double> ordersAndTheirProductSumMap = new HashMap<>();

        orderList.forEach(o -> ordersAndTheirProductSumMap.put(o, o.getProducts().stream()
                .mapToDouble(Product::getPrice).sum()));

        System.out.println("- get a map with order record and product total sum");
        System.out.println(ordersAndTheirProductSumMap);
        System.out.println("Size: " + ordersAndTheirProductSumMap.size() + "\n");
    }

    public static void printMostExpensiveProductByCategory(List<Product> productList, String category) {
        double maxPrice = productList.stream()
                .filter(p -> p.getCategory().equals(category))
                .mapToDouble(Product::getPrice)
                .max()
                .getAsDouble();

        System.out.println("- get the most expensive product by category");
        System.out.println("Category: " + category + ", Maximum Price: " + maxPrice);
    }
}
