package com.interview.Interview.Pepcoding.DynamicProgramming;

public class FriendsPairing {

    public static void main(String[] args) {
        String str = "123";
        pairing(str, "");


//        System.out.println();
//        int n = 3;
//        int res = countPairing(n);
//        System.out.println(res);
    }

    private static int countPairing(int n) {
        if(n<=2) {
            return n;
        }
        return countPairing(n-1) + (n-1)*countPairing(n-2);
    }

    /*
                 3     .
                 2\   /23
                    23       3       2
    friend is single 1\     12|    13/   friend is paired up
                            123
     */

    private static void pairing(String ques, String asf) {

        if(ques.length() == 0) {
            System.out.println(asf);
            return;
        }

        char ch = ques.charAt(0); //1,2
        String roq = ques.substring(1); //23,3
        pairing(roq, asf + ch);

        for (int i = 1; i < ques.length(); i++) {
            char ch2 = ques.charAt(i);
            String remaining = ques.substring(1, i) + ques.substring(i+1);
            pairing(remaining, asf+ch2);
        }
    }
}
