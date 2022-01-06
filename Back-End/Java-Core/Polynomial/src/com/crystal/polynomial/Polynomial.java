package com.crystal.polynomial;

public class Polynomial {
    double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public void add(Polynomial p) {
        if (coefficients.length >= p.getCoefficients().length) {
            for (int i = 0; i < coefficients.length; i++)
            {
                coefficients[i] = coefficients[i] + p.getCoefficients()[i];
            }
        }
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
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) return i;
        }
        return 0;
    }

    public Polynomial getFirstDerivative () {
        double[] firstDerivative = new double[coefficients.length - 1];
        for(int i = coefficients.length - 2; i >= 0; i--) {
            firstDerivative[i] = coefficients[i + 1] * (i + 1);
        }
        return new Polynomial(firstDerivative);
    }

    @Override
    public String toString() {
        String polynomial = "";
        for (int i = coefficients.length - 1; i > 0; i--) {
            // If it's the first coefficient printed and it's a negative value
            if (polynomial.equals("")) {
                if (coefficients[i] > 0) {
                    polynomial = polynomial + coefficients[i] + "x^" + i;
                }
                else if (coefficients[i] < 0) {
                    polynomial = polynomial + " - " + (-coefficients[i]) + "x^" + i;
                }
            }
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
