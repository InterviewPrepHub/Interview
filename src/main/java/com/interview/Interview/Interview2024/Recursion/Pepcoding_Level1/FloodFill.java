package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.Scanner;

public class FloodFill {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();  //4
//        int m = scanner.nextInt();  //3
        int[][] arr = { {0,1,1},
                        {0,0,1},
                        {1,0,0},
                        {0,1,0}};

        boolean[][] visited = new boolean[arr.length][arr.length];
        floodFill(arr, 0, 0, "", visited);
    }

    private static void floodFill(int[][] arr, int r, int c, String psf, boolean[][] visited) {
        if (r<0 || c<0 || r== arr.length || c==arr[0].length || arr[r][c]==1 || visited[r][c] == true) {
            return;
        }

        if (r == arr.length-1 && c == arr[0].length-1) {
            System.out.println(psf);
            return;
        }

        visited[r][c] = true;
        floodFill(arr, r-1, c, psf+"t", visited);
        floodFill(arr, r+1, c, psf+"d", visited);
        floodFill(arr, r, c-1, psf+"l", visited);
        floodFill(arr, r, c+1, psf+"r", visited);
        visited[r][c] = false;
    }
}
