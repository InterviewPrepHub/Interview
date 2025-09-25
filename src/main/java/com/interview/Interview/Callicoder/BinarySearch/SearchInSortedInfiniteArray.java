package com.interview.Interview.Callicoder.BinarySearch;

/*
Input: [2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28. 32, 35], target = 16
Output: 7
Explanation: The target is present at index '7' in the array.

 */
public class SearchInSortedInfiniteArray {

    public static void main(String[] args) {

        int a[] = {2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28, 32, 35}, target = 16;
        int res = search(a, target);
        System.out.println(res);
    }

    public static int search(int[] arr, int target) {

        int low = 0;
        int high = 1;

        //first find the lower and upperbound
        while (target > arr[high]) {
            low = high;
            high = 2 * high;
        }

        while (low<=high) {
            int mid = low + (high-low)/2;

            if(arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return -1;
    }
}
