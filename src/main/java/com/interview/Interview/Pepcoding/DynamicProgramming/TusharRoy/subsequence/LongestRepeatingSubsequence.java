package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.subsequence;

public class LongestRepeatingSubsequence {

    public static void main(String[] args) {

//        String s = "aabebcdd";
        String s = "abacbc";    // abc, cbc
        int n = s.length();

        int[][] T = new int[n+1][n+1];

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[i].length; j++) {
                if (s.charAt(i-1) == s.charAt(j-1) & i!=j) {
                    T[i][j] = T[i-1][j-1] + 1;
                } else {
                    T[i][j] = Math.max(T[i-1][j], T[i][j-1]);
                }
            }
        }

        System.out.println(T[n][n]);

    }
}

/*
Input: "aabebcdd"

Let’s try to form repeated subsequences:

Subsequence 1: a (index 0), b (index 2), d (index 6) → "abd"
Subsequence 2: a (index 1), b (index 4), d (index 7) → "abd"

They're **same subsequences**, from **different positions**.

Answer: 3
 */
