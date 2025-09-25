package com.interview.Interview.Callicoder.BinarySearch;

/*
Given a sorted array and a and a value x, find the floor of x in the array.
The floor of x is the largest element in the array smaller than or equal to x.
Output 1 if the floor doesn’t exist.

Example 1

Input : a[] = {1, 3, 9, 15, 15, 18, 21}, x = 5
Output : 3
3 is the largest element in the array smaller than or equal to 5.
Example 2

Input : a[] = {1, 3, 9, 15, 15, 18, 21}, x = 25
Output : 21
21 is the largest element in the array smaller than or equal to 25.
 */
public class FloorOfElementInSortedArray {

    public static void main(String[] args) {

        int a[] = {1, 3, 9, 15, 15, 18, 21}, x = 5;

        int res = searchFloor(a,x);
        System.out.println(res);
    }

    private static int searchFloor(int[] a, int x) {
        int low = 0, high = a.length-1, floor = -1;

        while (low<=high) {

            int mid = low+(high-low)/2;

            if(x == a[mid]) {
                return mid;
            } else if (a[mid] > x) {
                high = mid-1;
            } else {
                floor = a[mid];
                low = mid+1;
            }
        }
        return floor;
    }


}
