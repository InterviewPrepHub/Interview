package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.util.ArrayList;
import java.util.List;

public class PrintPermutation {

    public static void main(String[] args) {
        String s = "abc";
        permutation(s, "");

        int[] a = {1,2,3};
        permutation(a);
    }

    private static void permutation(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[a.length];
        permutation(a,new ArrayList<Integer>(), used, result);
        System.out.println(result);
    }

    private static void permutation(int[] a, List<Integer> curr, boolean[] used, List<List<Integer>> result) {

        if(curr.size() == a.length) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < a.length; i++) {

            if (used[i]) continue;  //skip already used number

            //choose
            curr.add(a[i]);
            used[i] = true;

            //explore
            permutation(a, curr, used, result);

            //not choose
            curr.remove(curr.size()-1);
            used[i] = false;
        }
    }



    private static void permutation(String ques, String asf) {

        if (ques.length() == 0) {
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < ques.length(); i++) {

            char ch = ques.charAt(i);
            String lros = ques.substring(0,i);
            String rros = ques.substring(i+1);
            permutation(lros+rros, asf+ch);
        }
    }
}

/*
Time complexity:

A permutation is an arrangement of all elements of a set in every possible order.

	For example, permutations of ABC are:
ABC, ACB, BAC, BCA, CAB, CBA

If there are n elements, the number of permutations is:

➕ Total Number of Permutations = n!

(That is: n × (n−1) × (n−2) × … × 1)
 */