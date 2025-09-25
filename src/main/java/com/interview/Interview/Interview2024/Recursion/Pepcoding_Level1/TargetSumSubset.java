package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

public class TargetSumSubset {

    public static void main(String[] args) {
        int[] arr = {10,20,30,40,50};
        int target = 70;

//        sumSubset(arr, 0, "", 0, target);

        sumSubsetUsingDP(arr, target);
    }

    private static void sumSubsetUsingDP(int[] arr, int target) {

        //create DP table
        boolean[][] T = new boolean[arr.length+1][target+1];

        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= target; j++) {
                if(j-arr[i-1] >= 0) {
                    T[i][j] = T[i - 1][j] || T[i - 1][j - arr[i - 1]];

                } else {
                    T[i][j] = T[i-1][j];
                }
            }

        }
        System.out.println(T[arr.length][target]);
    }

    private static void sumSubset(int[] arr, int idx, String set, int sumOfSet, int target) {

        if (idx == arr.length) {
            if(sumOfSet == target) {
                System.out.println(set);
            }
            return;
        }

        sumSubset(arr, idx+1, set+" "+arr[idx], sumOfSet+arr[idx], target);
        sumSubset(arr, idx+1, set, sumOfSet, target);
    }
}
