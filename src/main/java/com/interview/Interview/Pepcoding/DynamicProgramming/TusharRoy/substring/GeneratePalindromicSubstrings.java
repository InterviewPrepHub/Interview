package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.substring;

import java.util.ArrayList;
import java.util.List;

public class GeneratePalindromicSubstrings {

    public static List<String> palindromicSubstrings(String s) {
        List<String> out = new ArrayList<String>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // odd-length centers at (i, i)
            expandAndCollect(s, i, i, out);
            // even-length centers at (i, i+1)
            expandAndCollect(s, i, i + 1, out);
        }
        return out;
    }

    // expand around [L, R] and add each palindrome found
    private static void expandAndCollect(String s, int L, int R, List<String> out) {
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            out.add(s.substring(L, R + 1)); // collect the palindrome
            L--;
            R++;
        }
    }

    public static void main(String[] args) {
        String s1 = "abc";
        System.out.println(palindromicSubstrings(s1)); // [a, b, c]

        String s2 = "aaa";
        System.out.println(palindromicSubstrings(s2)); // [a, aa, aaa, a, aa, a]
        // Note: duplicates by position are included, matching the count=6.
    }

}
