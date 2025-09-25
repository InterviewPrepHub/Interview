package com.interview.Interview.TusharRoy.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {

    static class Job {
        int start, end, profit;
        Job(int s, int e, int p) { start = s; end = e; profit = p; }
    }

    static class JobComparator implements Comparator<Job> {
        public int compare(Job a, Job b) { return Integer.compare(a.end, b.end); }
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[] {
                new Job(1,3,5),
                new Job(2,5,6),
                new Job(4,6,5),
                new Job(6,7,4),
                new Job(5,8,11),
                new Job(7,9,2)
        };
        System.out.println(maxProfit(jobs)); // 17
    }

    /**
     * Sort the jobs by finish time.
     * For every job find the first job which does not overlap with this job
     * and see if this job profit plus profit till last non overlapping job is greater
     * than profit till last job.
     * @param jobs
     * @return
     *
     *   j     i     i     i     i     i
     * (1,3) (2,5) (4,6) (6,7) (5,8) (7,9)
     *   5     6     5     4    11     2
     *               10    9    16     7
     *                     10   17     8
     *                     14          12
     *                                 16
     *
     * when there is an overlap we dont add profit of i and current i  value else we add j
     * with current value of profit at i
     */
    public static int maxProfit(Job[] jobs){
        int T[] = new int[jobs.length];
        JobComparator comparator = new JobComparator();
        Arrays.sort(jobs, comparator);

        T[0] = jobs[0].profit;
        for(int i=1; i < jobs.length; i++){
            T[i] = Math.max(jobs[i].profit, T[i-1]);
            for(int j=i-1; j >=0; j--){
                if(jobs[j].end <= jobs[i].start){
                    T[i] = Math.max(T[i], jobs[i].profit + T[j]);
                    break;
                }
            }
        }
        int maxVal = Integer.MIN_VALUE;
        for (int val : T) {
            if (maxVal < val) {
                maxVal = val;
            }
        }
        return maxVal;
    }
}
