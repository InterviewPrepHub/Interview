package com.interview.Interview.Interview2024.AppleInterviewQuestionBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {

    public static void main(String[] args) {
        String[] str = {"eat","tea","tan","ate","nat","bat"};
        GroupAnagram a = new GroupAnagram();
        a.test(str);
    }

    private void test(String[] str) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : str) {
            char[] ch = new char[26];

            for (char c : s.toCharArray()) {
                ch[c - 'a']++;
            }

            String key = String.valueOf(ch);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
                map.get(key).add(s);
            } else {
                map.get(key).add(s);
            }

        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() +" --> "+entry.getValue());
        }
    }
}
