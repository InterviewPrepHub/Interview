package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

public class MinStack1 {

    int maxSize;
    int[] arr;
    int top;
    int[] minArr;
    int minTop;

    MinStack1(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        top = -1;
        minArr = new int[maxSize];
        minTop = -1;
    }

    public static void main(String[] args) {
        MinStack1 m = new MinStack1(5);
        m.push(10);
        m.push(20);
        m.push(30);
        m.push(5);
        m.push(8);

        System.out.println("peek : "+m.peek());
        System.out.println("pop : "+m.pop()); //8
        System.out.println("peek : "+m.peek());
        System.out.println("get min : "+m.getMin()); //5
        System.out.println("pop : "+m.pop()); //5
        System.out.println("get min : "+m.getMin()); //10
        System.out.println("pop : "+m.pop()); //30
        System.out.println("pop : "+m.pop()); //20
        System.out.println("pop : "+m.pop()); //10
        System.out.println("get min : "+m.getMin()); //10

    }

    public int size() {
        return -1;
    }

    public void push(int i) {

        if(top == arr.length) {
            System.out.println("Stack overflow");
        }

        top++;
        arr[top] = i;

        if(top == 0) {
            minTop++;
            minArr[minTop] = i;
        } else {
            if(arr[top] < arr[top-1]) {
                minTop++;
                minArr[minTop] = arr[top];
            }
        }


    }

    public int pop() {
        if(top < 0) {
            System.out.println("stack is empty");
        }
        int res = arr[top];
        top--;
        if(res == minArr[minTop] && minTop!=0) {
            minTop--;
        }
        return res;
    }

    public int peek() {
        return arr[top];
    }

    public int getMin() {
        if(top < 0) {
            System.out.println("no min element");
            return -1;
        }
        return minArr[minTop];
    }

    public int isEmpty() {
        return top = -1;
    }
}
