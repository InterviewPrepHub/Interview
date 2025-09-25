package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.Map;

public class TargetSumSubsets {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int k = 60;

        subsets(arr, k);
    }

    private static void subsets(int[] arr, int k) {

//        subsets(arr, 0, "", 0, k);
//        subsets(arr, 0, "", 0, k, new HashMap<>());

        int[] arr1 = {2,3,7,8,10};
        int k1 = 11;
        subsetTabulation(arr1,k1);
    }

    /*

              0   1   2   3   4   5   6   7   8   9   10  11
              T   F   F   F   F   F   F   F   F   F   F   F
         2    T   F   T   F   F   F   F   F   F   F   F   F
         3    T   F   T   T   F   T   F   F   F   F   F   F
         7    T   F   T   T   F   T   F   T   F   T   T   F
         8    T   F   T   T   F   T   F   T   T   F   T   T
         10   T   F   T   T   F   T   F   T   T   F   T   T

     */

    private static void subsetTabulation(int[] arr, int k) {
        boolean T[][] = new boolean[arr.length + 1][k + 1];

        // Fill first column separately
        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= arr[i - 1]) {
                    T[i][j] = T[i - 1][j] || T[i - 1][j - arr[i - 1]];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }

        System.out.println("Subset with sum " + k + " exists? " + T[arr.length - 1][k]);
    }


    private static void subsets(int[] arr, int idx, String set, int sos, int k) {

        if(idx == arr.length) {
            if(k == sos) {
                System.out.println(set);
            }
            return;
        }

//        System.out.println(idx+"| {"+set+"} |"+sos);

        subsets(arr, idx+1, set+arr[idx]+",", sos+arr[idx], k);
        subsets(arr, idx+1, set, sos, k);
    }

    private static void subsets(int[] arr, int idx, String set, int sos, int k, Map<String, Boolean> map) {

        if(idx == arr.length) {
            if(k == sos) {
                System.out.println(set);
            }
            return;
        }

        String key = idx+"|"+sos;
        if (map.containsKey(key)) {
            return;
        }

        subsets(arr, idx+1, set+arr[idx]+",", sos+arr[idx], k, map);
        subsets(arr, idx+1, set, sos, k, map);

        map.put(key, true);
    }
}
