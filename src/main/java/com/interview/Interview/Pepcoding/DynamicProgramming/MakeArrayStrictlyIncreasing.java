package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.*;

public class MakeArrayStrictlyIncreasing {

    public static void main(String[] args) {
        int[] a1 = {1,5,3,6,7}, a2 = {4,3,1};
        System.out.println(makeArrayIncreasing(a1,a2));
    }

    private static Map<String, Integer> memo;
    private static final int INF = Integer.MAX_VALUE;

    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        memo = new HashMap<>();

        int result = solve(0, arr1, arr2, Integer.MIN_VALUE);

        return result == INF ? -1 : result;
    }

    private static int solve(int idx, int[] arr1, int[] arr2, int prev) {
        // Base case: processed all elements
        if (idx == arr1.length) {
            return 0;
        }

        // Check memoization
        String key = idx + "," + prev;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int result1 = INF;
        int result2 = INF;

        // Option 1: Keep arr1[idx] if it maintains strictly increasing order
        if (arr1[idx] > prev) {
            result1 = solve(idx + 1, arr1, arr2, arr1[idx]);
        }

        // Option 2: Replace arr1[idx] with smallest valid element from arr2
        // Find first element in arr2 that is > prev using binary search
        int replaceIdx = findUpperBound(arr2, prev);

        if (replaceIdx < arr2.length) {
            result2 = 1 + solve(idx + 1, arr1, arr2, arr2[replaceIdx]);
        }

        int result = Math.min(result1, result2);
        memo.put(key, result);

        return result;
    }

    // Binary search to find first element > target (equivalent to upper_bound in C++)
    private static int findUpperBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
