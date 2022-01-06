package com.crystal.polynomial;

public class Polynomial {
    double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public void add(Polynomial p) {
        // If the current array is bigger or equal in size, simply add all the coefficients
        // from argument's array.
        if (coefficients.length >= p.getCoefficients().length) {
            for (int i = 0; i < coefficients.length; i++)
            {
                coefficients[i] = coefficients[i] + p.getCoefficients()[i];
            }
        }
        // If the argument's array is bigger, the field array is too small, so we create a new
        // one equal in size to the argument's array, compute the new coefficients and assign
        // the new array's value to the object's field.
        else {
            double[] newCoefficients = p.getCoefficients();
            for (int i = 0; i < coefficients.length; i++)
            {
                newCoefficients[i] = newCoefficients[i] + coefficients[i];
            }
            coefficients = newCoefficients;
        }
    }

    public void multiply(double d) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i] * d;
        }
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public int getDegree() {
        // Simply iterating through the polynomial array and returning the first non 0 value
        // will return the degree.
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) return i;
        }
        // If we found no non 0 values that means the array is full of zeros and the degree is also 0.
        return 0;
    }

    public Polynomial getFirstDerivative () {
        // Create a new array for the first derivative. Array has one less coefficient.
        double[] firstDerivative = new double[coefficients.length - 1];

        // Iterate through array and compute each coefficient.
        for(int i = firstDerivative.length - 1; i >= 0; i--) {
            firstDerivative[i] = coefficients[i + 1] * (i + 1);
        }

        // Return a new polynomial object containing the first derivative.
        return new Polynomial(firstDerivative);
    }

    @Override
    public String toString() {
        String polynomial = "";
        for (int i = coefficients.length - 1; i > 0; i--) {
            // If it's the first coefficient printed, and it's a negative value
            if (polynomial.equals("")) {
                if (coefficients[i] > 0) {
                    polynomial = polynomial + coefficients[i] + "x^" + i;
                }
                else if (coefficients[i] < 0) {
                    polynomial = polynomial + " - " + (-coefficients[i]) + "x^" + i;
                }
                // Specifically omitting the case when the coefficient is 0, because it should
                // not be printed.
            }
            // Not the first coefficient printed, print as usual
            else {
                if (coefficients[i] > 0) {
                    polynomial = polynomial + " + " + coefficients[i] + "x^" + i;
                }
                else if (coefficients[i] < 0) {
                    polynomial = polynomial + " - " + (-coefficients[i]) + "x^" + i;
                }
            }
        }

        if (coefficients[0] > 0) {
            if (polynomial.equals("")) {
                polynomial = polynomial + coefficients[0];
            }
            else {
                polynomial = polynomial + " + " + coefficients[0];
            }
        }
        else if (coefficients[0] < 0) {
            if (polynomial.equals("")) {
                polynomial = polynomial + coefficients[0];
            }
            else {
                polynomial = polynomial + " - " + (-coefficients[0]);
            }
        }

        return polynomial;
    }
}
