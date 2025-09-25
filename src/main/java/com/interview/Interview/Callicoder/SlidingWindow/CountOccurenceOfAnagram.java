package com.interview.Interview.Callicoder.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class CountOccurenceOfAnagram {

    public static void main(String[] args) {

        String str = "forxxorfxdofr";
        String word = "for";
        int k = word.length(); //3

        Map<Character, Integer> wordMap = new HashMap<>();
        for (Character ch : word.toCharArray()) {
            wordMap.put(ch, wordMap.getOrDefault(ch, 0)+1);
        }

        int windowStart = 0;
        int windowSum = 0;

        for (int windowEnd = k; windowEnd <= str.length(); windowEnd++) {

            String subStr = str.substring(windowStart,windowEnd);

            Map<Character, Integer> subStrMap = new HashMap<>();

            for(Character ch : subStr.toCharArray()) {
                subStrMap.put(ch, subStrMap.getOrDefault(ch, 0)+1);
            }

            if(isAnagram(wordMap, subStrMap)) {
                windowSum++;
            }

            windowStart++;
        }

        System.out.println(windowSum);

    }

    public static boolean isAnagram(Map<Character, Integer> map1, Map<Character, Integer> map2) {

        for(char c : map1.keySet()) {
            if(map1.get(c) != map2.get(c)) {
                return false;
            }
        }
        return true;
    }
}
