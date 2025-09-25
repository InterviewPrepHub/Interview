package com.interview.Interview.Callicoder.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of integers, find all triplets in the array that sum up to a given target value.

In other words, given an array arr and a target value target, return all triplets a, b, c such that a + b + c = target.

Example:

Input array: [7, 12, 3, 1, 2, -6, 5, -8, 6]
Target sum: 0

Output: [[2, -8, 6], [3, 5, -8], [1, -6, 5]]

 */
public class ThreeSumProblem {

    public static void main(String[] args) {

        int[] arr = {7, 12, 3, 1, 2, -6, 5, -8, 6};
        int target = 0;

        List<Integer[]> list = new ArrayList<>();

        Arrays.sort(arr);

        int n = arr.length;

        for (int i = 0; i < n; i++) {

            int left = i+1, right = n-1;

            while (left<=right) {
                if(arr[i] + arr[left] + arr[right] == target) {
                    list.add(new Integer[] {arr[i], arr[left], arr[right]});
                    left++;
                    right--;
                } else if (arr[i] + arr[left] + arr[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }

        }

        for (Integer[] i : list) {
            System.out.println("["+i[0]+","+i[1]+","+i[2]+"]");
        }
    }
}
