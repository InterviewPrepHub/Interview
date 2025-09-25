package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.subsequence;

/*
              ____c1==c2____ c(c1m)+c(mc2)+1
             |
 c(c1mc2) ---|
             |___c1!=c2_____ c(c1m)+c(mc2)-c(m)


Aspect	            Recursion	                Dynamic Programming (DP)
Time Complexity	    Exponential O(2^n)	        Polynomial O(n²)
Space Complexity	O(n) (recursion stack)	    O(n²) (DP table)

 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {

//        String s = "abcba";

//        String s = "abccbc";
        String s = "agbdba";
        LCSUsingDP(s);
        LCSUsingRecursion(s);
    }

    private static void LCSUsingRecursion(String s) {

        int res = LCSUsingRecursion(s, 0, s.length()-1);
        System.out.println(res);

    }

    private static int LCSUsingRecursion(String s, int m, int n) {

        if(m==n) {
            return 1;
        }

        if (m+1==n) {
            if(s.charAt(m) == s.charAt(n)) {
                return 2;
            }
        }

        if (s.charAt(m) == s.charAt(n)) {
            return LCSUsingRecursion(s, m+1, n-1) + 2;
        }

        return Math.max(LCSUsingRecursion(s, m+1, n),LCSUsingRecursion(s,m, n-1));
    }

    private static void LCSUsingDP(String s) {
        int[][] T = new int[s.length()+1][s.length()+1];

        for (int i = 0; i< s.length(); i++) {
            T[i][i] = 1;
        }

        for (int len = 2; len <= s.length(); len++) {
            for (int i = 0; i < s.length()-len+1; i++) {
                int j = i+len-1;

                if(len==2 && s.charAt(i) == s.charAt(j)) {
                    T[i][j] = 2;
                } else if (s.charAt(i) == s.charAt(j)) {
                    T[i][j] = T[i+1][j-1] + 2;
                } else {
                    T[i][j] = Math.max(T[i+1][j], T[i][j-1]);
                }
            }
        }

        System.out.println(T[0][s.length()-1]);
    }
}

/*

 */

