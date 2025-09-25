package com.interview.Interview.TusharRoy.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

public class JobSchedulingUsingBinarySearch {

    public static void main(String[] args) {
        int[] startTime = {1, 2, 3, 3};
        int[] endTime = {3, 4, 5, 6};
        int[] profit = {50, 10, 40, 70};

        Jobs[] jobs = new Jobs[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Jobs(startTime[i], endTime[i], profit[i]);

        }

        //1. sort jobs by start time
        Arrays.sort(jobs, new FinishJobComparator());

        //2. create array of start time for binary search
        int[] start = new int[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            start[i] = jobs[i].startTime;
        }

        //3. DP with memoization
        int[] T = new int[jobs.length];
        int maxProfit = getMaxProfit(0, jobs, start, T);
        System.out.println(maxProfit);

    }

    private static int getMaxProfit(int idx, Jobs[] jobs, int[] start, int[] T) {

        //option1: take current job
        int nextIdx = findTheNextNotConflictingJob(start, jobs[idx].endTime);
        int take = jobs[idx].profit + getMaxProfit(nextIdx, jobs, start, T);


        //option2: skip current job
        int skip = getMaxProfit(idx+1, jobs, start, T);

        return T[idx] = Math.max(take, skip);
    }

    private static int findTheNextNotConflictingJob(int[] start, int targetEnd) {

        int low = 0, high = start.length;

        while(low < high) {
            int mid = (low+high)/2;
            if(start[mid] > targetEnd) {
                high = mid-1;
            } else {
                low = mid;
            }
        }
        return low;
    }
}

class Jobs {
    int startTime;
    int endTime;
    int profit;

    public Jobs(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
}

class FinishJobComparator implements Comparator<Jobs> {

    @Override
    public int compare(Jobs o1, Jobs o2) {
        return Integer.compare(o1.startTime, o2.startTime);
    }
}
