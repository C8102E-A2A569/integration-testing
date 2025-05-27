package org.example.functions;

public class LogFunctions {
    private final Ln ln;
    private final Log2 log2;
    private final Log5 log5;
    private final Log10 log10;

    // Parameterized constructor
    public LogFunctions(Ln ln) {
        this.ln = ln;
        this.log2 = new Log2(ln);
        this.log5 = new Log5(ln);
        this.log10 = new Log10(ln);
    }

    // Default constructor
    public LogFunctions() {
        this(new Ln());
    }

    public Ln getLn() {
        return ln;
    }

    public Log2 getLog2() {
        return log2;
    }

    public Log5 getLog5() {
        return log5;
    }

    public Log10 getLog10() {
        return log10;
    }
}
