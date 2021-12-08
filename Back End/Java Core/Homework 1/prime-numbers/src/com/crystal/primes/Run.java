package com.crystal.primes;

public class Run {
    public static void main(String[] args) {
        for(int i = 0; i<=10000; i ++) {
            boolean isPrime = true;
            for(int j = 2; j <= i/2; j++) {
                if (i % j == 0) isPrime = false;
            }
            if(isPrime) System.out.println(i);
        }
    }
}
