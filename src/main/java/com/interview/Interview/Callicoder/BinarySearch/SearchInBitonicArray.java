package com.interview.Interview.Callicoder.BinarySearch;

/*
A bitonic array is an array that is first increasing and then decreasing.
Given an array of numbers which is first increasing and then decreasing, search te target in the array.
 */
public class SearchInBitonicArray {

    public static void main(String[] args) {

        int[] a = {2, 4, 8, 10, 7, 6, 1};
        int target = 6;
        int res = search(a, target);
        System.out.println("target element index : "+res);
    }


    public static int search(int[] a, int target) {

        int maxELementIndex = findMaxElementIndex(a, target);
        System.out.println("max element : "+maxELementIndex);

        //once you have max element index, then divide the array into two parts and find the target element

        int low1 = 0, high1 = maxELementIndex, low2 = maxELementIndex+1, high2 = a.length-1;

        int s1 = search(a, low1, high1, target);
        int s2 = search(a, low2, high2, target);

        if(s1==-1) {
            return s2;
        } else {
            return s1;
        }
    }

    public static int search(int[] a, int low, int high, int target) {

        while(low<=high) {
            int mid = low+(high-low)/2;

            if(a[mid] == target) {
                return mid;
            } else if (a[mid] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    private static int findMaxElementIndex(int[] a, int target) {
        int low = 0, high = a.length-1;
        //4,5,6,1,2,3
        while (low<=high) {

            int mid = low+(high-low)/2;

            if(a[mid] > a[mid-1] || a[mid] > a[mid+1]) {
                return mid;
            } else if (a[mid] < a[mid-1]) {
                low = mid-1;
            } else {
                high = mid+1;
            }
        }
        return -1;
    }
}
