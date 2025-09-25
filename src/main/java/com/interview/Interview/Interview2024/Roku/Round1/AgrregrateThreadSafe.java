package com.interview.Interview.Interview2024.Roku.Round1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgrregrateThreadSafe {

    Map<Integer, Integer> map;

    AgrregrateThreadSafe() {
        map = new ConcurrentHashMap<>();
    }

    public void add(int k, int v) {
        // Update the sum for the key k
        map.put(k, map.getOrDefault(k, 0) + v);
    }

    public Object getResult() {
        return map;
    }

    public static void main(String[] args) {
        AgrregrateThreadSafe agg = new AgrregrateThreadSafe();
        int n=100;
        List<Thread> threads = new ArrayList<>();
        for (int i=0; i<n; i++){
            Thread t = new Thread(new Runnable(){
                public void run() {
                    agg.add(1,2);
                    agg.add(1,4);
                    agg.add(2,1);
                    agg.add(1,1);
                }
            });
            t.start();
            threads.add(t);
        }
        for (Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(agg.getResult());
    }
}


/*

ConcurrentHashMap though thread safe but it is thread safe for indiviual operation of add method.
But it is not atomic leading to different result on each run of the program.

In the above program, multiple threads are concurrently calling the add method.

It can happen that when a thread fetches the value and puts it back into the map, another thread
might have modified the value associated with the same key, leading to data inconsistency.

To fix this issue, we can use compute api of concurrent hashmap to achieve atomicity.

 */