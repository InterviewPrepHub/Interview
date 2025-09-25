package com.interview.Interview.Callicoder.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/*
Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "aababcbb"
Output: 3
Explanation: The longest substring without repeating characters is "abc".
Example 2:

Input: s = "cccc"
Output: 1
Explanation: The longest substring without repeating characters is "c".
Example 3:

Input: s = ""
Output: 0
 */
public class LongestSubstringWithoutRepeatingChar {

    public static void main(String[] args) {

        String s = "aababcbb";
        Map<Character, Integer> map = new HashMap<>();

        int windowStart = 0;
        int max_len = Integer.MIN_VALUE;

        for (int windowEnd=0; windowEnd<s.length(); windowEnd++) {
            char ch = s.charAt(windowEnd); //a
            map.put(ch, map.getOrDefault(ch,0)+1); //{a = 1}

            //shrink window size until all characters in window are unique
            while (map.size() < windowEnd-windowStart+1) {
                char leftChar = s.charAt(windowStart);
                map.put(leftChar, map.getOrDefault(leftChar,0)-1);
                if (map.get(leftChar) ==0) {
                    map.remove(leftChar);
                }
                windowStart++;
            }

            if (map.size() == windowEnd-windowStart+1) {
                System.out.println(map);
                max_len = Math.max(max_len, map.size());
            }
        }

        System.out.println(max_len);
    }
}
