package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank;

public class NoOfIslands {

    public static void main(String[] args) {
        char[][] islands = {{'1', '1', '0', '0', '0'},
                            {'1', '1', '0', '0', '0'},
                            {'0', '0', '1', '0', '0'},
                            {'0', '0', '0', '1', '1'}};

        NoOfIslands n = new NoOfIslands();
        n.islandCount(islands);
    }

    private void islandCount(char[][] islands) {

        int count = 0;
        int m = islands.length;
        int n = islands[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (islands[i][j] == '1' && !visited[i][j]) {
                    drawOtherComponents(islands, i, j, visited);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private void drawOtherComponents(char[][] islands, int i, int j, boolean[][] visited) {
        if (i<0 || j<0 || i>= islands.length || j>=islands[0].length || islands[i][j] == '0' || visited[i][j] == true) {
            return;
        }

        visited[i][j] = true;
        drawOtherComponents(islands,i+1,j,visited);
        drawOtherComponents(islands,i,j+1,visited);
        drawOtherComponents(islands,i,j-1,visited);
        drawOtherComponents(islands,i-1,j,visited);

    }
}
