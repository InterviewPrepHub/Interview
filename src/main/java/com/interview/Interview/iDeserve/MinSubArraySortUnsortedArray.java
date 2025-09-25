package com.interview.Interview.iDeserve;

import java.util.Arrays;

public class MinSubArraySortUnsortedArray {

    public static void main(String[] args) {
        int[] arr = {1,4,7,3,10,48,17,26,30,45,50,62};

        int n = arr.length;

        int startIndex = 0, endIndex = 0;

        //find startIndex
        for (int i = 0; i < n; i++) {
            if(arr[i] > arr[i+1] && i+1 < n) {
                startIndex = i;
                break;
            }

        }

        //find endIndex
        for (int j = n-1; j >= 0; j--) {
            if (arr[j] < arr[j-1]) {
                endIndex = j;
                break;
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //find min & max in the subarray between startIndex & endIndex
        for (int k = startIndex; k <= endIndex; k++) {
            min = Math.min(min, arr[k]);
        }

        for (int k = startIndex; k <= endIndex; k++) {
            max = Math.max(max, arr[k]);
        }

        /*int nmin = 0, nmax = 0;
        for (int k = startIndex; k < endIndex; k++) {
            if(min == arr[k]) {
                nmin = k;
            }
            if(max == arr[k]) {
                nmax = k;
            }
        }*/

        int minIndex = 0, maxIndex = 0;
        //find the first element which is just bigger than min to get minIndex
        for (int i = 0; i <= startIndex; i++) {
            if(arr[i] > min) {
                minIndex = i;
                break;
            }
        }

        //find the first element which is just smaller than max to get maxIndex
        for (int i = endIndex; i <= n-1; i++) {
            if(arr[i] > max) {
                maxIndex = i-1;
                break;
            }
        }

        //the element coming between minIndex to maxIndex needs to be sorted to sort the array
        System.out.println("minIndex : "+minIndex+ ", maxIndex : "+maxIndex);

        int newSize = minIndex+maxIndex;
        int[] arr1 = new int[newSize];

        //sort array between minIndex &  maxIndex
        for (int i = 0; i < newSize; i++) {
            arr1[i] = arr[minIndex+i];
        }

        Arrays.sort(arr1);

    }
}
