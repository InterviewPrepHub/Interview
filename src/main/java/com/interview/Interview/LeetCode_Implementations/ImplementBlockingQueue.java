package com.interview.Interview.LeetCode_Implementations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImplementBlockingQueue {

    public static void main(String[] args) {

        MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(3);

        ExecutorService executorService = Executors.newFixedThreadPool(2); //2 threads : one prodcuder, one consumer

        executorService.submit(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    blockingQueue.enqueue(i);
                    System.out.println("Produced : "+i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        executorService.submit(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    int val = blockingQueue.dequeue();
                    System.out.println("Consumed : "+val);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        /*try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        executorService.shutdown();
    }
}

class MyBlockingQueue<T> {

    private Object[] queue;
    private int capacity;
    private int size = 0;
    private int front = 0;
    private int rear = 0;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Object[capacity];
    }

    //insert element in the queue, waiting for the space to become available
    //When you try to enqueue (put) into a full queue (bounded capacity),
    //the calling thread is blocked until space becomes available.
    public synchronized void enqueue(T item) throws InterruptedException {
        if(size==capacity) {
            wait(); //wait until space is available
        }

        queue[rear] = item;
        rear=(rear+1) % capacity;
        size++;
        notifyAll();    //notify all the waiting threads that elements is available
    }

    //retrieves and removes the head of the queue, waiting until an element becomes available
    //When you try to dequeue (take) from an empty queue, the calling thread is blocked (waits)
    //until an element becomes available.
    public synchronized T dequeue() throws InterruptedException {

        while (size == 0) {
            wait();
        }

        T element = (T) queue[front];
        queue[front] = null; //GC
        front = (front+1) % capacity;
        size--;
        notifyAll();
        return element;
    }

    public synchronized int size() {
        return size;
    }
}


















