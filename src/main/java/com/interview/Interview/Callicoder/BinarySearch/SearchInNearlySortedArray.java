package com.interview.Interview.Callicoder.BinarySearch;

/*
Example 1:

Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, target = 40
Output: 2
Output is index of 40 in the given array
Example 2:

Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, target = 90
Output: -1
-1 is returned since the target value is not found

 */
public class SearchInNearlySortedArray {

    public static void main(String[] args) {

        int arr[] =  {10, 3, 40, 20, 50, 80, 70}, target = 40;

        int low = 0, high = arr.length-1;

        while(low <= high) {
            int mid = low+(high-low)/2;

            if(arr[mid] == target) {
                System.out.println(mid);
                break;
            }

            if(target == arr[mid-1]) {
                System.out.println(mid-1);
                break;
            }

            if(target == arr[mid+1]) {
                System.out.println(mid+1);
                break;
            }

            if(target > arr[mid]) {
                low = mid+2;
            } else {
                high = mid-2;
            }
        }

    }
}
