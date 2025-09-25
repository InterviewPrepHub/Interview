package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.DP.pepcoding;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String s1 = "abcd";
        String s2 = "aebd";

        System.out.println(lcs(s1, s2));
    }

//        a   b   c   d   _
//    a   3   2   1   1   0
//    e   2   2   1   1   0
//    b   2   2   1   1   0
//    d   1   1   1   1   0
//    _   0   0   0   0   0

    private static int lcs(String s1, String s2) {
        int n = s1.length()+1;
        int m = s2.length()+1;

        int[][] T = new int[n][m];

        for (int i =T.length-2; i >=0 ; i--) {
            for (int j = T[0].length-2; j >=0 ; j--) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);

                if (c1 == c2) {
                    T[i][j] = 1 + T[i+1][j+1];
                } else {
                    T[i][j] = Math.max(T[i+1][j], T[i][j+1]);
                }
            }
        }
        return T[0][0];
    }
}
