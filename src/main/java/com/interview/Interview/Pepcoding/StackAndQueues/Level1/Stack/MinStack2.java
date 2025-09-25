package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.Stack;

public class MinStack2 {

    private Stack<Integer> stack = new Stack<>();
    private int min;


    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            min = val;
        } else if (val < min) {
            // Store encoded value
            stack.push(2 * val - min);
            min = val;
        } else {
            stack.push(val);
        }
    }

    public int pop() {
        if (stack.isEmpty()) throw new RuntimeException("Stack is empty");

        int top = stack.pop();
        int poppedValue;
        if (top < min) {
            // top is encoded, return min and restore previous min
            poppedValue = (int) min;
            min = 2 * min - top; // restore min
        } else {
            poppedValue = (int) top;
        }
        return poppedValue;
    }

    public int top() {
        if (stack.isEmpty()) throw new RuntimeException("Stack is empty");

        long top = stack.peek();
        return top < min ? (int) min : (int) top;
    }

    public int getMin() {
        if (stack.isEmpty()) throw new RuntimeException("Stack is empty");
        return (int) min;
    }

    public static void main(String[] args) {
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2

    }
}
