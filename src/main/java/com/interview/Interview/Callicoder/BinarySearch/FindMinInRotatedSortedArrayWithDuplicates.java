package com.interview.Interview.Callicoder.BinarySearch;

public class FindMinInRotatedSortedArrayWithDuplicates {

    /*
    If Duplicates Are Allowed (LeetCode 154)
    When nums[mid] == nums[hi], you can’t decide the side; safely shrink hi--:
     */

    public static int findMinWithDuplicates(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                // nums[mid] == nums[hi], drop hi to skip duplicate
                hi--;
            }
        }
        return nums[lo];
    }

    public static void main(String[] args) {

        System.out.println(findMinWithDuplicates(new int[]{2, 2, 2, 0, 1, 2}));      // 0
        System.out.println(findMinWithDuplicates(new int[]{10, 10, 10, 1, 10}));  // 1
        System.out.println(findMinWithDuplicates(new int[]{3, 4, 5, 1, 1, 2, 3}));    // 1

    }
}
