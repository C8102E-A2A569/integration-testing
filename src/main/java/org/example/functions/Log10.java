package org.example.functions;

public class Log10 extends Function {
    private final Ln ln;
    private static final double LN_10 = 2.302585092994046;

    public Log10(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / LN_10;
    }
}