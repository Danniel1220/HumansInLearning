package com.crystal.run;

import com.crystal.polynomial.Polynomial;

public class Run {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new double[] {-1, -2, -3, -4});
        Polynomial p2 = new Polynomial(new double[] {1, 3, 4, 4});
        Polynomial p3 = new Polynomial(new double[] {3, -3, -4, 5, 5});

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2 + "\n");

        p1.addPolynomial(p2);

        System.out.println("p1 + p2: " + p1 + "\n");

        p2.multiplyByValue(3);

        System.out.println("p2 * 3: " + p2 + "\n");
        System.out.println("p1: " + p1);
        System.out.println("p3: " + p3);
        System.out.println("Degree of p1: " + p1.getDegree());
        System.out.println("Degree of p3: " + p3.getDegree());

        p1.addPolynomial(p3);

        System.out.println("p1 + p3: " + p1 + "\n");
        System.out.println("p3: " + p3);
        System.out.println("First derivative of p3: " + p3.getFirstDerivative() + "\n");
        System.out.println("Result for p3 with x=2 is: " + p3.computeResult(2) + "\n");

        Polynomial p4 = new Polynomial(new double[] {0});

        System.out.println("p4: " + p4);
        System.out.println("Degree of p4: " + p4.getDegree());

        p4.multiplyByValue(5);

        System.out.println("p4 * 5: " + p4);

        p4.addPolynomial(p3);

        System.out.println("p3: "+ p3);
        System.out.println("p4 + p3: " + p4);
    }
}
