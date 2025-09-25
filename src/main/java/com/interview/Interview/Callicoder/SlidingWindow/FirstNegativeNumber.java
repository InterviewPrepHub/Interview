package com.interview.Interview.Callicoder.SlidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given an array of integers a, and a positive integer k, find the first negative integer for each and every window (contiguous subarray) of size k.
If a window does not contain a negative integer, then print 0 for that window.

Example 1

Input: a[] = {-5, 1, 2, -6, 9}, k = 2
Output : -5 0 -6 -6

Explanation: First negative integer in every window of size 2
{-5, 1} = -5
{1, 2} = 0 (does not contain a negative integer)
{2, -6} = -6
{-6, 9} = -6
Example 2

Input : a[] = {10, -1, -5, 7, -15, 20, 18, 24} , k = 3
Output : -1 -1 -5 -15 -15 0
Explanation: First negative integer in every window of size 3
{10, -1, -5} = -1
{-1, -5, 7} = -1
{-5, 7, -15} = -5
{7, -15, 20} = -15
{-15, 20, 18} = -15
{20, 18, 24} = 0 (does not contain a negative integer)
 */
public class FirstNegativeNumber {

    public static void main(String[] args) {

        int a[] = {-5, 1, 2, -6, 9}, k = 2;

        int n = a.length;
        int windowStart = 0;
        int[] firstNegNum = new int[n-k+1];
        int idx = 0;

        Deque<Integer> q = new ArrayDeque<>();

        for (int windowEnd = 0; windowEnd<a.length; windowEnd++) { //0,1,2

            if(a[windowEnd] < 0) {
                q.add(a[windowEnd]); //-5
            }

            if(windowEnd-windowStart+1 == k) {
                if (q.isEmpty()) {
                    firstNegNum[idx] = 0;
                    idx++;
                } else {
                    int num = q.peek();
                    firstNegNum[idx] = num;
                    idx++;

                    if(num == a[windowStart]) {
                        q.remove();
                    }
                }
                windowStart++;
            }

        }

        for (int i = 0; i < firstNegNum.length; i++) {
            System.out.println(firstNegNum[i]);
        }

    }
}
