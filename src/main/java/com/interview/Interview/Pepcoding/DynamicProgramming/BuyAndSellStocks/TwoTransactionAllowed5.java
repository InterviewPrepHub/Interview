package com.interview.Interview.Pepcoding.DynamicProgramming.BuyAndSellStocks;

/*
Best Time to Buy and Sell Stocks - Two Transaction Allowed (Hard) | Dynamic Programming

🎯 Goal:
Maximize total profit from at most two non-overlapping transactions.

We want to split the timeline (days) at every possible point i, and try:

First transaction from day 0 to day i

Second transaction from day i+1 to the end

Then, we choose the best possible split (i.e., the split that gives us the maximum combined profit).

🔁 Why Left to Right?
In the left-to-right pass, for each day i:

We calculate the maximum profit we could have made from day 0 to day i using one transaction.

This simulates doing the first transaction ending on or before day i.

We track:

leftProfits[i] = max(leftProfits[i-1], prices[i] - minPriceSoFar);

🔁 Why Right to Left?
In the right-to-left pass, for each day i:

We calculate the maximum profit we could make from day i to the end using one transaction.

This simulates doing the second transaction starting on or after day i.

We track:

rightProfits[i] = max(rightProfits[i+1], maxPriceSoFar - prices[i]);

🧠 Intuition:
For each day i, we assume:

We make our first buy-sell before or on day i → profit = leftProfits[i]

Then make our second buy-sell on or after day i → profit = rightProfits[i]

Total profit at split i = leftProfits[i] + rightProfits[i]

Then we try all possible i (from 0 to n-1) and take the max of all combinations.

 */
public class TwoTransactionAllowed5 {

    public static void main(String[] args) {

        int[] price = {100, 80, 120, 130, 70, 60, 100, 125};

        int n = price.length;

        if (price == null || price.length < 2) {
            return;
        };

        int min_price = price[0];
        int[] leftProfit = new int[n];
        for (int i = 1; i < n; i++) {
            min_price = Math.min(min_price, price[i]);
            leftProfit[i] = Math.max(leftProfit[i-1], price[i]-min_price);
        }

        int max_price = price[n-1];
        int[] rightProfit = new int[n];
        for (int i = n-2; i >=0 ; i--) {
            max_price = Math.max(max_price, price[i]);
            rightProfit[i] = Math.max(rightProfit[i+1], max_price-price[i]);
        }

        int max_profit = 0;
        for (int i = 0; i < n; i++) {
            max_profit = Math.max(max_profit, (leftProfit[i] + rightProfit[i]));
        }

        System.out.println(max_profit);

    }

}
