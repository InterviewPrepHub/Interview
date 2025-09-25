package com.interview.Interview.Pepcoding.DynamicProgramming;

public class FibnocciSum {

    public static void main(String[] args) {
        int n = 6;
        int fibn = fib(n, new int[n+1]);
        System.out.println(fibn);
    }

    private static int fib(int n, int[] qb) {

        if(n == 0 || n == 1) {
            return n;
        }

        if(qb[n]!=0) {
            return qb[n];
        }

        System.out.println("hello : "+n);

        int fnm1 = fib(n-1, qb);
        int fnm2 = fib(n-2, qb);
        int fibn = fnm1 + fnm2;

        qb[n] = fibn;
        return fibn;
    }
}

/*
https://www.youtube.com/watch?v=94dfRrDANRY&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy

Time complexity
O(n) = O(n-1) + O(n-2) + k

O(n) ~ 2 O(n-1) + k
O(n) =  k(2 pow n -1)
 */
