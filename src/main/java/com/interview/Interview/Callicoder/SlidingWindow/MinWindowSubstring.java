package com.interview.Interview.Callicoder.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, find the smallest substring of s that has all the characters of t (including duplicates).
If there is no such substring, then return an empty string.

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The smallest substring of s that includes all the characters of t is "BANC".

 */
public class MinWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";

        Map<Character, Integer> tMap = new HashMap<>();
        for (Character ch: t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0)+1);
        }

        int windowStart = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        int minWindowSubstrLength = Integer.MAX_VALUE;
        int minWindowSubstrStart = 0;

        for (int windowEnd=0; windowEnd<s.length(); windowEnd++) {

            char ch = s.charAt(windowEnd);

            sMap.put(ch, sMap.getOrDefault(ch, 0)+1);

            while (containsAll(sMap, tMap)) {

                if(windowEnd-windowStart+1 < minWindowSubstrLength) {
                    minWindowSubstrLength = windowEnd-windowStart+1;
                    minWindowSubstrStart = windowStart;
                }

                char leftChar = s.charAt(windowStart);
                sMap.put(leftChar, sMap.get(leftChar)-1);
                if (sMap.get(leftChar) == 0) {
                    sMap.remove(leftChar);
                }
                windowStart++;
            }
        }

        String str = s.substring(minWindowSubstrStart, minWindowSubstrStart+minWindowSubstrLength);
        System.out.println("res : "+str);
    }

    private static boolean containsAll(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry: tMap.entrySet()) {

            char ch = entry.getKey();
            int count = entry.getValue();

            if(!sMap.containsKey(ch)) {
                return false;
            }

            if(sMap.get(ch) < count) {
                return false;
            }
        }
        return true;
    }
}
