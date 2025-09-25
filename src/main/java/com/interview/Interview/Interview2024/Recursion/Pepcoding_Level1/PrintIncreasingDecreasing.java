package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.Scanner;

public class PrintIncreasingDecreasing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int n1 = scanner.nextInt();
        printDecreasing(n);
        printIncreasing(n1);
    }

    public static void printDecreasing(int n){
        System.out.println(n);
        printDecreasing(n-1);
    }

    public static void printIncreasing(int n){
        printDecreasing(n-1);
        System.out.println(n);
    }

}
