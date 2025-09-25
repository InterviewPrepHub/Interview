package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given an array A having positive and negative integers and a number k, find the minimum length sub array of A with sum = k.

 */
public class MinimumLenSubArrayWithSumK {

    public static void main(String[] args) {
        int arr1[] = {2, 4, 6, 10, 2, 1}, K1 = 12;

        int arr2[] = {-8, -8, -3, 8}, K2 = 5;

        minLenSubArray(arr1, K1);
    }

    private static void minLenSubArray(int[] arr1, int k1) {

        int min = Integer.MAX_VALUE;
        int start = -1, end = -1;

        for (int i = 0; i < arr1.length; i++) {
            int curr_sum = 0;
            for (int j = i; j < arr1.length && (j-i+1) < min; j++) {
                curr_sum += arr1[j];
                if(curr_sum == k1) {
                    start = i;
                    end = j;
                    min = end - start + 1;
                    break;
                }
            }
        }

        System.out.println(min);

        while(start<=end) {
            System.out.print(arr1[start] + " ");
            start++;
        }
    }
}
