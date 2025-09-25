package com.interview.Interview.Callicoder.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/*
Given a string, find the length of the longest possible substring in it that has exactly K distinct characters.
If there is no possible substring then print -1.

You can assume that K is less than or equal to the length of the given string.

Example 1:

Input: S = "aabacbebebe", K = 3
Output: 7
Explanation: "cbebebe" is the longest substring with 3 distinct characters.
Example 2:

Input:
S = "aaaa", K = 2
Output: -1
Explanation: There's no substring with 2 distinct characters.

 */
public class LongestSubstringWithKDistinctChars {

    public static void main(String[] args) {

        String s = "aabacbebebe";
        int k = 3;

        int windowStart = 0;
        int max_len = Integer.MIN_VALUE;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int windowEnd=0;windowEnd<s.length();windowEnd++) {
            char ch = s.charAt(windowEnd);  //a
            map.put(ch, map.getOrDefault(ch,0)+1);  //{a:3},{b:2},{c:1},{e:1}

            while (map.size() > k) {
                char leftChar = s.charAt(windowStart);  //a,a,b,a
                map.put(leftChar, map.get(leftChar)-1); //{a:0},{b:1},{c:1},{e:1}
                if(map.get(leftChar) == 0) {
                    map.remove(leftChar);   //{b:1},{c:1},{e:1}
                }
                windowStart++;
            }

            //check if window has exactly k distinct char
            if(map.size() == k) {
                max_len = Math.max(max_len, windowEnd-windowStart+1); //6-4+1
            }
        }
        System.out.println(max_len);
    }

    private static int count(Map<Character, Integer> map) {
        int sum = 0;
        for (Character ch: map.keySet()) {
            sum += map.get(ch);
        }
        return sum;
    }
}
