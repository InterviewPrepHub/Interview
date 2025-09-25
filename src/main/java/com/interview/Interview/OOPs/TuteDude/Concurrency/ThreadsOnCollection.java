package com.interview.Interview.OOPs.TuteDude.Concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadsOnCollection {

    public static void main(String[] args) {

        Mine m = new Mine();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m.add();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m.print();
            }
        });


        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        t2.start();
    }
}


class Mine {

    List<Integer> list = new CopyOnWriteArrayList<>();

    public void add() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            System.out.println("Added");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print() {
        for(Integer i: list) {
            System.out.println(i);
            System.out.println("Printed");
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/*
while running this code, I sometimes get the below exception

Exception in thread "Thread-1" java.util.ConcurrentModificationException
	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1096)
	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1050)
	at org.interview.prep.code.testing.Concurrency.Mine.print(ThreadsOnCollection.java:56)
	at org.interview.prep.code.testing.Concurrency.ThreadsOnCollection$2.run(ThreadsOnCollection.java:22)
	at java.base/java.lang.Thread.run(Thread.java:1570)


this happens when a thread is trying to iterate & read a collection it while the other thread is trying to modify it.

thread t1 is trying to add which is modifying and t2 is trying to read and print it

current modification is not allowed while iterating arraylist


Solution:

1. use synchronised keyword for method
2. we can use t1.join() to make sure t1 is finished before t2 starts. Here t1.join() will wait for t1 to finish and join this t1 thread to main thread
but the issue is that this is not multi threaded application

3. CopyOnWriteArrayList -> CopyOnWriteArrayList creates a new underlying array before any modification operation,
ensuring that iterators do not throw ConcurrentModificationException

4. using ReentrantLock -> flexible locking mechanism



 */