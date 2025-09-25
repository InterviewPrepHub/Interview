package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy;

public class MinEditDistance {

    /*
                    a   b   c   d   e   f
                0   1   2   3   4   5   6
            a   1   0   1   2   3   4   5
            z   2   1   1   2   3   4   5
            c   3   2   2   1   2   3   4
            e   4   3   3   2   2   2   3
            d   5   4   4   3   2   3   3

            here if I had null string which is indicated by zero it would take 5 edits to convert
            null to "azced"

            3 in the end which is T[n][n] which means it take 3 edits to abcdef to azced

            if(s1.charAt(i) == s2.charAt(j)) {

                T[i][j] = T[i-1][j-1]; ---> get the diagonal value
            } else {
                T[i][j] = Math.min(T[i-1][j], T[i-1][j-1], T[i][j-1]);
            }


     */

    public static void main(String[] args) {

        String s1 = "abcdef";
        String s2 = "azced";
        int[][] T = new int[s1.length()+1][s2.length()+1];

        for (int i = 0; i < T[0].length; i++) {
            T[0][i] = i;
        }

        for (int i = 0; i < T.length; i++) {
            T[i][0] = i;
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[i].length; j++) {
                if(s1.charAt(i-1) != s2.charAt(j-1)) {
                    T[i][j] = Math.min(Math.min(T[i][j-1], T[i-1][j-1]), T[i-1][j]) + 1;
                } else {
                    T[i][j] = T[i-1][j-1];
                }
            }
        }

        System.out.println(T[s1.length()][s2.length()]);
    }
}
