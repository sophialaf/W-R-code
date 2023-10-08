import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Plotter extends JPanel {
    private long[] bubbleTimes = new long[100];
    private long[] quickTimes = new long[100];

    public Plotter() {
        generateSortingTimes();
    }

    private void generateSortingTimes() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int arrSize = rand.nextInt(10000) + 1; // Generate random array size between 1 and 10,000
            int[] arr = new int[arrSize];
            for (int j = 0; j < arrSize; j++) {
                arr[j] = rand.nextInt(100); 
            }

            int[] arrCopy = arr.clone(); // Create a copy for fair comparison

            long startTime = System.nanoTime();
            BubbleSort.bubblesort(arrCopy);
            bubbleTimes[i] = System.nanoTime() - startTime;

            arrCopy = arr.clone(); // Reset array
            startTime = System.nanoTime();
            QuickSort.quicksort(arrCopy, 0, arrCopy.length - 1);
            quickTimes[i] = System.nanoTime() - startTime;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Draw grid lines
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 1; i < 10; i++) {
            int y = panelHeight * i / 10;
            g2d.drawLine(0, y, panelWidth, y);
            int x = panelWidth * i / 10;
            g2d.drawLine(x, 0, x, panelHeight);
        }

        // Calculate max values
        long maxTime = Math.max(getMaxValue(bubbleTimes), getMaxValue(quickTimes));
        int dataSize = bubbleTimes.length;

        // Calculate scales
        double xScale = (double) panelWidth / (dataSize - 1);
        double yScale = (double) (panelHeight - 80) / (2 * maxTime); // Subtracting 80 for padding

        // Draw x-axis markers
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= 10; i++) {
            int x = (int) (i * 1000 * xScale);
            int y = panelHeight - 30;
            g2d.drawLine(x, panelHeight - 30, x, panelHeight - 40);
            String marker = Integer.toString(i * 1000);
            g2d.drawString(marker, x - 5, y + 12);
        }

        // Draw y-axis markers
        for (int i = -100; i <= 100; i += 20) {
            int y = (int) (panelHeight / 2.0 - i * yScale);
            int x = 30;
            g2d.drawLine(30, y, 40, y);
            String marker = Integer.toString(i);
            g2d.drawString(marker, x - 20, y + 5);
        }

        // Draw data points and connect with lines
        g2d.setColor(Color.MAGENTA);
        for (int i = 1; i < dataSize; i++) {
            int x1 = (int) ((i - 1) * xScale);
            int y1 = (int) (panelHeight / 2.0 - bubbleTimes[i - 1] * yScale) - 80;
            int x2 = (int) (i * xScale);
            int y2 = (int) (panelHeight / 2.0 - bubbleTimes[i] * yScale) - 80;
            g2d.drawLine(x1, y1, x2, y2);
            g2d.fillOval(x2 - 2, y2 - 2, 4, 4); // Draw a dot at each data point
        }

        g2d.setColor(Color.GREEN);
        for (int i = 1; i < dataSize; i++) {
            int x1 = (int) ((i - 1) * xScale);
            int y1 = (int) (panelHeight / 2.0 - quickTimes[i - 1] * yScale) - 80;
            int x2 = (int) (i * xScale);
            int y2 = (int) (panelHeight / 2.0 - quickTimes[i] * yScale) - 80;
            g2d.drawLine(x1, y1, x2, y2);
            g2d.fillOval(x2 - 2, y2 - 2, 4, 4); // Draw a dot at each data point
        }

        // Draw x-axis title
        g2d.setColor(Color.RED);
        g2d.drawString("Size of Input", panelWidth - 70, panelHeight - 10);

        // Draw y-axis title
        g2d.setColor(Color.RED);
        g2d.drawString("Time (ns)", 5, 15);

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Complexity Plot", panelWidth / 2 - 60, 30);

        // Draw origin point
        g2d.setColor(Color.RED);
        g2d.fillOval(28, panelHeight / 2 - 82, 4, 4);
    }

    private long getMaxValue(long[] values) {
        long max = Long.MIN_VALUE;
        for (long value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Comparison");
        Plotter comparison = new Plotter();
        frame.add(comparison);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Display the chart
        for (int i = 0; i < 100; i++) {
            System.out.println("Array Size: " + (i + 1) + ", Bubble Sort Time: " + comparison.bubbleTimes[i] + ", Quick Sort Time: " + comparison.quickTimes[i]);
        }
    }
}
