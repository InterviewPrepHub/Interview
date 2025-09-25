package com.interview.Interview.Pepcoding.StackAndQueues.Level1.Stack;

public class MinRemoveToMakeValidParanthesis {

    public static void main(String[] args) {
        String str = "lee(t(c)o)de)";
//        String str = "a)b(c)d";
        minRemove(str);
    }

    private static void minRemove(String str) {

        StringBuilder sb = new StringBuilder();
        int open = 0;

        //drop extra ')'
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                open++;
                sb.append(ch);
            } else if(ch == ')') {
                if(open > 0) {
                    open--;
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }

        }

//        System.out.println(sb);

        //drop extra '(' from right
        StringBuilder sb1 = new StringBuilder();
        for (int i = sb.length()-1; i >=0 ; i--) {
            char c = sb.charAt(i);
            if(c == '(' && open > 0) {
                open--;
            } else {
                sb1.append(c);
            }
        }

        System.out.println(sb1.reverse().toString());
    }

}
