package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

public class Fibnocci {

    public static void main(String[] args) {
        int n = 6;
        int fibn = fib(n);

        System.out.println(fibn);

    }

    private static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        System.out.println("Hello "+n);
        int fnm1 = fib(n-1);
        int fnm2 = fib(n-2);
        int fibn = fnm1 + fnm2;
        return fibn;
    }
}
