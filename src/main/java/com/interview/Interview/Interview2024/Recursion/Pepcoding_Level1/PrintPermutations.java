package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

public class PrintPermutations {

    public static void main(String[] args) {
        String str = "abc";
        pp(str,"");
    }

    private static void pp(String str, String psf) {

        if (str.length()==0) {
            System.out.println(psf);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            pp(str.substring(0,i)+str.substring(i+1),psf+ch);
        }
    }
}
