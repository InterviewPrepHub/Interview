package com.interview.Interview.Pepcoding.DynamicProgramming;

public class HouseRobberII {

    public static void main(String[] args) {

//        int[] arr = {5,10,10,100,5,6};
        int[] arr = {2,3,2};
        int inc = arr[0];
        int exc = 0;

        //we run the house robber solution for all elements except the last element
        for (int i = 1; i < arr.length-1; i++) {
            int new_inc = exc + arr[i];
            int new_exc = Math.max(inc, exc);
            inc = new_inc;
            exc = new_exc;

        }

        System.out.println(inc);
    }
}
