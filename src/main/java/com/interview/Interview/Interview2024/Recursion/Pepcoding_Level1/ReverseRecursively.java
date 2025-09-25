package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

public class ReverseRecursively {

    public static void main(String[] args) {
        String str = "Divya";
        String res = reverse(str);
        System.out.println(res);
    }

    private static String reverse(String str) {
        if (str.length() == 0){
            return str;
        }

        char ch = str.charAt(0);    //D
        String s = str.substring(1);    //ivya

        String rev = reverse(s);
        String res = rev + ch;

        return res;

    }
}
