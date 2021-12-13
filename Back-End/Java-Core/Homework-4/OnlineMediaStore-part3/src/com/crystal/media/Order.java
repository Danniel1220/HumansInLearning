package com.crystal.media;

import com.crystal.disc.DigitalVideoDisc;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<DigitalVideoDisc> orderArr = new ArrayList<DigitalVideoDisc>();

    public void addToOrder(DigitalVideoDisc disc) {
        orderArr.add(disc);
    }

    public List<DigitalVideoDisc> getOrderArr() {
        return orderArr;
    }
}
