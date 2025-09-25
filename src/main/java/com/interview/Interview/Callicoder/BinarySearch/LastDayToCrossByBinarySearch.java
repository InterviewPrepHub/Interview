package com.interview.Interview.Callicoder.BinarySearch;

import java.util.LinkedList;
import java.util.Queue;

public class LastDayToCrossByBinarySearch {

    /*
        left = 0, right = cells.size()-1;
        mid ---> try filling  (0 to mid) cells val = 1
                |
                |---> Now check with BFS/DFS from the first row [mid+1] with land value
                |---> if reached to the bottom row try with  l = mid+1
     */

    public static int latestDayToCross(int row, int col, int[][] cells) {
        int low = 1, high = cells.length;
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (canCross(row, col, cells, mid)) {
                ans = mid;      // update answer
                low = mid + 1;  // try later days
            } else {
                high = mid - 1; // try earlier days
            }
        }

        return ans;
    }

    // Flood first 'day' cells and check if crossing is still possible using BFS
    private static boolean canCross(int row, int col, int[][] cells, int day) {
        int[][] grid = new int[row][col];

        // Flood first 'day' cells
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1; // water
        }

        // BFS from all land cells in top row
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        for (int c = 0; c < col; c++) {
            if (grid[0][c] == 0) {
                queue.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            if (r == row - 1) return true; // reached bottom

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col &&
                        grid[nr][nc] == 0 && !visited[nr][nc]) {
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int row = 2, col = 2;
        int[][] cells = {
                {1, 1},
                {2, 1},
                {1, 2},
                {2, 2}
        };

        System.out.println("Last day to cross: " + latestDayToCross(row, col, cells)); // Output: 2
    }
}
