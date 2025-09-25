package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(-1,0));
        input.add(Arrays.asList(1,1));
        input.add(Arrays.asList(1,2));
        input.add(Arrays.asList(1,3));
        input.add(Arrays.asList(1,4));
        input.add(Arrays.asList(2,5));
        int n = 6;
        int k = 2;
        System.out.println(topK(input,n,k));

    }

    public static List<List<Integer>> topK(List<List<Integer>> input, int n, int k){

        //minHeap
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>((a,b) -> a.get(0)*a.get(0) + a.get(1)*a.get(1) - b.get(0)*b.get(0) - b.get(1)*b.get(1));

        for (List<Integer> in : input) {
            minHeap.add(in);
        }

        List<List<Integer>> result = new ArrayList<>();
        while (k !=0) {
            result.add(minHeap.poll());
            k--;
        }

        return result;

    }
}
