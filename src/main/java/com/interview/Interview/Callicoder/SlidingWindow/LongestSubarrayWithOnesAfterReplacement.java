package com.interview.Interview.Callicoder.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/*
Given an array A of 0's and 1's, and a value K which indicates that you may change up to K values from 0 to 1.
Return the length of the longest (contiguous) subarray that contains only 1’s.

Example 1:

Input: A = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], K = 2
Output: 6

To obtain the longest contiguous subarray of 1s, you can flip the 0s at index 5 and 10 to 1s:
[1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1]
Example 2:

Input: A = [0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k=3
Output: 9
Explanation: Replace the 0s at index 6, 9, and 10 with 1s to get the longest contiguous subarray of 1s.
 */
public class LongestSubarrayWithOnesAfterReplacement {

    public static void main(String[] args) {

        int[] a = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        Map<Integer, Integer> map = new HashMap<>();

        int maxOnes = Integer.MIN_VALUE;
        int numReplacements = 0;
        int windowStart = 0;

        for(int windowEnd = 0; windowEnd < a.length; windowEnd++) {
            if(a[windowEnd] == 0) {
                numReplacements++;
            }

            while(numReplacements > k) {
                if(a[windowStart] == 0) {
                    numReplacements--;
                }
                windowStart++;
            }

            maxOnes = Math.max(maxOnes, windowEnd-windowStart+1);
        }

        System.out.println(maxOnes);
    }

    private static int countZeros(Map<Integer, Integer> map) {
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int digit = entry.getKey();

            if(digit == 0) {
                count++;
            }
        }
        return count;


    }
}
