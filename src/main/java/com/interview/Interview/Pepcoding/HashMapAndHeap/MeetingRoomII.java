package com.interview.Interview.Pepcoding.HashMapAndHeap;

import java.util.*;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.

Example1

Input: intervals = [(0,30),(5,10),(15,20)] -> (5,10),(15,20),(0,30)
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)


Example2

Input: intervals = [(2,7)]
Output: 1
Explanation:
Only need one meeting room

 */
public class MeetingRoomII {

    public static void main(String[] args) {

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0,30));
        list.add(new Interval(5,10));
        list.add(new Interval(15,20));

        MeetingRoomII mr = new MeetingRoomII();
        System.out.println(mr.minMeetingRooms(list));


    }

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return 0;

        // 1) Sort by start time
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return Integer.compare(a.startTime, b.startTime);
            }
        });

        // 2) Min-heap of end times (one per room in use)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        // 3) Process meetings in start order
        for (int i = 0; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            // If earliest room frees up, reuse it
            if (!minHeap.isEmpty() && cur.startTime >= minHeap.peek()) {
                minHeap.poll();
            }
            // Allocate/extend a room until cur.endTime
            minHeap.offer(cur.endTime);
        }

        // Number of rooms needed
        return minHeap.size();
    }

    /*public void minMeetingRooms(List<Interval> intervals) {

        int n = intervals.size();
        int count = 1;

        //sort the meetings by their end time
        Collections.sort(intervals, new IntervalComparator());

        for (Interval interval : intervals) {
            System.out.println(interval.startTime+", "+interval.endTime);
        }
        System.out.println();

        //create a minHeap, min heap will have the root which is ends the earliest
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a,b) -> a.endTime - b.endTime);
        minHeap.add(intervals.get(0));

        for (int i = 1; i < n; i++) {
            Interval curr = intervals.get(i);
            Interval prev = minHeap.peek();
            if(curr.startTime >= prev.endTime) {
                prev.endTime = curr.endTime;
            } else {
                minHeap.add(curr);
            }

            //since we removed prev from the minHeap above [minHeap.remove()] so we can now add back to minHeap or we do peek()
//            minHeap.add(prev);
        }



        System.out.println(minHeap.size());

    }*/

    static class Interval {
        int startTime;
        int endTime;

        Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    static class IntervalComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval o1, Interval o2) {
            return Integer.compare(o1.endTime, o2.endTime);
        }
    }


}
