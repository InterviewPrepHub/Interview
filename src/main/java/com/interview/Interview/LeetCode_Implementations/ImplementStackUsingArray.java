package com.interview.Interview.LeetCode_Implementations;

public class ImplementStackUsingArray {

    private int maxSize;
    private int top;
    private int[] stackArr;

    public ImplementStackUsingArray(int size) {
        this.maxSize = size;
        this.stackArr = new int[maxSize];
        this.top = -1;
    }

    public static void main(String[] args) {
        
        ImplementStackUsingArray stk = new ImplementStackUsingArray(5);
        //using array
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);
        stk.push(5);

        System.out.println(stk.peek());
        System.out.println(stk.pop());
        System.out.println(stk.peek());
    }

    private int pop() {
        if(isEmpty()) {
            System.out.println("Stack is empty.Cannot pop out any element");
        }
        return stackArr[top--];
    }

    private int peek() {
        if(isEmpty()) {
            System.out.println("Stack is empty.");
        }
        return stackArr[top];
    }

    private boolean isEmpty() {
        return (top == -1);
    }

    private void push(int val) {
        if(isFull()) {
            return;
        }
        // It first increments top by 1, then uses the new value.
        stackArr[++top] = val;
    }

    private boolean isFull() {
        return (top == maxSize-1);
    }


}
