package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

/*
divide n people into k teams such that no team is empty

similar to friends pairing
 */
public class PartitionIntoSubsets {

    /*

    f(n,k) = f(n-1,k) + f(n-1,k-1)

 add 3 to team  [13-2] [1-23]          [12-3]
                    [1-2]   12[2]      12[1]  [12]
                              \        /
                                123[3]
                                n = 123, k = 3


  lets take k = 4 & n = 5 [5 people & 4 teams]
            n----->
          0   1   2   3   4   5
    0     0   0   0   0   0   0
 k  1     0   1   1   1   1   1
    2     0   0   1   3   7   15
    3     0   0   0   1   6   25
    4     0   0   0   0   1   10

  if(k>n) dp[i][j] = 0
  if(k==n) dp[i][j] = 1
  if(k<n) dp[i][j] = k * dp[i][j-1] + dp[i-1][j-1]

     */

    public static void main(String[] args) {

        int n = 5;
        int k = 4;
        int[][] dp = new int[k+1][n+1];

        for (int t = 0; t <= k; t++) {
            for (int p = 0; p <= n ; p++) {
                if (t == 0) {
                    dp[t][p] = 0;
                }
                else if (p == 0) {
                    dp[t][p] = 0;
                }
                else if(t == p) {
                    dp[t][p] = 1;
                }
                else if(t > p) {
                    dp[t][p] = 0;
                }
                else {
                    dp[t][p] = t * dp [t][p-1] + dp[t-1][p-1];
                }
            }
        }

        System.out.println(dp[k][n]);


        /*int n = 5;
        int k = 4;
        int[][] dp = new int[n+1][k+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= k; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < n; i++) {   // row = no of teams
            for (int j = 1; j < k; j++) {   //col = no of people
                if(j > i) {
                    dp[i][j] = 0;
                }
                else if(i==k) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = i * dp[i][j-1] + dp[i-1][j-1];
                }
            }
        }
        System.out.println(dp[n][k]);*/
    }


}
