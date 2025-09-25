package com.interview.Interview.Interview2024.JPMorgan.Round2;

import java.util.HashSet;

public class LetterDisplaced {

    public static void main(String[] args) {

        LetterDisplaced l = new LetterDisplaced();
        String s= "bbj";
        HashSet<String> set = new HashSet<>();
        l.generatePermuation(s, 0, s.length(), set);

        int res = 0;
        for (String str : set) {
            res = maxDiff(str, s);
        }
        System.out.println(res);

    }

    private static int maxDiff(String str, String s) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if(str.charAt(i) == s.charAt(i)) {

            } else {
                c++;
            }
        }
        return c;
    }

    private static void generatePermuation(String s, int start, int end, HashSet<String> set) {

        if(start == end-1) {
            set.add(s+" ");
        } else {
            for (int i = start; i < end; i++) {
                s = swap(s, start, i);
                generatePermuation(s, start+1, end, set);
                s = swap(s, start, i);
            }
        }
    }

    private static String swap(String str, int start, int end) {
        char[] ch = str.toCharArray();
        char temp = ch[start];
        ch[start] = ch[end];
        ch[end] = temp;
        return String.valueOf(ch);
    }
}
