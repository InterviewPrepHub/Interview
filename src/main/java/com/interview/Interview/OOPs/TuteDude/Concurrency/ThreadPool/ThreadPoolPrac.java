package com.interview.Interview.OOPs.TuteDude.Concurrency.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolPrac {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable msg0 = new Message(0);
        executor.execute(msg0);

        Runnable msg1 = new Message(1);
        executor.execute(msg1);

        Runnable msg2 = new Message(2);
        executor.execute(msg2);

        Runnable msg3 = new Message(3);
        executor.execute(msg3);

//        executor.shutdown();
//        executor.shutdownNow(); // this will stop all the threads immediately even if executor is doing work so you get error

//        try {
//            executor.awaitTermination(3, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        while (!executor.isTerminated()) {
//
//        }

        System.out.println("everything done!");

    }
}


/*
you can recycle your threads ina threadpool
 */