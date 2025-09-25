package com.interview.Interview.Interview2024.Roku.Round1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;


/*

ConcurrentHashMap though thread safe but it is thread safe for indiviual operation of add method.
But it is not atomic leading to different result on each run of the program.

In the above program, multiple threads are concurrently calling the add method.

It can happen that when a thread fetches the value and puts it back into the map, another thread
might have modified the value associated with the same key, creating issues with data inconsistency.

To fix this issue, we can use compute api of concurrent hashmap to achieve atomicity.
This means that no other threads can modify the map while the computation is in progress, ensuring thread safety.

Please see below with comments for explanation:

 */
public class AtomicAgrregrate {

    Map<Integer, Integer> map;

    AtomicAgrregrate() {
        map = new ConcurrentHashMap<>();
    }

    /*
    The compute method takes a key and a BiFunction which allows you to atomically compute
    a new value based on the existing value (if any) associated with the key.

    Here the bifunction is the function which takes two arguments and produces a result.
    Explained with below:

     */

    public void add(int k, int v) {
        // Update the sum for the key k
//        map.compute(k, (key, oldValue) -> (oldValue == null) ? v : oldValue + v);

        map.compute(k, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer key, Integer oldValue) {
                if (oldValue == null) {
                    // Add input value to the existing value
                    return v;
                } else {
                    return oldValue + v;
                }
            }
        });
    }

    public Object getResult() {
        return map;
    }

    public static void main(String[] args) {
        AtomicAgrregrate agg = new AtomicAgrregrate();
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
