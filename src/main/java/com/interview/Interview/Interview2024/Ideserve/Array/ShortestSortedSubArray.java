package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Minimum length subarray of an unsorted array sorting which results in complete sorted array

 */
public class ShortestSortedSubArray {

    public static void main(String[] args) {
        int[] arr = {1,4,7,5,10,18,17,26,30,45,50,62};
        shortestSubarray(arr);
    }

    private static void shortestSubarray(int[] arr) {

        int n = arr.length;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            if (i+1 !=n && arr[i] > arr[i+1]) {
                startIndex = i;
                break;
            }
        }

        for (int i = n-1; i >= 0; i--) {
            if (i-1 >=0 && arr[i] < arr[i-1]) {
                endIndex = i;
                break;
            }
        }

        System.out.println(startIndex+"----"+endIndex);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int left = 0, right = 0;
        for(int i = startIndex;i>=0;i--) {
            if (arr[i] > max) {
                max = arr[i];
                left = i;
            }
        }

        for(int i = endIndex;i<n;i++) {
            if (arr[i] < min) {
                min = arr[i];
                right = i;
            }
        }

        System.out.println(max+"----"+min);
        int len = right - left +1;
        System.out.println(len);
    }
}
