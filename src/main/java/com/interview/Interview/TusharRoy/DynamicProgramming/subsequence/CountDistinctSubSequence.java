package com.interview.Interview.TusharRoy.DynamicProgramming.subsequence;

import java.util.HashMap;

public class CountDistinctSubSequence {

    public static void main(String[] args) {
//        String s = "abcbac";
        String s = "abc";
        int[] dp = new int[s.length()+1];
        dp[0] = 1;

        HashMap<Character, Integer> lastSeen = new HashMap<>();
        for (int i = 1; i < dp.length; i++) {
            char ch = s.charAt(i-1);    //a

            dp[i] = 2 * dp[i-1];    //1,2

            if (lastSeen.containsKey(ch)) {
                int j = lastSeen.get(ch);   //{a->0}
                dp[i] = dp[i] - dp[j-1];    //{1,}
            }

            lastSeen.put(ch, i);
        }
        System.out.println(dp[s.length()]);
    }
}

/*
    .   |    a   |    b   |    c   |   b         |    a         |    c           |
     1  | 2*1=2  | 2*2=4  | 2*4=8  | 2*8=16-2=14 | 2*14=28-1=27 | 2*27=54-4=50   |
     .     .       .         .        .    b*
           a       a         a        a    ab*
                   b         b        b    bb
                   ab        ab       ab   abb
                             c        c    cb
                             ac       ac   acb
                             bc       bc   bcb
                             abc      abc  abcb

   here * signifies that its already seen in the list of subsequence

 */
