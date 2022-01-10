package com.crystal.run;

import com.crystal.queue.IntegerQueue;

public class Run {
    public static void main(String[] args) {
        IntegerQueue queue = new IntegerQueue();
        System.out.println(queue.isEmpty());
        for(int i = 0; i < 10000; i++) {
            queue.add(i);
        }
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        for(int i = 0; i < 5000; i++) {
            queue.remove();
        }
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        for(int i = 0; i < 5000; i++) {
            queue.remove();
        }
        System.out.println(queue);
        System.out.println(queue.isEmpty());
    }
}
