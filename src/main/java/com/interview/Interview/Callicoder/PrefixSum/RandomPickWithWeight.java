package com.interview.Interview.Callicoder.PrefixSum;


import java.util.Arrays;
import java.util.Random;

/*
w = [1,3]

prefix = [1,4]

total = 4 --> running sum

r = 1 --> falls int first range [0] 1/4
r = 2,3,4 -> fall in the sec range [1]  3/4
 */
public class RandomPickWithWeight {

    private int[] prefix;
    private int total;
    private Random rand;

    // Constructor: build prefix sums
    public RandomPickWithWeight(int[] w) {
        prefix = new int[w.length];
        rand = new Random();

        int runningSum = 0;
        for (int i = 0; i < w.length; i++) {
            runningSum += w[i];
            prefix[i] = runningSum;
        }
        total = runningSum;
    }

    // Pick index according to weight
    public int pickIndex() {
        // Pick random int in [1..total]
        int r = rand.nextInt(total) + 1;

        // Binary search for first prefix >= r
        int lo = 0, hi = prefix.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prefix[mid] < r) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // Example usage
    public static void main(String[] args) {
        int[] w = {1, 3};
        RandomPickWithWeight obj = new RandomPickWithWeight(w);

        // Run many picks to see distribution
        int[] counts = new int[w.length];
        for (int i = 0; i < 10000; i++) {
            counts[obj.pickIndex()]++;
        }
        System.out.println(Arrays.toString(counts));
        // Expect about [2500, 7500] (approx)
    }
}
