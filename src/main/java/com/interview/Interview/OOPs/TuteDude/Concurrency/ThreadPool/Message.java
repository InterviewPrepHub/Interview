package com.interview.Interview.OOPs.TuteDude.Concurrency.ThreadPool;

public class Message implements Runnable{

    int msg;

    public Message(int msg) {
        this.msg = msg;
    }


    @Override
    public void run() {
        System.out.println("Message : "+msg+" recieved by : "+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


/*
In a thread pool you can recycle your task.
 */