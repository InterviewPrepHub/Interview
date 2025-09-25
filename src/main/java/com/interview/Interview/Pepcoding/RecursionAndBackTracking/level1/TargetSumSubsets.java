package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

public class TargetSumSubsets {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40 ,50};
        int target = 70;

        sumSubsets(arr, 0, "", 0, target);

    }

    private static void sumSubsets(int[] arr, int idx, String set, int sos, int target) {

        if(idx == arr.length) {
            if(sos == target) {
                System.out.println(set);
            }
            return;
        }

        sumSubsets(arr, idx+1, set + arr[idx] + ",", sos+arr[idx], target);
        sumSubsets(arr, idx+1, set , sos, target);

    }
}

/*
For example, consider the problem of generating all subsets of a set of n elements. The number of subsets of a set is
2^n, which means the time complexity is O(2^n). This is because each element can either be included in a subset or not,
leading to 2^n possible subsets.

Combination Formula nCm

The combination formula nCm (read as "n choose m") is used to calculate the number of ways to choose
m elements from a set of n elements, without regard to the order of selection. The formula is given by:

nCm= n!/m!(n−m)!


 */
