package com.interview.Interview.TusharRoy.DynamicProgramming.subsequence;

public class LongestCommonSubstring {

    public static void main(String[] args) {

        String s1 = "abcdaf", s2 = "zbcdf";     //"bcd"

        int[][] T = new int[s1.length()+1][s2.length()+1];

        int max = 0;
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    T[i][j] = 1+T[i-1][j-1];
                    if (max < T[i][j]) {
                        max = T[i][j];
                    }
                }
            }
        }

        System.out.println(max);
    }
}
