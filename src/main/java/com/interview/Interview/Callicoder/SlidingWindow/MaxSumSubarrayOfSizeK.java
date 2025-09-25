package com.interview.Interview.Callicoder.SlidingWindow;

/*
Given an array of positive integers, and a positive number k, find the maximum sum of any contiguous subarray of size k.

Example 1

Input: [3, 5, 2, 1, 7], k=2
Output: 8
Explanation: Subarray with maximum sum is [1, 7].

Example 2

Input: a[] = {4, 2, 3, 5, 1, 2}, k = 3
Output: 10
Explanation: Subarray with maximum sum is [2, 3, 5]

 */
public class MaxSumSubarrayOfSizeK {

    public int maxSumSubarray(int[] arr, int k) {
        int max_sum = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length-k; i++) {
            int sum = 0;
            for (int j = i; j < i+k; j++) { //{0,1}
                sum += arr[j]; //{3,8}
            }
            max_sum = Math.max(max_sum, sum);
        }
        return max_sum;
    }

    public int maxSumSubarray1(int[] a, int k) {

        int windowStart = 0;
        int windowSum = 0;
        int max_Sum = Integer.MIN_VALUE;

        for (int windowEnd = 0; windowEnd<a.length;windowEnd++) {
            //{4, 2, 3, 5, 1, 2}     k = 3
            //{4,2,3}, {2,3,5}
            windowSum += a[windowEnd];
            if (windowEnd-windowStart+1 == k) {
                max_Sum = Math.max(max_Sum, windowSum);
                windowSum = windowSum - a[windowStart];
                windowStart++;
            }
        }
        return max_Sum;
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 2, 1, 7};
        int k = 2;
        MaxSumSubarrayOfSizeK m = new MaxSumSubarrayOfSizeK();
        int res1 = m.maxSumSubarray(a,k);
        System.out.println(res1);

        int res2 = m.maxSumSubarray1(a,k);
        System.out.println(res2);
    }
}
