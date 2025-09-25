package com.interview.Interview.LeetCode_Implementations.Queue;

import com.interview.Interview.Pepcoding.Node.LLNode;

public class ImplementQueueUsingLinkedList {

    private LLNode front;
    private LLNode rear;

    ImplementQueueUsingLinkedList() {
        front = null;
        rear = null;
    }

    public static void main(String[] args) {

        ImplementQueueUsingLinkedList l = new ImplementQueueUsingLinkedList();

        l.enqueue(10);
        l.enqueue(20);

        l.display();

        l.dequeue();
        l.dequeue();

    }

    public void enqueue(int value) {
        LLNode newNode = new LLNode(value);

        //if queue is empty
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        System.out.println("Enqueued : "+ value);
    }

    public int dequeue() {
        if (front == null) {
            System.out.println("Queue is empty");
            return -1;
        }

        int element = front.data;
        LLNode newFront = front.next;
        front.next = null;
        front = newFront;

        if(front == null) {
            rear = null;
        }

        System.out.println("Dequeued : "+element);
        return element;
    }

    public void display() {

        LLNode temp = front;

        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }

        System.out.println();

    }
}
