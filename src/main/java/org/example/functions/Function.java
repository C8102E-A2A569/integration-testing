package org.example.functions;

import java.io.FileWriter;
import java.io.IOException;

abstract class Function {
    public abstract double calculate(double x);

    public void exportToCSV(String filename, double start, double end, double step) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Result\n");

            for (double x = start; x <= end; x += step) {
                try {
                    double result = calculate(x);
                    writer.write(x + "," + result + "\n");
                } catch (Exception e) {
                    // Skip invalid points
                }
            }
        }
    }
}
