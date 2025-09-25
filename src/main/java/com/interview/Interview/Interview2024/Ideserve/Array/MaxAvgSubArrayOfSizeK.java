package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given an integer array and a number k, print the maximum average subarray of size k.

 */
public class MaxAvgSubArrayOfSizeK {

    public static void main(String[] args) {
//                   0, 1, 2, 3, 4, 5, 6
        int[] arr = {11,-8,16,-7,24,-2,3};
        int k = 3;
        maxAvgSubArray(arr, k);
    }

    private static void maxAvgSubArray(int[] arr, int k) {

        int start = 0;
        int sum = 0;
        int n = arr.length;
        int max_sum = Integer.MIN_VALUE;

        for (int end = 0; end < n; end++) { //2,3,4
            sum += arr[end];    //19,1,33

            if(end-start+1 == k) {
                //find avg sum
                int avg = sum/k;

                if(avg > max_sum) {
                    max_sum = avg;
                }

                //update sum for next window
                sum = sum - arr[start]; //8,9,
                start++;    //1,2
            }

        }

        System.out.println(max_sum);
    }
}
