package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

/*
tell if knight travel in a way that it would visit all cells in a chess board such that no cell is visited twice
 */
public class KnightsTour {

    public static void main(String[] args) {

        int[][] chess = new int[8][8];
        printKnightsTour(chess, 0, 0, 1);
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int move) {

        if(r<0 || c<0 || r>= chess.length || c>= chess.length || chess[r][c] > 0) {
            return;
        } else if (move == chess.length * chess.length) {
            chess[r][c] = move;
            display(chess);
            chess[r][c] = 0;
            return;
        }

        chess[r][c] = move;
        printKnightsTour(chess, r-2, c+1, move+1);
        printKnightsTour(chess, r-1, c+2, move+1);
        printKnightsTour(chess, r+1, c+2, move+1);
        printKnightsTour(chess, r+2, c+1, move+1);
        printKnightsTour(chess, r+2, c-1, move+1);
        printKnightsTour(chess, r+1, c-2, move+1);
        printKnightsTour(chess, r-1, c-2, move+1);
        printKnightsTour(chess, r-2, c-1, move+1);

        chess[r][c] = 0;

    }

    public static void display(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                System.out.println(chess[i][j]);
            }
            System.out.println();
        }
    }
}
