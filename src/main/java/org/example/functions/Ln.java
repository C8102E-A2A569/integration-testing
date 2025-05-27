package org.example.functions;

public class Ln extends Function {
    private static final double EPSILON = 1e-10;

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm is undefined for x <= 0");
        }

        if (Math.abs(x - 1) < 0.5) {
            double y = x - 1;
            double term = y;
            double sum = term;
            int n = 2;

            while (Math.abs(term) > EPSILON) {
                term = -term * y * (n - 1) / n;
                sum += term;
                n++;
            }

            return sum;
        }
        else {
            int power = 0;
            double normalized = x;

            while (normalized > 1.5) {
                normalized /= 2;
                power++;
            }

            while (normalized < 0.5) {
                normalized *= 2;
                power--;
            }

            double y = normalized - 1;
            double term = y;
            double sum = term;
            int n = 2;

            while (Math.abs(term) > EPSILON) {
                term = -term * y * (n - 1) / n;
                sum += term;
                n++;
            }

            return sum + power * 0.693147180559945; // ln(2) ≈ 0.693...
        }
    }
}