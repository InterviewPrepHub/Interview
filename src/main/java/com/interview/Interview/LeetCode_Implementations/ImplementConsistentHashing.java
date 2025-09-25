package com.interview.Interview.LeetCode_Implementations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImplementConsistentHashing {

    public static void main(String[] args) {

        ConsistentHashing ch = new ConsistentHashing(100000); // totalSlots can be 2^16, 2^32, etc.

        ch.addNode(new StorageNode("A", "10.131.213.12"));
        ch.addNode(new StorageNode("B", "10.131.217.11"));
        ch.addNode(new StorageNode("C", "10.131.142.46"));
        ch.addNode(new StorageNode("D", "10.131.114.17"));
        ch.addNode(new StorageNode("E", "10.131.189.18"));

        String[] files = {"f1.txt", "f2.txt", "f3.txt", "f4.txt", "f5.txt"};
        for (String file : files) {
            StorageNode node = ch.getNodeForKey(file);
            System.out.println("File " + file + " is assigned to node " + node);
        }

        // Demonstrate adding a node
        ch.addNode(new StorageNode("F", "10.131.200.11"));
        System.out.println("After adding node F:");
        for (String file : files) {
            StorageNode node = ch.getNodeForKey(file);
            System.out.println("File " + file + " is assigned to node " + node);
        }

    }
}

class ConsistentHashing {

    private final int totalSlots;

    //key refers to the hash position of an item (file) on the hash ring
    //This position is used to determine which node in the distributed system should store the item.
    private final List<Long> keys = new ArrayList<>();

    //Actual storage nodes
    private final List<StorageNode> nodes = new ArrayList<>();


    public ConsistentHashing(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    //Hash function: SHA256 , then mod totalSlots
    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(key.getBytes());
            long hash = 0;

            for (int i = 0; i < 8; i++) {
                hash = (hash << 8) | (digest[i] & 0xff);
            }

            return Math.abs(hash) % totalSlots;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Hash function
    public void addNode(StorageNode node) {
        long key = hash(node.host);
        int idx = Collections.binarySearch(keys, key);

        //If the key already exists in the list, binarySearch returns its index (>= 0).
        //If an exact match is found (idx >= 0), that’s the node’s position.
        if (idx >= 0) {
            throw new IllegalArgumentException("Collision: Node already exists at this position");
        }
        //insertion point
        idx = -idx - 1;
        keys.add(idx, key);
        nodes.add(idx, node);

        System.out.println("Added node: "+node+" at position : "+key);
    }

    //Remove node from ring
    public void removeNode(StorageNode node) {
        long key = hash(node.host);
        int idx = Collections.binarySearch(keys, key);

        if (idx < 0) {
            throw new IllegalArgumentException("No such node exists!");
        }
        keys.remove(key);
        nodes.remove(node);

        System.out.println("Removed node: "+node+" from position : "+key);
    }

    //Assign a key (file) to a Node
    //If the key does not exists in the list, binarySearch returns its index (< 0).
    public StorageNode getNodeForKey(String item) {
        if (keys.isEmpty()) {   //if there are no nodes in the hash ring then nothing can be added
            return null;
        }
        long key = hash(item);
        int idx = Collections.binarySearch(keys, key);

        /*
            If an exact match is found (idx >= 0), that’s the node’s position.
	        If no exact match is found, binarySearch returns -(insertionPoint) - 1,
	        indicating where this key[node] would be inserted to keep the list sorted.
         */
        if (idx < 0) {
            idx = -idx-1;

            /*
                Wrap-around case: If idx == keys.size(), that means the key would logically
                be after the last node on the ring — but consistent hashing is a circle,
                so we wrap around and assign it to the first node (index 0).
             */
            if (idx == keys.size()) idx = 0;
        }
        return nodes.get(idx);
    }

}

//Each storage node is assigned a position in the hash space (using a hash function).
class StorageNode {

    public final String host;
    public final String name;

    public StorageNode(String host, String name) {
        this.host = host;
        this.name = name;
    }

    @Override
    public String toString() {
        return name+" ["+host+"]";
    }
}
