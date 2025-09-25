package com.interview.Interview.Pepcoding.HashMapAndHeap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
 */
public class MeetingRoom {

    public static void main(String[] args) {

        MeetingRoom m = new MeetingRoom();

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0,30));
        list.add(new Interval(5,10));
        list.add(new Interval(15,20));

        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(0,30));
        list1.add(new Interval(35,40));
        list1.add(new Interval(45,60));

        System.out.println(m.canAttendMeetings(list));
    }

    public boolean canAttendMeetings(List<Interval> intervals) {

        int n = intervals.size();

        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {   //O(n)
            start[i] = intervals.get(i).startTime;
            end[i] = intervals.get(i).endTime;
        }

        Arrays.sort(end);   //O(n log n)
        Arrays.sort(start);

        /*
            start -> 0, 5, 15
            end -> 10, 20, 30
         */
        for (int i = 0; i < start.length -1; i++) {
            if(start[i+1] < end[i]) {
                return false;
            }
        }
        return true;
    }

    static class Interval {
        int startTime;
        int endTime;

        Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

    }

}
