package com.interview.Interview.Callicoder.PrefixSum;


/*
Given an integer array arr[], find the subarray (containing at least one element) which has the maximum possible sum, and return that sum.
Note: A subarray is a continuous part of an array.

Examples:

Input: arr[] = [2, 3, -8, 7, -1, 2, 3]
Output: 11
Explanation: The subarray [7, -1, 2, 3] has the largest sum 11.

Input: arr[] = [-2, -4]
Output: -2
Explanation: The subarray [-2] has the largest sum -2.

Input: arr[] = [5, 4, 1, 7, 8]
Output: 25
Explanation: The subarray [5, 4, 1, 7, 8] has the largest sum 25.
 */
public class MaxSubarraySum {

    public static void main(String[] args) {
//        int[] arr = {2, 3, -8, 7, -1, 2, 3};

        int[] arr = {5,-3,5};
        maxSubarraySum(arr);

//        printMaxSubarraySum(arr);
    }

    private static void printMaxSubarraySum(int[] arr) {
        int max_so_far = arr[0];
        int max_ending_here = arr[0];
        int start = 0, end = 0, s = 0;

        for (int i = 0; i < arr.length; i++) {
            max_ending_here = max_ending_here + arr[i];

            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }

            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = s+1;
            }
        }

        System.out.println("max_so_far : "+max_so_far+", start : "+start+", end : "+end);

    }

    private static void maxSubarraySum(int[] arr) {
        int max_so_far = arr[0];    //2,5,5,7,7,8,11
        int max_ending_here = arr[0];   //2,5,-3,7,6,8,11

        for (int i = 1; i < arr.length; i++) {
            max_ending_here = Math.max(arr[i], max_ending_here+ arr[i]);     //5,
            max_so_far = Math.max(max_ending_here, max_so_far);             //5
        }

        System.out.println(max_so_far);
    }
}
