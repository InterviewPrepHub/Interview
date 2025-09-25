package com.interview.Interview.Pepcoding.HashMapAndHeap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the k most frequent elements.
You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]

 */
public class TopKFrequentElement {

    public static void main(String[] args) {

        int nums[] = {1,1,1,2,2,3}, k = 2;

        //count freq of each element
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num,0)+1);
        }

        //create min heap of size k
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a,b) -> a.getValue()-b.getValue());

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); //remove least frequent
            }
        }

        //extract result from heap
        int[] res = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.poll().getKey();
        }

        for (int r : res) {
            System.out.println(r);
        }


    }
}
