package com.interview.Interview.Callicoder.BinarySearch;

/*
Given a sorted array of n elements, find if a given element target is present in the array.


 */
public class BinarySearch {

    public static void main(String[] args) {

        int a[] = { 2, 3, 4, 10, 40 };
        int x = 10;
        int res = bsIterative(a, x);
        System.out.println(res);

//        bsRecursive(a, x);
    }

    private static int bsIterative(int[] a, int x) {

        int low = 0;
        int high = a.length-1;

        while (low<=high) {
            int mid = (low+high)/2;
            if (x == a[mid]) {
//                System.out.println(mid);
                return mid;
            } else if (x < a[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;

    }

    private static void bsRecursive(int[] a, int x) {

        int low = 0, high = a.length-1;

        int res = bsRecursive(a, x, low, high);

        System.out.println(res);
    }

    private static int bsRecursive(int[] a, int x, int low, int high) {

        if(low > high) {
            return -1;
        }

        int mid = (low+high)/2;

        if(x == a[mid]) {
            return mid;
        } else if (x < a[mid]) {
            return bsRecursive(a,x,low, mid-1);
        } else {
            return bsRecursive(a,x,mid+1, high);
        }
    }
}
