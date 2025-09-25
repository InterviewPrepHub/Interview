package com.interview.Interview.Callicoder.SlidingWindow;

/*
Given a string with lowercase letters only. If you are allowed to replace at most k letters with any letter,
find the length of the longest substring having the same letters after replacement.

Example 1:

Input: s="abcababb", k=2
Output: 5
Explanation: Replace the two 'a' with 'b' in the substring 'ababb' to get the longest substring "bbbbb" with same letters.
Example 2:

Input: s="abccde", k=1
Output: 3
Explanation: Replace the 'b' or 'd' with 'c' to get the the longest substring "ccc" with same letters.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithSameLettersAfterReplacement {

    public static void main(String[] args) {
        String s="abcababb";
        int k=2;

        int windowStart = 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxRepeatLetterCount = 0;
        int max_len = Integer.MIN_VALUE;

        for (int windowEnd=0; windowEnd<s.length(); windowEnd++) {

            char ch = s.charAt(windowEnd);
            map.put(ch, map.getOrDefault(ch, 0)+1); //{a=2,b=1,c=1}

            //calculate max repeating character in current window
            int maxRepeatingLetterCount = getMaxRepeatingLetterCount(map);  //2

            /*
                in current window we have a letter that repeats maxRepeatingLetterCount times,
                if we have the rest of the letters in the window <= k, then we can replace them
                all. Otherwise we need to shrink the window, since we are not allowed to replace
                more than k.

             */

            while(windowEnd-windowStart+1 - maxRepeatingLetterCount > k) {

                char leftChar = s.charAt(windowStart);
                map.put(leftChar, map.get(leftChar)-1);
                windowStart++;

            }

            /*
                at this point, no of remaining letters in windows are less than or equal to k.
                So we can replace them all to obtain the substring with the same letters and
                update the max length of current window

             */
            max_len = Math.max(max_len, windowEnd-windowStart+1);
        }

        System.out.println(max_len);
    }

    private static int getMaxRepeatingLetterCount(Map<Character, Integer> map) {

        int max = Integer.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }
}
