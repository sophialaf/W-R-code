import java.util.Random;

public class SortingComparison {
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000); // Adjust range as needed
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr;
        long bubbleTime = 0, quickTime = 0;

        for (int i = 0; i < 1000; i++) {
            arr = generateRandomArray(100); // Adjust array size as needed

            int[] arrCopy = arr.clone(); // Create a copy for fair comparison

            long startTime = System.nanoTime();
            BubbleSort.sort(arrCopy);
            bubbleTime += System.nanoTime() - startTime;

            arrCopy = arr.clone(); // Reset array
            startTime = System.nanoTime();
            QuickSort.sort(arrCopy, 0, arrCopy.length - 1);
            quickTime += System.nanoTime() - startTime;
        }

        // Calculate average sorting times
        long averageBubbleTime = bubbleTime / 1000;
        long averageQuickTime = quickTime / 1000;

        System.out.println("Average Bubble Sort Time: " + averageBubbleTime + " nanoseconds");
        System.out.println("Average Quick Sort Time: " + averageQuickTime + " nanoseconds");
    }
}
