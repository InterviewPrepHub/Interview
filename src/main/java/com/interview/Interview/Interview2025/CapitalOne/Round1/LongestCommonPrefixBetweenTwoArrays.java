package com.interview.Interview.Interview2025.CapitalOne.Round1;

import java.util.HashSet;
import java.util.Set;

/*
Given two arrays of integers (firstArray, secondArray), find the length of the longest common prefix (LCP) between
any number from the first array and any number from the second array.

A prefix of a number is its leftmost digits.

Return 0 if no common prefix exists.

Example 1:
firstArray = [25,298,2655,54546,54,555]
secondArray = [12,255,266,244,26,5,54547]
→ Output: 4 (prefix "5454")

Example 2 (your failing test):
firstArray = [25,298,2655,544,54,555]
secondArray = [12,255,266,244,26,5,54444441]
→ Output: 3 (prefix "544")
 */


public class LongestCommonPrefixBetweenTwoArrays {

    // Return length of the longest common prefix across the two arrays
    public static int solution(int[] firstArray, int[] secondArray) {
        Set<String> prefixSet = new HashSet<>();

        // Collect ALL prefixes of firstArray numbers
        for (int x : firstArray) {
            String s = String.valueOf(x);
            for (int k = 1; k <= s.length(); k++) {
                prefixSet.add(s.substring(0, k));
            }
        }

        // For each number in secondArray, try longest prefix first
        int best = 0;
        for (int y : secondArray) {
            String t = String.valueOf(y);
            for (int k = t.length(); k >= 1; k--) {
                if (prefixSet.contains(t.substring(0, k))) {
                    best = Math.max(best, k);
                    break; // no longer prefix possible for this number
                }
            }
        }
        return best;
    }

    // quick test
    public static void main(String[] args) {
        int[] first  = {25, 298, 2655, 544, 54, 555};
        int[] second = {12, 255, 266, 244, 26, 5, 54444441};
        System.out.println(solution(first, second)); // prints 3
    }
}


