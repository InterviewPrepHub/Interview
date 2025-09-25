package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
we have floor M m wide an N m length [MXN]
we have tiles which are Mm length and 1 m wide [MX1]
how many ways can you tile up the floor.
 */
public class TilingDominoesII {

    public static void main(String[] args) {

        int m = 5;
        int n = 1;

        int[] dp = new int[n+1];

        for (int i = 1; i <=n ; i++) {
            if(i<m) {
                dp[i] = i;
            } else if(i==m) {
                dp[i] = 2;
            } else {
                //dp[i-1] -> tile kept vertically
                //dp[i-m] -> tile kept horizontally
                dp[i] = dp[i-1] + dp[i-m];
            }
        }

    }
}
