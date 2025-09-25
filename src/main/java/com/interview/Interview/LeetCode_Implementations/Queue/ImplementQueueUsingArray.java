package com.interview.Interview.LeetCode_Implementations.Queue;

public class ImplementQueueUsingArray {

    private int SIZE = 5;
    private int[] items = new int[SIZE];
    private int front, rear;    //track the start and end of queue

    public ImplementQueueUsingArray() {
        front = -1;
        rear = -1;
    }

    public static void main(String[] args) {
        ImplementQueueUsingArray q = new ImplementQueueUsingArray();

        //attempt to dequeue from empty queue
        q.deQueue();

        //enqueue elements
        for (int i = 0; i <=5 ; i++) {
            q.enQueue(i);
        }

        q.deQueue();

        q.display();
    }

    public boolean isFull() {
        return (front == 0 && rear == SIZE-1);
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    //inserts elements at the rear end and updates the rear index.
    public void enQueue(int element) {
        if(isFull()) {
            System.out.println("Queue is full");
            return;
        }

        if (front == -1) {
            front = 0;  //mark front for the first element
        }
        rear++;
        items[rear] = element;
        System.out.println("Inserted : "+element);
    }

    public int deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        int element = items[front];
        if(front >=rear) {
            //queue has only one element, reset queue after removal
            front = -1;
            rear = -1;
        } else {
            front++;
        }
        System.out.println(element+" : deleted");
        return element;
    }

    public void display() {
        if(isEmpty()) {
            System.out.println("Empty queue");
            return;
        }
        System.out.println("Front index : "+front);
        System.out.print("Items -> ");

        for (int i = front; i <= rear; i++) {
            System.out.println(items[i]+" ");
        }
        System.out.println("Rear Index-> "+rear);
    }

}
