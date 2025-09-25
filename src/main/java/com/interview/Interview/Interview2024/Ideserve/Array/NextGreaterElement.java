package com.interview.Interview.Interview2024.Ideserve.Array;

import java.util.Stack;

/*
Given an array of integers(positive or negative), print the next greater element of all elements in the array.
If there is no greater element then print null.

Next greater element of arr element arr[i], is an integer arr[j] such that
arr[i] < arr[j]
i<j
j-1 is min

98  23  54  12  20  7   27
n   54  n   20  27  27  n
 */
public class NextGreaterElement {

    public static void main(String[] args) {
        int[] arr = {98, 23, 54, 12, 20, 7, 27};
        nextGreaterEle(arr);
    }

    private static void nextGreaterEle(int[] arr) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if(st.isEmpty()) {
                st.push(arr[i]);
            }
            else if(arr[i] < st.peek()) {
                st.push(arr[i]);
            } else {
                while(arr[i] > st.peek() && !st.isEmpty()) {
                    int num = st.pop();
                    System.out.println(num+"'s greater element is "+arr[i]);
                }
                st.push(arr[i]);
            }
        }

        while (!st.isEmpty()) {
            int num = st.pop();
            System.out.println(num+"'s greater element is "+(-1));
        }
    }
}
