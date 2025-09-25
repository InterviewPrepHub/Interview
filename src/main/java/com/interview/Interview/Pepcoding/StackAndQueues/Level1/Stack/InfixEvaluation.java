package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

import java.util.Stack;

/*
here we have two stacks - operand [numbers] & operator [+,-,*,?,() etc]

if lower priority operator arrives it will not pop anything until the
operator stack is not empty or if we see any openin g bracket or if we
dont see lower priority
 */
public class InfixEvaluation {

    /*public static void main(String[] args) {

        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        String s = "2+(5-3*6/2)";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            boolean res = Character.isDigit(ch);

            while (ch != )

            if(res) {
                operand.push(ch-'0');
            } else {
                operator.push(ch);
            }
        }
    }*/
}
