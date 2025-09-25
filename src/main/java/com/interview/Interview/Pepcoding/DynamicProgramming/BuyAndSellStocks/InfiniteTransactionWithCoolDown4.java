package com.interview.Interview.Pepcoding.DynamicProgramming.BuyAndSellStocks;

public class InfiniteTransactionWithCoolDown4 {

    /*                     buy state                    sold state              cooldown state
           prices[i]        bsp                         ssp                     csp
           10             -10 b1                        0                       0
           15             -10 b1[-10 better than 15]    5 b1s2                  0
           17             -10 b1                        7 b1s3                  5 b1s2c3
           20[20-5 = 15]  -10 b1[-10 better than 15]    10 b1s4                 7 b1s3c4
           16             -9 b5 [7-16]                  10 b1s5                 10 b1s4c5
           18             -8 b6                         10 b1s6                 10 b1s5c6
           22             -8 b6                         14 b1s6                 10 b1s5c7
           20             -8 b6                         14 b1s6                 14 b1s5c8
     */

    public static void main(String[] args) {

        int[] price = {10,15,17,20,16,18,22,20,22,20,23,25};
        
        int bsp = -price[0];
        int ssp = 0;
        int csp = 0;

        for (int i = 0; i < price.length; i++) {
            int nbsp = 0;
            int nssp = 0;
            int ncsp = 0;

            nbsp = Math.max(bsp, csp-price[i]);
            nssp = Math.max(ssp, price[i]+bsp);
            ncsp = Math.max(csp, ssp);

            bsp = nbsp;
            ssp = nssp;
            csp = ncsp;
        }

        System.out.println("bsp : "+bsp+" ssp : "+ssp+" csp: "+csp);
        
    }
}
