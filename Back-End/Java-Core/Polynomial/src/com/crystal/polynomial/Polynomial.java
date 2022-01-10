package com.crystal.polynomial;

public class Polynomial {
    double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public void addPolynomial(Polynomial p) {
        if (coefficients.length >= p.getCoefficients().length) {
            for (int i = 0; i < coefficients.length; i++)
            {
                coefficients[i] = coefficients[i] + p.getCoefficients()[i];
            }
        }
        // Argument's coefficients array is too big for current polynomial
        else {
            double[] newCoefficients = p.getCoefficients();
            for (int i = 0; i < coefficients.length; i++)
            {
                newCoefficients[i] = newCoefficients[i] + coefficients[i];
            }
            coefficients = newCoefficients;
        }
    }

    public void multiplyByValue(double d) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i] * d;
        }
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public int getDegree() {
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) return i;
        }
        // If we found no non 0 values that means the array is full of zeros and the degree is also 0.
        return 0;
    }

    public Polynomial getFirstDerivative () {
        double[] firstDerivative = new double[coefficients.length - 1];

        // Iterate through array and compute each coefficient.
        for(int i = firstDerivative.length - 1; i >= 0; i--) {
            firstDerivative[i] = coefficients[i + 1] * (i + 1);
        }

        return new Polynomial(firstDerivative);
    }

    public double computeResult(double x) {
        double result = 0d;

        for (int i = 0; i < coefficients.length; i++) {
            result = result + (coefficients[i] * Math.pow(x, i));
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuffer polynomialBuffer = new StringBuffer();

        for (int i = coefficients.length - 1; i > 0; i--) {
            // If it's the first coefficient printed
            if (polynomialBuffer.length() == 0) {
                if (coefficients[i] > 0) {
                    polynomialBuffer.append(coefficients[i] + "x^" + i);
                }
                else if (coefficients[i] < 0) {
                    polynomialBuffer.append(" - " + (-coefficients[i]) + "x^" + i);
                }
                // Specifically omitting the case when the coefficient is 0, because it should
                // not be printed.
            }
            else {
                if (coefficients[i] > 0) {
                    polynomialBuffer.append(" + " + coefficients[i] + "x^" + i);
                }
                else if (coefficients[i] < 0) {
                    polynomialBuffer.append(" - " + (-coefficients[i]) + "x^" + i);
                }
                // Specifically omitting the case when the coefficient is 0, because it should
                // not be printed.
            }
        }

        // The iteration does not go all the way to the last value so as not to display the X for it.
        if (coefficients[0] > 0) {
            if (polynomialBuffer.equals("")) {
                polynomialBuffer.append(coefficients[0]);
            }
            else {
                polynomialBuffer.append(" + " + coefficients[0]);
            }
        }
        else if (coefficients[0] < 0) {
            if (polynomialBuffer.equals("")) {
                polynomialBuffer.append(coefficients[0]);
            }
            else {
                polynomialBuffer.append(" - " + (-coefficients[0]));
            }
        }

        // If the polynomial string is still empty, that means all the coefficients are 0 so
        // we return a 0 so as not to print a blank string.
        if (polynomialBuffer.length() == 0) return "0";

        return polynomialBuffer.toString();
    }
}
