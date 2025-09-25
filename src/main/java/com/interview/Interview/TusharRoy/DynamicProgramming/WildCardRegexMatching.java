package com.interview.Interview.TusharRoy.DynamicProgramming;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).


ex:

a*b => ab, acb, aab, axyb -> True
       b, a, ac, abc -> False
       so when the pattern is *, we can have any no of characters including empty char replace the * but in the same order
       here it's between a & b.

a?b => azb, aab, aqb -> True
       ab,a,b axyb -> False
       ? should replace exactly

pattern = x?y*z
string = xaylmz


T[i][j] = T[i-1][j-1]  if str[i] == pattern[j] || pattern[j] == '?'
          or
          T[i-1][j] || T[i][j-1]  if pattern[j] == '*'
          or
          false

                pattern ---->
            0   x   ?   y   *   z
       0    T   F   F   F   F   F
       x    F   T   F   F   F   F              x?y*   xay
       a    F   F   T   F   F   F
       y    F   F   F   T   T   F
       l    F   F   F   F   T   F
       m    F   F   F   F   T   F
       z    F   F   F   F   T   T

 */
public class WildCardRegexMatching {

    public static void main(String[] args) {


    }
}
