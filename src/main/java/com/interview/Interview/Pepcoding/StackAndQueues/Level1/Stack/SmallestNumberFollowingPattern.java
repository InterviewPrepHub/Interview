package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.Stack;

public class SmallestNumberFollowingPattern {

    public static void main(String[] args) {

        String s = "ddidddid";

        Stack<Integer> st = new Stack<>();

        int num = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); //d,d,i

            if(c == 'd') {
                st.push(num);   //3,2,1
                num++;  //1,2,3,4
            } else {
                st.push(num);   //4
                num++;      // 5
                while (!s.isEmpty()) {
                    System.out.println(st.pop());   //4,5,3,2,1
                }
            }
        }
        st.push(num);
        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }

    }
}
