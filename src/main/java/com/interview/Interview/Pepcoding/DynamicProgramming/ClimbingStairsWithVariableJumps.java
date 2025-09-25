package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.Arrays;

public class ClimbingStairsWithVariableJumps {

    public static void main(String[] args) {
        int[] arr = {3,3,0,2,2,3};
        climbStairs(arr);
    }

    private static void climbStairs(int[] arr) {

        int[] dp = new int[arr.length+1];
        Arrays.fill(dp,0);
        int k = dp.length; //7
        dp[k-1] = 1; //dp[6] = 1

        for (int i = arr.length-1; i >=0 ; i--) {
            int steps = arr[i]; // i = 5, steps = 3

            if(steps > 0) {
                for (int j = 1; j <= steps; j++) { //j = 1
                    if(i+j>k-1) {
                        break;
                    }
                    dp[i] = dp[i] + dp[i+j];
                }
            }
        }

        System.out.print("[");
        for (int i=0;i<dp.length;i++) {
            System.out.print(dp[i]+",");
        }
        System.out.print("]");
    }
}
