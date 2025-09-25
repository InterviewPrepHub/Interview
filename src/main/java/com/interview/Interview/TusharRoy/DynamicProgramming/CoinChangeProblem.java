package com.interview.Interview.TusharRoy.DynamicProgramming;

public class CoinChangeProblem {

    public static int coinChange(int[] coins, int total) {
        int n = coins.length;
        int[][] T = new int[n + 1][total + 1];

        // Initialize: with 0 coins, impossible to form >0 sum
        for (int j = 1; j <= total; j++) {
            T[0][j] = Integer.MAX_VALUE - 1; // avoid overflow on +1 later
        }

        // Initialize: with any coins, sum=0 requires 0 coins
        for (int i = 0; i <= n; i++) {
            T[i][0] = 0;
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= total; j++) {
                if (coins[i - 1] <= j) {
                    T[i][j] = Math.min(T[i - 1][j], 1 + T[i][j - coins[i - 1]]);
                } else {
                    T[i][j] = T[i - 1][j];
                }
            }
        }

        return T[n][total] == Integer.MAX_VALUE - 1 ? -1 : T[n][total];
    }

    public static void main(String[] args) {
        int[] coins = {1, 5, 6, 8};
        int total = 11;
        System.out.println("Minimum coins needed: " + coinChange(coins, total));
    }

}
