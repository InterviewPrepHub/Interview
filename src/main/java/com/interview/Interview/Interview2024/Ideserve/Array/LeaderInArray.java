package com.interview.Interview.Interview2024.Ideserve.Array;

import java.util.Stack;

/*
Given an array of integers, print the leaders in the array. A leader is an element which is larger than
all the elements in the array to its right.

 */
public class LeaderInArray {
    public static void main(String[] args) {
        int[] arr = {98,23,54,12,20,7,27};

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);

        }
    }


}
