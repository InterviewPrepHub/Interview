package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.Stack;

public class StackToQueueAdapterRemoveEfficient {

    Stack<Integer> mainS = new Stack<>();
    Stack<Integer> helperS = new Stack<>();

    public static void main(String[] args) {

        StackToQueueAdapterRemoveEfficient s = new StackToQueueAdapterRemoveEfficient();
        s.add(10);
        s.add(20);
        s.add(30);
        System.out.println("peek : "+ s.peek());
        System.out.println("pop : "+ s.remove());
        System.out.println("peek : "+ s.peek());
    }

    public void add(int val) { //10,20

        while (!mainS.isEmpty()) {
            helperS.push(mainS.pop()); //helperS = 10, mainS = {}
        }

        if (mainS.isEmpty()) {
            mainS.push(val); // mainS = 20
        }

        while(!helperS.isEmpty()) {
            mainS.push(helperS.pop());  //main = 10,20
        }


    }

    public int remove() {
        return mainS.pop();
    }

    public int peek() {
        return mainS.peek();
    }
}
