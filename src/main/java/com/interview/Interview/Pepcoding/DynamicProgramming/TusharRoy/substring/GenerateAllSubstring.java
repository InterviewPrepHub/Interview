package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy.substring;

import java.util.ArrayList;

public class GenerateAllSubstring {

    public static void main(String[] args) {
        String s = "123";

        //to store all substring
        ArrayList<String> res = new ArrayList<>();
        //to store current string
        StringBuilder sb = new StringBuilder();
        subString(s, s.length(), 0, sb, res);
        System.out.println(res);
    }

    private static void subString(String s, int n, int idx, StringBuilder curr, ArrayList<String> res) {

        if(idx == n) {
            return;
        }

        //get the character
        char ch = s.charAt(idx); //1

        //append char to curr string
        curr.append(ch);    //1,12,123

        //add curr string to result
        res.add(curr.toString());   //{1,12,123}

        //move to next index
        subString(s, n, idx+1, curr, res);

        //remove curr ch from current string
        curr.deleteCharAt(curr.length()-1);     //{12,1,'',2,''}

        //if the curr string is empty then skip curr index to start the new substring
        if (curr.length() == 0) {
            subString(s, n, idx+1, curr, res);
        }
    }
}
