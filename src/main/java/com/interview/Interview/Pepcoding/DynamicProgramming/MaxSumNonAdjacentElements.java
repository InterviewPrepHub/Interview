package com.interview.Interview.Pepcoding.DynamicProgramming;

public class MaxSumNonAdjacentElements {

    public static void main(String[] args) {
        int[] arr = {5,10,10,100,5,6};

        int n = arr.length;

        /*
                    5   10  10  100   5    6
              inc   5   10  15  110   20   116
              exc   0   5   10  15    110  110
         */

        /*int[] inc = new int[n];
        int[] exc = new int[n];

        inc[0] = arr[0];
        exc[0] = 0;

        for (int i = 1; i < n; i++) {
            inc[i] = arr[i] + exc[i-1];
            exc[i] = Math.max(inc[i-1], exc[i-1]);
        }
        System.out.println(inc[n-1]);*/


        int inc1 = arr[0];
        int exc1 = 0;

        for (int i = 1; i < n; i++) {
            int inc2 = exc1+arr[i];
            int exc2 = Math.max(inc1, exc1);

            inc1 = inc2;
            exc1 = exc2;
        }

        int ans = Math.max(inc1, exc1);

        System.out.println(inc1);
    }
}
