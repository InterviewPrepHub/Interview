package com.interview.Interview.Callicoder.TwoPointers;

import java.util.ArrayList;
import java.util.List;

/*
Idea (pointers)

Precompute the last index of every character.

Scan with index i, keep a window [start, end].

Update end = max(end, last[s[i]]).
When i == end, you’ve closed a partition ⇒ record end - start + 1, set start = i + 1.

(O(n) time, O(Σ) space)
 */
public class PartitionLabels {

    public static List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] last = new int[26];
        for (int i = 0; i < n; i++) last[s.charAt(i) - 'a'] = i;

        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {                 // window closed
                res.add(end - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcc")); // [4, 2]
        System.out.println(partitionLabels("ababcbacadefegdehijhklij")); // [9,7,8]
    }
}
