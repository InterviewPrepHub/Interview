package com.interview.Interview.iDeserve;

/*
How it works (quickly)

Your partition puts the pivot in its final sorted spot, with all smaller on the left.
For “k-th largest”, we want the index target = n - k (e.g., k=1 → index n-1).

After each partition:

If p == target → found the element.
If p < target → the target lies to the right.
If p > target → the target lies to the left.

Average O(n) time, O(1) extra space.
 */
public class KthLargestElementInArray {

    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};

        // Demo: k-th largest
        System.out.println(kthLargest(arr, 1)); // 8
        System.out.println(kthLargest(arr, 2)); // 7
        System.out.println(kthLargest(arr, 3)); // 6
        System.out.println(kthLargest(arr, 8)); // 1
    }

    public static int kthLargest(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        int n = arr.length;
        int target = n - k;      // index of k-th largest in ascending order
        int start = 0, end = n - 1;

        while (start <= end) {
            int p = partition(arr, start, end); // same partition as quicksort
            if (p == target) {
                return arr[p];
            } else if (p < target) {
                start = p + 1;   // search right side (bigger elements)
            } else {
                end = p - 1;     // search left side (smaller elements)
            }
        }
        throw new IllegalStateException("Unexpected state");
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int pIndex = start - 1;

        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {     // strictly smaller to the left
                pIndex++;
                swap(arr, i, pIndex);
            }
        }
        swap(arr, pIndex + 1, end);   // put pivot in final position
        return pIndex + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void print(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}
