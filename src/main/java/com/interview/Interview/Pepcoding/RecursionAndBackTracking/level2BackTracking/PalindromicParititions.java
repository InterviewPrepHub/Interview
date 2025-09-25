package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level2BackTracking;

//https://www.youtube.com/watch?v=lDYIvtBVmgo

/*

Given a string, how many minimum splits would it take so that each partition after split is a palindrome

            a   b   c   b   m
            0   1   2   3   4
       0    0   1   2   1   2
       1        0   1   0   1
       2            0   1   2
       3                0   1
       4                    0

       for len = 3, split
       a | b  c -> 1+T[0][0]+T[1][2] ->  2
       a  b | c -> 1+T[0][1]+T[2][2] ->  2

       for len = 4, split
       0 | 1 2 3
       a | b c b

       1+T[0][0]+T[1][3] = 1+0+0 = 1

       0 1 | 2 3
       a b | c b

       1+T[0][1]+T[2][3] = 1+1+1 = 3

       0 1 | 2 3
       a b | c b

       1+T[0][1]+T[2][3] = 1+1+1 = 3

       0 1 2 | 3
       a b c | b

       1+T[0][2]+T[3][3] = 1+2+0 = 3

       hence the best split is

       0 | 1 2 3
       a | b c b

 */

public class PalindromicParititions {

    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[][] dp = new int[n][n];

        // Step 1: Precompute palindromes
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || isPalindrome[i + 1][j - 1]) {
                        isPalindrome[i][j] = true;
                    }
                }
            }
        }

        // Step 2: Fill DP table
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (isPalindrome[i][j]) {
                    dp[i][j] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j] + 1);
                    }
                    dp[i][j] = min;
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "abcbmp";
        System.out.println("Minimum cuts: " + minCut(s)); // Output: 2
    }
}
