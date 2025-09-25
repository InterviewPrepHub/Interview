package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.ArrayList;

public class GetSubsequence {

    public static void main(String[] args) {
        String s = "abc";
        ArrayList<String> res = getSubsequence(s);
        for (String s1:res) {
            System.out.println(s1);
        }
    }

    //time complexity - 2 raise to power s.length
    private static ArrayList<String> getSubsequence(String s) {

        if (s.length() == 0) {
            ArrayList<String> res = new ArrayList<>();
            res.add("");
            return res;
        }

        char ch = s.charAt(0); //a
        String remStr = s.substring(1); //bc
        ArrayList<String> rres = getSubsequence(remStr);

        ArrayList<String> mres = new ArrayList<>();
        for (String res : rres) {
            mres.add(" "+res);
        }
        for (String res : rres) {
            mres.add(ch+res);
        }

        return mres;
    }
}
