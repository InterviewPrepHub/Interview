package com.interview.Interview.TusharRoy.DynamicProgramming;

public class UnboundedKnapsack {

    public static void main(String[] args) {

        int[] wt = {1,3,4,5};
        int[] val = {1,4,5,7};

        int total = 7;

        int[] dp = new int[total+1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < wt.length; j++) {
                if(wt[j] <= i) {
                    int rbagc = i - wt[j];
                    int rbagv = dp[rbagc];
                    int tbagv = rbagv + val[i];

                    max = Math.max(tbagv, max);
                }
            }
            dp[i] = max;
        }

        System.out.println(dp[total]);

    }
}
