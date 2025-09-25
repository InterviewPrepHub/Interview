package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount
of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
have security systems connected and it will automatically contact the police if two adjacent houses
were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount
of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber {

    public static void main(String[] args) {
//        int[] arr = {5,10,10,100,5,6};

        int[] arr = {2,3,2};
        int inc = arr[0];   //5
        int exc = 0;        //0

        for (int i = 1; i < arr.length; i++) {
            int new_inc = exc + arr[i];         // 10,15
            int new_exc = Math.max(inc, exc);   // 5,

            inc = new_inc;
            exc = new_exc;
        }

        int res = Math.max(inc, exc);
        System.out.println(res);

    }
}
