package com.interview.Interview.LeetCode_Implementations;

import org.interview.prep.code.leetcode.design.implementation.Node.LLNode;

public class ImplementStackUsingLinkedList {

    LLNode top;

    public ImplementStackUsingLinkedList() {
        top = null;
    }

    public static void main(String[] args) {
        ImplementStackUsingLinkedList impl = new ImplementStackUsingLinkedList();

        impl.push(1);
        impl.push(2);
        impl.push(3);
        impl.push(4);
        impl.push(5);
        System.out.println(impl.pop());
        System.out.println(impl.peek());
        System.out.println(impl.pop());
    }

    //Push: add element to top
    private void push(int val) {
        LLNode new_node = new LLNode(val);
        new_node.next = top;
        top = new_node;
    }

    //Pop: Remove and return top element
    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot pop from empty stack");
        }

        int val = top.data;
        top = top.next;
        return val;
    }

    //Peek: return the top element
    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
