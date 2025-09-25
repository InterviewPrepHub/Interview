package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecursionAndBacktrackingBasic {

    public static void main(String[] args) {

        //print increasing I
        System.out.println();
        printIncI(1);

        //print increasing II
        System.out.println();
        printIncII(5);

        //print decreasing
        System.out.println();
        printDec(5);

        System.out.println();
        printDecInc(5);

        System.out.println();
        factorial(5); //5*4*3*2*1


        System.out.println();
        calcXPowerN(2,3);

        System.out.println();
        printZigZag(3);

        System.out.println();
        towerOfHanoi(3, 'A', 'B', 'C');

        System.out.println();
        int[] a = {10,20,30};
        displayArr(a, a.length-1);

        System.out.println();
        displayArrInReverse(a, a.length-1);

        System.out.println();
        int[] a1 = {10,8,30,1,6};
        int maxEle = maxOfAnArr(a1, 0);
        System.out.println("maxElement : "+maxEle);

        System.out.println();
        int[] a2 = {2,6,8,9,8,3};
        int res = firstIndexOfOccurrence(a2, 0, 8);
        System.out.println(res);

        System.out.println();
        int res1 = lastIndexOfOccurrence(a2, a2.length-1, 8);
        System.out.println(res1);

        /*System.out.println();
        int[] res3 = new int[a2.length];
        allIndicesInArr(a2, 0, 8, res3, 0);

        for (int i = 0; i < res3.length; i++) {
            System.out.println(res3[i]);
        }*/


        System.out.println();
        /*
            String s = abc
            s.length = 3
            no of subsequence = 2 power 3 = 8

            substring = n*(n+1)/2 ---> a, ab, abc, b, bc, c
         */
        ArrayList<String> res4 = getSubsequence("abc");
        System.out.println("List of all subsequence : ");
        for (String s : res4) {
            System.out.println(s);
        }

        System.out.println();

        System.out.println("Print all subsequence : ");
        printSubsequence("abc","");


    }

    private static void printSubsequence(String str, String ans) {

        if(str.length() == 0) {
            System.out.println(ans);
            return;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);

        printSubsequence(ros, ans+"");
        printSubsequence(ros, ans + ch);
    }

    /*
    ✅ Time complexity = O(n * 2^n)
    (because for each of the 2^n subsequences, we may be doing string concatenation of length up to n)

    Space Complexity
	•	You’re returning an ArrayList<String> with 2^n strings.
	•	Each string is at most length n.

    ✅ Space complexity = O(n * 2^n) for the list
    Plus O(n) space for the recursion call stack.
     */

    private static ArrayList<String> getSubsequence(String s) {
        if(s.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        char ch = s.charAt(0);
        String ros = s.substring(1);

        ArrayList<String> rres = getSubsequence(ros);
        ArrayList<String> mres = new ArrayList<>();

        for (String res : rres) {
            mres.add(""+ res);
            mres.add(ch + res);
        }
        return mres;
    }

    /*private static int allIndicesInArr(int[] a, int idx, int data, int[] res, int i) {

        if(idx == a.length) {
            if(a[idx] == data) {
                res[i] = idx;
            } else {
                res[i] = -1;
            }
        }
        int aiop1 = allIndicesInArr(a, idx+1, data, res, i);

        if(a[idx] == data) {
            res[i] = idx;
            i++;
        } else {
            res[i] = aiop1;
            i++;
        }
        return 0;
    }*/

    private static int lastIndexOfOccurrence(int[] a, int idx, int data) {

        if (idx == 0) {
            if(a[idx] == data) {
                return idx;
            } else {
                return -1;
            }

        }
        int fo1 = lastIndexOfOccurrence(a, idx-1, data);
        if(a[idx] == data) {
            return idx;
        } else {
            return fo1;
        }
    }

    private static int firstIndexOfOccurrence(int[] a, int idx, int data) {

        if (idx == a.length-1) {
            if(a[idx] == data) {
                return idx;
            } else {
                return -1;
            }

        }
        int fo1 = firstIndexOfOccurrence(a, idx+1, data);
        if(a[idx] == data) {
            return idx;
        } else {
            return fo1;
        }

    }

    private static int maxOfAnArr(int[] a1, int idx) {

        //{10,8,30,1,6}
        if(idx == a1.length-1) return idx;
        int misa = maxOfAnArr(a1, idx+1);
        if (misa > a1[idx]) {
            return misa;
        } else {
            return a1[idx];
        }
    }

    private static int findMax(int[] a1) {

        int max = a1[0];
        for (int i=1;i<a1.length;i++) {
            max = Math.max(max, a1[i]);
        }
        return max;
    }

    private static void displayArrInReverse(int[] arr, int idx) {
        if (idx<0) return;
        System.out.println(arr[idx]);
        displayArrInReverse(arr, idx-1);
    }

    private static void displayArr(int[] arr, int idx) {

        if (idx<0) return;
        displayArr(arr, idx-1);
        System.out.println(arr[idx]);
    }

    /*
        print instructions to move disk from tower1 to tower2 using tower3
            - move one disk at a time
            - never place a small disk under a larger
            - you can only move one disk at a time
     */
    private static void towerOfHanoi(int n, char A, char B, char C) {

        if (n == 0) return;
        towerOfHanoi(n-1, A,C,B);
        System.out.println(n +"["+A+" -> "+B+"]");
        towerOfHanoi(n-1, C,B,A);

    }

    private static void printZigZag(int n) {
        if (n==0) return;
        System.out.println("Pre : "+n);
        printZigZag(n-1);
        System.out.println("In : "+n);
        printZigZag(n-1);
        System.out.println("Post : "+n);
    }

    /*
        calcXPowerN(x, n) = x * calcXPowerN(x,n)
     */
    private static int calcXPowerN(int x, int n) {

        if(n == 0) return 1;
        int pnm1 = calcXPowerN(x, n-1);
        int pn = x * pnm1;
        return pn;

    }

    private static int factorial(int n) {

        if (n==1) return 1;
        int fnm1 = factorial(n-1);
        int fn = n * fnm1;
        return fn;
    }

    private static void printDecInc(int n) {

        //dec
        if(n<=0) return;
        System.out.println(n);
        printDecInc(n-1);
        System.out.println(n);

    }

    private static void printDec(int i) {

        if (i <= 0) {
            return;
        }
        System.out.println(i);
        printDec(i-1);
    }

    public static void printIncII(int n) {
        if(n<=0) return;
        printIncII(n-1);
        System.out.println(n);

    }

    public static void printIncI(int n){
        if(n>5) {
            return;
        }
        System.out.println(n);
        printIncI(n+1);
    }
}
