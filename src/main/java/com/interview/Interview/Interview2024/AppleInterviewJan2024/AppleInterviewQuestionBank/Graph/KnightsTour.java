package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph;

public class KnightsTour {

    public static void main(String[] args) {

        int r = 2;
        int c = 3;
        int[][] chess = new int[5][5];

        printKnightsTour(chess, r, c, 1);
    }

    public static void printKnightsTour(int[][] board, int row, int col, int move) {
        if (row < 0 || col < 0 || row>=board.length || col >= board[0].length || board[row][col] > 0){
            return;
        } else if (move == board.length * board.length) {
            board[row][col]  = move;
            displayBoard(board);
            board[row][col] = 0;
            return;
        }

        board[row][col] = move;
        printKnightsTour(board, row-2, col+1, move+1);
        printKnightsTour(board, row-1, col+2, move+1);
        printKnightsTour(board, row+1, col+2, move+1);
        printKnightsTour(board, row+2, col+1, move+1);
        printKnightsTour(board, row+2, col-1, move+1);
        printKnightsTour(board, row-1, col+2, move+1);
        printKnightsTour(board, row+1, col-2, move+1);
        printKnightsTour(board, row+2, col-2, move+1);
        board[row][col] = 0;
    }

    private static void displayBoard(int[][] board) {

        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                System.out.println(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
