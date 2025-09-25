package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
n -> number of houses

4 houses -> n
n    r b g
1    1 5 7
2    5 8 4
3    3 2 9
4    1 2 4

here i cannot paint two adjacent houses in same colour

            1   2   3   4
     r      1   10  8   8
     b      5   9   7   10
     g      7   5   18  11

     here the fourth house has r = 8, b = 10 & g = 11 and min of all three is 8
 */
public class PaintHouse {

    public static void main(String[] args) {
        int [][] paint = {{1,5,7},{5,8,4},{3,2,9},{1,2,4}};
        int n = 4;

        int[][] dp = new int[n][3];

        dp[0][0] = paint[0][0];
        dp[0][1] = paint[0][1];
        dp[0][2] = paint[0][2];

        for (int i = 1; i < paint.length; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + paint[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + paint[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + paint[i][2];
        }

        System.out.println(dp[n-1][0]+" "+dp[n-1][1]+" "+dp[n-1][2]);
        System.out.println(dp[n-2][0]+" "+dp[n-2][1]+" "+dp[n-2][2]);
        System.out.println(dp[n-3][0]+" "+dp[n-3][1]+" "+dp[n-3][2]);
        int res = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
        System.out.println(res);
    }
}
