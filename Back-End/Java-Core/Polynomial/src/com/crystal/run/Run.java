package com.crystal.run;

import com.crystal.polynomial.Polynomial;

public class Run {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new double[] {-1, -2, -3, -4});
        Polynomial p2 = new Polynomial(new double[] {1, 3, 4, 4});
        Polynomial p3 = new Polynomial(new double[] {3, -3, -4, 5, 5});
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);

        System.out.println();
        p1.add(p2);
        System.out.println("p1 + p2: " + p1);

        System.out.println();
        p2.multiply(3);
        System.out.println("p2 * 3: " + p2);

        System.out.println();
        System.out.println("p1: " + p1);
        System.out.println("p3: " + p3);
        System.out.println("Degree of p1: " + p1.getDegree());
        System.out.println("Degree of p3: " + p3.getDegree());
        p1.add(p3);
        System.out.println("p1 + p3: " + p1);
        System.out.println();

        System.out.println("p3: " + p3);
        System.out.println("First derivative of p3: " + p3.getFirstDerivative());

    }
}
