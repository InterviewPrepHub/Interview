package com.interview.Interview.Pepcoding.DynamicProgramming;

public class ClimbingStairsWithMinMoves {

    public static void main(String[] args) {
        int[] arr = {3,3,0,2,2,3};
        climbStairs(arr);
    }

    private static void climbStairs(int[] arr) {

        int n = arr.length;
        Integer[] dp = new Integer[n+1];

        dp[n] = 0; // at end, no moves needed

        for (int i = n-1; i >= 0; i--) {
            if (arr[i] > 0) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= arr[i] && i+j <= n; j++) {
                    if (dp[i+j] != null) {
                        min = Math.min(min, dp[i+j]);
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
            }
        }

        System.out.println(dp[0]);
        print(dp);

    }

    private static void print(Integer[] dp) {
        System.out.print("[");
        for (int i=0;i<dp.length;i++) {
            System.out.print(dp[i]+",");
        }
        System.out.print("]");
    }


}
