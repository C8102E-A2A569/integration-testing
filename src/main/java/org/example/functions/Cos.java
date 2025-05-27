package org.example.functions;

public class Cos extends Function {
    private static final double EPSILON = 1e-10;

    @Override
    public double calculate(double x) {
        x = x % (2 * Math.PI);

        double term = 1.0;
        double sum = term;
        int n = 1;

        while (Math.abs(term) > EPSILON) {
            term = -term * x * x / ((2 * n - 1) * (2 * n));
            sum += term;
            n++;
        }

        return sum;
    }
}
