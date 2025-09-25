package com.interview.Interview.TusharRoy.DynamicProgramming.subsequence;

import java.util.ArrayDeque;

/*
        10  22  9   33        21          50     41  60         80        1
       ---------------------------------------------------------------------
        .   10  .   10  9     10  9       10     10  10  10     10  10    .
            22      22  33    21  21      22     22  22  22     22  22
                    33                    33     33  33  33     33  33
                                          50     41  50  41     41  50
                                                     60  60     60  60
                                                                80  80
Total   1   2   1   3         2           4      4   5          6        1
length
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] arr = {10,22,9,33,21,50,41,60,80,1};

        int[] dp = new int[arr.length];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) { //22
            int max = 0;
            for (int j = 0; j < i ; j++) { //10
                if(arr[j] <= arr[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max+1;
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i]+",");
        }

        /*
        	•	Outer loop runs n times.
	        •	Inner loop runs up to i times → in worst case: n iterations.
	        •	So total iterations:
            1 + 2 + 3 + ... + (n - 1) = O(n²)

            ✅ Time Complexity = O(n²)

            ✅ Space Complexity = O(n)
         */

        //print longest increasing subsequence
//        System.out.println();
//        System.out.println("print longest increasing subsequence");
//        pringLIS(omax, omi, arr, dp);

    }

    private static void pringLIS(int omax, int omi, int[] arr, int[] dp) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(omax, omi, arr[omi], arr[omi] + ","));

        while (q.size() > 0) {
            Pair rem = q.removeFirst();

            if(rem.len == 1) {
                System.out.println(rem.psf);
            }

            for (int j = 0; j < rem.idx; j++) {
                if(dp[j] == rem.len-1 && arr[j] <= rem.val ) {
                    q.add(new Pair(dp[j], j, arr[j], arr[j]+","));
                }
            }
        }
    }

    static class Pair {
        int len;
        int idx;
        int val;
        String psf;

        public Pair(int len, int idx, int val, String psf) {
            this.len = len;
            this.idx = idx;
            this.val = val;
            this.psf = psf;
        }
    }
}
