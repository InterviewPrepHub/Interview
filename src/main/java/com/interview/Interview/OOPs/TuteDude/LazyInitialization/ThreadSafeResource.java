package com.interview.Interview.OOPs.TuteDude.LazyInitialization;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


//lazy initialization of a resource in a thread-safe manner using synchronized block
public class ThreadSafeResource {

    private String data;

    public synchronized String getData() {
        if(data == null) {
            System.out.println("initializing data");
            data = "Lazy initialization data";
        }
        return data;
    }

    //how could you access resource [data]
    //from multiple threads in a thread-safe manner?

    public static void main(String[] args) {

        ThreadSafeResource resource = new ThreadSafeResource();

        // Simulating multiple threads accessing the resource
        firstWay(resource);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName()+" : "+resource.getData());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        executor.execute(() -> System.out.println(resource.getData()));
        executor.execute(() -> System.out.println(resource.getData()));

        executor.shutdown();
    }

    private static void firstWay(ThreadSafeResource resource) {
        Thread thread1 = new Thread(() -> {
            System.out.println(resource.getData());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(resource.getData());
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
