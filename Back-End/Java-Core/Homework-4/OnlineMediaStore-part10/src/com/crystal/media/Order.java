package com.crystal.media;

import com.crystal.dao.DigitalVideoDisc;
import com.crystal.dao.Media;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private List<Media> orderArr = new ArrayList<Media>();

    public void addMedia(Media mediaItem) {
        orderArr.add(mediaItem);
    }

    public List<Media> getOrderArr() {
        return orderArr;
    }
}
