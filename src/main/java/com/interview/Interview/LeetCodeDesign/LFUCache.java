package com.interview.Interview.LeetCodeDesign;

import java.util.HashMap;

/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

🔹 What is LFU Cache?

Cache = a small storage that keeps recently used items so we don’t always go to slow storage (like DB/disk).

LFU (Least Frequently Used) cache = when the cache is full and we need to add a new item, we evict (remove)
the item that has been used the least number of times.
If multiple items have the same lowest frequency, we evict the one that is least recently used (LRU) among them.

So:
👉 Priority = use count first (frequency), then recency.

The functions get and put must each run in O(1) average time complexity.

🔹 Components Inside LFU

Node → each entry with (key, value, freq)
Example: key=1, value=1, freq=2 (means key=1’s value=1, accessed twice so far).

Frequency Buckets → group of nodes that have the same frequency.
Each bucket is a doubly linked list to maintain recency order (so we know which to remove first).

HashMaps for fast access:

nodeMap: key → node (so we can fetch/update quickly in O(1)).
freqMap: frequency → list of nodes with this frequency.
minFreq → always tracks the smallest frequency present in the cache (so we know where to evict from).

 */

public class LFUCache {
    private final int capacity;
    private int size;
    private int minFreq;
    private final HashMap<Integer, Node> nodeMap;               // key -> node
    private final HashMap<Integer, DoublyLinkedList> freqMap;   // freq -> DLL of nodes

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        touch(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            touch(node); // increase freq & move lists
            return;
        }

        // evict if full
        if (size == capacity) {
            DoublyLinkedList minList = freqMap.get(minFreq);
            Node toEvict = minList.popLast(); // least recently used among minFreq
            nodeMap.remove(toEvict.key);
            size--;
            if (minList.isEmpty()) {
                freqMap.remove(minFreq);
            }
        }

        // insert new node with freq=1
        Node newNode = new Node(key, value);
        nodeMap.put(key, newNode);
        freqMap.computeIfAbsent(1, f -> new DoublyLinkedList()).addFirst(newNode);
        minFreq = 1;
        size++;
    }

    // Increase node's frequency and move it to the appropriate list
    private void touch(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);
        if (oldList.isEmpty()) {
            freqMap.remove(oldFreq);
            if (minFreq == oldFreq) {
                minFreq++; // next min freq
            }
        }

        node.freq++;
        freqMap.computeIfAbsent(node.freq, f -> new DoublyLinkedList()).addFirst(node);
    }

    // --------- Node & DLL ---------
    /*
        Node → represents each entry with (key, value, freq)
        Example: key=1, value=1, freq=2 (means key=1’s value=1, accessed twice so far).
    */
    private static class Node {
        int key, val, freq;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    // DLL of nodes with same frequency; head is most-recently-used within this freq
    // maintains order of nodes with same frequency.
    // It has head and tail pointers for easy addition and removal
    private static class DoublyLinkedList {
        private final Node head, tail;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }

        Node popLast() {
            if (isEmpty()) return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    // --------- Quick test ---------
    public static void main(String[] args) {
        // Example: capacity = 2
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);               // {1=1(freq1)}
        cache.put(2, 2);               // {1=1(freq1), 2=2(freq1)}  minFreq=1

        System.out.println(cache.get(1)); // 1   -> 1's freq becomes 2
        // Now: 1(freq2), 2(freq1)  minFreq=1

        cache.put(3, 3);               // evict key 2 (freq1 is min, and 2 is LRU within freq1)
        System.out.println(cache.get(2)); // -1 (evicted)
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(1)); // 1  (freq keeps increasing)

        cache.put(4, 4);               // current: 1(high freq), 3(freq1 or 2); minFreq likely 1 -> evict key 3
        System.out.println(cache.get(3)); // -1 (evicted)
        System.out.println(cache.get(4)); // 4
        System.out.println(cache.get(1)); // 1
    }
}


/*
🔹 What is LFU Cache?

Cache = a small storage that keeps recently used items so we don’t always go to slow storage (like DB/disk).

LFU (Least Frequently Used) cache = when the cache is full and we need to add a new item, we evict (remove)
the item that has been used the least number of times.
If multiple items have the same lowest frequency, we evict the one that is least recently used (LRU) among them.

So:
👉 Priority = use count first (frequency), then recency.

🔹 Time Complexity

For each operation:

get(key)

Lookup node in nodeMap → O(1)
Remove node from its frequency list → O(1) (doubly linked list removal)
Add node to new frequency list → O(1)
Update maps & minFreq → O(1)
✅ Overall: O(1)

put(key, value)

If key exists → update value + updateFrequency → O(1)
If key doesn’t exist:
Check capacity, evict from minFreq list (head/tail node removal) → O(1)
Insert new node & add to frequency list → O(1)
✅ Overall: O(1)

So:
👉 Both get and put operations are O(1) average time complexity.

🔹 Space Complexity

What do we store?

nodeMap: one entry per key → O(capacity)
freqMap: frequency → list of nodes
Each node belongs to exactly one list
Across all lists, still only capacity nodes total
Some extra pointers for head/tail sentinels → negligible
→ O(capacity)

So:
👉 O(capacity) total space.

🔹 Summary

Time Complexity:
get() → O(1)
put() → O(1)

Space Complexity:
O(capacity)

 */