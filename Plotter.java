import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Plotter extends JFrame {
    private JFreeChart chart;

    public Plotter(String title) {
        super(title);
    }

    public void createChart(CategoryDataset dataset) {
        chart = ChartFactory.createBarChart(
                "Sorting Comparison",
                "Algorithm",
                "Time (ns)",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        Plotter plotter = new Plotter("Sorting Comparison");
        CategoryDataset dataset = createDataset(); // Implement createDataset() method
        plotter.createChart(dataset);
        plotter.pack();
        plotter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plotter.setVisible(true);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data points (average sorting times) for bubble and quick sort

        return dataset;
    }
}
