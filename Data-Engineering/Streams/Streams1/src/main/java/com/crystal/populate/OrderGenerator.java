package com.crystal.populate;

import com.crystal.dao.Customer;
import com.crystal.dao.Order;
import com.crystal.dao.Product;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class OrderGenerator {
    private List<Product> productList;

    public List<Order> generateOrders() {
        List<Order> orderList = new ArrayList<>();

        List<Integer> alreadyGeneratedIds = new ArrayList<>();

        CustomerGenerator customerGenerator = new CustomerGenerator();
        List<Customer> customerList= customerGenerator.generateCustomerList(20);
        Random random = new Random();


        for (int i = 0; i < 100; i++) {

            List<Product> orderProductsList = new ArrayList<>();

            int productsAmount = random.nextInt(11) + 10;

            for (int j = 0; j < productsAmount; j++) {
                orderProductsList.add(productList.get(random.nextInt(productList.size())));
            }

            int year = 2021;
            int month = random.nextInt(11) + 1;
            int day = random.nextInt(28) + 1;

            LocalDate orderDate = LocalDate.of(year, month, day);
            LocalDate deliveryDate = orderDate.plusDays(2);

            int id = random.nextInt(99999 - 10000) + 10000;
            while (alreadyGeneratedIds.contains(id)) {
                id = random.nextInt(99999 - 10000) + 10000;
            }
            alreadyGeneratedIds.add(id);

            Order order = new Order(
                    id,
                    "Processing",
                    orderDate,
                    deliveryDate,
                    orderProductsList,
                    customerList.get(random.nextInt(customerList.size())));

            orderList.add(order);
        }

        return orderList;
    }
}
