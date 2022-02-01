package com.crystal.populate;

import com.crystal.dao.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ProductGeneratorTest {
    ProductGenerator productGenerator;

    @Before
    public void init() {
        productGenerator = new ProductGenerator();
    }

    @Test
    public void shouldGenerate5DigitIds() {
        List<Product> productList = productGenerator.generateProducts();
        productList.forEach(p -> Assert.assertTrue("Product had invalid id: " + p.getId(),
                p.getId() >= 10000 && p.getId() <= 99999)
        );
    }

    @Test
    public void shouldGenerate3DigitPrices() {
        List<Product> productList = productGenerator.generateProducts();
        productList.forEach(p -> Assert.assertTrue("Product had invalid price: " + p.getPrice(),
                p.getPrice() >= 100 && p.getPrice() <= 999)
        );
    }

    @Test
    public void shouldNotHaveDuplicateIds() {
        Random random = new Random();
        List<Product> productList = productGenerator.generateProductCategory(new ArrayList<>(), "Toy",
                "Toys", 1000, random);
        List<Long> productIdsList = productList.stream().mapToLong(Product::getId).boxed().collect(Collectors.toList());
        Set<Long> productIdsSet = new HashSet<>(productIdsList);

        Assert.assertFalse("Found duplicate ids", productIdsSet.size() < productIdsList.size());
    }


}
