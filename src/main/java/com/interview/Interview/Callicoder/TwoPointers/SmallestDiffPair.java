package com.interview.Interview.Callicoder.TwoPointers;

import java.util.Arrays;

/*
Given two non-empty arrays of integers, find the pair of values (one value from each array) with the smallest (non-negative) difference.

Example

Input: [1, 3, 15, 11, 2], [23, 127, 235, 19, 8]

Output: [11, 8]; this pair has the smallest difference.

 */
public class SmallestDiffPair {

    public static void main(String[] args) {
        
        
        int[] a1 = {1, 3, 15, 11, 2};
        int[] a2 = {23, 127, 235, 19, 8};

        Arrays.sort(a1);
        Arrays.sort(a2);

        int n = a1.length;
        int minDiff = Integer.MAX_VALUE;
        
        int i = 0, j = 0;

        while (i<n && j<n) {
            int currDiff = Math.abs(a1[i] - a2[j]);
            minDiff = Math.min(currDiff, minDiff);

            if(a1[i] < a2[j]) {
                i++;
            } else {
                j++;
            }
        }

        System.out.println(minDiff);
    }
}
