package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

public class GetAllSubstrings {

    public static void main(String[] args) {
        getSubstrings("abcb");
    }

    private static void getSubstrings(String str) {
        getSubstrings(str, 0, "");
    }

    private static void getSubstrings(String str, int index, String currentString) {
        int n = str.length();
        if (index == n) {
            return;
        }

        for (int i = index; i < str.length(); i++) {
            currentString += str.charAt(i);
            System.out.println(currentString);
            getSubstrings(str, i+1, currentString);
            currentString = currentString.substring(0, currentString.length()-1);
        }
    }
}
