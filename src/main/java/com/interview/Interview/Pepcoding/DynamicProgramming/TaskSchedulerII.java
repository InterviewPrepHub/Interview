package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class TaskSchedulerII {

    public static long minDays(int[] task, int space) {

        Map<Integer, Long> lastDone = new HashMap<>();
        Long day = 1L;

        for (int t : task) {
            if (lastDone.containsKey(t)) {
                long earliest = lastDone.get(t) + space + 1;
                if (day < earliest) {
                    day = earliest; //these are break days
                }
            }

            lastDone.put(t, day);
            day++;
        }
        return day-1;
    }

    public static void main(String[] args) {
        TaskSchedulerII solver = new TaskSchedulerII();
        int[] tasks = {1, 2, 1, 2, 3, 1};
        int space = 3;
        long ans = solver.minDays(tasks, space);
        System.out.println(ans); // 9
    }
}
