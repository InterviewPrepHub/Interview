package com.interview.Interview.iDeserve;

/*
Given an increasing sequence of numbers from 1 to n with only one missing number, how can you find that missing number
without traversing the sequence in linear fashion. In other words, time complexity of your algorithm must be less than O(n)?

 */
public class FindMissingNumInIncSequence {

    public static void main(String[] args) {
        int[] arr = {1,2,4,5,6,7,8};

        int low = 0, high = arr.length-1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == mid + 1) {
                // Left side is perfect, go right
                low = mid + 1;
            } else {
                // Missing number is on left
                high = mid - 1;
            }
        }

        // Missing number is at position `low`
        System.out.println("Missing number: " + (low + 1));

    }
}
