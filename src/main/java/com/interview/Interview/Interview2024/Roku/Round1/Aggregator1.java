package com.interview.Interview.Interview2024.Roku.Round1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Aggregator1 {

    Map<Integer, Integer> map;

    Aggregator1() {
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
        Aggregator agg = new Aggregator();
        agg.add(1, 2);
        agg.add(1, 4);
        agg.add(2, 1);
        agg.add(1, 1);
        //output: (1,7) (2,1)
        System.out.println(agg.getResult());

        // Reset the aggregator for the next example
        agg = new Aggregator();
        agg.add(1, 2);
        agg.add(1, 4);
        agg.add(2, 1);
        agg.add(1, 1);
        agg.add(2, 3);
        //output: (1,7) (2,4)
        System.out.println(agg.getResult());
    }
}
