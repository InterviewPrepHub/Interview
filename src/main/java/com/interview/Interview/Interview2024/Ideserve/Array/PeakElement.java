package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given an array of size n, find a peak element in the array.
For example:
In Array [1,4,3,6,7,5] 4 and 7 are Peak Elements. Return any one Peak Element.

 */
public class PeakElement {

    public static void main(String[] args) {
        int[] arr = {1,4,3,6,7,5};
        System.out.println(getPeakElement(arr));
    }

    private static int getPeakElement(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if(i-1 >=0 && i+1<= arr.length-1 && arr[i-1] < arr[i] && arr[i+1] < arr[i]) {
                return arr[i];
            }
        }
        return -1;
    }
}
