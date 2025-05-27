package org.example.functions;

public class Log5 extends Function {
    private final Ln ln;
    private static final double LN_5 = 1.6094379124341003;

    public Log5(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / LN_5;
    }
}
