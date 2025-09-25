package com.interview.Interview.LeetCode_Implementations.Queue;

import java.util.Stack;

/*
Queue Implementation Using One Stack and Recursion
 */
public class ImplementQueueUsingStacks {

    Stack<Integer> s = new Stack<>();

    public static void main(String[] args) {

        ImplementQueueUsingStacks stk = new ImplementQueueUsingStacks();
        stk.enqueue(10);
        stk.enqueue(20);
        System.out.println(stk.dequeue());
        stk.enqueue(30);
        stk.enqueue(40);

        System.out.println(stk.dequeue());
        stk.enqueue(50);

        System.out.println(stk.s);

    }

    public void enqueue(int val){
        s.push(val);
    }

    public int dequeue() {

        if (s.isEmpty()) {
            return -1;
        }

        if (s.size() == 1) {
            return s.pop();
        }

        int top = s.pop();
        int res = dequeue();
        s.push(top);
        return res;

    }

}
