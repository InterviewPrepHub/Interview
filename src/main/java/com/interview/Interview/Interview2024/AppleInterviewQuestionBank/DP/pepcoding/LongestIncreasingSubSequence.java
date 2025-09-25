package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.DP.pepcoding;

public class LongestIncreasingSubSequence {

    public static void main(String[] args) {
        int[] arr = {10,22,9,33,21,50,41,60,80,1};
        System.out.println(lis(arr));
    }

    private static int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int max_so_far = 0;
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max_len = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (dp[j] > max_len) {
                        max_len = dp[j];
                    }
                }
            }
            dp[i] = max_len+1;

            if (dp[i] > max_so_far) {
                max_so_far = dp[i];
            }

        }

        return max_so_far;
    }
}
