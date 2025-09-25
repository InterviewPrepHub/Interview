package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Arrays;

public class NextSmallestPalindrome {

    public static void main(String[] args) {
        int num = 123896;

        //convert num to String
        String input = String.valueOf(num);

        nextPalindrome(input);

    }

    private static void nextPalindrome(String input) {
        // Convert the input to a char array for easy manipulation
        char[] digits = input.toCharArray();

        // Find the middle index for odd-length input or the left-middle index for even-length input
        int mid = (digits.length - 1) / 2;

        // Flag to check if we need to increment the middle digit
        boolean incrementMiddle = false;

        // Start from the middle and move towards the beginning
        for (int i = mid; i >= 0; i--) {
            int j = digits.length - 1 - i;

            if (digits[i] < digits[j]) {
                incrementMiddle = true;
            }

            // Mirror the first half to the second half
            digits[j] = digits[i];
        }

        // If the middle digit needs to be incremented
        if (incrementMiddle) {
            int carry = 1;
            for (int i = mid; i >= 0; i--) {
                int digit = Character.getNumericValue(digits[i]) + carry;
                carry = digit / 10;
                digits[i] = (char) ((digit % 10) + '0');
            }
        }

        System.out.println(new String(digits));
    }
}
