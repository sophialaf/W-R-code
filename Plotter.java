import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Plotter extends JPanel {
    private long[] bubbleTimes = new long[1000];
    private long[] quickTimes = new long[1000];

    public Plotter() {
        generateSortingTimes();
    }

    private void generateSortingTimes() {
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            int[] arr = new int[100];
            for (int j = 0; j < 100; j++) {
                arr[j] = rand.nextInt(1000); // Adjust range as needed
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
        }

        long maxTime = Math.max(getMaxValue(bubbleTimes), getMaxValue(quickTimes));
        double xScale = (double) panelWidth / bubbleTimes.length;
        double yScale = (double) panelHeight / maxTime;

        g2d.setColor(Color.BLUE);
        for (int i = 1; i < bubbleTimes.length; i++) {
            int x1 = (int) ((i - 1) * xScale);
            int y1 = (int) (bubbleTimes[i - 1] * yScale);
            int x2 = (int) (i * xScale);
            int y2 = (int) (bubbleTimes[i] * yScale);
            g2d.drawLine(x1, panelHeight - y1, x2, panelHeight - y2);
        }

        g2d.setColor(Color.RED);
        for (int i = 1; i < quickTimes.length; i++) {
            int x1 = (int) ((i - 1) * xScale);
            int y1 = (int) (quickTimes[i - 1] * yScale);
            int x2 = (int) (i * xScale);
            int y2 = (int) (quickTimes[i] * yScale);
            g2d.drawLine(x1, panelHeight - y1, x2, panelHeight - y2);
        }

        // Draw x-axis title
        g2d.setColor(Color.BLACK);
        g2d.drawString("Size of Input", panelWidth - 70, panelHeight - 5);

        // Draw y-axis title
        g2d.rotate(Math.toRadians(-90));
        g2d.drawString("Time (ns)", -panelHeight / 2 - 30, 15);
        g2d.rotate(Math.toRadians(90));

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Complexity Plot", panelWidth / 2 - 60, 30);
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
    }
}
