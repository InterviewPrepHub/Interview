package com.interview.Interview.OOPs.TuteDude.Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronisedPrac {

    public static void main(String[] args) {
        Task4 task4 = new Task4();

        Work w1 = new Work(task4);
        w1.start();

        Work w2 = new Work(task4);
        w2.start();
    }
}

/*

ensure that only one thread can execute this method at a time. This prevents the race condition where
multiple threads read and modify the value variable concurrently.

 */
class Task4 {
    int value = 0;

    public synchronized int increase() {
        value+=1;
        return value;
    }

    //AtmoicInteger performs atomic operations on integers in a thread safe way
    AtomicInteger val = new AtomicInteger();

    public int atomicIncrease() {
        return val.incrementAndGet();
    }
}

class Work extends Thread {

    Task4 task4 = null;

    public Work(Task4 task4) {
        this.task4 = task4;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "->" + task4.increase());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
