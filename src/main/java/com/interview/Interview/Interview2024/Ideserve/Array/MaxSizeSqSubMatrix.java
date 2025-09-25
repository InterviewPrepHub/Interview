package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given a matrix of dimensions mxn having all entries as 1 or 0, find out the size of maximum size square sub-matrix with all 1s.

 */
public class MaxSizeSqSubMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };

        int maxSize = maxSizeSquareSubMatrix(matrix);
        System.out.println("The size of the maximum size square sub-matrix with all 1s is: " + maxSize);
    }

    private static int maxSizeSquareSubMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int maxSize = 0;

        // Fill the first row and first column of dp matrix
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            maxSize = Math.max(maxSize, dp[i][0]);
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
            maxSize = Math.max(maxSize, dp[0][j]);
        }

        // Fill the rest of the dp matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    // If matrix[i][j] is 1, update dp[i][j] with the minimum of its neighbors + 1
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }

        return maxSize;
    }
}










