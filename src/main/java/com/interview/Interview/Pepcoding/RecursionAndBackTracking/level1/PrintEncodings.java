package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

public class PrintEncodings {

    public static void main(String[] args) {
        String s = "123";
        encoding(s, "");
    }

    /*
                                     3         .
                                      2\       /23
                                           23                 3
                                             1\             /12
                                                    123
     */

    private static void encoding(String ques, String asf) {
        if(ques.length() == 0) {
            System.out.println(asf);
            return;
        } else if (ques.length() == 1) {
            char ch = ques.charAt(0);
            if(ch == '0') {
                return;
            } else {
                int chv = ch - '0';
                char code = (char)('a' + chv - 1);
                asf = asf + code;
                System.out.println(asf);
            }
        } else {
            char ch = ques.charAt(0); //1
            String roq = ques.substring(1);
            if(ch == '0') {
                return;
            } else {
                int chv = ch - '0';
                char code = (char) ('a' +chv - 1);
                encoding(roq, asf+code);
            }

            String ch12 = ques.substring(0,2); //12
            String ros = ques.substring(2);

            int ch12v = Integer.parseInt(ch12);

            if(ch12v <= 26) {
                char code = (char) ('a' + ch12v - 1); //12 => l
                encoding(ros, asf+code);
            }
        }
    }
}
