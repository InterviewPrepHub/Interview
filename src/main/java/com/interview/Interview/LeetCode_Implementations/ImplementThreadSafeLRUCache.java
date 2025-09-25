package com.interview.Interview.LeetCode_Implementations;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
To implement an LRU (Least Recently Used) cache in Java with thread safety and high concurrency,
you can utilize the ConcurrentLinkedHashMap library. This library combines a ConcurrentHashMap
with a linked list to maintain access order, ensuring efficient concurrent access and automatic
eviction of the least recently used entries.

https://codesignal.com/learn/courses/advanced-real-life-concurrency-challenges/lessons/implementing-a-thread-safe-lru-cache-with-high-concurrency?utm_source=chatgpt.com

 */
public class ImplementThreadSafeLRUCache {

    public static void main(String[] args) {

        /*LRUCacheThreadSafe<Integer, Integer> lru = new LRUCacheThreadSafe<>(3);
        lru.put(1,10);
        lru.put(2,20);
        lru.put(3,30);
        System.out.println(lru.get(2));
        lru.put(4,40);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));*/

        LRUCacheThreadSafe<String, String> cache = new LRUCacheThreadSafe<>(3);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Simulate concurrent access
        for (int i = 1; i <= 5; i++) {
            int threadId = i;
            executor.submit(() -> {
                String key = "key" + threadId;
                cache.put(key, "value" + threadId);
                System.out.println("Thread-" + threadId + " put " + key);

                // Access cache
                for (int j = 1; j <= 5; j++) {
                    String k = "key" + j;
                    String value = cache.get(k);
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

class LRUCacheThreadSafe<K,V> {

    private final ConcurrentMap<K,V> cache;

    public LRUCacheThreadSafe(int capacity) {
        this.cache = new ConcurrentLinkedHashMap.Builder<K,V>()
                .maximumWeightedCapacity(capacity)
                .build();
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }
}
