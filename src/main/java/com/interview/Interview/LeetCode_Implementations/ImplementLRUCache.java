package com.interview.Interview.LeetCode_Implementations;

import java.util.HashMap;
import java.util.Map;

public class ImplementLRUCache {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}


/*
we will be implementing the Cache using doubly linked list & hashmap
 */
class LRUCache {

    private int capacity;
    private Map<Integer, DNode> cacheMap;
    private DNode head;
    private DNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new DNode(-1, -1);
        this.tail = new DNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void put(int key, int value) {

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
    }

    public int get(int key) {

        if (!cacheMap.containsKey(key)) {
            return -1;
        }

        DNode node = cacheMap.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }

    private void addToHead(DNode nodeToAdd) {
        DNode nextNode = head.next;
        head.next = nodeToAdd;
        nodeToAdd.prev = head;
        nodeToAdd.next = nextNode;
        nextNode.prev = nodeToAdd;
    }

    private void remove(DNode node) {
        DNode prevNode = node.prev;
        DNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

    }

}

class DNode {

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