package com.interview.Interview.Interview2024.Microsoft.Round1;

/*

Given integer array A[] with distinct elements. Find max number of slices in which array can be divided,
so that if all slices are combined, we get a complete sorted array. Each slice need to be sorted individually,
and then these sorted slices should be combined.
Ex1. A=[2,4,1,6,5,9,7]. Ret val = 3. slices[2,4,1], [6,5], [9,7]
Ex2. A=[4,3,2,6,1]. Ret val =1. Array cannot be sliced.
Ex3. A=[2,1,6,4,3,7] Ret val =3. slices [2,1], [6,4,3],[7]

What logic can be used here to slice the arrays?

here we find the maximum number of contiguous subarrays (slices) that we can split the given array
into such that after sorting each subarray individually and then concatenating them back together,
the entire array is sorted.

The key idea here is to identify "chunks" in the array such that sorting each chunk individually
results in a sorted array when concatenated together. This can be achieved by keeping track of the
maximum element seen so far while traversing the array from left to right. At each index, if the
maximum element seen so far is equal to the current index, it means that all elements up to this
index can form a valid slice.

 */

import java.util.Arrays;

public class ArraySlicing {

    //{2, 4, 1, 6, 5, 9, 7}
    public static int maxSlices(int[] A) {
        int n = A.length;
        int[] sortedA = A.clone();
        Arrays.sort(sortedA);   //1,2,4,5,6,7,9

        int maxSeen = Integer.MIN_VALUE;
        int slices = 0;

        for (int i = 0; i < n; i++) {
            maxSeen = Math.max(maxSeen, A[i]);  //2,4,4 | 6,6 | 9,9
            if (maxSeen == sortedA[i]) {
                slices++; //3
            }
        }
        return slices;
    }

    public static void main(String[] args) {
        int[] A1 = {2, 4, 1, 6, 5, 9, 7};
        System.out.println(maxSlices(A1)); // Expected output: 3

        int[] A2 = {4, 3, 2, 6, 1};
        System.out.println(maxSlices(A2)); // Expected output: 1
    }
}
