package com.interview.Interview.TusharRoy.DynamicProgramming.substring;

public class CountPalindromicSubstring {

    public static void main(String[] args) {
        String s = "banana";
        solve(s);
    }

    // Builds DP, prints count and longest palindromic substring
    private static void solve(String s) {
        if (s == null || s.length() == 0) {
            System.out.println(0);
            System.out.println("");
            return;
        }

        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        int count = 0;
        int bestL = 0, bestR = 0;   // track longest palindrome's bounds
        int bestLen = 1;

        // currLen = length-1 (0 means length 1)
        for (int currLen = 0; currLen < n; currLen++) {
            for (int i = 0, j = i + currLen; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (currLen <= 1) {
                        // length 1 or 2
                        pal[i][j] = true;
                    } else {
                        pal[i][j] = pal[i + 1][j - 1];
                    }
                }

                if (pal[i][j]) {
                    count++;
                    int len = j - i + 1;
                    if (len > bestLen) {
                        bestLen = len;
                        bestL = i;
                        bestR = j;
                    }
                }
            }
        }

        String longest = s.substring(bestL, bestR + 1);
        System.out.println("count = " + count);
        System.out.println("longest = " + longest);
    }
}
