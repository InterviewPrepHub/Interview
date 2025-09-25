package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        factorial(n);

//        int n1 = scanner.nextInt();
//        int x = scanner.nextInt();
//        System.out.println(power(x,n1));

//        System.out.println(logPower(x,n1));

        int n = scanner.nextInt();
        printZigZag(n);


    }

    private static void printZigZag(int n) {
        if(n==0) {
            return;
        }
        System.out.println("Pre : "+n);
        printZigZag(n-1);
        System.out.println("In : "+n);
        printZigZag(n-1);
        System.out.println("Post : "+n);

    }

    private static int logPower(int x, int n) {

        if(n == 0) {
            return 1;
        }

        int pxnb2 = logPower(x, n/2);
        int pxn = pxnb2 * logPower(x, n/2);

        if (n%2==1) {
            pxn = x * pxnb2 * pxnb2;
        }
        return pxn;
    }

    private static int power(int x,int n) {
        if (n==0) {
            return 1;
        }

        int pxnm1 = power(x, n-1);
        int pxn = x * pxnm1;
        return pxn;
    }

    private static int factorial(int n) {
        if(n == 1) {
            return 1;
        }
        int fnm1 = factorial(n-1);
        int fn = n * fnm1;
        return fn;
    }
}
