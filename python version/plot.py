import random
import time
import matplotlib.pyplot as plt


print("1")
def bubblesort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n-i-1):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]

def quicksort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr)//2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quicksort(left) + middle + quicksort(right)


print("check one")

def generate_random_array(size):
    return [random.randint(0, 10) for _ in range(size)]

def measure_time(sort_func, arr):
    start_time = time.time()
    sort_func(arr)
    end_time = time.time()
    return end_time - start_time

print("check two")

def main():
    bubble_times = []
    quick_times = []

    for _ in range(1000):
        arr_size = random.randint(10, 100)
        arr = generate_random_array(arr_size)

        bubble_arr = arr.copy()
        bubble_time = measure_time(bubblesort, bubble_arr)
        bubble_times.append((arr_size, bubble_time))

        quick_arr = arr.copy()
        quick_time = measure_time(quicksort, quick_arr)
        quick_times.append((arr_size, quick_time))
    print("check three")
    bubble_sizes, bubble_timings = zip(*bubble_times)
    quick_sizes, quick_timings = zip(*quick_times)

    plt.figure(figsize=(10, 6))

    plt.scatter(bubble_sizes, bubble_timings, color='blue', label='Bubble Sort')
    plt.scatter(quick_sizes, quick_timings, color='red', label='Quick Sort')

    # Regression lines can be added here using polyfit or other methods


    print("check four")
    plt.xlabel('Size of Input')
    plt.ylabel('Time (s)')
    plt.title('Sorting Algorithm Comparison')
    plt.legend()
    plt.grid(True)
    print("check 5")
    plt.show()

print("done")

if __name__ == "__main__":
    main()
    


main()

