package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Arrays;

public class NextPalindromeNumber {

    public static void main(String[] args) {
        int n = 1200;

        int res = findNextPalindrome(n);
        System.out.println(res);
    }

    private static int findNextPalindrome(int n) {

        while (true) {
            n++;
            if (isPalindrome(n)) {
                return n;
            }
        }
    }

    private static boolean isPalindrome(int n) {
        int oNum = n;
        int rNum = 0;

        while(n > 0) {
           int digit = n % 10;
           rNum = rNum * 10 + digit;
           n = n/10;
        }

        return oNum == rNum;
    }
}











