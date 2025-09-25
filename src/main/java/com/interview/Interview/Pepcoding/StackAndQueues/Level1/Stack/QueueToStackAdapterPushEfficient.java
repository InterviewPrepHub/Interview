package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueToStackAdapterPushEfficient {

    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    QueueToStackAdapterPushEfficient(){
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }
    public static void main(String[] args) {

        QueueToStackAdapterPushEfficient s = new QueueToStackAdapterPushEfficient();
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println("top : " +s.top());
        System.out.println("pop : " +s.pop());
        System.out.println("top : " +s.top());

    }

    public void push(int val) {
        mainQ.add(val);
    }

    public int pop() {
        while(mainQ.size() != 1) {
            helperQ.add(mainQ.remove());
        }

        int popVal = mainQ.remove();

        while (!helperQ.isEmpty()) {
            mainQ.add(helperQ.remove());
        }
        return popVal;
    }

    public int top() {
        while(mainQ.size() != 1) {
            helperQ.add(mainQ.remove());
        }

        int topVal = mainQ.remove();
        helperQ.add(topVal);

        while (!helperQ.isEmpty()) {
            mainQ.add(helperQ.remove());
        }
        return topVal;
    }

}
