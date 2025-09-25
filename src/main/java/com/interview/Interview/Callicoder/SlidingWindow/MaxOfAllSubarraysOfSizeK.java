package com.interview.Interview.Callicoder.SlidingWindow;

/*
Given an array of integers a, and an integer k, find the maximum for each and every contiguous subarray of size k.

Example

Input: a[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, k = 3
Output: 3 3 4 5 5 5 6
Explanation:
Maximum of subarray {1, 2, 3} is 3
Maximum of subarray {2, 3, 1} is 3
Maximum of subarray {3, 1, 4} is 4
Maximum of subarray {1, 4, 5} is 5
Maximum of subarray {4, 5, 2} is 5
Maximum of subarray {5, 2, 3} is 5
Maximum of subarray {2, 3, 6} is 6

 */
public class MaxOfAllSubarraysOfSizeK {

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, k = 3;

        int windowStart = 0;
        int windowMax = 0;
        int max = Integer.MIN_VALUE;

        for (int windowEnd=0; windowEnd < a.length; windowEnd++) {
            windowMax=Math.max(windowMax, a[windowEnd]);

            if(windowEnd-windowStart+1==k) {
                max = Math.max(max, windowMax);
                windowStart++;
            }
        }

        System.out.println(max);
    }
}
