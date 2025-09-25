package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given an increasing sequence of numbers from 1 to n with only one missing number,
how can you find that missing number without traversing the sequence in linear fashion.
In other words, time complexity of your algorithm must be less than O(n)?

 */
public class MissingNumInIncSequence {

    public static void main(String[] args) {

        int[] arr = {1,2,3,5,6,7,8};

        int n = arr.length;
        int start = 0;
        int end = n -1;

        while (start<=end) {
            int mid = start+(end-start)/2;
            if(start == end) {
                System.out.println(start+1);
                break;
            }
            if(mid+1 != arr[mid]) {
                end = mid;
            } else {
                start = mid+1;
            }
        }



    }
}
