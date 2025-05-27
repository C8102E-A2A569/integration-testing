package org.example.functions;

public class Log2 extends Function {
    private final Ln ln;
    private static final double LN_2 = 0.693147180559945;

    public Log2(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / LN_2;
    }
}