package com.interview.Interview.Pepcoding.HashMapAndHeap;

import com.interview.Interview.Pepcoding.Node.HMNode;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Implement a HashMap without using any built-in hash table libraries.
 */
public class DesignHashMap {

    public static void main(String[] args) {

        /*MyHashMap map = new MyHashMap();
        map.put(1,20);
        map.put(2,10);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(2,30);
        System.out.println(map.get(2));
        map.put(3,40);
        map.remove(2);
        System.out.println(map.get(2));
        System.out.println(map.get(3));*/

        //Concurrency test on simple hashmap

        MyHashMap map = new MyHashMap();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // submit 100 tasks concurrently
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int key = i % 10; // keys between 0 and 9
            executor.submit(() -> {
                map.put(key, key * 10);
                System.out.println(Thread.currentThread().getName() + " put: " + key);
            });
            executor.submit(() -> {
                int value = map.get(key);
                System.out.println(Thread.currentThread().getName() + " get: " + key + " -> " + value);
            });
            executor.submit(() -> {
                map.remove(key);
                System.out.println(Thread.currentThread().getName() + " removed: " + key);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finished all tasks");
    }

    /*
    was able to get below issue in response. This shows
    Values being overwritten or lost: Several threads are putting and removing values, and the get operations often return -1
    (meaning the key is not found) even when other threads have supposedly inserted the key. This suggests that some put or
    remove operations are overwriting or deleting data in a way that's not thread-safe.

> Task :org.interview.prep.code.leetcode.design.implementation.Pepcoding.HashMapAndHeap.DesignHashMap.main()
pool-1-thread-4 put: 1
pool-1-thread-3 removed: 0
pool-1-thread-1 put: 0
pool-1-thread-1 removed: 1
pool-1-thread-1 removed: 2
pool-1-thread-4 put: 2
pool-1-thread-1 put: 3
pool-1-thread-1 removed: 3
pool-1-thread-1 put: 4
pool-1-thread-4 get: 3 -> -1
pool-1-thread-4 removed: 4
pool-1-thread-2 get: 0 -> -1
pool-1-thread-3 get: 2 -> -1
pool-1-thread-1 get: 4 -> 40
pool-1-thread-5 get: 1 -> -1
pool-1-thread-2 get: 5 -> -1
pool-1-thread-3 removed: 5
pool-1-thread-5 get: 6 -> -1
pool-1-thread-5 get: 7 -> -1
pool-1-thread-2 removed: 6
pool-1-thread-5 removed: 7
pool-1-thread-5 get: 8 -> -1
pool-1-thread-5 removed: 8
pool-1-thread-4 put: 5
pool-1-thread-3 put: 7
pool-1-thread-4 get: 9 -> -1
pool-1-thread-1 put: 6
pool-1-thread-3 removed: 9
pool-1-thread-1 get: 0 -> 0
pool-1-thread-3 removed: 0
pool-1-thread-2 put: 8
pool-1-thread-3 get: 1 -> -1
pool-1-thread-5 put: 9
pool-1-thread-4 put: 0
pool-1-thread-2 removed: 1
pool-1-thread-4 removed: 2
pool-1-thread-5 get: 2 -> 20
pool-1-thread-5 removed: 3
pool-1-thread-4 get: 3 -> -1
pool-1-thread-4 get: 4 -> -1
pool-1-thread-4 removed: 4
pool-1-thread-1 put: 1
pool-1-thread-1 get: 5 -> 50
pool-1-thread-1 removed: 5
pool-1-thread-3 put: 2
pool-1-thread-3 get: 6 -> 60
pool-1-thread-3 removed: 6
pool-1-thread-2 put: 3
pool-1-thread-5 put: 4
pool-1-thread-4 put: 5
pool-1-thread-2 get: 7 -> 70
pool-1-thread-4 removed: 7
pool-1-thread-1 put: 6
pool-1-thread-3 put: 7
pool-1-thread-2 get: 8 -> 80
pool-1-thread-1 removed: 8
pool-1-thread-4 put: 8
pool-1-thread-2 get: 9 -> 90
pool-1-thread-5 removed: 9
pool-1-thread-4 get: 0 -> -1
pool-1-thread-4 get: 1 -> 10
pool-1-thread-3 put: 9
pool-1-thread-1 put: 0
pool-1-thread-5 removed: 0
pool-1-thread-4 removed: 1
pool-1-thread-5 removed: 2
pool-1-thread-1 get: 2 -> -1
pool-1-thread-5 get: 3 -> 30
pool-1-thread-1 removed: 3
pool-1-thread-2 put: 1
pool-1-thread-1 get: 4 -> 40
pool-1-thread-2 removed: 4
pool-1-thread-2 get: 5 -> -1
pool-1-thread-2 removed: 5
pool-1-thread-3 put: 2
pool-1-thread-3 get: 6 -> -1
pool-1-thread-3 removed: 6
pool-1-thread-1 put: 5
pool-1-thread-4 put: 3
pool-1-thread-4 removed: 7
pool-1-thread-1 get: 7 -> -1
pool-1-thread-5 put: 4
pool-1-thread-1 get: 8 -> -1
pool-1-thread-5 removed: 8
pool-1-thread-2 put: 6
pool-1-thread-5 get: 9 -> -1
pool-1-thread-2 removed: 9
pool-1-thread-2 get: 0 -> 0
pool-1-thread-2 removed: 0
pool-1-thread-3 put: 7
pool-1-thread-1 put: 9
pool-1-thread-4 put: 8
pool-1-thread-5 put: 0
pool-1-thread-2 put: 1
pool-1-thread-5 get: 1 -> 10
pool-1-thread-3 get: 2 -> 20
pool-1-thread-2 removed: 2
pool-1-thread-1 removed: 1
pool-1-thread-3 get: 3 -> -1
pool-1-thread-2 removed: 3
pool-1-thread-2 get: 4 -> -1
pool-1-thread-1 removed: 4
pool-1-thread-1 get: 5 -> 50
pool-1-thread-1 removed: 5
pool-1-thread-4 put: 2
pool-1-thread-3 put: 4
pool-1-thread-2 put: 5
pool-1-thread-3 get: 6 -> 60
pool-1-thread-1 put: 6
pool-1-thread-5 put: 3
pool-1-thread-1 removed: 7
pool-1-thread-2 removed: 6
pool-1-thread-1 get: 8 -> 80
pool-1-thread-4 get: 7 -> 70
pool-1-thread-2 removed: 8
pool-1-thread-2 removed: 9
pool-1-thread-4 get: 9 -> 90
pool-1-thread-4 get: 0 -> -1
pool-1-thread-4 removed: 0
pool-1-thread-3 put: 7
pool-1-thread-3 get: 1 -> -1
pool-1-thread-5 put: 8
pool-1-thread-3 removed: 1
pool-1-thread-3 get: 2 -> -1
pool-1-thread-3 removed: 2
pool-1-thread-1 put: 9
pool-1-thread-1 get: 3 -> 30
pool-1-thread-1 removed: 3
pool-1-thread-2 put: 0
pool-1-thread-4 put: 1
pool-1-thread-5 put: 2
pool-1-thread-3 put: 3
pool-1-thread-2 get: 4 -> 40
pool-1-thread-4 removed: 4
pool-1-thread-1 put: 4
pool-1-thread-3 get: 5 -> -1
pool-1-thread-1 get: 6 -> -1
pool-1-thread-2 removed: 5
pool-1-thread-2 get: 7 -> -1
pool-1-thread-3 removed: 6
pool-1-thread-2 removed: 7
pool-1-thread-2 get: 8 -> -1
pool-1-thread-2 removed: 8
pool-1-thread-5 put: 5
pool-1-thread-5 get: 9 -> -1
pool-1-thread-5 removed: 9
pool-1-thread-4 put: 6
pool-1-thread-4 get: 0 -> 0
pool-1-thread-4 removed: 0
pool-1-thread-1 put: 7
pool-1-thread-2 put: 9
pool-1-thread-3 put: 8
pool-1-thread-1 get: 1 -> 10
pool-1-thread-2 removed: 1
pool-1-thread-5 put: 0
pool-1-thread-2 removed: 2
pool-1-thread-1 get: 2 -> 20
pool-1-thread-4 put: 1
pool-1-thread-2 get: 3 -> -1
pool-1-thread-2 get: 4 -> -1
pool-1-thread-1 removed: 3
pool-1-thread-2 removed: 4
pool-1-thread-2 get: 5 -> 50
pool-1-thread-2 removed: 5
pool-1-thread-3 put: 2
pool-1-thread-3 get: 6 -> 60
pool-1-thread-3 removed: 6
pool-1-thread-5 put: 3
pool-1-thread-5 get: 7 -> 70
pool-1-thread-5 removed: 7
pool-1-thread-4 put: 4
pool-1-thread-4 get: 8 -> 80
pool-1-thread-4 removed: 8
pool-1-thread-1 put: 5
pool-1-thread-1 get: 9 -> 90
pool-1-thread-1 removed: 9
pool-1-thread-2 put: 6
pool-1-thread-2 get: 0 -> -1
pool-1-thread-2 removed: 0
pool-1-thread-3 put: 7
pool-1-thread-3 get: 1 -> -1
pool-1-thread-3 removed: 1
pool-1-thread-5 put: 8
pool-1-thread-5 get: 2 -> -1
pool-1-thread-5 removed: 2
pool-1-thread-4 put: 9
pool-1-thread-4 get: 3 -> 30
pool-1-thread-4 removed: 3
pool-1-thread-1 put: 0
pool-1-thread-1 get: 4 -> 40
pool-1-thread-2 put: 1
pool-1-thread-3 put: 2
pool-1-thread-3 get: 5 -> -1
pool-1-thread-4 put: 4
pool-1-thread-1 removed: 4
pool-1-thread-5 put: 3
pool-1-thread-2 put: 5
pool-1-thread-3 removed: 5
pool-1-thread-5 removed: 6
pool-1-thread-1 get: 6 -> -1
pool-1-thread-3 get: 7 -> -1
pool-1-thread-3 get: 8 -> -1
pool-1-thread-3 removed: 8
pool-1-thread-5 removed: 7
pool-1-thread-5 get: 9 -> -1
pool-1-thread-4 put: 6
pool-1-thread-5 removed: 9
pool-1-thread-5 get: 0 -> 0
pool-1-thread-5 removed: 0
pool-1-thread-2 put: 7
pool-1-thread-2 get: 1 -> 10
pool-1-thread-2 removed: 1
pool-1-thread-1 put: 8
pool-1-thread-1 get: 2 -> 20
pool-1-thread-1 removed: 2
pool-1-thread-3 put: 9
pool-1-thread-3 get: 3 -> -1
pool-1-thread-3 removed: 3
pool-1-thread-5 put: 1
pool-1-thread-4 put: 0
pool-1-thread-5 get: 4 -> -1
pool-1-thread-4 removed: 4
pool-1-thread-4 get: 5 -> -1
pool-1-thread-4 removed: 5
pool-1-thread-2 put: 2
pool-1-thread-2 get: 6 -> 60
pool-1-thread-2 removed: 6
pool-1-thread-1 put: 3
pool-1-thread-3 put: 4
pool-1-thread-1 get: 7 -> 70
pool-1-thread-3 removed: 7
pool-1-thread-3 get: 8 -> 80
pool-1-thread-3 removed: 8
pool-1-thread-5 put: 5
pool-1-thread-5 get: 9 -> 90
pool-1-thread-4 put: 6
pool-1-thread-5 removed: 9
pool-1-thread-5 get: 0 -> -1
pool-1-thread-5 removed: 0
pool-1-thread-2 put: 7
pool-1-thread-2 get: 1 -> -1
pool-1-thread-2 removed: 1
pool-1-thread-3 put: 9
pool-1-thread-4 put: 0
pool-1-thread-1 put: 8
pool-1-thread-4 removed: 2
pool-1-thread-3 get: 2 -> -1
pool-1-thread-4 get: 3 -> 30
pool-1-thread-3 removed: 3
pool-1-thread-3 get: 4 -> 40
pool-1-thread-3 removed: 4
pool-1-thread-5 put: 1
pool-1-thread-5 get: 5 -> 50
pool-1-thread-5 removed: 5
pool-1-thread-1 put: 3
pool-1-thread-4 put: 4
pool-1-thread-3 put: 5
pool-1-thread-2 put: 2
pool-1-thread-2 get: 7 -> -1
pool-1-thread-3 put: 7
pool-1-thread-1 get: 6 -> 60
pool-1-thread-4 removed: 6
pool-1-thread-5 put: 6
pool-1-thread-3 get: 8 -> -1
pool-1-thread-1 removed: 7
pool-1-thread-3 removed: 8
pool-1-thread-1 get: 9 -> -1
pool-1-thread-3 removed: 9
pool-1-thread-3 get: 0 -> 0
pool-1-thread-3 removed: 0
pool-1-thread-3 get: 1 -> 10
pool-1-thread-3 removed: 1
pool-1-thread-2 put: 8
pool-1-thread-2 get: 2 -> 20
pool-1-thread-2 removed: 2
pool-1-thread-4 put: 9
pool-1-thread-4 get: 3 -> -1
pool-1-thread-4 removed: 3
pool-1-thread-1 put: 0
pool-1-thread-1 get: 4 -> -1
pool-1-thread-1 removed: 4
pool-1-thread-5 put: 1
pool-1-thread-5 get: 5 -> -1
pool-1-thread-3 put: 2
pool-1-thread-5 removed: 5
pool-1-thread-5 get: 6 -> -1
pool-1-thread-5 removed: 6
pool-1-thread-2 put: 3
pool-1-thread-4 put: 4
pool-1-thread-5 put: 7
pool-1-thread-3 put: 6
pool-1-thread-2 get: 7 -> 70
pool-1-thread-1 put: 5
pool-1-thread-1 get: 8 -> 80
pool-1-thread-3 removed: 7
pool-1-thread-3 get: 9 -> 90
pool-1-thread-3 removed: 9
pool-1-thread-4 removed: 8
pool-1-thread-2 put: 8
pool-1-thread-1 put: 9
Finished all tasks

BUILD SUCCESSFUL in 2s
2 actionable tasks: 2 executed
8:27:19 pm: Execution finished ':org.interview.prep.code.leetcode.design.implementation.Pepcoding.HashMapAndHeap.DesignHashMap.main()'.

     */

}

class MyHashMap {

    //array of buckets, and each bucket is a linked list
    //each bucket store (key, value) pair in a list
    //hash the key to find the correct bucket => hash(key) % capacity
    //search bucket's list for key
    private HMNode[] buckets;
    public static final int SIZE = 1000; //fixed capacity

    public MyHashMap() {
        buckets = new HMNode[SIZE];
    }

    public int hash(int key) {
        return Integer.hashCode(key) % SIZE;
    }


    public void put(int key, int value) {
        int index = hash(key);
        HMNode head = buckets[index];

        HMNode curr = head;
        while (curr!=null) {
            if(curr.key == key) {
                curr.value = value; //updating existing key
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }
                return;
            }
            curr = curr.next;
        }

        //if key is not found then create a new Node with key and value and add to head
        HMNode new_node = new HMNode(key, value);
        new_node.next = head;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
        buckets[index] = new_node;
    }

    public int get(int key) {
        int index = hash(key);
        HMNode node = buckets[index];
        while (node !=null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return -1;
    }

    void remove(int key) {
        int index = hash(key);
        HMNode curr = buckets[index];
        HMNode prev = null;

        while (curr!=null) {
            if (curr.key == key) {
                if (prev == null) {
                    buckets[index] = curr.next; //remove head node
                } else {
                    prev.next = curr.next; //remove somewhere in middle or tail node
                }
            }
            prev = curr;
            curr = curr.next;
        }
    }
}
