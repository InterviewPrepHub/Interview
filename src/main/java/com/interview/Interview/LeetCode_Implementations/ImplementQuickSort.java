package com.interview.Interview.LeetCode_Implementations;

public class ImplementQuickSort {

    public static void main(String[] args) {
        int[] a = {7,2,1,6,8,5,3,4};

        quicksort(a, 0, a.length-1);

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private static void quicksort(int[] a, int start, int end) {

        if(start>=end) return;

        int pIndex = partition(a, start, end);

        quicksort(a, start, pIndex-1);
        quicksort(a, pIndex+1, end);
    }

    private static int partition(int[] a, int start, int end) {

        int pivot = a[end];
        int pIndex = start;

        for (int i = start; i <= end-1; i++) {
            if(a[i] <= pivot) {
                swap(a, i, pIndex);
                pIndex++;
            }
        }
        swap(a, pIndex, end);
        return pIndex;
    }

    private static void swap(int[] a, int i, int pIndex) {
        int temp = a[i];
        a[i] = a[pIndex];
        a[pIndex] = temp;
    }
}
