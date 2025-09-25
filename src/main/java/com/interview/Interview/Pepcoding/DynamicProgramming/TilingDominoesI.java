package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
we have floor 2 m wide an n m length
we have tiles which are 2m length and 1 m wide
how many ways can you tile up the floor.
 */
public class TilingDominoesI {

    public static void main(String[] args) {
        int n = 4;
        int[] dp = new int[n+1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[n]);

    }
}
