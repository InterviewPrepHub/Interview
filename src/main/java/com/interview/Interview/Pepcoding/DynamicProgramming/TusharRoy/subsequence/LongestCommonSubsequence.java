package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.subsequence;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "acbd";
        
        int[][] T = new int[s1.length()+1][s2.length()+1];
        int max = 0;

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[i].length; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    T[i][j] = 1 + T[i-1][j-1];
                } else {
                    T[i][j] = Math.max(T[i-1][j], T[i][j-1]);
                }
                max = Math.max(max, T[i][j]);
            }
        }
        System.out.println(max);
    }
}

/*
                a   b   c   d
            0   0   0   0   0
        a   0   1   1   1   1
        c   0   1   1   2   2
        b   0   1   2   3   3
        d   0   1   2   3   4
 */
