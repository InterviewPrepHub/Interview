package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given an array of size n, find the element which occurs more than n/2 times.
This element is called Majority Element.
 */
public class MajorityElement {

    public static void main(String[] args) {
//        int[] arr = {2, 6, 2, 2, 6, 2, 2, 8, 2, 1};
        int[] arr = {7, 6, 7, 7, 6, 7, 7, 8, 7, 1};
        findMajorityElement(arr);

        int[] arr1 = {1, 7, 8, 2, 6, 8, 1, 3, 2, 8};
        findMajorityElement(arr1);
    }

    private static void findMajorityElement(int[] arr) {

        int candidate = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(count == 0) {
                candidate = arr[i];
                count++;
            } else {
                count--;
            }

        }

        System.out.println(candidate); //now that we know candidate element, find weather the candidate element is majority element

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == candidate) {
                count++;
            }
        }

        int res = count >= (arr.length/2) ? candidate : -1;

        System.out.println(res);
    }
}
