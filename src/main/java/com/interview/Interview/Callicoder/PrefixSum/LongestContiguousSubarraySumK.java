package com.interview.Interview.Callicoder.PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
You want the longest subarray with sum k. Use the same prefix-sum + hashmap idea, but instead of returning
on the first hit, keep track of the max length seen so far.
 */
public class LongestContiguousSubarraySumK {

    // Returns {maxLen, start, end}. If none, returns {0, -1, -1}.
    public static int[] longestSubarrayWithSumK(int[] arr, int k) {
        Map<Long, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0L, -1); // prefix sum 0 occurs before start

        long prefix = 0L;
        int bestLen = 0, bestL = -1, bestR = -1;

        for (int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            long need = prefix - k;
            if (firstIndex.containsKey(need)) {
                int start = firstIndex.get(need) + 1;
                int len = i - start + 1;
                if (len > bestLen) {
                    bestLen = len;
                    bestL = start;
                    bestR = i;
                }
            }

            // store earliest index only
            if (!firstIndex.containsKey(prefix)) {
                firstIndex.put(prefix, i);
            }
        }
        return new int[]{bestLen, bestL, bestR};
    }

    // Demo
    public static void main(String[] args) {
        int[] nums1 = {1, -1, 5, -2, 3};
        int k1 = 3;
        int[] res1 = longestSubarrayWithSumK(nums1, k1);
        System.out.println("len=" + res1[0] + ", range=[" + res1[1] + "," + res1[2] + "]");
        // Expected: len=4, range=[0,3] for [1,-1,5,-2]

        int[] nums2 = {1, 4, 20, 3, 10, 5};
        int k2 = 33;
        int[] res2 = longestSubarrayWithSumK(nums2, k2);
        System.out.println("len=" + res2[0] + ", range=[" + res2[1] + "," + res2[2] + "]");
        // Expected: len=3, range=[2,4] for [20,3,10]
    }
}
