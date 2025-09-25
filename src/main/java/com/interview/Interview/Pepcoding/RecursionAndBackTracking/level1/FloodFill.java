package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

public class FloodFill {

    public static void main(String[] args) {
        int[][] arr = {{0,1,0,0,0,0,0},
                       {0,1,0,1,1,1,0},
                       {0,0,0,0,0,0,0},
                       {1,0,1,1,0,1,1},
                       {1,0,1,1,0,1,1},
                       {1,0,0,0,0,0,0}};

        int r = arr.length;
        int c = arr[0].length;
        boolean[][] visited = new boolean[r][c];
        floodFill(arr, 0, 0, "", visited);
    }

    private static void floodFill(int[][] arr, int row, int col, String psf, boolean[][] visited) {

        if(row < 0 || col < 0 || row == arr.length || col == arr[0].length || arr[row][col] == 1 || visited[row][col] == true) {
            return;
        }

        if(row == arr.length-1 && col == arr[0].length-1) {
            System.out.println("path : "+psf);
            return;
        }

        visited[row][col] = true;
        floodFill(arr, row-1, col, psf+"t", visited);   //top
        floodFill(arr, row, col-1, psf+"l", visited);   //left
        floodFill(arr, row+1, col, psf+"d", visited);   //down
        floodFill(arr, row, col+1, psf+"r", visited);   //right
        visited[row][col] = false;
    }
}
