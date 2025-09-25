package com.interview.Interview.Callicoder.BinarySearch;

public class MinCostToMakeArrayEqual {

    public static long minCost(int[] nums, int[] cost) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        long answer = getCost(nums, cost, nums[0]);

        // Binary search over possible target values
        while (left < right) {
            int mid = (left + right) / 2;
            long cost1 = getCost(nums, cost, mid);
            long cost2 = getCost(nums, cost, mid + 1);

            if (cost1 <= cost2) {
                right = mid;
                answer = cost1;
            } else {
                left = mid + 1;
                answer = cost2;
            }
        }

        return answer;
    }

    // Calculate total cost if all nums become target
    private static long getCost(int[] nums, int[] cost, int target) {
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += (long) Math.abs(nums[i] - target) * cost[i];
        }
        return total;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2};
        int[] cost = {2, 3, 1, 14};
        System.out.println(minCost(nums, cost)); // Output: 8
    }

    /*
    say you want to convert every number to 5 in array  nums = {1, 3, 5, 2};
    how many operation to convert 1 to 5 => 5-1 = 4
    cost of each operation is 2 for each operation
    total cost = 4*2 = 8

    similarly for others
    (5-1) = 4*2 = 8
    (5-3) = 2*3 = 6
    (5-5) = 0*1 = 0
    (5-2) = 3*14 = 52

    total cost = 8+6+0+52 = 66


    Intuition:
    left[min]         mid=left+(right-left)/2               right[max]
       1                         3                              5
       cost of converting all elements to 3 is 4+0+2+14 = 20
       check mid+1 = 4
       cost of converting all elements to 4 is 6+3+1+28 = 38
       since cost[mid+1] < cost[mid] --> 38 > 20
       hence the new right = mid-1 = 2

    left[min]         mid=left+(right-left)/2               right[max]
       1                         1                              2
       cost of converting all elements to 1 is 0+6+4+14 = 24
       check mid+1 = 2
       cost of converting all elements to 2 is 2+3+3+0 = 8
       since cost[mid+1] < cost[mid] --> 8 < 24

    left[min]         mid=left+(right-left)/2               right[max]
       2                         2                              2
       cost of converting all elements to 2 is 2+3+3+0 = 8
     */
}
