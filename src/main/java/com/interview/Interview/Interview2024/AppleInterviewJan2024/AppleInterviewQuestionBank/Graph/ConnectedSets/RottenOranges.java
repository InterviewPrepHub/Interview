package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph.ConnectedSets;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

[2,1,1]
[0,1,1]
[1,0,1]

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

[2,1,1]
[1,1,0]
[0,1,1]

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
 */
public class RottenOranges {

    public static void main(String[] args) {
        RottenOranges r = new RottenOranges();
        int[][] grid = {{1,2,1},
                        {1,1,0},
                        {0,1,1}};

//        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
        int res = r.orangesRotting(grid);
        System.out.println(res);
    }

    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Count the number of fresh oranges
        int freshOranges = 0;

        // Enqueue rotten oranges and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j}); //create a new array with two elements
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Perform BFS
        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int[] current = queue.poll();

                for (int[] direction : directions) {
                    int newRow = current[0] + direction[0];
                    int newCol = current[1] + direction[1];

                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        freshOranges--;
                    }
                }
            }

            minutes++;
        }

        return freshOranges == 0 ? minutes : -1;
    }

}
