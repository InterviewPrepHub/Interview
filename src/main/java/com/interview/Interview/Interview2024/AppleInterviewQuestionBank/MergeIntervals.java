package com.interview.Interview.Interview2024.AppleInterviewQuestionBank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10}, {15,18}};

        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        List<int[]> output = new ArrayList<>();

        int[] curr_interval = intervals[0];
        output.add(curr_interval);

        for (int[] interval : intervals) {
            int curr_begin = curr_interval[0];
            int curr_end = curr_interval[1];

            int next_begin = interval[0];
            int next_end = interval[1];

            if (curr_end >= next_begin) {
                curr_interval[0] = curr_begin;
                curr_interval[1] = Math.max(curr_end, next_end);
            } else {
                curr_interval = interval;
                output.add(curr_interval);
            }
        }

        for (int[] out: output) {
            System.out.println("{"+out[0] +","+out[1]+"}");
        }

    }
}
