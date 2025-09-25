package com.interview.Interview.Callicoder.BinarySearch;

/*
Given that the sorted array is guaranteed to have unique elements, return the minimum element of this array.


You must write an algorithm that runs in O(log n) time complexity.

Example 1:

Input: nums = [5, 7, 9, 1, 3]
Output: 1
Explanation: The original array was [1, 3, 5, 7, 9] rotated 3 times.

Example 2:

Input: nums = [4, 6, 8, 10, 0, 1, 2]
Output: 0
Explanation: The original array was [0, 1, 2, 4, 6, 8, 10] and it was rotated 4 times.

Example 3:

Input: nums = [5, 10, 15, 20]
Output: 5
Explanation: The original array was [15, 10, 15, 20] and it was rotated 4 times.
 */
public class FindMinInRotatedSortedArray {

    /*
    Intuition
        -   If nums[mid] > nums[hi], the minimum is in the right half (rotation lies to the right).
        -   If nums[mid] < nums[hi], the minimum is in the left half or at mid (right half is sorted).
        -   Shrink the search space until lo == hi → that index holds the minimum.
     */
    public static int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                // min is to the right of mid
                lo = mid + 1;
            } else {
                // nums[mid] <= nums[hi], min is at mid or to the left
                hi = mid;
            }
        }
        return nums[lo]; // or nums[hi]
    }

    public static void main(String[] args) {

        System.out.println(findMin(new int[]{3,4,5,1,2}));      // 1
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));  // 0
        System.out.println(findMin(new int[]{11,13,15,17}));    // 11 (no rotation)

    }
}
