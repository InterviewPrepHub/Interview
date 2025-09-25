package com.interview.Interview.TusharRoy.DynamicProgramming;

public class EggDroppingProblem {

    /*

                0   1   2   3   4   5   6
            0   0   0   0   0   0   0   0
            1   0   1   2   3   4   5   6
            2   0   1   2   2   3   3   3

            time : O(egg * floor^2)
            space : O(egg * floor)

            |   |   |
            1   2   3
            1 + max(0,1)
            1 + max(1,0)
            1 + max(2,0)


     */
    // T[e][h] = min attempts (worst case) with e eggs and h floors
    static int minAttempts(int eggs, int floors) {
        int[][] T = new int[eggs + 1][floors + 1];

        // Base cases
        for (int e = 1; e <= eggs; e++) {
            T[e][0] = 0; // 0 floors -> 0 attempts
            T[e][1] = 1; // 1 floor  -> 1 attempt
        }
        for (int h = 1; h <= floors; h++) {
            T[1][h] = h; // 1 egg -> linear scan
        }

        // Fill DP
        for (int e = 2; e <= eggs; e++) {
            for (int h = 2; h <= floors; h++) {
                int best = Integer.MAX_VALUE;
                for (int x = 1; x <= h; x++) {
                    // drop from floor x
                    int worst = 1 + Math.max(T[e - 1][x - 1], T[e][h - x]);
                    if (worst < best) best = worst;
                }
                T[e][h] = best;
            }
        }

        return T[eggs][floors];
    }

    public static void main(String[] args) {
        int n = 2; // eggs
        int f = 6; // floors
        System.out.println(minAttempts(n, f)); // -> 3
    }
}
