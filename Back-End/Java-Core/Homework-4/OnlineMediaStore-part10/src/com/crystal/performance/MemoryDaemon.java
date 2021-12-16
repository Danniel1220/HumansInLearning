package com.crystal.performance;

public class MemoryDaemon implements Runnable {
    @Override
    public void run() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024;
        System.out.printf("Currently using %d kilobytes of memory.\n", usedMemory);
    }
}
