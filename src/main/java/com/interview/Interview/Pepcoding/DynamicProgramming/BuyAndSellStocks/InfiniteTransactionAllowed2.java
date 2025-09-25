package com.interview.Interview.Pepcoding.DynamicProgramming.BuyAndSellStocks;

/*
Given an array of stock prices, find the maximum profit that can be earned by performing
multiple non-overlapping transactions (buy and sell)

 */
public class InfiniteTransactionAllowed2 {

    public static void main(String[] args) {

        int[] price = {100, 80, 120, 130, 70, 60, 100, 125};

        int profit = 0;

        for (int i = 1; i < price.length; i++) {
            int curr_profit = price[i] - price[i-1];
            if (curr_profit > 0) {
                profit += curr_profit;
            }
        }
        System.out.println(profit);

    }
}
