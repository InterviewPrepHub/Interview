package com.interview.Interview.iDeserve;

/*
Given two integer arrays where second array is duplicate of first array with just 1 element missing.
Find the element.
 */
public class MissingNumberInDuplicateArray {

    public static void main(String[] args) {
        int[] a1 = {9, 7, 8, 5, 4, 6, 2, 3, 1};
        int[] a2 = {2, 3, 4, 9, 1, 8, 5, 6};

        int result = a1[0];

        for (int i = 1; i < a1.length; i++) {
            result = result ^ a1[i];
        }

        for (int i = 0; i < a2.length; i++) {
            result = result ^ a2[i];
        }

        System.out.println(result);
    }
}
