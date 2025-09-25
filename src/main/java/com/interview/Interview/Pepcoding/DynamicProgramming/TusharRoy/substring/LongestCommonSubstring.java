package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.substring;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        String s1 = "abcdaf";
        String s2 = "zbcdf";

        int[][] T = new int[s1.length()+1][s2.length()+1];

        int max = 0;
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[i].length; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    T[i][j] = T[i-1][j-1]+1;
                } else {
                    T[i][j] = 0;
                }
                max = Math.max(max, T[i][j]);
            }
        }
        System.out.println(max);
    }
}
