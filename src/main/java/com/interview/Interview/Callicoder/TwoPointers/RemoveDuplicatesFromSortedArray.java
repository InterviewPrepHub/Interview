package com.interview.Interview.Callicoder.TwoPointers;

import java.util.HashSet;
import java.util.Set;

/*
Given a sorted array, remove all the duplicates from the array in-place such that each element appears only once,
and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example

Given array [1, 1, 1, 3, 5, 5, 7]

The output should be 4, with the first four elements of the array being [1, 3, 5, 7]
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        int[] a = {1, 1, 1, 3, 5, 5, 7};

        Set<Integer> set = new HashSet<>();

        int i=0, j=1, n = a.length;

        for (; j < n; j++) {
            if(a[i] != a[j]) {
                i++;
                a[i]=a[j];
            }
        }
        for (int k = 0; k < i+1; k++) {
            System.out.println(a[k]);
        }

    }
}
