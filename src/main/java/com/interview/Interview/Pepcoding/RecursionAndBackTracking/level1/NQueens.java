package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

public class NQueens {

    public static void main(String[] args) {
        int n = 2;
        printNQueens(new int[n][n], "", 0);
    }

    private static void printNQueens(int[][] chess, String psf, int row) {

        if(chess.length == row) {
            System.out.println(psf);
            return;
        }

        for (int col=0;col<chess.length;col++) {
            chess[row][col] = 1;
            printNQueens(chess, psf+row+"-"+col+",", row+1);
            chess[row][col] = 0;
        }
    }
}
