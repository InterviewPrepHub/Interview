package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.ArrayList;

/*
https://www.geeksforgeeks.org/count-ways-reach-nth-stair-using-step-1-2-3/
A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
Implement a method to count how many possible ways the child can run up the stairs.

Input : 4
Output : 7
Explanation:
Below are the seven ways
 1 step + 1 step + 1 step + 1 step
 1 step + 2 step + 1 step
 2 step + 1 step + 1 step
 1 step + 1 step + 2 step
 2 step + 2 step
 3 step + 1 step
 1 step + 3 step

 */

public class GetStairsPath {

    public static void main(String[] args) {
        ArrayList<String> res = getPath(4);
        System.out.println(res.size());
    }

    private static ArrayList<String> getPath(int n) {
        if(n==0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(" ");
            return bres;
        } else if(n < 0) { // we can go from 0 to 0 but we cannot go from negative to 0 so we just stand
            //take a case where you are at stair 2 and u want to hop by 3 steps at a time then yuo well be at -1 [2-3]
            //so there will no result for such cases and we shall move to the next.
            ArrayList<String> bres = new ArrayList<>();
            return bres;
        }
        ArrayList<String> res1 =  getPath(n-1);
        ArrayList<String> res2 =  getPath(n-2);
        ArrayList<String> res3 =  getPath(n-3);

        ArrayList<String> paths = new ArrayList<>();
        for (String path : res1) {
            paths.add(path+1);
        }
        for (String path : res2) {
            paths.add(path+2);
        }
        for (String path : res3) {
            paths.add(path+3);
        }
        return paths;
    }
}
