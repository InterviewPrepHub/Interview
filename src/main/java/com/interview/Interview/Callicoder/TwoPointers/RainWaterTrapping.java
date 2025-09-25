package com.interview.Interview.Callicoder.TwoPointers;

public class RainWaterTrapping {

    /*
        The core idea is that at any index, the amount of water that can be trapped depends on the minimum of the
        tallest bars on the left and right sides — because water is bounded by the shorter wall.

        I use two pointers, left starting at index 0 and right at the end of the array.
        I also maintain two variables: maxLeft and maxRight to keep track of the highest bars seen so far from both ends.

        I only need to calculate trapped water at the side that has the smaller current height, because the trapped
        water at that position is guaranteed to be bounded by that side.
     */
    private static void trap(int[] height) {
        //use two pointers and maintain the leftmax and rightmax  on the fly
        //solution - https://www.youtube.com/watch?v=UHHp8USwx4M

        int left = 0, right = height.length-1, maxLeft = 0, maxRight = 0, water = 0;

        while (left <= right) {
            if(height[left] <= height[right]) {
                if (height[left] < maxLeft) {
                    water += maxLeft - height[left];
                } else {
                    maxLeft = height[left];
                }
                left++;
            } else {
                if (height[right] < maxRight) {
                    water += maxRight - height[right];
                } else {
                    maxRight = height[right];
                }
                right--;
            }
        }
        System.out.println("total trapped water : "+water);
    }

    public static void main(String[] args) {

    }
}
