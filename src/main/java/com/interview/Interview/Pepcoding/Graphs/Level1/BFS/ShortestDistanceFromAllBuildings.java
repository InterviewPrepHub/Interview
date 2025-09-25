package com.interview.Interview.Pepcoding.Graphs.Level1.BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
You’re given an m x n grid where:

0 = empty land (you can walk through),
1 = building (cannot walk through),
2 = obstacle (cannot walk through).

You want to build a house on empty land such that it reaches all shortest total travel distance.
You may move up/down/left/right (4-directional) through cells with 0.
Return the shortest travelled distance for such a house. If it is not possible to build such a house
according to above rules then return -1.

Pick an empty land cell 0 such that the sum of shortest path distances from that cell to all buildings (1) is minimum.
A path cannot pass through buildings or obstacles; only through 0s.
If no empty land cell can reach every building, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

Output: the minimum total distance (integer) or -1 if impossible.

 */
public class ShortestDistanceFromAllBuildings {

    //Solution : https://www.youtube.com/watch?v=LBZJdtDZmVw
    public static void main(String[] args) {

        int[][] input = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(shortestDistance(input));

    }

    private static int shortestDistance(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        int[][] reach = new int[rows][cols];

        int totalBuildings = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, reach, dist);
                    totalBuildings++;
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (reach[i][j] == totalBuildings && dist[i][j] < minDist) {
                    minDist = dist[i][j];
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    /*
        1,0,2,0,1
        0,0,0,0,0
        0,0,1,0,0

     */

    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
    private static void bfs(int[][] grid, int row, int col, int[][] reach, int[][] dist) {

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        q.add(new int[]{row,col});
        visited[row][col] = true;

        int d = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            d++;
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();

                for (int[] dir : DIRS) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (!isValid(grid, nr, nc, rows, cols, visited)) {
                        continue;
                    }

                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    reach[nr][nc]++;
                    dist[nr][nc] += d;
                }
            }
        }

    }

    private static boolean isValid(int[][] grid, int nr, int nc, int rows,  int cols, boolean[][] visited) {
        if(nr < 0 || nr > rows-1 || nc <0 || nc > cols-1) {
            return false;
        }

        if (visited[nr][nc]) return false; //if we have visited before
        if (grid[nr][nc] != 0) return false;    //can only walk on empty land
        return true;
    }
}
