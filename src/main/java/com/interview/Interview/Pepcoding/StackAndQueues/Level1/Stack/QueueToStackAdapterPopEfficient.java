package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.ArrayDeque;
import java.util.Queue;

//Pop efficient
//two queues to create one stack
public class QueueToStackAdapterPopEfficient {

    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    QueueToStackAdapterPopEfficient() {
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }

    public static void main(String[] args) {

        QueueToStackAdapterPopEfficient q = new QueueToStackAdapterPopEfficient();
        q.push(10);
        q.push(20);
        q.push(30);
        System.out.println(q.pop());

    }

    // this is the main logic method
    public void push(int val) {
        if(mainQ.isEmpty()) {
            mainQ.add(val);
        } else {
            helperQ.add(val); //20
            while(!mainQ.isEmpty()) {
                int data = mainQ.remove();
                helperQ.add(data); //20,10
            }

            while (!helperQ.isEmpty()) {
                mainQ.add(helperQ.remove());
            }

        }

    }

    //below methods are straightforward
    public int size() {
        return mainQ.size();
    }

    public int pop() {  //pop efficient - o(1)
        if(mainQ.isEmpty()) {
            System.out.println("stack underflow");
            return -1;
        }
        return mainQ.remove();
    }

    public int peek() {
        if(mainQ.size() == 0) {
            System.out.println("stack underflow");
            return -1;
        }
        return mainQ.peek();
    }
}
