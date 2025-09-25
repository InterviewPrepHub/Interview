package com.interview.Interview.Pepcoding.DynamicProgramming;

//given a string of digits, encode the string and print the encoding
public class DecodeWays {

    public static void main(String[] args) {
        String s = "21123";
//        printEncoding(s, "");
        decodeWaysByDP(s);
    }

    private static void decodeWaysByDP(String s) {
        int n = s.length();
        int[] dp = new int[n];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {

            //4 cases
            if(s.charAt(i-1) == '0' && s.charAt(i) == '0') {
                dp[i] = 0;
            } else if(s.charAt(i-1) == '0' && s.charAt(i) != '0') {
                dp[i] = dp[i-1];
            } else if(s.charAt(i-1) != '0' && s.charAt(i) == '0')  {
                if((s.charAt(i-1) == '1') || (s.charAt(i-1) == '2')) {
                    dp[i] = (i>=2) ? dp[i-2] : 1;
                } else {
                    dp[i] = 0;
                }
            } else {

                dp[i] = dp[i-1];

                if(Integer.parseInt(s.substring(i-1,i+1)) <= 26) {
                    dp[i] = dp[i-1]+ ((i>=2) ? dp[i-2] : 1);
                } else {
                    dp[i] = dp[i-1];
                }

            }

        }

        System.out.println(dp[n-1]);
    }

    private static void printEncoding(String ques, String asf) {

        if(ques.length() == 0) {
            System.out.println(asf);
            return;
        } else if (ques.length() == 1) {
            char ch = ques.charAt(0);
            if (ch=='0') {
                return;
            } else {
                int chv = ch - '0';
                char code  = (char) ('a'+chv - 1);
                System.out.println(asf+code);
            }
        } else {
            char ch = ques.charAt(0);   //1
            String ros = ques.substring(1);     //23

            if(ch == '0') {
                return;
            } else {
                int chv = ch - '0';
                char code = (char) ('a'+ chv-1);
                printEncoding(ros, asf+code);
            }

            String ch2 = ques.substring(0,2);   //12
            String roq = ques.substring(2);     //3

            int ch2v = Integer.parseInt(ch2);
            if(ch2v <=26) {
                char code =(char) ('a' + ch2v - 1);
                printEncoding(roq, asf+code);
            }
        }
    }
}
