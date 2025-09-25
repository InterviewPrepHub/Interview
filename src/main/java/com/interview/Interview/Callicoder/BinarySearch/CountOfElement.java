package com.interview.Interview.Callicoder.BinarySearch;

/*
Given a sorted array of integers and a target element. Find the number of occurrences of the target in the array.
You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: a[] = {1, 1, 2, 2, 2, 2, 3},  target = 2
Output: 4 (2 appears four times in the array)
 */
public class CountOfElement {

    public static void main(String[] args) {
//        int a[] = {1, 1, 2, 2, 2, 2, 3},  target = 2;
        int a[] = {1, 1, 2, 2, 2, 2, 3},  target = 2;

        int low = 0, high = a.length-1;
        int firstPosition = 0;

        while (low<=high) {
            int mid = low+(high-low)/2;
            if(target == a[mid]) {
                firstPosition = mid;
                high = mid-1; //when you find mid, you search the target before mid
            } else if (target < a[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        int lastPosition = 0;

        int low1 = 0, high1 = a.length-1;
        while (low1<=high1) {
            int mid = low1+(high1-low1)/2;
            if(target == a[mid]) {
                lastPosition = mid;
                low1 = mid+1; //when you find mid, you search the target after mid
            } else if (target < a[mid]) {
                high1 = mid-1;
            } else {
                low1 = mid+1;
            }
        }

        System.out.println(firstPosition+" "+lastPosition);

        int count = lastPosition-firstPosition+1;
        System.out.println(count);
    }
}
