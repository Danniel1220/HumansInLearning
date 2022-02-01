package com.crystal.populate;

import com.crystal.dao.Customer;
import com.crystal.dao.Order;
import com.crystal.dao.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class OrderGeneratorTest {
    OrderGenerator orderGenerator;
    ProductGenerator productGenerator;

    @Before
    public void init() {
        productGenerator = new ProductGenerator();
        List<Product> productList = productGenerator.generateProducts();
        orderGenerator = new OrderGenerator(productList);
    }

    @Test
    public void shouldGenerate5DigitIds() {
        List<Order> orderList = orderGenerator.generateOrders(100, 20);
        orderList.forEach(o -> Assert.assertTrue("Order had invalid id: " + o.getId(),
                o.getId() >= 10000 && o.getId() <= 99999)
        );
    }

    @Test
    public void shouldNotHaveDuplicateIds() {
        List<Order> orderList = orderGenerator.generateOrders(10000, 20);
        List<Long> orderIdsList = orderList.stream().mapToLong(Order::getId).boxed().collect(Collectors.toList());
        Set<Long> orderIdsSet = new HashSet<>(orderIdsList);

        Assert.assertFalse("Found duplicate ids", orderIdsSet.size() < orderIdsList.size());
    }
}
