package com.interview.Interview.iDeserve;

/*
Given an array of integers, print the leaders in the array. A leader is an element which is larger than all the
elements in the array to its right.

 */
public class LeadersInArray {

    public static void main(String[] args) {

        int[] arr = {98,23,54,12,20,7,27};

        int n = arr.length;

        int num = arr[n-1];  //27

        System.out.println(num);

        for (int i = n-2; i >= 0; i--) {
            if(num < arr[i]) {
                System.out.println(arr[i]);
                num = arr[i];
            }
        }
    }
}
