package com.interview.Interview.iDeserve;

import java.util.Arrays;

/*
Given a string, find the first non-repeating character in that string.

Example:
Input: ADBCGHIEFKJLADTVDERFSWVGHQWCNOPENSMSJWIERTFB

Output:
K

 */
public class FirstNonRepeatingCharacterInString {

    public static void main(String[] args) {
        String input = "ADBCGHIEFKJLADTVDERFSWVGHQWCNOPENSMSJWIERTFB";

        int n = input.length();

        int[] charToIndex = new int[256];
        Arrays.fill(charToIndex, -1);

        for (int i = 0; i < n; i++) {
            if(charToIndex[input.charAt(i)] == -1) {
                charToIndex[input.charAt(i)] = i;
            } else {
                charToIndex[input.charAt(i)] = -2;
            }
        }

        int minIdx = n;     //index of first non repeating character
        for (int i = 0; i < 256; i++) {
            if (charToIndex[i] >= 0 && charToIndex[i] < minIdx) {
                minIdx = charToIndex[i];
            }
        }

        if (minIdx >=0 && minIdx < n) {
            System.out.println(input.charAt(minIdx));
        } else {
            System.out.println("No non repeating character");
        }
    }
}
