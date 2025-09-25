package com.interview.Interview.Pepcoding.DynamicProgramming;

/*

You are given n -> no of houses and k -> no of colors
In the next n rows you are given k spaces separated number which is representing cost of painting nth house with one of the k colours
You are reqd to calculate and paint all houses without painting any consecutive house with the same colour.


n = 4, k = 3 , here k can be more than 3
n    r b g
0    1 5 7
1    5 8 4
2    3 2 9
3    1 2 4

here the k = 3 but k can be more than 3 also like below

1,5,7,2,1,4
5,8,4,3,6,1
3,2,9,7,2,3
1,2,4,9,1,7

here you find the min in all the columns except the one where you are in


TC -> O(n * k^2)
For each house (n) and each color (k), you're checking k-1 colors from the previous house → k * k per house.


 */

public class PaintHouse2 {

    public static void main(String[] args) {
        int n = 4;  // no of houses
        int k = 6;  // no of colours
        int [][] paint = {{1,5,7,2,1,4},{5,8,4,3,6,1},{3,2,9,7,2,3},{1,2,4,9,1,7}};

        int[][] dp = new int[n][k];

        //For the first house, we can use any color — so we just copy the paint costs directly to dp[0][j].
        for (int j = 0;j< k;j++) {
            dp[0][j] = paint[0][j];
        }

        /*
        For each house i and each color j:
        You find the minimum cost from the previous house (i-1) among all colors except j (since you can’t use the
        same color for adjacent houses).
        Then you add the current house's paint cost paint[i][j] to that minimum.

         */
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;

                for (int p=0;p<k;p++) {
                    if(p!=j) {
                        min = Math.min(min, dp[i-1][p]);
                    }
                }
                dp[i][j] = min + paint[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int l = 0; l < k; l++) { //find min of last row
            min = Math.min(dp[n-1][l], min);
        }

        System.out.println(min);
    }
}
