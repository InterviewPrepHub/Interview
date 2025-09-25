package com.interview.Interview.Callicoder.BinarySearch;

/*
Given an array of distinct numbers sorted in ascending order. The array has been rotated k-times (clockwise) around a pivot, find the value of k.

Example 1:

Input: a = [5, 7, 9, 1, 3]
Output: 3
Explanation: The original array must be [1, 3, 5, 7, 9] and it was rotated 3 times.

Example 2:

Input: a = [4, 6, 8, 10, 11, 0, 1, 2]
Output: 4
Explanation: The original array would be [0, 1, 2, 4, 6, 8, 10] and it was rotated 4 times.

Example 3:

Input: nums = [5, 10, 15, 20]
Output: 0
Explanation: The array has not been rotated.

 */
public class RotationCountInRotatedSortedArray {

    public static void main(String[] args) {

        int[] a = {5, 7, 9, 1, 3};

        int low = 0, high = a.length-1;

        while(low <= high) {

            int mid = low+(high-low)/2;

            if(a[mid] < a[mid-1]) {
                System.out.println(mid);
                break;
            }

            if(a[mid] > a[mid+1]) {
                System.out.println(mid+1);
                break;
            }

            //check id left array is sorted
            if(a[low] < a[mid]) {
                low = mid+1;
            } else { //check if right array is sorted
                high = mid-1;
            }
        }

    }
}
