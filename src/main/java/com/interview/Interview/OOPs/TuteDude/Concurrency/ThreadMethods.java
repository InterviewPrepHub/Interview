package com.interview.Interview.OOPs.TuteDude.Concurrency;

public class ThreadMethods {

    public static void main(String[] args) {

//        System.out.println("Thread 1 is starting");
//        Task1 t1 = new Task1("T1");
//        t1.start();
//
//        System.out.println("Thread 2 is starting");
//        Task1 t2 = new Task1("T2");
//        t2.start();

        System.out.println("Thread 3 is starting");
        Task2 t3 = new Task2("T3");
//        t3.run();
        Thread t3Thread = new Thread(t3);
        t3Thread.start();

        System.out.println("Thread 4 is starting");
        Task2 t4 = new Task2("T4");
//        t4.run();
        Thread t4Thread = new Thread(t4);
        t4Thread.start();

        //when we implement Runnable interface, this code works like a normal java code and not multi threaded





    }


}


class Task1 extends Thread {

    String name;

    public Task1(String name) {
        this.name= name;
    }
    @Override
    public void run() {
        Thread.currentThread().setName(this.name);
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"->"+i);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class Task2 implements Runnable {

    String name;

    public Task2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.name);
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"->"+i);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}