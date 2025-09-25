package com.interview.Interview.Pepcoding.HashMapAndHeap;

import com.interview.Interview.Pepcoding.Node.LockFreeNode;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
Java provides a ConcurrentHashMap which uses lock striping internally and is well-optimized for concurrency.
Java’s ConcurrentHashMap uses a combination of CAS (compare-and-swap) and finer-grained locking.


Here’s what we’d design:
	•	Buckets: An array of buckets, each bucket is typically a linked list or better, a lock-free linked list.
	•	CAS-based atomic operations: For lock-free insertions/removals.

CAS (Compare-And-Swap):
	•	CAS lets you atomically:
	•	Read a value
	•	Compare it with an expected value
	•	Swap in a new value if the value hasn’t changed (i.e. no other thread updated it).
	•	Achieved via java.util.concurrent.atomic package (AtomicReference, AtomicInteger, etc.)
	•	Avoids blocking like synchronized, reduces contention.

When we write multithreaded programs in Java (meaning programs where multiple tasks run at the same time),
we sometimes want to change a value (like a variable or a link in a data structure) without two threads messing
each other up. Imagine two people writing in the same notebook at the same time—it can get messy!

Java gives us special atomic classes in the package java.util.concurrent.atomic, like:
	•	AtomicReference<T>
	•	AtomicInteger
	•	AtomicLong

These classes let us:
✅ Read a value.
✅ Check a condition (e.g. is the value what I expect?).
✅ Update the value—but only if nobody else changed it in the meantime.

This read-check-update happens all at once—in one step, so no other thread can sneak in between these steps and mess things up.

🧪 What does AtomicReference do?

Let’s imagine we have a linked list, and one node points to the next. If we want to change where it points (for example,
inserting a new node at the start), two threads might try to do this at the same time.

Instead of:
head = newNode;
(which can get messed up by another thread)

We use:
head.compareAndSet(expectedHead, newNode);

This means:
	•	“If head is still what I think it is (expectedHead), then change it to newNode—otherwise, don’t change it.”
	•	This is atomic—it happens all at once.

⸻

🔒 Why not just use locks?

Locks are also used to protect shared data:

synchronized (lock) {
    // do something
}

But locks can be slow and can cause deadlocks (threads waiting forever).

Atomic classes give us a lock-free way to update data, which is:
✅ Faster
✅ More scalable
✅ Less likely to freeze

 */
public class DesignConcurrentHashMap {

    private final int SIZE = 16;
    private final AtomicReference<LockFreeNode>[] buckets;

    public DesignConcurrentHashMap() {
        buckets = new AtomicReference[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new AtomicReference<>(null);
        }
    }


    public static void main(String[] args) {
        DesignConcurrentHashMap map = new DesignConcurrentHashMap();
        map.put(1,20);
        System.out.println(map.get(1));
        map.put(2,10);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));

        System.out.println("Concurrency test");


        //concurrency test
        /*
        A thread pool with, say, 10 threads.
	•	Each thread will perform:
	•	Randomized put calls (to insert new keys).
	•	Randomized get calls (to read values).
	•	Randomized remove calls (to delete keys).
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3); //say there are 5 worker threads
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {

                //Submitting Tasks to the Thread Pool
                for (int j = 0; j < 1000; j++) {    //each thread performs 1000 operations
                    //generating random numbers so that each thread picks different key and values
                    int key = random.nextInt(100);
                    int value = random.nextInt(1000);
                    map.put(key, value);    //simulate writing

                    int fetchValue = map.get(key);  //simulate reading
                    if(random.nextInt(5) < 3) {    //randomly generate number between 0-9 and check if the number is < 3 [0,1,2]
                        map.remove(key);
                    }
                }

                executorService.shutdown();

                try {
                    if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                        executorService.shutdownNow();
                        System.err.println("Threads didn't finish in time");
                    }
                } catch (InterruptedException e) {
                    executorService.shutdownNow();
                    Thread.currentThread().interrupt();
                }

                //check some final state --> spot check
                for (int j = 0; j < 3; j++) {
                    System.out.println("key : "+j+" value : "+map.get(j));
                }

            });
        }
    }

    private int hash(int key) {
        return Integer.hashCode(key) % SIZE;
    }

    public void put(int key, int value) {

        int index = hash(key);
        AtomicReference<LockFreeNode> bucket = buckets[index];

        LockFreeNode head;
        while(true) {
            head = bucket.get();
            LockFreeNode curr = head;

            while (curr!=null) {
                if(curr.key==key) {
                    curr.value = value;
                    return;
                }
                curr = curr.next.get();
            }

            LockFreeNode new_node = new LockFreeNode(key, value);
            new_node.next.set(head);
            if(bucket.compareAndSet(head, new_node)) {
                return;
            }
        }

    }

    public Integer get(int key) {

        int index = hash(key);
        LockFreeNode node = buckets[index].get();

        while (node!=null) {
            if(node.key == key) {
                return node.value;
            }
            node = node.next.get();
        }
        return -1;
    }

    public void remove(int key) {
        int index = hash(key);
        AtomicReference<LockFreeNode> bucket = buckets[index];

        while (true) {
            LockFreeNode head = bucket.get();
            LockFreeNode curr = head;
            LockFreeNode prev = null;

            while (curr!=null) {
                if (curr.key == key) {
                    if (prev == null) {
                        //remove head
                        if(bucket.compareAndSet(head, curr.next.get())) {
                            return;
                        }
                        break;
                    }else {
                        //remove middle
                        if(prev.next.compareAndSet(curr, curr.next.get())) {
                            return;
                        }
                        break;
                    }
                }
                prev = curr;
                curr = curr.next.get();
            }
            return;
        }
    }
}
