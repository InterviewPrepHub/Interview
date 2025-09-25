package com.interview.Interview.Callicoder.PrefixSum;

import java.util.ArrayDeque;
import java.util.Deque;

public class GetMaxProfitSubarrayAtmostKMonths {

    public static long getMaxProfit(int[] pnl, int k) {
        int n = pnl.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + pnl[i];
        }

        long ans = Long.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            // Remove indices from deque which are out of window (i - idx > k)
            while (!dq.isEmpty() && i - dq.peekFirst() > k) {
                dq.pollFirst();
            }
            // Try to update the answer using the current prefix sum and the smallest seen
            if (!dq.isEmpty()) {
                ans = Math.max(ans, prefix[i] - prefix[dq.peekFirst()]);
            }
            // Maintain deque monotonicity: remove from back if new prefix is smaller or equal
            while (!dq.isEmpty() && prefix[dq.peekLast()] >= prefix[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] pnl = {-3, 4, 3, -2, 2, 5};
        int k = 4;
        System.out.println(getMaxProfit(pnl, k));  // Output: 8
    }
}
