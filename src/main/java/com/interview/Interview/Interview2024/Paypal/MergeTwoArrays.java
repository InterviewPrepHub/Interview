package com.interview.Interview.Interview2024.Paypal;

public class MergeTwoArrays {

    public static void main(String[] args) {
        int[] arr1 = {3, 5, 7, 0, 0};
        int m = 3; // Actual size of arr1
        int[] arr2 = {1, 2};
        int n = 2;

        int index1 = m-1;
        int index2 = n-1;

        int mergedIndex = m + n - 1 ;

        while(index1 >= 0 && index2 >= 0) {
            if(arr1[index1] > arr2[index2]) {
                arr1[mergedIndex] = arr1[index1];
                index1--;
            } else {
                arr1[mergedIndex] = arr2[index2];
                index2--;
            }
            mergedIndex--;
        }

        while(index2 >= 0) {
            arr1[mergedIndex] = arr2[index2];
            index2--;
            mergedIndex--;
        }

        for (int j = 0; j < arr1.length; j++) {
            System.out.print(arr1[j] + " ");
        }
    }
}
