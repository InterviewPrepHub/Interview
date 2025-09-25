package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {

        int[] a = {1,2,3};

        List<List<Integer>> result = new ArrayList<>();
        backtrack(a, 0, new ArrayList<Integer>(), result);
        System.out.println(result);
    }

    private static void backtrack(int[] a, int index, ArrayList<Integer> curr, List<List<Integer>> result) {

        //add the current subset
        result.add(new ArrayList<>(curr));

        for (int i = index; i < a.length; i++) {
            curr.add(a[i]); //choose
            backtrack(a, i+1, curr, result);
            curr.remove(curr.size()-1); //unchoose
        }
    }
}
