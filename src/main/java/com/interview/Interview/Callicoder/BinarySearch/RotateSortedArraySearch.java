package com.interview.Interview.Callicoder.BinarySearch;

/*
Example 1:

Input: nums = [8, 11, 13, 15, 1, 4, 6], target = 1
Output: 4

Example 2:

Input: nums = [1, 4, 6, 8, 11, 13, 15], target = 3
Output: -1

 */
public class RotateSortedArraySearch {

    public static void main(String[] args) {
        int nums[] = {8, 11, 13, 15, 1, 4, 6}, target = 1;


        int low = 0, high = nums.length-1;

        while (low<=high) {
            int mid = low + (high-low)/2;


            if(target == nums[mid]) {
                System.out.println(mid);
                break;
            }
            if(nums[low] <= nums[mid]) {   //check if left array is a rotated array
                //check if target is in left side [start to mid]

                if(target >= nums[low] && target <= nums[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }

            } else {    //check if left array is a rotated array
                //check if target is in right side [mid to end]

                if(target <= nums[mid] && target >= nums[high]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }

    }
}
