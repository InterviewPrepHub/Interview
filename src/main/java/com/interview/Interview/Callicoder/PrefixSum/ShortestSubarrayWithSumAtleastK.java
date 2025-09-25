package com.interview.Interview.Callicoder.PrefixSum;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumAtleastK {

    // Returns the length of the shortest subarray with sum >= K, or -1 if none.
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n+1];
        for (int i = 0; i < n; ++i)
            prefix[i+1] = prefix[i] + nums[i];

        int minLen = n+1;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i <= n; ++i) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
                minLen = Math.min(minLen, i - dq.pollFirst());
            }
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return minLen <= n ? minLen : -1;
    }


    public static void main(String[] args) {
//        System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3));          // 3  -> [2,-1,2]
//        System.out.println(shortestSubarray(new int[]{1, 2, 3, 4, 5}, 11));    // 3  -> [3,4,5]
        System.out.println(shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167)); // 3
        System.out.println(shortestSubarray(new int[]{1, -1, 1, -1, 1}, 2));   // -1
    }
}

/*
           j--------------->j
int[] a = {84, -37, 32, 40, 95}, k = 167
           i

i = 0
sum = 0+84 < 167    j++
sum = 84-37 = 47 < 167    j++
sum = 47+32 = 79 < 167    j++
sum = 79+40 = 119 < 167    j++
sum = 119+95 = 214 > 167    j++ => 4


now lets shrink and see are windowSum > 167
i++  = 1
sum = 214-84 = 130 < 167

                214
               /
             119
            /
    84    79
      \  /
       47

       there is a dip in sum here from 84 to 47 then in the prefix sum we are only getting increased version
       so having 84 in the window does not make sense

       so far we can leave 84 and start the sum from 47
       hence if we are left with window ----> [-37, 32, 40, 95]

       so we need to keep the element in a particular order ie over here its increasing order
       so we should use the monotonic dequeue.


 */
