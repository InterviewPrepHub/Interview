package com.interview.Interview.Callicoder.BinarySearch;

/*
Given an array of integers a sorted in ascending order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: a = [1, 4, 4, 10, 10, 15, 20], target = 10
Output: [3, 4]
Example 2:

Input: a = [1, 4, 4, 10, 10, 15, 20], target = 15
Output: [5, 5]
Example 3:

Input: a = [1, 4, 4, 10, 10, 15, 20], target = 0
Output: [-1, -1]
 */
public class
FirstAndLastPositionOfSortedArray {

    public static void main(String[] args) {

        int[] a = {1, 4, 4, 10, 10, 15, 20};
        int k = 10;

        System.out.println(findFirst(a,k));
        System.out.println(findLast(a,k));

    }

    private static int findFirst(int[] a, int k) {

        int start = 0, end = a.length-1;
        int result = -1;

        while (start<=end) {
            int mid = start+(end-start)/2;

            if(k == a[mid]) {
                result = mid;
                end = mid-1;
            } else if (k < a[mid]) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return result;
    }

    private static int findLast(int[] a, int k) {

        int start = 0, end = a.length-1;

        int result = -1;

        while (start<=end) {
            int mid = start+(end-start)/2;

            if(k == a[mid]) {
                result = mid;
                start = mid+1;
            } else if (k < a[mid]) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return result;
    }
}
