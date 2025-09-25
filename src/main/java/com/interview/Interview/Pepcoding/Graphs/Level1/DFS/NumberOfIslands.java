package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

public class NumberOfIslands {

    public static void main(String[] args) {

        char[][] grid = {
                {'1','1','0','1','0'},
                {'1','1','0','1','0'},
                {'0','0','0','0','0'},
                {'0','1','0','1','1'}
        };

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1' && visited[i][j] == false) {
                    drawTreeForCompenent(grid, i, j, visited);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static void drawTreeForCompenent(char[][] grid, int i, int j, boolean[][] visited) {

        if(i<0 || j<0 || i>= grid.length || j>= grid[0].length || visited[i][j] == true || grid[i][j]=='0') {
            return;
        }

        visited[i][j] = true;
        drawTreeForCompenent(grid, i+1, j, visited);  //down
        drawTreeForCompenent(grid, i-1, j, visited);  //up
        drawTreeForCompenent(grid, i, j-1, visited);  //left
        drawTreeForCompenent(grid, i, j+1, visited);  //right
    }

}
