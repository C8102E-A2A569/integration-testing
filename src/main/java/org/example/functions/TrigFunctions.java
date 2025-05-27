package org.example.functions;

public class TrigFunctions {
    private final Sin sin;
    private final Cos cos;

    // Parameterized constructor for dependency injection
    public TrigFunctions(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    // Default constructor that creates its own dependencies
    public TrigFunctions() {
        this(new Sin(), new Cos());
    }

    public Sin getSin() {
        return sin;
    }

    public Cos getCos() {
        return cos;
    }
}