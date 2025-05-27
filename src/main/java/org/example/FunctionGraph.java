package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class FunctionGraph {

    public static void displayChart(String csvFilePath) {
        XYSeries series = new XYSeries(csvFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // пропускаем заголовок
                    continue;
                }

                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                series.add(x, y);
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV: " + e.getMessage());
            return;
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph: " + csvFilePath,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        JFrame frame = new JFrame("Function Graph - " + csvFilePath);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

