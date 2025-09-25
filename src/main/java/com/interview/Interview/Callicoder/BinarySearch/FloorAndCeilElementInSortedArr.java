package com.interview.Interview.Callicoder.BinarySearch;

public class FloorAndCeilElementInSortedArr {

    public static void main(String[] args) {
        int[] arr = {1, 3, 9, 15, 15, 18, 21};
        int x = 5;

        floorAndCeil(arr, x);
    }

    public static void floorAndCeil(int[] arr, int x) {

        int low = 0, high = arr.length - 1, floor = -1, ceil = -1;

        while(low<=high) {
            int mid = low+(high-low)/2;

            if(arr[mid] == x) {
                System.out.println(mid);
                return;
            } else if(x < arr[mid]) {
                floor = arr[mid];
                high = mid-1;
            } else {
                ceil = arr[mid];
                low = mid+1;
            }
        }

        System.out.println("floor : "+floor);
        System.out.println("ceil : "+ceil);
    }
}
