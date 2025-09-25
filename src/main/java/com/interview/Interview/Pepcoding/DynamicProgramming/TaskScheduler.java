package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.*;

public class TaskScheduler {

    public static void main(String[] args) {
//        char[] ch = {'A','C','A','B','D','B','A','A','A','A'}; int n = 1;
//        char[] ch = {'A','A','A','B','B','B'}; int n = 2;
//        char[] ch = {'A','C','A','B','D','B'}; int n = 2;
        char[] ch = {'A','A','A','B'}; int n = 2;


//        int minIntervals = leastInterval(ch, n);
//        System.out.println(minIntervals);

        String res = scheduler(ch, n);
        System.out.println(res);
    }

    static class Node {
        char task;
        int count;
        Node(char t, int c) { task = t; count = c; }
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char t : tasks) freq.put(t, freq.getOrDefault(t, 0) + 1);

        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            maxHeap.offer(new Node(e.getKey(), e.getValue()));
        }

        int intervals = 0;

        while (!maxHeap.isEmpty()) {
            List<Node> used = new ArrayList<>();
            int slots = n + 1; // one cycle

            // pick up to n+1 tasks with highest remaining freq
            while (slots > 0 && !maxHeap.isEmpty()) {
                Node cur = maxHeap.poll();
                cur.count--;
                used.add(cur);
                intervals++;
                slots--;
            }

            // push back tasks that still have remaining count
            for (Node node : used) {
                if (node.count > 0) maxHeap.offer(node);
            }

            // if heap not empty, we had to idle to complete the cycle
            // ensures that when there are still tasks waiting, we pad the current cycle with idle intervals to maintain cooldown.
            if (!maxHeap.isEmpty()) {
                intervals += slots; // remaining slots are idle
            }
        }

        return intervals;
    }

    private static String scheduler(char[] tasks, int n) {

        Map<Character, Integer> freq = new HashMap<>();
        for (char t : tasks) freq.put(t, freq.getOrDefault(t, 0) + 1);

        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            maxHeap.offer(new Node(e.getKey(), e.getValue()));
        }

        StringBuilder plan = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            List<Node> used = new ArrayList<>();
            int slots = n + 1;

            while (slots > 0 && !maxHeap.isEmpty()) {
                Node cur = maxHeap.poll();
                plan.append(cur.task);
                cur.count--;
                used.add(cur);
                slots--;
            }

            for (Node node : used) {
                if (node.count > 0) maxHeap.offer(node);
            }

            if (!maxHeap.isEmpty()) {
                // fill remaining slots in this cycle with idles
                while (slots > 0) {
                    plan.append('I');   // add an idle slot
                    slots--;            // decrease slots by 1
                }
            }
        }

        return plan.toString();
    }
}
