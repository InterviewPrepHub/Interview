package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.util.ArrayList;

public class GetStairsPath {

    public static void main(String[] args) {
        ArrayList<String> paths = getPath(4);
        for (String p : paths) {
            System.out.println(p);
        }

        System.out.println();
        
        printPath(4,"");
    }

    private static void printPath(int n, String ans) {

        if (n==0) {
            System.out.println(ans);
            return;
        }

        if (n<0) {
            return;
        }

        printPath(n-1, ans+"1");
        printPath(n-2, ans+"2");
        printPath(n-3, ans+"3");


    }

    private static ArrayList<String> getPath(int n) {

        if (n==0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        if (n<0) {
            ArrayList<String> bres = new ArrayList<>();
            return bres;
        }

        ArrayList<String> path1 = getPath(n-1);
        ArrayList<String> path2 = getPath(n-2);
        ArrayList<String> path3 = getPath(n-3);

        ArrayList<String> paths = new ArrayList<>();

        for (String path : path1) {
            paths.add("1"+path);
        }

        for (String path : path2) {
            paths.add("2"+path);
        }

        for (String path : path3) {
            paths.add("3"+path);
        }

        return paths;

    }
}
