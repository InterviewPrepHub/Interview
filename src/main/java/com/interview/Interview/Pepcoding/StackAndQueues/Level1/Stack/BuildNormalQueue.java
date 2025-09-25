package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

public class BuildNormalQueue {

    int maxSize;
    int[] arr;
    int front;

    BuildNormalQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
    }

    public static void main(String[] args) {
        BuildNormalQueue q = new BuildNormalQueue(4);

        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        System.out.println("peek : "+q.peek());
        System.out.println("remove : "+q.remove());
        System.out.println("peek : "+q.peek());
        System.out.println("remove : "+q.remove());
    }

    public int size() {
        return -1;
    }

    public void add(int i) {
        if(front==arr.length){
            System.out.println("queue overflow");
        }
        front++;
        arr[front] = i;
    }

    public int remove() {
        if(front == 0) {
            System.out.println("queue is empty");
        }
        int res = arr[arr.length-front-1];
        front--;
        return res;
    }

    public int peek() {
        return arr[arr.length-front-1];
    }

    public int isEmpty() {
        return front = -1;
    }
}
