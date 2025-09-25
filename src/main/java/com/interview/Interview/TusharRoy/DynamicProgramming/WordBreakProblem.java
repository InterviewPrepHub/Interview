package com.interview.Interview.TusharRoy.DynamicProgramming;

public class WordBreakProblem {

    /*
        word = Iamace ---> I | am | ace ----> so yes

                 I   a   m   a   c   e
                 0   1   2   3   4   5
        I    0   T   T   T   T   F   T
        a    1       T   T   T   F   T
        m    2           F   F   F   F
        a    3               T   F   F
        c    4                   F   F
        e    5                       F


        len = 1   I |  a |  m |  a  | c |  e
        len = 2   Ia      am      ma      ac      ce
                  01      12      23      34      45
     */
}
