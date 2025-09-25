package com.interview.Interview.Callicoder.BinarySearch;

/*
Given a sorted array of numbers, find if a given number key is present in the array. Though we know that
the array is sorted, we don’t know if it’s sorted in ascending or descending order.

Example 1:

Input: [2, 8, 11, 19], key = 11
Output: 2
Example 2:

Input: [32, 28, 17, 9, 3], key = 9
Output: 3

 */
public class OrderAgnosticBinarySearch {

    public static void main(String[] args) {

        /*int[] a = {32, 28, 17, 9, 3};
        int k = 9;*/

        int[] a = {2, 8, 11, 19};
        int k = 11;

        //check the order for the sorted array

        if(k < a[0]) {
            //array is desending order so low is last element and high is first element

            int low = 0, high = a.length - 1;

            while(low<=high) {
                int mid = (low+high)/2; //2

                if(k == a[mid]) {
                    System.out.println(mid);
                    break;
                } else if (k < a[mid]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        } else {
            //2, 8, 11, 19
            int low = 0, high = a.length - 1; //3

            while(low<=high) {
                int mid = (low+high)/2; //1

                if(k == a[mid]) {
                    System.out.println(mid);
                    break;
                } else if (k < a[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
        }

    }
}
