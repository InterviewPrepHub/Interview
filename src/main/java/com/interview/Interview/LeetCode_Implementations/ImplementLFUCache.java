package com.interview.Interview.LeetCode_Implementations;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/*
LFU -> least frequently used
LFU eviction depends on frequency of access

In LFU,

-> You need to track how often each key is used.

Eviction rule:
	•	Remove the least frequently used key.
	•	If multiple keys have the same frequency, remove the least recently used among them.


Map<Integer, LinkedHashSet<FFNode>> freqToFNodes;  -->  buckets for frequency

key -> frequency count
value -> LinkedHasSet<FFNode> -> a list of FNodes that have been accessed that many times

LinkedHasSet helps preserve insertion/access order
if multiple FNodes have same frequency , we can evict least recently used[LRU] FNode, if multiple FNodes have same frequency

 */
public class ImplementLFUCache {

    /*
        ✅ Goal
            Design a data structure with:

            get(key): return the value if key exists, otherwise return -1.
            put(key, value): insert or update the value. If the cache reaches capacity, evict the least frequently used key.
            If there's a tie in frequency, evict the least recently used key among them.

        ✅ To support O(1) get and put, we use:

            1. Map<Integer, Node> — keyToNode
               Map from key to its Node object that holds: key, value, frequency.

            2. Map<Integer, LinkedHashSet<Node>> — freqToNodes
               Map from frequency to a set of nodes with that frequency.

               Use LinkedHashSet to maintain insertion order (for LRU eviction within same frequency).

            3. int minFreq
               minFreq always keeps track of the current lowest frequency of any key in the cache.
               So when the cache reaches its capacity and we need to evict an item, we know exactly which frequency bucket to evict from.

               minFreq is the smallest frequency value among all nodes currently stored in the cache — it is the key (i.e., the integer) in the map:

                Map<Integer, LinkedHashSet<FNode>> freqToFNodes;
        🧠 So:
                freqToFNodes: maps frequency → set of nodes with that frequency.
                minFreq: tells us which frequency to evict from when the cache is full.


     */

    private final int capacity;
    private int minFreq;    //keep track of min freq count across all keys in cache
    private final Map<Integer, FNode> keyToFNode;
    private final Map<Integer, LinkedHashSet<FNode>> freqToFNodes;

    public ImplementLFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToFNode = new HashMap<>();
        this.freqToFNodes = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToFNode.containsKey(key)) {
            return -1;
        }

        FNode FNode = keyToFNode.get(key);
        increaseFrequency(FNode);
        return FNode.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToFNode.containsKey(key)) {
            FNode FNode = keyToFNode.get(key);
            FNode.value = value;
            increaseFrequency(FNode);
        } else {
            // Evict if full
            if (keyToFNode.size() >= capacity) {
                evictLFU();
            }

            //add new key and value and update frequency group
            FNode newFNode = new FNode(key, value);
            keyToFNode.put(key, newFNode);

            //To initialize and update frequency group 1.
            //This is needed when a new node is added to the cache, since all new nodes start with frequency 1.
            if (!freqToFNodes.containsKey(1)) {     //checks whether there's already a set for frequency 1.
                freqToFNodes.put(1, new LinkedHashSet<>());
            }
            freqToFNodes.get(1).add(newFNode);
            minFreq = 1;
        }
    }

    private void increaseFrequency(FNode FNode) {
        int oldFreq = FNode.freq;
        int newFreq = ++FNode.freq;

        freqToFNodes.get(oldFreq).remove(FNode);
        if (freqToFNodes.get(oldFreq).isEmpty()) {
            freqToFNodes.remove(oldFreq);
            if (oldFreq == minFreq) {
                minFreq++;
            }
        }

//        freqToFNodes.computeIfAbsent(newFreq, ignore -> new LinkedHashSet<>()).add(FNode);
        if (!freqToFNodes.containsKey(newFreq)) {
            freqToFNodes.put(newFreq, new LinkedHashSet<>());
        }
        freqToFNodes.get(newFreq).add(FNode);
    }

    private void evictLFU() {
        LinkedHashSet<FNode> FNodes = freqToFNodes.get(minFreq);
        FNode FNodeToRemove = FNodes.iterator().next(); // LRU among minFreq
        FNodes.remove(FNodeToRemove);
        if (FNodes.isEmpty()) {
            freqToFNodes.remove(minFreq);
        }
        keyToFNode.remove(FNodeToRemove.key);
    }

    public static void main(String[] args) {
        ImplementLFUCache cache = new ImplementLFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3); // evicts key 2 (LFU)
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3
        cache.put(4, 4); // evicts key 1
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4

    }
}

class FNode {
    int key;
    int value;
    int freq = 1; //how many times key was accessed

    public FNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

