package com.crystal.run;

import com.crystal.dao.Customer;
import com.crystal.dao.Order;
import com.crystal.dao.Product;
import com.crystal.populate.OrderGenerator;
import com.crystal.populate.ProductGenerator;
import com.crystal.service.RequestService;

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

        RequestService requestService = new RequestService();

        System.out.println(requestService.getExpensiveBooks(productList));
        System.out.println(requestService.getBabyProductOrders(orderList));
        System.out.println(requestService.getDiscountedToys(productList));
        System.out.println(requestService.getOrder(orderList,
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 7, 1), 2));
        System.out.println(requestService.getCheapBooks(productList));
        System.out.println(requestService.get3MostRecentOrders(orderList));
        System.out.println(requestService.getOrder(orderList, LocalDate.of(2021, 7, 15)));
        System.out.println(requestService.getOrdersSumByMonth(orderList, Month.MARCH));
        System.out.println(requestService.getOrderAveragePaymentByDay(orderList, LocalDate.of(2021, 5, 17)));
        System.out.println(requestService.getStatisticFiguresForBooks(productList));
        System.out.println(requestService.getMapWithOrderIdAndProductCount(orderList));
        System.out.println(requestService.getMapWithOrderRecordsOfCustomers(orderList));
        System.out.println(requestService.getMapWithOrderRecordsAndTotalProductSum(orderList));
        System.out.println(requestService.getMostExpensiveProductByCategory(productList, "Toys"));
    }
}
