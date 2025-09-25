package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.Stack;

public class LargestAreaHistogram {

    public static void main(String[] args) {
        int[] a = {6,2,5,4,5,1,6};
        //         0,1,2,3,4,5,6

        int n = a.length;   //7

        int[] rb = new int[n];
        Stack<Integer> s1= new Stack<>();
        s1.push(n-1);   //6
        rb[n-1] = n;    //7 -> because if we cannot find the smallest element on the right side then the len of arr is the boundary

        for (int i = n-2; i <=0 ; i--) {    //
            while (s1.size() > 0 && a[i] < a[s1.peek()]) {
                s1.pop();
            }
            if (s1.size() == 0) {
                rb[i] = n;
            } else {
                rb[i] = s1.peek();
            }
            s1.push(i);
        }


        int[] lb = new int[n];


        s1.push(0);
        lb[0] = -1;
        for (int i = 1; i < n; i++) {
            while(!s1.isEmpty() && a[i] < a[s1.peek()]) {
                s1.pop();
            }
            if (s1.size() == 0) {
                lb[i] = -1;
            } else {
                lb[i] = s1.peek();
            }
            s1.push(i);
        }

        int max_area = 0;

        for (int i = 0; i < n; i++) {
            int width = rb[i] - lb[i] - 1;
            int area = a[i] * width;
            max_area = Math.max(max_area, area);
        }

        System.out.println(max_area);


    }
}
