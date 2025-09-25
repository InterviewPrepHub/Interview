package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy;

public class knapsack {

    public static void main(String[] args) {
        int[] wt = {1,3,4,5};
        int[] val = {1,4,5,7};

        int total = 7;

        solutionRecursive(val, wt, total);
        solutionDp(val, wt, total);
    }

    private static void solutionRecursive(int[] val, int[] wt, int total) {

        int res = solutionRecursive(val, wt, wt.length, total);
        System.out.println(res);
    }

    private static int solutionRecursive(int[] val, int[] wt, int n, int total) {

        if(n == 0 || total == 0) {
            return 0;
        }

        if(wt[n-1] > total) {
            return solutionRecursive(val, wt, n-1, total);
        }

        //here we either pick the wt or dont pick the weight
        int include = solutionRecursive(val, wt, n-1, total-wt[n-1]) + val[n-1];
        int exclude = solutionRecursive(val, wt, n-1, total);

        return Math.max(include, exclude);
    }

    private static void solutionDp(int[] val, int[] wt, int total) {
        int[][] T = new int[wt.length+1][total+1];

        for(int i=0; i <= wt.length; i++){
            for(int j=0; j <= total; j++){
                if(i == 0 || j == 0){
                    T[i][j] = 0;
                    continue;
                }
                if(j - wt[i-1] >= 0){
                    T[i][j] = Math.max(T[i-1][j], T[i-1][j-wt[i-1]] + val[i-1]);
                }else{
                    T[i][j] = T[i-1][j];
                }
            }
        }
        System.out.println(T[wt.length][total]);

    }


}
