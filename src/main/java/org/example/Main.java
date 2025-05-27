package org.example;

import org.example.functions.*;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Create the actual implementations (not mocks) for production
        Sin sin = new Sin();
        Cos cos = new Cos();
        Ln ln = new Ln();

        // Initialize the system with real implementations
        SystemFunction system = new SystemFunction(
                new TrigFunctions(sin, cos),
                new LogFunctions(ln)
        );

        try {
            system.exportToCSV("system_results.csv", -2.0, 3.0, 0.1);
            system.getTrigFunctions().getSin().exportToCSV("sin_results.csv", -2.0, 3.0, 0.1);
            system.getTrigFunctions().getCos().exportToCSV("cos_results.csv", -2.0, 3.0, 0.1);
            system.getLogFunctions().getLn().exportToCSV("ln_results.csv", 0.1, 3.0, 0.1);
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
        }
    }
}