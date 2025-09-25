package com.interview.Interview.Callicoder.BinarySearch;

public class CeilOfElementInSortedArray {

    public static void main(String[] args) {

        int a[] = {1, 3, 9, 15, 15, 18, 21}, x = 5;

        int res = searchCeil(a,x);  //smallest number >= 5 ---> 9
        System.out.println(res);
    }

    private static int searchCeil(int[] a, int x) {
        int low = 0, high = a.length-1, ceil = -1;

        while (low<=high) {

            int mid = low+(high-low)/2;

            if(x == a[mid]) {
                return mid;
            } else if (a[mid] > x) {
                ceil = a[mid];
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ceil;
    }
}
