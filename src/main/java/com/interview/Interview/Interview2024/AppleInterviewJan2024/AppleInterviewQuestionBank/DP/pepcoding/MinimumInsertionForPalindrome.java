package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.DP.pepcoding;

public class MinimumInsertionForPalindrome {    //similar to longest common subsequence

    public static void main(String[] args) {
        String str = "geeks";
//        int res = findInsertions(str, 0, str.length()-1);
//        System.out.println(res);
        int val = findInsertionLCS(str);
        System.out.println(val);
    }

//        g   e   e   k   s   _
//    s   3   3   2   2   2   0
//    k   3   3   2   1   0   0
//    e   3   3   2   0   0   0
//    e   2   2   1   0   0   0
//    g   1   0   0   0   0   0
//    _   0   0   0   0   0   0


    private static int findInsertionLCS(String str) {
        StringBuilder s = new StringBuilder(str);
        StringBuilder rev = s.reverse();
        int n = s.length();
        //The minimum number of insertions needed is the length of the input string minus ‘l’.
        return n - lcs(str, rev.toString());
    }

    private static int lcs(String s1, String s2) {
        int n = s1.length()+1;
        int m = s2.length()+1;
        int[][] dp = new int[n][m];

        for (int i = dp.length-2; i >=0 ; i--) {
            for (int j = dp[0].length-2; j >=0 ; j--) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);

                if (c1==c2) {
                    dp[i][j] = 1+dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[0][0];
    }

    private static int findInsertions(String str, int l, int r) {

        if (l==r) return 0;
        if (l==r-1) return (str.charAt(l) == str.charAt(r)) ? 0 : 1;

        return (str.charAt(l) == str.charAt(r)) ?
                findInsertions(str, l+1, r-1) :
                (1 + Math.min(findInsertions(str,l,r-1), findInsertions(str, l+1, r)));
    }
}
