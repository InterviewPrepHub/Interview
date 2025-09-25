package com.interview.Interview.LeetCode_Implementations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ImplementThreadSafeLRUCacheII {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Simulate concurrent access
        for (int i = 1; i <= 5; i++) {
            int threadId = i;
            executor.submit(() -> {
                int key = threadId;
                cache.put(key, threadId);
                System.out.println("Thread-" + threadId + " put " + key);

                // Access cache
                for (int j = 1; j <= 5; j++) {
                    int k = j;
                    int value = cache.get(k);
                    System.out.println("Thread-" + threadId + " got " + k + ": " + value);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class LRUCacheII {

    private final int capacity;
    private final Map<Integer, DNode> cacheMap;
    private final DNode head;
    private final DNode tail;
    private final ReentrantLock lock = new ReentrantLock();  // NEW

    public LRUCacheII(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new DNode(-1, -1);
        this.tail = new DNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void put(int key, int value) {
        lock.lock();  // NEW
        try {
            if (cacheMap.containsKey(key)) {
                DNode oldNode = cacheMap.get(key);
                remove(oldNode);
            }

            DNode node = new DNode(key, value);
            cacheMap.put(key, node);
            addToHead(node);

            if (cacheMap.size() > capacity) {
                DNode nodeToDelete = tail.prev;
                remove(nodeToDelete);
                cacheMap.remove(nodeToDelete.key);
            }
        } finally {
            lock.unlock();  // NEW
        }
    }

    public int get(int key) {
        lock.lock();  // NEW
        try {
            if (!cacheMap.containsKey(key)) {
                return -1;
            }

            DNode node = cacheMap.get(key);
            remove(node);
            addToHead(node);
            return node.value;
        } finally {
            lock.unlock();  // NEW
        }
    }

    private void addToHead(DNode nodeToAdd) {
        // Already protected by lock from put/get
        DNode nextNode = head.next;
        head.next = nodeToAdd;
        nodeToAdd.prev = head;
        nodeToAdd.next = nextNode;
        nextNode.prev = nodeToAdd;
    }

    private void remove(DNode node) {
        // Already protected by lock from put/get
        DNode prevNode = node.prev;
        DNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private static class DNode {
        int key;
        int value;
        DNode next;
        DNode prev;

        public DNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}


// upon running it first time, i did get inconsistency issue
/*
Thread-3 put 3  //add 3  [3]
Thread-1 put 1  //add 1  [1<->3]
Thread-4 put 4  //add 4  [4<->1<->3]
Thread-2 put 2  //add 2  [2<->4<->1]
Thread-5 put 5  //add 5  [5<->2<->4]
Thread-2 got 1: 1   ---> 1 was evicted
Thread-2 got 2: -1  ---> 2 is not yet evicted, but it could happend that by the time thread 2 get the key 2, it was already evicted
Thread-1 got 1: 1
Thread-4 got 1: 1
Thread-4 got 2: -1
Thread-5 got 1: 1
Thread-5 got 2: -1
Thread-5 got 3: 3
Thread-3 got 1: 1
Thread-3 got 2: -1
Thread-2 got 3: 3
Thread-1 got 2: -1
Thread-4 got 3: 3
Thread-5 got 4: -1
Thread-3 got 3: 3
Thread-3 got 4: -1
Thread-2 got 4: -1
Thread-1 got 3: 3
Thread-4 got 4: -1
Thread-5 got 5: 5
Thread-3 got 5: 5
Thread-2 got 5: 5
Thread-1 got 4: -1
Thread-1 got 5: 5
Thread-4 got 5: 5
 */
