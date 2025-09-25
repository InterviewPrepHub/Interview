package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 4;
//        int cpn = countPaths(n, new int[n+1]);
//        System.out.println(cpn);
        Map<Integer, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> paths = getPaths(n, map);
        System.out.println(paths);
    }

    private static ArrayList<String> getPaths(int n, Map<Integer, ArrayList<String>> map) {

        if(n==0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        } else if(n<0) {
            return new ArrayList<>();
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        /*
        👣 Why n-1, n-2, and n-3 in the Climb Stairs problem?

        This is based on the problem definition — specifically, the allowed steps at each move.

        You use n-1, n-2, and n-3 in recursion because you’re modeling a problem where you’re allowed to take 1, 2, or 3 steps at a time.
        Change the recursion based on the allowed steps in the problem.

        🧩 Generalization:

        If your allowed jumps are in an array, say int[] jumps = {1, 2, 3}, you can write:
        for (int jump : jumps) {
            getPaths(n - jump);
        }
         */
        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> path1 = getPaths(n-1, map);
        ArrayList<String> path2 = getPaths(n-2, map);
        ArrayList<String> path3 = getPaths(n-3, map);


        for (String p1 : path1) {
            paths.add("1"+p1);
        }

        for (String p2 : path2) {
            paths.add("2"+p2);
        }

        for (String p3 : path3) {
            paths.add("3"+p3);
        }

        map.put(n, paths);
        return paths;

    }

    private static int countPaths(int n, int[] qb) {

        if(n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        }

        if(qb[n]>0) {
            return qb[n];
        }
        System.out.println("hello : "+ n);
        int cnm1 = countPaths(n-1, qb);
        int cnm2 = countPaths(n-2, qb);
        int cnm3 = countPaths(n-3, qb);

        int cp = cnm1+cnm2+cnm3;
        qb[n] = cp;
        return cp;
    }
}
