package com.interview.Interview.Interview2025.Rubrik.Round1;

/*
implement member functions of a queue with fixed buffer space with time cmplexity & space
 */
public class ImplementQueueWithFixedBuffer {

    public static void main(String[] args) {
        FixedSizeQueue queue = new FixedSizeQueue(3);

        System.out.println(queue.enqueue(10)); // true
        System.out.println(queue.enqueue(20)); // true
        System.out.println(queue.enqueue(30)); // true
        System.out.println(queue.enqueue(40)); // false, full

        System.out.println(queue.dequeue()); // 10
        System.out.println(queue.enqueue(40)); // true
        System.out.println(queue.peek()); // 20
    }
}

class FixedSizeQueue {
    private final int[] buffer;
    private final int capacity;
    private int front = 0;
    private int rear = 0;
    private int size = 0;

    public FixedSizeQueue(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
    }

    public boolean enqueue(int value) {
        if (isFull()) return false;

        buffer[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) return null;

        int value = buffer[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public Integer peek() {
        if (isEmpty()) return null;
        return buffer[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }
}
