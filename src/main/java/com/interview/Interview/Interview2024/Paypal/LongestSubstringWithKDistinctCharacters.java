package com.interview.Interview.Interview2024.Paypal;

import java.util.HashMap;

/*
Given a string, find the longest substring with k distinct characters.
E.g. apppassdaapsllddddddpaassddd, k=4, answer - ddddddpaassddd
 */
public class LongestSubstringWithKDistinctCharacters {

    public static void main(String[] args) {
        String s = "apppassdaapsllddddddpaassddd";
        int k = 4;

        System.out.println(longestSubstring(s,k));
    }

    private static String longestSubstring(String s, int k) {
        int start = 0;
        int max_len = 0;
        int max_start = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            map.put(ch, map.getOrDefault(ch,0)+1);

            while (map.size() > k) {    //a = 3, p = 1, s = 1, d = 0, l = 1  [apppassdaapsl] =
                char startCh = s.charAt(start);
                map.put(startCh, map.get(startCh)-1);

                if (map.get(startCh) == 0) {
                    map.remove(startCh);
                }
                start++;
            }

            int curr_len = end - start + 1;
            if (curr_len > max_len) {
                max_len = curr_len;
                max_start = start;
            }
        }

        return s.substring(max_start, max_start+max_len);
    }
}
