package com.crystal.run;

import com.crystal.queue.IntegerQueueInterface;

public class Run {
    public static void main(String[] args) {
        IntegerQueueInterface queue = new IntegerQueueInterface();
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
