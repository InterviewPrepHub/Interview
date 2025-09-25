package com.interview.Interview.Callicoder.PrefixSum;

public class RangeSumQueryImmutable {

    public static void main(String[] args) {
        int[] a = {-2, 0, 3, -5, 2, -1};
        sumRange(a, 0,2);
        sumRange(a, 2,5);
    }

    public static void sumRange(int[] arr, int a, int b) {

        int n = arr.length;

        //calculate prefix sum
        int[] pre = new int[n+1];

        for (int i = 0; i < n; i++) {
            pre[i+1] = pre[i]+arr[i];
        }

        int i1 = b+1;
        int i2 = a+1;

        int total_sum = pre[i1] - pre[i2-1];

        System.out.println(total_sum);
    }
}
