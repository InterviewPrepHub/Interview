package com.interview.Interview.Callicoder.TwoPointers;

import java.util.Arrays;

/*
Given an array of integers, return the indices of the two numbers whose sum is equal to a given target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9.

The output should be [0, 1].
Because nums[0] + nums[1] = 2 + 7 = 9.
 */
public class TwoSumProblem {

    public static void main(String[] args) {

        int nums[] = {2, 7, 11, 15}, target = 9;

        //use sorting with two pointer approach
        Arrays.sort(nums);

        int left = 0, right = nums.length-1;

        while(left<right) {

            if(nums[left] + nums[right] == target) {
                System.out.println("left: "+nums[left]+", right: "+nums[right]);
                break;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }

    }
}
