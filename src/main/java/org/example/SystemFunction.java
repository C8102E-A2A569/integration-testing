package org.example;

import org.example.functions.LogFunctions;
import org.example.functions.TrigFunctions;

import java.io.FileWriter;
import java.io.IOException;

public class SystemFunction {
    private final TrigFunctions trigFunctions;
    private final LogFunctions logFunctions;

    public SystemFunction(TrigFunctions trigFunctions, LogFunctions logFunctions) {
        this.trigFunctions = trigFunctions;
        this.logFunctions = logFunctions;
    }


    public double calculate(double x) {
        if (x <= 0) {
            return trigFunctions.getCos().calculate(x) + trigFunctions.getSin().calculate(x);
        } else {
            double ln = logFunctions.getLn().calculate(x);
            double log2 = logFunctions.getLog2().calculate(x);
            double log5 = logFunctions.getLog5().calculate(x);
            double log10 = logFunctions.getLog10().calculate(x);

            if (x == 1.0) {
                return 0.0;
            }

            if (Math.abs(log5) < 1e-10) {
                return 0.0;
            }

            double innerPart = Math.pow(ln * log2, 3) / log5 - log10 * Math.pow(log5, 3);
            return Math.pow(innerPart, 3);
        }
    }


    public TrigFunctions getTrigFunctions() {
        return trigFunctions;
    }

    public LogFunctions getLogFunctions() {
        return logFunctions;
    }

    public void exportToCSV(String filename, double start, double end, double step) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Result\n");

            for (double x = start; x <= end; x += step) {
                double result = calculate(x);
                writer.write(x + "," + result + "\n");
            }
        }
    }
}
