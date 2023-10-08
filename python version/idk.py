import csv
import random
import time

def bubblesort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n-i-1):
            if arr[j] > arr[j+1] :
                arr[j], arr[j+1] = arr[j+1], arr[j]

def quicksort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quicksort(left) + middle + quicksort(right)

def generate_random_array(size):
    return [random.randint(0, 100) for _ in range(size)]

def measure_time(sort_function, arr):
    start_time = time.time()
    sort_function(arr)
    return time.time() - start_time

bubble_times = []
quick_times = []

for _ in range(1000):
    arr_size = random.randint(10, 10000)
    arr = generate_random_array(arr_size)

    bubble_arr = arr.copy()
    bubble_time = measure_time(bubblesort, bubble_arr)
    bubble_times.append((arr_size, bubble_time))

    quick_arr = arr.copy()
    quick_time = measure_time(quicksort, quick_arr)
    quick_times.append((arr_size, quick_time))

# Write data to CSV file
with open('sorting_times.csv', 'w', newline='') as csvfile:
    fieldnames = ['Array Size', 'Bubble Sort Time', 'Quick Sort Time']
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
    writer.writeheader()
    for size, bubble_time in bubble_times:
        quick_time = next(time for s, time in quick_times if s == size)
        writer.writerow({'Array Size': size, 'Bubble Sort Time': bubble_time, 'Quick Sort Time': quick_time})
