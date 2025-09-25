package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int k = 56;
        binarySearchArr(arr, 0, arr.length-1, k);
    }

    private static void binarySearchArr(int[] arr, int start, int end, int k) {

        while (start <= end) {
            int mid = start + (end-start)/2;
            if(arr[mid] == k) {
                System.out.println(mid);
                return;
            } else if (k > arr[mid]) {
                start = mid+1;
            } else {
                end = mid -1;
            }
        }

    }

    public static void sortedRotatedArray(int[] arr, int k) {


    }
}
