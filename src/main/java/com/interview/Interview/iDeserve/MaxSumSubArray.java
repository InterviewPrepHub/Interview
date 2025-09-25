package com.interview.Interview.iDeserve;

public class MaxSumSubArray {

    public static void main(String[] args) {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        int max_ending_here = nums[0];  //max subarray sum ending at current index
        int max_so_far = nums[0];       //over max subarray sum

        for (int i = 0; i < nums.length; i++) {
            max_ending_here = Math.max(nums[i], max_ending_here+nums[i]);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }

        System.out.println(max_so_far);
    }
}
