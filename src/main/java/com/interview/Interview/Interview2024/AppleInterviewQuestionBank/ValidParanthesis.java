package com.interview.Interview.Interview2024.AppleInterviewQuestionBank;

import java.util.Stack;

public class ValidParanthesis {

    public boolean isValid(String str) {

        if (str.length() == 0) {
            return true;
        }
        if (str.length() == 1) {
            return false;
        }

        Stack<Character> stk = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c=='{') {
                stk.push(c);
            }
            else if ((c == ')' && !stk.isEmpty() && stk.peek() == '(')) {
                stk.pop();
            }
            else if ((c == '}' && !stk.isEmpty() && stk.peek() == '{')) {
                stk.pop();
            }
            else if ((c == ']' && !stk.isEmpty() && stk.peek() == '[')) {
                stk.pop();
            }
        }

        if (stk.empty()) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ValidParanthesis v = new ValidParanthesis();
        boolean b = v.isValid("()[]{}");
        System.out.println(b);
    }
}
