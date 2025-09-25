package com.interview.Interview.Interview2024.Ideserve.Array;

public class PivotOfSortedRotatedArray {

    public static void main(String[] args) {
        int[] arr = {78,82,99,10,23,35,49,51,60};

        findPivot(arr);
    }

    private static void findPivot(int[] arr) {
        int n = arr.length-1;
        if(arr[0] < arr[n]) {
            System.out.println("arr is not rotated");
        }

        int start = 0, end = arr.length-1;

        while (start <= end) {
            int mid = start +(end - start)/2;

            if(arr[mid] > arr[mid+1]) {
                System.out.println("pivot : "+mid);
            } else if (arr[start] <= arr[mid]) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
    }
}
