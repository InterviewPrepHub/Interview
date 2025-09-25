package com.interview.Interview.LeetCode_Implementations;

public class QuickSortImpl {

    public static void main(String[] args) {
        int[] arr={7,2,1,6,8,5,3,4};
        sort(arr, 0, arr.length-1);
        print(arr);

    }

    public static void sort(int[] arr, int start, int end) {

        if (start < end) {
            int pIndex = partition(arr, start, end);
            sort(arr, start, pIndex-1);
            sort(arr, pIndex+1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {

        int pivot = arr[end];
        int pIndex = start - 1;

        for (int i = start; i < end; i++) {
            if(arr[i] < pivot) {
                pIndex++;
                swap(arr, i, pIndex);
            }
        }

        swap(arr, pIndex+1, end);
        return pIndex+1;
    }

    private static void swap(int[] arr,int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
