package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.Scanner;

public class NQueens {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] chess = new int[n][m];

        nQueen(chess, "", 0);
    }

    private static void nQueen(int[][] chess, String psf, int row) {

        if (row == chess.length){
            System.out.println(psf);
            return;
        }

        for (int col = 0; col < chess[0].length; col++) {
            if(isQueenSafe(chess, row, col)) {
                chess[row][col] = 1;
                nQueen(chess, psf+row+"-"+col, row+1);
                chess[row][col] = 0;
            }
        }
    }

    private static boolean isQueenSafe(int[][] chess, int row, int col) {

        for (int i = row-1, j=col; i >= 0 ; i++) {
            if (chess[i][j] == 1) {
                return false;
            }
        }
        for (int i = row-1, j=col-1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 1) {
                return false;
            }
        }
        for (int i = row-1, j=col+1; i >= 0 && j<= chess.length ; i--, j++) {
            if (chess[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
