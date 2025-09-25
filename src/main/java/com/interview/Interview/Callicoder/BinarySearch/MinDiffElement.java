package com.interview.Interview.Callicoder.BinarySearch;

/*
Given an array of integers sorted in ascending order, and a target value, find the element in the array that has minimum difference with the target value.

Example 1:

Input: a[] = [2, 5, 10, 12, 15], target = 6
Output: 5
Explanation: The difference between the target value '6' and '5' is the minimum.
Example 2:

Input: a[] = [2, 5, 10, 12, 15], target = 5
Output: 5
Example 3:

Input: a[] = [2, 5, 10, 12, 15], target = 8
Output: 10
Example 4:

Input: a[] = [2, 5, 10, 12, 15], target = 11
Output: 10
Example 5:

Input: a[] = [2, 5, 10, 12, 15], target = 20
Output: 15

 */
public class MinDiffElement {

    public static void main(String[] args) {
        int a[] = {2, 5, 10, 12, 15}, target = 11;
        int res = search(a,target);
        System.out.println(res);
    }

    public static int search(int[] arr, int k) {

        int low = 0, high = arr.length-1, n = arr.length;

        if(k<arr[0]) {
            return arr[0];
        }

        if(k>arr[n-1]) {
            return arr[n-1];
        }

        while(low<=high) {
            int mid = low+(high-low)/2;

            if(k==arr[mid]) {
                return mid;
            } else if (k< arr[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        System.out.println("low: "+low+", high: "+high);

        /*
        at the end of the while loop,
        a[start] is the ceiling of the target [smallest number greater than target]
        a[end] is the floor of the target [largest number smaller than target]

        return element whose difference with target is smaller
         */

        if ((arr[low] - k) < (k - arr[high]))
            return arr[low];
        return arr[high];
    }
}
