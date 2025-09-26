package com.interview.Interview.LeetCodeDesign;

import java.util.HashMap;
import java.util.Map;

/*
How this LFU works (O(1) get/put)

Policy:
    - Evict the key with the lowest access frequency.
    - Break ties by recency within that frequency (oldest in the frequency's queue goes first).

Data Structures:
    - nodes: key -> Node(value, freq)
    - freqList: freq -> LinkedHashSet<key>  (preserves insertion order to model recency within the same freq)
    - minFreq: smallest freq currently present in the cache. Points to the bucket we evict from.

We keep two maps:
    - key → Node to find the value and its current frequency fast.
    - freq → LinkedHashSet<key> to track all keys that currently have that frequency.
      LinkedHashSet preserves recency within the same frequency, so when ties occur we evict the least-recent among the least-frequent.

We also track minFreq, the smallest frequency currently present in the cache (so eviction is O(1) by looking at freq → set for minFreq).

On every successful get(key):
Increase the key’s frequency: move it from freq f set to freq f+1 set.
If the old freq f set becomes empty and f == minFreq, increment minFreq.

On put(key, value):
If the key exists, update the value and treat it like a get (frequency bump).
If the key doesn’t exist and we’re at capacity:
Evict one key from the minFreq bucket (the first in its LinkedHashSet is the least-recent in that freq).
Insert the new key at frequency 1, and set minFreq = 1.
This provides amortized O(1) for get and put with deterministic eviction.


 */
public class LFUCacheI<K,V> {

    private final int capacity;
    private int minFreq;

    // This field is not used by the LFU logic; you had it in your template.
    // Keeping it to match your requested structure (can be safely removed).
    private final DoublyLinkedList dll;

    private final Map<K, Node> cache;                    // key -> node (value, freq, pointers)
    private final Map<Integer, DoublyLinkedList> freqMap; // freq -> doubly linked list of nodes with that freq


    public LFUCacheI(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("capacity must be >= 0");
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFreq = 0; // 0 means "empty cache"
        this.dll = new DoublyLinkedList(); // unused; placeholder to match your skeleton
    }


    /**
     * Put key->val.
     * - If present: update value and bump frequency.
     * - If absent:
     *      - If at capacity, evict LFU (and LRU within that freq).
     *      - Insert with freq=1; set minFreq=1.
     */
    public void put(K key, V val) {
        if (capacity == 0) return; // no-op cache

        Node existing = cache.get(key);
        if (existing != null) {
            existing.val = val;
            updateFrequency(existing);
            return;
        }

        // Evict if full
        if (cache.size() >= capacity) {
            removeLeastFrequentlyUsed();
        }

        // Insert as freq=1
        Node newNode = new Node(key, val);
        cache.put(key, newNode);

        DoublyLinkedList list = freqMap.get(1);
        if (list == null) {
            list = new DoublyLinkedList();
            freqMap.put(1, list);
        }
        list.addNodeToFront(newNode);

        // New key always resets minFreq to 1
        minFreq = 1;
    }

    /** Get value; bump key's frequency bucket if present. */
    @SuppressWarnings("unchecked")
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null; // miss
        }
        updateFrequency(node);
        return (V) node.val;
    }

    /*
     * When a node's frequency is increased:
     *  - Remove the node from its current frequency list (freqMap.get(currFreq)).
     *  - If that list becomes empty and currFreq == minFreq, increment minFreq.
     *  - Increment node.freq.
     *  - Add node to the new frequency list (creating it if needed).
     */
    private void updateFrequency(Node node) {
        int currFreq = node.freq;
        DoublyLinkedList list = freqMap.get(currFreq);
        if (list != null) {
            list.removeNode(node);
            if (list.size == 0) {
                freqMap.remove(currFreq);
                // if we just removed the last node in the minFreq bucket,
                // the new min frequency becomes currFreq + 1
                if (minFreq == currFreq) {
                    minFreq = currFreq + 1;
                }
            }
        }

        node.freq++;
        DoublyLinkedList nextList = freqMap.get(node.freq);
        if (nextList == null) {
            nextList = new DoublyLinkedList();
            freqMap.put(node.freq, nextList);
        }
        nextList.addNodeToFront(node);
    }

    /** Evict LFU (and LRU within LFU): remove from freqMap[minFreq] tail, then from cache. */
    private void removeLeastFrequentlyUsed() {
        DoublyLinkedList list = freqMap.get(minFreq);
        if (list == null || list.size == 0) {
            // defensive; should not happen if minFreq is tracked correctly
            // fallback scan (rare path)
            int guess = Integer.MAX_VALUE;
            for (Map.Entry<Integer, DoublyLinkedList> e : freqMap.entrySet()) {
                if (e.getValue().size > 0 && e.getKey() < guess) {
                    guess = e.getKey();
                    list = e.getValue();
                }
            }
            if (list == null || list.size == 0) return;
            minFreq = guess;
        }

        Node nodeToRemove = list.removeNodeFromEnd(); // LRU within that freq
        if (list.size == 0) {
            freqMap.remove(minFreq);
            // minFreq will be reset to 1 on next insert, or bumped via updateFrequency on next access
        }
        if (nodeToRemove != null) {
            cache.remove(nodeToRemove.key);
        }
    }



    // ===================== Node =====================

    class Node {
        K key;
        V val;
        int freq;
        Node prev;
        Node next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.freq = 1; // new entries always start at frequency 1
        }
    }

    // ===================== Doubly Linked List =====================

    class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        public DoublyLinkedList() {
            this.head = new Node(null, null); // dummy head
            this.tail = new Node(null, null); // dummy tail
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        /** Add node right after head (most-recent within its frequency). */
        public void addNodeToFront(Node node) {
            Node next = head.next; // could be tail or another node

            node.next = next;
            node.prev = head;

            head.next = node;
            next.prev = node;

            size++;
        }

        /** Remove an arbitrary node from this DLL. */
        void removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            node.prev = null;
            node.next = null;
            size--;
        }

        /** Remove the least-recent node (the one right before tail). */
        Node removeNodeFromEnd() {
            if (size == 0) return null;
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }

    public static void main(String[] args) {
        LFUCacheI<Integer, String> cache = new LFUCacheI<>(2);

        System.out.println("Put(1, A)");
        cache.put(1, "A");

        System.out.println("Put(2, B)");
        cache.put(2, "B");

        System.out.println("Get(1): " + cache.get(1)); // "A"
        // Key 1 now freq=2

        System.out.println("Put(3, C) - should evict key=2");
        cache.put(3, "C");

        System.out.println("Get(2): " + cache.get(2)); // null (evicted)
        System.out.println("Get(3): " + cache.get(3)); // "C"
        // Key 3 now freq=2

        System.out.println("Put(4, D) - should evict LFU among {1,3}");
        cache.put(4, "D");

        System.out.println("Get(1): " + cache.get(1)); // may be null (evicted if LRU at freq=2)
        System.out.println("Get(3): " + cache.get(3)); // "C" if still present
        System.out.println("Get(4): " + cache.get(4)); // "D"
    }
}


/*

🔎 Example Walkthrough

Step 1: Put(1,"A")
Insert new node key=1, val="A", freq=1.
Cache = {1:"A"(freq=1)}, minFreq=1.

Step 2: Put(2,"B")
Insert new node key=2, val="B", freq=1.
Cache = {1:"A"(freq=1), 2:"B"(freq=1)}, minFreq=1.

Step 3: Get(1)
Key=1 found. Increase its freq from 1 → 2.
Now: {1:"A"(freq=2), 2:"B"(freq=1)}.
minFreq = 1 (because key=2 is still at freq=1).

Step 4: Put(3,"C")
Cache is full (capacity=2).
Evict LFU: minFreq=1, so evict key=2 ("B").
Insert 3:"C"(freq=1).
Cache = {1:"A"(freq=2), 3:"C"(freq=1)}, minFreq=1.

Step 5: Get(3)
Key=3 found. Increase freq 1 → 2.
Cache = {1:"A"(freq=2), 3:"C"(freq=2)}.
minFreq bumps to 2 (since no key at freq=1).

Step 6: Put(4,"D")
Cache full.
Evict LFU: both {1,3} have freq=2. We evict the LRU among them (the one least recently used in freq=2 bucket).
Suppose key=1 was accessed earlier than 3, so evict 1.
Insert key=4:"D"(freq=1).
Cache = {3:"C"(freq=2), 4:"D"(freq=1)}, minFreq=1.
 */