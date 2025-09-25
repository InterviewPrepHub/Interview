package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.DP;

public class MinimumJumps {

    public static void main(String[] args) {
        int[] arr = {3,3,0,2,1,2,4,2,0,0};
        System.out.println(minJumps(arr));
    }

    private static int minJumps(int[] arr) {
        int n = arr.length;
        Integer[] dp = new Integer[n];
        dp[n-1] = 0;

        for (int i = n-2; i >= 0; i--) {
            int steps = arr[i];
            int min = Integer.MAX_VALUE;

            for (int j = 1; j <= steps && i+j < n; j++) {
                if (dp[i+j] != null && dp[i+j] < min) {
                    min = dp[i+j];
                }
            }

            if (min != Integer.MAX_VALUE) {
                dp[i] = min + 1;
            }
        }

        System.out.println("Minimum Jumps: " + dp[0]);
        return dp[0];
    }
}
