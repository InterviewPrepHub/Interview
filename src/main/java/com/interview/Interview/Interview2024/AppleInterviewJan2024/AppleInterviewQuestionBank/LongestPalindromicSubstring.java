package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank;

public class LongestPalindromicSubstring {

    public static String lps(String s) {
        int start = 0;
        int end = 0;
        int n = s.length();
        boolean[][] palin = new boolean[n][n];
        for (int curr_len=0;curr_len<palin.length;curr_len++) {
            for (int i=0, j=curr_len;j< palin.length;i++,j++) {

                if(curr_len == 0) {
                    palin[i][j] = true;
                }
                else if (curr_len == 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        palin[i][j] = true;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && palin[i+1][j-1]) {
                        palin[i][j] = true;
                    }
                }

                if(palin[i][j]){
                    start = i;
                    end = j;
                }
            }
        }

        String ans = "";
        for (int i=start;i<=end;i++) {
            ans += s.charAt(i);
        }
        return ans;
    }

    /*public String lps(String str) {

        int start = 0, end = 0;
        int n = str.length();

        boolean[][] palin = new boolean[n][n];

        for (int curr_len = 0; curr_len<palin.length; curr_len++){
            for (int i=0, j= curr_len; i<palin.length;i++,j++) {

                if (curr_len == 0) {
                    palin[i][j] = true;
                } else if (curr_len == 1) {
                    if(str.charAt(i) == str.charAt(j)){
                        palin[i][j] = true;
                    }
                } else {
                    if (str.charAt(i) == str.charAt(j) && palin[i+1][j-1]) {
                        palin[i][j] = true;
                    }
                }

                if (palin[i][j]) {
                    start = i;
                    end = j;
                }

            }
        }

        String ans = "";
        for (int i = start;i<=end;i++) {
            ans+=str.charAt(i);
        }
        return ans;

    }*/

    public static void main(String[] args) {
        LongestPalindromicSubstring s = new LongestPalindromicSubstring();
        String ans = s.lps("banana");
        System.out.println(ans);
    }
}
