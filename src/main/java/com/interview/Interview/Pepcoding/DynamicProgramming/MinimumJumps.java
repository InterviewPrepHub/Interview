package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.ArrayDeque;

public class MinimumJumps {

    public static int jumpDP(int[] arr) {
        int n = arr.length;
        Integer[] dp = new Integer[n];
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int steps = arr[i];
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= steps && i + j < n; j++) {
                if (dp[i + j] != null) {
                    min = Math.min(min, dp[i + j]);
                }
            }
            if (min != Integer.MAX_VALUE) dp[i] = min + 1;
        }
        return dp[0] == null ? Integer.MAX_VALUE : dp[0];
    }


    public static void main(String[] args) {
        int[] arr = {3,3,0,2,1,2,4,2,0,0};
        int k = arr.length;
        Integer[] dp = new Integer[k];

        dp[k-1] = 0;

        for (int i = k-2; i >=0; i--) {
            int steps = arr[i];
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= steps & i+j<k ; j++) {     //steps -> j to steps
                if(dp[i+j] !=null) {
                    min = Math.min(min, dp[i+j]);
                }
            }
            if(min != Integer.MAX_VALUE) {
                dp[i] = min+1;
            }
        }

        System.out.println(dp[0]);

        //print all paths with minimum jumps

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, arr[0], dp[0], 0+""));

        while (q.size()!=0) {
            Pair rem = q.remove();

            for (int j = 1; j <= rem.s && rem.i+j< arr.length; j++) {
                if(dp[rem.i+j] !=null && dp[rem.i+j] < rem.j-1) {

                }
            }
        }
    }

    static class Pair {
        int i;
        int s;
        int j;
        String psf;

        public Pair(int i, int s, int j, String psf) {
            this.i = i;
            this.s = s;
            this.j = j;
            this.psf = psf;
        }
    }
}
