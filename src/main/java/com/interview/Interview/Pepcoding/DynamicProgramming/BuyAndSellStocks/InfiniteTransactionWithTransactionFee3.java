package com.interview.Interview.Pepcoding.DynamicProgramming.BuyAndSellStocks;


/*
      prices[i]     bsp                                     ssp
      10            -10   b1                                0    s1
      15            -10  [-10 b1 or -15 b2]                 2 [15-10-3]  b1s2       //new buy state purane sell state pe banti hai
      17            -10  [-10 b1 or 2-17=-15 b1s2b3]        4 [17-10-3]  b1s3       //ab hum min price late hai jab buy state karte hai
 */
public class InfiniteTransactionWithTransactionFee3 {

    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int bsp = -prices[0]; // holding
        int ssp = 0;          // not holding (cash)

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];

            int nbsp = Math.max(bsp, ssp - price);          // buy or keep holding
            int nssp = Math.max(ssp, bsp + price - fee);    // sell (pay fee) or keep cash

            bsp = nbsp;
            ssp = nssp;
        }
        return ssp; // best profit with no stock at the end
    }

    public static void main(String[] args) {
        int[] price = {10,15,17,20,16,18,22,20,22,20,23,25};
        int fee = 3;
        System.out.println(maxProfit(price, fee));
    }
}
