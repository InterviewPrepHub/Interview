package com.interview.Interview.TusharRoy.DynamicProgramming;

public class CuttingRodToMaxProfit {
 /*
            0   1   2   3   4   5
    2(1)    0   2   4   6   8   10  ---> if you have a rod of length 1 and you can cut it only into 1 piece -> max profit = 2
    5(2)    0   2   5   7   10  12
    7(3)    0   2   5   7   10  12
    8(4)    0   2   5   7   10  12

    if you have a rod of length 3 and you can cut it only into 1 piece or 2 pieces
    T[i][j] = T[i][j-price[i]] + price[i]
*/
    public static void main(String[] args) {
        int n = 5; // rod length of 5
        int[] price = {2,5,7,8};
        
        int[][] T = new int[price.length][n+1];

        //base case
        for (int j = 0; j < price.length; j++) {
            T[0][j] = price[0] * j; //using only pieces of length 1
        }

        for (int i = 1; i < price.length; i++) {
            for (int j = 1; j <=n; j++) {
                if(j < i+1) {
                    T[i][j] = T[i-1][j];
                } else {
                    T[i][j] = Math.max(T[i-1][j], T[i][j-(i+1)]+price[i]);
                }

            }
        }
        System.out.println(T[price.length-1][n]);
    }
}
