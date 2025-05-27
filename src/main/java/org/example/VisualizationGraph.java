package org.example;

import org.example.functions.LogFunctions;
import org.example.functions.TrigFunctions;

import javax.swing.*;
import java.io.IOException;

public class VisualizationGraph {
    public static void main(String[] args) {
        TrigFunctions trigFunctions = new TrigFunctions();
        LogFunctions logFunctions = new LogFunctions();
        SystemFunction systemFunction = new SystemFunction(trigFunctions, logFunctions);

        try {
            // Генерация CSV
            trigFunctions.getSin().exportToCSV("sin_results.csv", -2 * Math.PI, 2 * Math.PI, 0.1);
            trigFunctions.getCos().exportToCSV("cos_results.csv", -2 * Math.PI, 2 * Math.PI, 0.1);
            logFunctions.getLn().exportToCSV("ln_results.csv", 0.1, 5.0, 0.1);
            logFunctions.getLog2().exportToCSV("log2_results.csv", 0.1, 5.0, 0.1);
            logFunctions.getLog5().exportToCSV("log5_results.csv", 0.1, 5.0, 0.1);
            logFunctions.getLog10().exportToCSV("log10_results.csv", 0.1, 5.0, 0.1);

            systemFunction.exportToCSV("system_negative.csv", -5.0, 0.0, 0.1);
            systemFunction.exportToCSV("system_positive.csv", 0.1, 5.0, 0.1);
            //Для x <= 0 (от -0.2 до 0 включительно):
            //Будут записаны значения cos(x) + sin(x)
            //Для x > 0 (от 0.01 до 0.2):
            //Будут записаны значения по сложной логарифмической формуле
            //Но если в вычислениях произойдёт ошибка (например, логарифм от нуля или деление на нуль), то такие точки будут пропущены из-за try-catch в методе exportAroundBoundary.
            exportAroundBoundary(systemFunction, "system_boundary.csv", -0.2, 0.2, 0.01);

            System.out.println("CSV files generated successfully.");

            // Построение графиков
            SwingUtilities.invokeLater(() -> {
                FunctionGraph.displayChart("sin_results.csv");
                FunctionGraph.displayChart("cos_results.csv");
                FunctionGraph.displayChart("ln_results.csv");
                FunctionGraph.displayChart("log2_results.csv");
                FunctionGraph.displayChart("log5_results.csv");
                FunctionGraph.displayChart("log10_results.csv");
                FunctionGraph.displayChart("system_negative.csv");
                FunctionGraph.displayChart("system_positive.csv");
                FunctionGraph.displayChart("system_boundary.csv");
            });

        } catch (IOException e) {
            System.err.println("Error generating CSV files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void exportAroundBoundary(SystemFunction system, String filename,
                                             double start, double end, double step) throws IOException {
        try (java.io.FileWriter writer = new java.io.FileWriter(filename)) {
            writer.write("X,Result\n");

            for (double x = start; x <= end; x += step) {
                try {
                    double result = system.calculate(x);
                    writer.write(x + "," + result + "\n");
                } catch (Exception e) {
                    // Skip invalid points
                }
            }
        }
    }
}

