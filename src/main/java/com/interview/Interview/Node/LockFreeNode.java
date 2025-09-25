package com.interview.Interview.Node;

import java.util.concurrent.atomic.AtomicReference;

/*
this class is a node in a lock free linkedlist

With volatile , changes made by one thread is immediately visible to all threads
Without volatile, one might cache the old value, leading to stale reads

 */
public class LockFreeNode {

    public final int key;   //unique identifier of node
    public volatile int value;  //volatile shows that each thread reading or writing to value sees the most recent one
    public final AtomicReference<LockFreeNode> next;    //safely update pointer using atomic operation(compare & set)

    public LockFreeNode(int key, int value) {
        this.key = key;
        this.value = value;
        next = new AtomicReference<>(null);;
    }
}
