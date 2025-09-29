package com.interview.Interview.Interview2025.Facebook.Round4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We are given a list of words.
We need to group words that follow the same “neighbour-character difference pattern.”

👉 Example 1:
Input = [AB, DC, DE, PQ, XY, AC, HG, DF, ML, GI]

"AB" → differences [1] (B - A = 1)

"PQ" → [1]

"XY" → [1]
So "AB", "PQ", "XY" go into the same group.

"DC" → differences [-1]

"HG" → [-1]

"ML" → [-1]
So "DC", "HG", "ML" are grouped.
 */
public class NeighbourCharacterPattern {

    public static List<List<String>> groupWords(String[] words) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String word : words) {
            String key = buildKey(word);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(word);
        }

        return new ArrayList<List<String>>(map.values());
    }

    private static String buildKey(String word) {
        if (word.length() <= 1) return "single";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < word.length(); i++) {
            int diff = word.charAt(i) - word.charAt(i - 1);
            sb.append(diff).append(",");
        }
        return sb.toString();
    }

    // demo
    public static void main(String[] args) {
        String[] input1 = {"AB", "DC", "DE", "PQ", "XY", "AC", "HG", "DF", "ML", "GI"};
        System.out.println(groupWords(input1));

        String[] input2 = {"ABCD", "ABCE", "PQRS", "CDEG", "WXYZ", "IJKM"};
        System.out.println(groupWords(input2));
    }
}
