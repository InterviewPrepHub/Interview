package com.interview.Interview.Interview2025.Tesco.Round1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Tesco has around 3200 stores and more than 10% of the stores have around 800 colleagues each.

In a store, a colleague can work for multiple departments. Here are shifts of a colleague in various departments:

In Bakery department: From 8 to 10
In Checkout department: From 10 to 12
In Diary department: From 14 to 19
Given the above split of shifts, provide an API/method to return the different shifts of a colleague for the day
after merging contiguous shifts. This will be exposed to the colleague in different UI and help them plan their day accordingly.


Bakery -> [8,10]
Checkout -> [10,12]
Diary -> [14,19]

result -> [8,12] [14,19]

 */
public class Solution {

    public static void main(String[] args) {

        //14 to 19, 8 to 10, 10 to 12
        //8 to 10, 9 to 12, 11 to 15
        List<int[]> list1 = Arrays.asList(new int[] {8,10}, new int[] {10,12}, new int[] {14,19});
        List<int[]> list2 = Arrays.asList(new int[] {14,19}, new int[] {8,10}, new int[] {10,12});
        List<int[]> list = Arrays.asList(new int[] {8,10}, new int[] {-9,12}, new int[] {11,15});
        List<int[]> result = mergeShifts(list);

        System.out.println("merge shifts");
        for (int[] shift : result) {
            System.out.println("["+shift[0]+","+shift[1]+"]");
        }

        testNegativeCase_ThrowException();

    }

    static void testNegativeCase_ThrowException() {
        List<int[]> list = Arrays.asList(new int[] {8,10}, new int[] {-9,12}, new int[] {11,15});
        try {
            mergeShifts(list);
            System.out.println("negative test case failed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<int[]> mergeShifts(List<int[]> list) {
        
        List<int[]> result = new ArrayList<>();
        
        //sorting shifts by start time
        list.sort((a,b) -> Integer.compare(a[0],b[0])); // O(n log n)
        
        //merge intervals
        int[] curr = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int[] next = list.get(i);
            
            if(curr[1] >= next[0]) {
                curr[1] = Math.max(curr[1], next[1]);
            } else {
                result.add(curr);
                curr = next;
            }
            
        }
        
        result.add(curr);
        return result;
    }

}
