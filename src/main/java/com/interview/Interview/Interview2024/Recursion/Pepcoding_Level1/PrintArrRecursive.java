package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

public class PrintArrRecursive {

    //similar to print increasing
    public static void main(String[] args) {
//        int[] arr = {10,20,30};
//        printArr(arr, arr.length-1);
//        System.out.println();
//        System.out.println("reverse array");
//        printArrInReverse(arr, arr.length-1);
//        System.out.println("max in an array");
        int[] arr1 = {20, 30, 40 ,10, 30, 40, 80};
//        int max_of_arr = printMaxInArr(arr1, 0);
//        System.out.println(max_of_arr);
        int x = 40;
//        int first = firstOcurrence(arr1, 0, x);
//        System.out.println(first);
        int last = lastOccurence(arr1, arr1.length-1, x);
        System.out.println(last);
    }

    private static int lastOccurence(int[] arr1, int i, int x) {
        if (i==0) {
            return i;
        }
        int idx = lastOccurence(arr1, i-1, x);
        if (x == arr1[i]) {
            return i;
        } else {
            return idx;
        }
    }

    private static int firstOcurrence(int[] arr1, int i, int x) {
        if (i==arr1.length) {
            return -1;
        }
        int idx = firstOcurrence(arr1, i+1, x);
        if (x == arr1[i]) {
            return i;
        } else {
            return idx;
        }
    }

    private static int printMaxInArr(int[] arr1, int i) {

        if (i == arr1.length-1) {
            return arr1[i];
        }

        int misa = printMaxInArr(arr1, i+1);
        if(misa > arr1[i]) {
            return misa;
        } else {
            return arr1[i];
        }
    }

    private static void printArrInReverse(int[] arr, int i) {

        if (i == -1) {
            return;
        }
        System.out.println(arr[i]);
        printArr(arr, i-1);
    }

    private static void printArr(int[] arr, int i) {

        if (i==-1) {
            return;
        }
        printArr(arr, i-1);
        System.out.println(arr[i]);
    }
}
