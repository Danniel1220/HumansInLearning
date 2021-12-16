package com.crystal.io;

import com.crystal.media.Order;

import java.io.*;

public class OrderSaver {
    public static void saveOrder(Order order, String orderPath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(orderPath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(order);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static Order restoreOrder(String orderPath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(orderPath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Order order = (Order) objectInputStream.readObject();
        objectInputStream.close();
        return order;
    }
}
