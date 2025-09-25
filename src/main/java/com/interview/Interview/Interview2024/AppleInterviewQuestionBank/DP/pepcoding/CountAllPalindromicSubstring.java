package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.DP.pepcoding;

public class CountAllPalindromicSubstring {

    public static void main(String[] args) {
        String str = "abccbc";
        int count = countSubString(str);
        System.out.println(count);
    }

    private static int countSubString(String str) {

        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        for (int len = 0; len < str.length(); len++) {
            for (int i = 0, j = len; j<dp.length ; i++, j++) {

                if (len==0) {
                    dp[i][i] = true;
                } else if (len==1) {
                    if (str.charAt(i) == str.charAt(j)) {
                        dp[i][j] = true;
                    }
                } else {
                    if (str.charAt(i) == str.charAt(j) && dp[i+1][j-1] == true) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }

                if (dp[i][j]) {
                    count++;
                }
            }
        }


        return count;
    }
}
