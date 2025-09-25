package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph.ConnectedSets;

/*
Given a 2–d matrix , which has only 1’s and 0’s in it. Find the total number of connected sets in that matrix.
 */

public class ConnectedSets {

    public static void main(String[] args) {
        int[][] set = {{0, 0, 1, 0},
                       {1, 0, 1, 0},
                       {0, 1, 0, 0},
                       {1, 1, 1, 1}};

        int res = findConnectedSets(set);
        System.out.println(res);
    }

    private static int findConnectedSets(int[][] set) {
        int count = 0;
        int n = set.length;
        int m = set[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (set[i][j] == 1 && !visited[i][j]) {
                    checkNeighbours(i,j,visited, set);
                    count++;
                }
            }
        }
        return count;
    }

    private static void checkNeighbours(int i, int j, boolean[][] visited, int[][] set) {

        if(i<0 || i>=set.length || j<0 || j>=set.length || set[i][j]==0 || visited[i][j]==true) {
            return;
        }

        visited[i][j] = true;

        checkNeighbours(i+1, j, visited, set); //bottom
        checkNeighbours(i-1, j, visited, set); //top
        checkNeighbours(i, j+1, visited, set); //right
        checkNeighbours(i, j-1, visited, set); //left

    }
}
