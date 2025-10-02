package com.interview.Interview.Callicoder.PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers and a target sum, find whether there is a contiguous subarray whose sum is equal to the target.

int[] arr = {1, 4, 20, 3, 10, 5};
int target = 33;

Expected Output:
true (since the subarray [20, 3, 10] sums to 33)

prefix+=arr[i]
need = prefix - target
                       {prefix,i}
i   prefix    need    map[firstIndex]  start
      0                 {0,-1}
0     1        -32      {1,0}
1     5        -28      {5,1}
2     25       -8       {25,2}
3     28       -5       {28,3}
4     38        5       {38,4}         {2,4}
5     43        10      {43,5}

{5, 10, 15, -4, 5} target = 26
                       {prefix,i}
i   prefix    need    map[firstIndex]  start
      0                 {0,-1}
0     5        -21      {5,0}
1     15       -11      {15,1}
2     30        4       {30,2}
3     26        0       {28,3}
4     38        5       {38,4}         {0,4}
 */

public class ContiguousSubArray {

    // Returns [start, end] (inclusive) of a subarray summing to target, or [-1, -1] if none.
    public static int[] findSubarrayWithSum(int[] arr, int target) {
        Map<Long, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0L, -1); // prefix sum 0 "seen" before the array starts

        long prefix = 0;
        for (int i = 0; i < arr.length; i++) {
            prefix += arr[i];
            long need = prefix - target;

            if (firstIndex.containsKey(need)) {
                int start = firstIndex.get(need) + 1;
                return new int[]{start, i};
            }
            // store earliest index for this prefix
            firstIndex.putIfAbsent(prefix, i);
        }
        return new int[]{-1, -1};
    }

    // Helper to print the found subarray
    private static void printSubarray(int[] arr, int[] range) {
        if (range[0] == -1) {
            System.out.println("No subarray found.");
            return;
        }
        System.out.print("Found subarray [" + range[0] + ", " + range[1] + "]: ");
        for (int i = range[0]; i <= range[1]; i++) {
            System.out.print(arr[i] + (i < range[1] ? " " : ""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 4, 20, 3, 10, 5};
        int target1 = 33;
        int[] ans1 = findSubarrayWithSum(arr1, target1);
        printSubarray(arr1, ans1); // Expected: indices [2,4] => 20 3 10

        int[] arr2 = {5, 10, 15, -4};
        int target2 = 26;
        int[] ans2 = findSubarrayWithSum(arr2, target2);
        printSubarray(arr2, ans2); // Expected: [0,3] => 5 10 15 -4
    }
}
