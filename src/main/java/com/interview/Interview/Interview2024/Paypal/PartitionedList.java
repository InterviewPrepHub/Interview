package com.interview.Interview.Interview2024.Paypal;

/*
Given a list of strings, and a partition size n, write a method that returns a list of lists with all the partitioned lists.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionedList {

    public static List<List<String>> partitionStrings(List<String> inputList, int n) {
        List<List<String>> result = new ArrayList<>();
        int len = inputList.size();
        for (int i = 0; i < len; i+=n) {
            int endIndex = Math.min(i+n, len);
            List<String> sublist = inputList.subList(i, endIndex);
            result.add(sublist);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> str = Arrays.asList("abc", "def", "ghi", "jkl", "mno");
        int n = 3;
        List<List<String>> res = partitionStrings(str, n);
        for (List<String> list: res) {
            System.out.println(list);
        }
    }
}
