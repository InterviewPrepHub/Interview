package com.interview.Interview.iDeserve;

import java.util.*;

/*
Given k lists of sorted integers. Find shortest range that includes one number from each of the k lists.

Input:-
L1 :- { 4, 10, 15, 24, 26}
L2 :- { 0, 9, 12, 20 }
L3 :- { 5, 18, 22, 30 }
Output:-  {20,24}

 */
public class ShortestRangeInKSortedList {

    public static void main(String[] args) {

        int[] a1 = { 4, 10, 15, 24, 26};
        int[] a2 = { 0, 9, 12, 20 };
        int[] a3 = { 5, 18, 22, 30 };

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.stream(a1).boxed().toList());
        lists.add(Arrays.stream(a2).boxed().toList());
        lists.add(Arrays.stream(a3).boxed().toList());

        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.data));
        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        //initialise minHeap with first element of each list
        for (int i = 0; i < lists.size(); i++) {
            int val = lists.get(i).get(0);
            minHeap.add(new Element(val, i, 0));
            max = Math.max(max, val);
        }

        //process heap
        while (true) {
            Element min = minHeap.poll();   //{0,1,0},{4,0,0},{5,2,0}

            //update range if its smaller
            if((max - min.data) < (rangeEnd - rangeStart)) {
                rangeStart = min.data;;
                rangeEnd = max;
            }

            //If the list has more elements, push the next one
            if(min.indexInList+1 < lists.get(min.listIndex).size()) {
                // Still elements left in this list, keep going
                int nextVal = lists.get(min.listIndex).get(min.indexInList+1);
                minHeap.add(new Element(nextVal, min.listIndex, min.indexInList+1));
                max = Math.max(max, nextVal);
            } else {
                break;
            }
        }

        int[] newArr = new int[]{rangeStart, rangeEnd};
        System.out.println("{"+rangeStart+","+rangeEnd+"}");
    }

    static class Element {
        int data;
        int listIndex;
        int indexInList;

        public Element(int data, int listIndex, int indexInList) {
            this.data = data;
            this.listIndex = listIndex;
            this.indexInList = indexInList;
        }
    }
}
