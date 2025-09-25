package com.interview.Interview.Callicoder.SlidingWindow;

/*
Smallest Subarray with a given sum

Given an array of positive integers a and a positive number K, find the length of the smallest contiguous subarray
whose sum is greater than or equal to K. Return 0 if no such subarray exists.

Variations of the problem

Find the length of the smallest contiguous subarray whose sum is equal to K (Instead of greater than equal to K).
Check out the approach for this variation →
What if the array has negative numbers as well?
Example 1:

Input: a = [2, 1, 4, 3, 2, 5], K = 7
Output: 2
Explanation: The smallest subarray with a sum greater than or equal to '7' is [4, 3]
Example 2:

Input: [3, 4, 1, 1, 6], K = 8
Output: 3
Explanation: Smallest subarrays with sum greater than or equal to '8' are [3, 4, 1] or [1, 1, 6].
Example 3:

Input: a = [1, 3, 2, 1, 5], K = 15
Output: 0
Explanation: No subarray exists with sum greater than or equal to 15

we need to keep the sum in increasing order or a particular order ---> use monotonic stack, queue or dequeue

 */
public class MinSizeSubarraySum {

    public static void main(String[] args) {

        int a[] = {2, 1, 4, 3, 2, 5}, k = 7;

        int windowStart = 0;
        int windowSum = 0;
        int min_len = Integer.MAX_VALUE;

        for (int windowEnd=0; windowEnd<a.length; windowEnd++) {
            windowSum+=a[windowEnd];

            while(windowSum >= k) {
                min_len = Math.min(min_len, windowEnd-windowStart+1);
                windowSum = windowSum - a[windowStart];
                windowStart++;
            }
        }

        System.out.println(min_len);
    }
}
