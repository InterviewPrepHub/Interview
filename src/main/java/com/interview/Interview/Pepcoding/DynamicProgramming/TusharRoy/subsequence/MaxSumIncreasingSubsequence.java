package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.subsequence;

public class MaxSumIncreasingSubsequence {

    public static void main(String[] args) {

        int[] arr = {4,6,1,3,8,4,6}; // 4+6+8 = 18

        int[] s = new int[arr.length];
        int[] T = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            s[i]=arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            T[i] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]){
                    int sum = arr[i] + s[j];
                    if(sum > s[i]) {
                        s[i] = sum;
                        T[i] = j;
                    }
                }
            }
        }

        print(s);
    }

    private static void print(int[] s) {

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
            max = Math.max(max, s[i]);
        }
        System.out.println(max);
    }
}
