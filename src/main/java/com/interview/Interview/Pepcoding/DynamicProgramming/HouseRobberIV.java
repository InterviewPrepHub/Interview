package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
There are several consecutive houses along a street, each of which has some money inside.
There is also a robber, who wants to steal money from the homes, but he refuses to steal
from adjacent homes.

The capability of the robber is the maximum amount of money he steals from one house of all
the houses he robbed.

You are given an integer array nums representing how much money is stashed in each house.
More formally, the ith house from the left has nums[i] dollars.

You are also given an integer k, representing the minimum number of houses the robber will
steal from. It is always possible to steal at least k houses.

Return the minimum capability of the robber out of all the possible ways to steal at least k houses.

Example 1:

Input: nums = [2,3,5,9], k = 2
Output: 5

 */
public class HouseRobberIV {

    static int INF = Integer.MAX_VALUE;
    static int[][] memo;

    public static void main(String[] args) {

        int nums[] = {2,3,5,9}, k = 2;
        System.out.println(dfs(0, k, nums));
        memo = new int[nums.length+1][k+1];
        System.out.println(dfsWithMemo(0,k,nums));

    }

    //time complexity -> 2^N
    //space complexity -> recursion stack O(n).
    static int dfs(int i, int k, int[] nums) {
        if (k == 0) return 0;                 // robbed enough
        if (i >= nums.length) return INF;     // ran out of houses

        // option 1: rob house i
        int take = Math.max(nums[i], dfs(i+2, k-1, nums));

        // option 2: skip house i
        int skip = dfs(i+1, k, nums);

        return Math.min(take, skip);
    }

    /*
        time complexity -> O(n*k)
        If you memoize (i, k):
            - There are at most n * k distinct states.
            - Each state is computed once and takes O(1) work (just max, min, and recursive returns).

        Space = O(n * k) for the memo table + recursion stack O(n).
     */
    static int dfsWithMemo(int i, int k, int[] nums) {
        if (k == 0) return 0;                 // robbed enough
        if (i >= nums.length) return INF;     // ran out of houses

        // option 1: rob house i
        int take = Math.max(nums[i], dfs(i+2, k-1, nums));

        // option 2: skip house i
        int skip = dfs(i+1, k, nums);

        return memo[i][k] = Math.min(take, skip);
    }
}
