package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.Arrays;

public class MinCostInMazeTraversal {

    /*
        You can only move either down or right at any point in time.
    */
    public static void main(String[] args) {
        int[][] maze = {{1,3,1},
                        {1,5,1},
                        {4,2,1}};

        minCostPath(maze,0,0);

        int[][] dp = new int[maze.length][maze[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int res = minCostPathDP(maze, 0, 0, dp);
        System.out.println(res);

        minCostPathTabulation(maze);
    }

    /*
    📈 Time & Space Complexity:
	•	Time: O(m × n) → visit each cell once
	•	Space: O(m × n) → for the dp array
     */
    private static void minCostPathTabulation(int[][] maze) {

        int n = maze.length;
        int m = maze[0].length;

        int[][] T = new int[n][m];
        T[n-1][m-1] = maze[n-1][m-1];

        for (int i = n-2; i >= 0 ; i--) {   // row
            int j = m-1;    //col
            T[i][j] = T[i+1][j]+maze[i][j];

        }

        for (int j = m-2; j >= 0 ; j--) {   //col
            int i = n-1;    //row
            T[i][j] = T[i][j+1] + maze[i][j];
        }

        for (int i = n-2; i >= 0 ; i--) {
            for (int j = m-2; j >=0 ; j--) {
                T[i][j] = Math.min(T[i+1][j], T[i][j+1]) + maze[i][j];
            }
        }

        System.out.println(T[0][0]);
    }

    /*
    ⏱ Time Complexity (With Memoization):

      O(m × n) → since each cell is visited at most once and stored

    🧠 Space Complexity:
	    •	Recursion stack: O(m + n) (max depth)
	    •	DP array: O(m × n)

     */
    private static int minCostPathDP(int[][] maze, int r, int c, int[][] dp) {

        if(r<0 || c<0 || r>=maze.length || c >= maze[0].length) {
            return Integer.MAX_VALUE;
        }

        if (r == maze.length-1 && c== maze.length-1) {
            return maze[r][c];
        }

        if(dp[r][c]!=-1) {
            return dp[r][c];
        }

        int costDown = minCostPath(maze, r+1, c);
        int costLeft = minCostPath(maze, r, c+1);
        dp[r][c] = maze[r][c]+Math.min(costLeft, costDown);

        return dp[r][c];
    }



    /*
 Time Complexity in Recursive Combinatorial Problems

 	1.	Identify Choices per Step:
Ask yourself — At each step, how many options do I have?
	2.	Count Recursive Calls:
If you have k choices at each step, and n total steps, time complexity can go up to:

O(k^n)



Problem: Count all paths from top-left (0,0) to bottom-right (m-1,n-1) using only right or down.
	•	At each step, you have 2 choices: move right or move down.
	•	To reach the end, you must take exactly (m-1) downs and (n-1) rights in some order.

	O(2^(n+m))



 */
    private static int minCostPath(int[][] maze, int r, int c) {

        if(r<0 || c<0 || r>=maze.length || c >= maze[0].length) {
            return Integer.MAX_VALUE;
        }

        if (r == maze.length-1 && c== maze.length-1) {
            return maze[r][c];
        }

        System.out.println("row : "+r+" col : "+c);
        int costDown = minCostPath(maze, r+1, c);
        int costLeft = minCostPath(maze, r, c+1);
        return maze[r][c]+Math.min(costLeft, costDown);
    }
}


