# Algorithm Speed Comparison: Bubble Sort vs. Quick Sort

## Context

In this analysis, I will compare the execution speed of two sorting algorithms: Bubble Sort and Quick Sort. The goal is to demonstrate that Quick Sort outperforms Bubble Sort in terms of execution time for a given dataset.


### Bubble Sort

```java
public class BubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
```
### Peuso code for Bubble Sort:
int array arr
n = length of arr
while i<n & j<(n-i+1){
    if the value in arr at place j > j+1
        int max = j
        increment j
}



###Quick Sort
```java
public class QuickSort {
    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
```
#### Pseudo code for Quick Sort:
function QuickSort(arr, low, high)
    if low < high then
        pi = Partition(arr, low, high)
        QuickSort(arr, low, pi - 1)
        QuickSort(arr, pi + 1, high)

function Partition(arr, low, high)
    pivot = arr[high]
    i = low - 1

    for j = low to high - 1 do
        if arr[j] < pivot then
            i = i + 1
            Swap(arr[i], arr[j])

    Swap(arr[i + 1], arr[high])
    return i + 1


