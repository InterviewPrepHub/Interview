package com.interview.Interview.TusharRoy.DynamicProgramming;

/*
* -> matches 0 or more occurrences of ch before *
. -> matches any single character

i = string j = pattern

T[i][j] = T[i-1][j-1] if str[i] = pattern[j] || pattern[j] = '*'
          or
          T[i][j-2] --> 0 occurrence                                | if pattern[j]=='*'
          T[i-1][j] if str[i]==pattern[j-1] || pattern[j-1]=='.'    |


a.b -> acb, apb, aab -> true
       ab, acyb -> false

a*b -> b, ab, aab, aaab -> true
       a, acb -> false

a*b.*y -> by, bly, ably -> true

 */
public class RegexMatching {

    public static void main(String[] args) {

        String s = "xaabyc";
        String p = "xa*b.c";

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        boolean T[][] = new boolean[str.length + 1][pattern.length + 1];

        T[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            if (pattern[i-1] == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == str[i - 1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    T[i][j] = T[i][j - 2];
                    if (pattern[j-2] == '.' || pattern[j - 2] == str[i - 1]) {
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }

        System.out.println(T[str.length][pattern.length]);
    }
}
