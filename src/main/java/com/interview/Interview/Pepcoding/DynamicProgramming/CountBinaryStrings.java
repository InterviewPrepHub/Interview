package com.interview.Interview.Pepcoding.DynamicProgramming;

import java.util.*;

public class CountBinaryStrings {

    public static void main(String[] args) {
        int n = 6;
//        countBinaryStringsByDP();

        countBinaryStringsWithoutDPArr();
//        countBinaryStrings(n);
//        HashMap<String, Integer> map = new HashMap<>();
//        countBinaryStrings(n, map);
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() +"-"+entry.getValue());
//        }
    }

    private static void countBinaryStringsByDP() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp0 = new int[n+1];
        int[] dp1 = new int[n+1];

        dp0[1] = 1;
        dp1[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp1[i] = dp1[i-1] + dp0[i-1];
            dp0[i] = dp1[i-1];
        }
        System.out.println(dp0[n]+dp1[n]);
    }

    private static void countBinaryStringsWithoutDPArr() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int oczeros = 1;
        int ocones = 1;

        for (int i = 2; i <= n; i++) {
            int nczeros = ocones;
            int ncones = ocones + oczeros;


            ocones = ncones;
            oczeros = nczeros;
        }
        System.out.println(oczeros+ocones);
    }

    public static int countBinaryStrings(int n, HashMap<String, Integer> map) {
        return countBinaryStringsUtil(n, 0, "", map);
    }

    private static int countBinaryStringsUtil(int n, int lastDigit, String path, HashMap<String, Integer> map) {
        // Base case: if the length of the string is 0, return 1 (empty string)
        if (n == 0) {
//            System.out.println(path);
            return 1;
        }

        int count = 0;

        String key = n+","+lastDigit;

        // If the last digit was 0, we can only append 1 to the string
        if (lastDigit == 0) {
            count += countBinaryStringsUtil(n - 1, 1, path + "1", map);
        } else {
            // If the last digit was 1, we can append either 0 or 1 to the string
            count += countBinaryStringsUtil(n - 1, 0, path + "0", map);
            count += countBinaryStringsUtil(n - 1, 1, path + "1", map);
        }
        map.put(key, count);

        return count;
    }

    public static int countBinaryStrings(int n) {
        return countBinaryStringsUtil(n, 0, "");
    }

    // Helper function to count binary strings with no consecutive 0s
    private static int countBinaryStringsUtil(int n, int lastDigit, String path) {
        // Base case: if the length of the string is 0, return 1 (empty string)
        if (n == 0) {
            System.out.println(path);
            return 1;
        }

        int count = 0;

//        System.out.println(n+"-"+lastDigit);

        // If the last digit was 0, we can only append 1 to the string
        if (lastDigit == 0) {
            count += countBinaryStringsUtil(n - 1, 1, path + "1");
        } else {
            // If the last digit was 1, we can append either 0 or 1 to the string
            count += countBinaryStringsUtil(n - 1, 0, path + "0");
            count += countBinaryStringsUtil(n - 1, 1, path + "1");
        }

        return count;
    }


}
