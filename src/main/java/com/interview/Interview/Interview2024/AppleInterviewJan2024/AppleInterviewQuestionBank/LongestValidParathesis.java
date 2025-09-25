package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank;

import java.util.Stack;

public class LongestValidParathesis {

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int maxLength = 0;
        stack.push(-1); // Initialize with -1 to handle cases where ')' is the first character

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
//        String str = "(()())()";
        String str = ")()()())";
        int result = longestValidParentheses(str);
        System.out.println("Length of the longest valid parentheses substring: " + result);
    }
}
