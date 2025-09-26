package com.interview.Interview.LeetCodeDesign;

import java.util.HashMap;
import java.util.Map;

/*
Adding a node: Always add new nodes to the front (head) of the doubly linked list.
Removing a node: Remove nodes from the back (tail) of the doubly linked list when the cache exceeds its capacity.
Updating a node: When a node is accessed or updated, move it to the front (head) of the doubly linked list to mark it
as the most recently used.

By maintaining the doubly linked list and the HashMap, you ensure that both the access (get) and insertion (put)
operations are efficient, with an average time complexity of O(1).
 */

public class LRUCache<K,V> {

    private final int capacity;
    private final Map<K, Node> cache;
    private final DoublyLinkedList dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList();
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        Node node = cache.get(key);
        dll.removeNode(node);
        dll.addNodeToFront(node);
        return (V) node.val;
    }

    public void put(K key, V value) {
        if (!cache.containsKey(key)) {
            Node node = new Node(key, value);
            if (cache.size() >= capacity) {
                Node tailNode = dll.removeTail(); // remove LRU
                cache.remove(tailNode.key);
            }
            cache.put(key, node);
            dll.addNodeToFront(node);
        } else {
            Node node = cache.get(key);
            node.val = value; // update
            dll.removeNode(node);
            dll.addNodeToFront(node);
        }
    }

    // ------------------ Node ------------------
    class Node {
        K key;
        V val;
        Node prev;
        Node next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // ------------------ Doubly Linked List ------------------
    class DoublyLinkedList {
        private final Node head;
        private final Node tail;

        DoublyLinkedList() {
            head = new Node(null, null);
            tail = new Node(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addNodeToFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node removeTail() {
            if (tail.prev == head) {
                return null; // empty
            }
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }

    // ------------------ Demo ------------------
    public static void main(String[] args) {
        LRUCache<Integer, Integer> lru = new LRUCache<>(2);

        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1)); // 1

        lru.put(3, 3); // evicts key=2
        System.out.println(lru.get(2)); // null
        System.out.println(lru.get(3)); // 3

        lru.put(4, 4); // evicts key=1
        System.out.println(lru.get(1)); // null
        System.out.println(lru.get(3)); // 3
        System.out.println(lru.get(4)); // 4
    }
    
}


