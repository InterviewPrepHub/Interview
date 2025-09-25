package com.interview.Interview.iDeserve;

public class MaxElementFromEachSubArrayOfSizeK {

    public static void main(String[] args) {
        int[] arr = {9,6,11,8,10,5,4,13,93,14};
        int k = 4;

        int windowStart = 0;
        int windowMax = 0;
        int max = Integer.MIN_VALUE;

        for (int windowEnd = k; windowEnd < arr.length; windowEnd++) {

            windowMax = Math.max(windowMax, arr[windowEnd]);

            if(windowEnd-windowStart+1 == k) {
                max = Math.max(max, windowMax);
                windowStart++;
            }

            System.out.println(windowMax);
        }

    }
}
