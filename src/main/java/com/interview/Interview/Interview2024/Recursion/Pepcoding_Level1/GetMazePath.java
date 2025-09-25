package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.ArrayList;
import java.util.Scanner;

public class GetMazePath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<String> res = mazePath(1, 1, n, m);
        System.out.println(res.size());

//        int res1 = countPaths(3, 2);
//        System.out.println(res1);
    }

    /*
    Time complexity
     */

    private static ArrayList<String> mazePath(int sr, int sc, int dr, int dc) {

        if(sc == dc && sr == dr){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        ArrayList<String> hPaths = new ArrayList<>();
        ArrayList<String> vPaths = new ArrayList<>();

        if(sc < dc) {
            hPaths = mazePath(sr, sc+1, dr, dc);
        }

        if (sr < dr) {
            vPaths = mazePath(sr+1, sc, dr, dc);
        }

        ArrayList<String> paths = new ArrayList<>();

        for (String hPath : hPaths) {
            paths.add("h"+hPath);
        }

        for (String vPath : vPaths) {
            paths.add("v"+vPath);
        }

        return paths;
    }


    public static int countPaths(int n, int m) {
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1; // Only one way to get to any cell in the first row or column
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // Ways from above + ways from left
                }
            }
        }

        return dp[n - 1][m - 1]; // Return the count of paths to the bottom-right corner
    }

}
