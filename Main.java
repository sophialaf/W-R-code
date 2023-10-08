
public class Main {
    public static void main(String[] args) {
        int[] dataset = {5, 2, 7, 1, 8, 3};

        // Measure time for Bubble Sort
        long startTimeBubble = System.nanoTime();
        BubbleSort.sort(dataset.clone());
        long endTimeBubble = System.nanoTime();
        long timeElapsedBubble = endTimeBubble - startTimeBubble;

        // Measure time for Quick Sort
        long startTimeQuick = System.nanoTime();
        QuickSort.sort(dataset.clone(), 0, dataset.length-1);
        long endTimeQuick = System.nanoTime();
        long timeElapsedQuick = endTimeQuick - startTimeQuick;

        System.out.println("Bubble Sort Time (ns): " + timeElapsedBubble);
        System.out.println("Quick Sort Time (ns): " + timeElapsedQuick);

        if (timeElapsedBubble < timeElapsedQuick) {
            System.out.println("Bubble Sort is faster.");
        } else {
            System.out.println("Quick Sort is faster.");
        }
    }
}
