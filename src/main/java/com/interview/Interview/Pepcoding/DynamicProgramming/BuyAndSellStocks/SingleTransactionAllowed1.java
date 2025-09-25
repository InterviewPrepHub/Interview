package com.interview.Interview.Pepcoding.DynamicProgramming.BuyAndSellStocks;

public class SingleTransactionAllowed1 {
/*
find min price at each day and check the profit
 */
    public static void main(String[] args) {

        int[] price = {100, 80, 120, 130, 70, 60, 100, 125};
        int min_price = Integer.MAX_VALUE;
        int profit = 0;

        int n = price.length;
        for (int i=0;i<n;i++) {
            min_price = Math.min(price[i], min_price);
            profit = Math.max(profit, price[i]-min_price);
        }

        System.out.println(profit);
    }
}
