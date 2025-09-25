package com.interview.Interview.Callicoder.TwoPointers;

/*
Given an array of numbers sorted in increasing order, return a new array containing squares
of all the numbers of the input array sorted in increasing order.

Example 1:

Input: a[] = [-5, -2, -1, 0, 4, 6]
Output: [0, 1, 4, 16, 25, 36]
Explanation: After squaring, the array becomes [25, 4, 1, 0, 16, 36].
After sorting, it becomes [0, 1, 4, 16, 25, 36].
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        int a[] = {-5, -2, -1, 0, 4, 6};

        int n = a.length;

        int[] a1 = new int[n];

        int i=0, j=n-1, right=0, left=0, k = n-1;


        while(i<=j) {
            left = a[i]*a[i];
            right = a[j]*a[j];
            if(left < right) {
                a1[k] = right;
                j--;
            } else {
                a1[k] = left;
                i++;
            }
            k--;
        }

        for (int l = 0; l < n; l++) {
            System.out.println(a1[l]);
        }

    }
}
