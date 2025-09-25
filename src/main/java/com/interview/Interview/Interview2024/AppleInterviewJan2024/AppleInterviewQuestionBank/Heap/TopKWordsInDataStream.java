package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Heap;

import java.util.*;

public class TopKWordsInDataStream {

    int k;
    HashMap<String, Integer> map;
    PriorityQueue<String> pq;

    TopKWordsInDataStream(int k) {
        this.k = k;
        this.map = new HashMap<>();
        this.pq = new PriorityQueue<>((w1,w2) -> {
            int freq = map.get(w1) - map.get(w2);
            return (freq == 0) ? w2.compareTo(w1) : freq;
        });
    }

    public void add(String word) {
        map.put(word, map.getOrDefault(word, 0)+1);
        updateTopK(word);
    }

    private void updateTopK(String word) {
        if (pq.contains(word)) {
            pq.remove(word);
        }
        pq.add(word);
        if (pq.size() > k) {
            pq.poll();
        }
    }

    public List<String> topK() {
        List<String> topKWords = new ArrayList<>(pq);
        Collections.sort(topKWords, (w1, w2) -> {
            int freq = map.get(w2) - map.get(w1);
            return (freq == 0) ? w2.compareTo(w1) : freq;
        });
        return topKWords;
    }

    public static void main(String[] args) {
        TopKWordsInDataStream t = new TopKWordsInDataStream(2);
        t.add("lint");
        t.add("code");
        t.add("code");

        System.out.println(t.topK());
    }
}
