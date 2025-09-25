package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level2BackTracking;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {
        int[] a = {1,2,4,3};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }

    private static void nextPermutation(int[] a) {

        int n = a.length;   //3

        if (a==null || a.length <= 1) return;

        //find pivot i: rightmost i with a[i] < a[i+1]
        int i = n-2;
        while (i>=0 && a[i] >= a[i+1]) {
            i--;
        }

        if(i >= 0) {
            //find the rightmost j > i with a[j] > a[i]
            int j = n-1;
            while (a[j] <= a[i]) {
                j--;
            }

            swap(a, i, j);  //{1,3,4,2}
        }

        reverse(a, i+1, n-1);   //reverse last two {4,2} --> {1,3,2,4}
    }

    private static void reverse(int[] a, int l, int r) {
        while (l<r) {
            int temp = a[l];
            a[l] = a[r];
            a[r] = temp;
            l++;
            r--;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
