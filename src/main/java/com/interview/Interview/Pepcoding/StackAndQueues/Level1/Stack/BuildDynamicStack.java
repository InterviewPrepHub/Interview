package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

public class BuildDynamicStack {

    int maxSize;
    int[] arr;
    int top;
    int[] narr;


    BuildDynamicStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[4];
        this.narr = new int[2*arr.length];
        top = -1;
    }

    public static void main(String[] args) {

        BuildDynamicStack s = new BuildDynamicStack(4);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        System.out.println("peek : "+s.peek());
        System.out.println("pop : "+s.pop());
        System.out.println("peek : "+s.peek());
        System.out.println("pop : "+s.pop());

        System.out.println("size : "+s.size());
    }

    public int size() {
        return top+1;
    }

    public boolean isEmpty() {
        if(arr.length == 0) {
            return true;
        }
        return false;
    }

    public void push(int a) {
        if(ifFull()) {
//            throw new StackOverflowError();
            //loop the previous arr and add all its connect to the new arr
            for (int i = 0; i < arr.length; i++) {
                narr[i] = arr[i];
            }

            //add the new element in the remaining empty spots of the arr & inc top subsequently
            top++;
            narr[top] = a;

        } else {
            top++;
            arr[top] = a;
        }
    }

    private boolean ifFull() {
        if(top == arr.length-1) {
            return true;
        }
        return false;
    }

    public int pop() {
        if(top == -1) {
            System.out.println("Stack underflow");
            return -1;
        } else if(top >= arr.length) {
            int res = narr[top];
            top--;
            return res;
        } else {
            int res = arr[top];
            top--;
            return res;
        }
    }

    public int peek() {
        if(top > arr.length-1) {
            return narr[top];
        }
        return arr[top];
    }
}
