package com.interview.Interview.Pepcoding.DynamicProgramming.BuyAndSellStocks;

public class KTransactionAllowed6 {

    public static void main(String[] args) {

        int[] price = {2,5,7,1,4,3,1,3};
        int k = 3;
        int n = price.length;

        int[][] dp = new int[k+1][n];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, price[j]-price[m]+dp[i-1][m]);
                }
                dp[i][j] = Math.max(dp[i][j-1], maxVal);
            }
        }

        System.out.println(dp[k][n-1]);
    }
}

/*
        price--->
        2  5  7  1  4  3  1  3
K    0
     1
     2
     3
 */