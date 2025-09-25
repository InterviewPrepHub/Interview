package com.interview.Interview.Callicoder.TwoPointers;

import java.util.Arrays;

public class TripletsSumClosestToTarget {

    private static int findTripletSumClosestToTarget(int[] a, int targetSum) {

        Arrays.sort(a);
        int smallestDiff = Integer.MAX_VALUE;

        for (int i = 0; i < a.length - 2; i++) {
            // Skip duplicates
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }

            // Fix one number
            int firstNum = a[i];

            // Use Two-sum approach to get the other two numbers
            // such that the sum of all three numbers are closest to target
            int left = i + 1, right = a.length - 1;

            while (left < right) {
                int currSum = firstNum + a[left] + a[right];
                int currDiff = targetSum - currSum;

                if (currDiff == 0) {
                    return currSum;
                }

                if (Math.abs(currDiff) < Math.abs(smallestDiff)) {
                    smallestDiff = currDiff;
                }

                if (targetSum > currSum) {
                    //if targetSum > currSum,
                    // inc left pointer to inc sum so that the diff moves closer to zero
                    left++;
                } else {
                    //if targetSum < currSum,
                    // dec right pointer to dec sum so that the diff moves closer to zero
                    right--;
                }
            }
        }
        return targetSum - smallestDiff;
    }

    public static void main(String[] args) {
//        int a[] = {-2, -4, 6, 3, 7}, target = 2;
//        findTripletSumClosestToTarget(a, target);   //1

        int a1[] = {10, 2, 30, 49, 8}, target1 = 50;
        int res = findTripletSumClosestToTarget(a1, target1);   //48
        System.out.println(res);
    }

}
