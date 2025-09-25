package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.util.ArrayList;

public class GetMazePathWithJumps {

    public static void main(String[] args) {

        ArrayList<String> res = getMazePath(1,1,3,3);
        System.out.println(res);
    }

    private static ArrayList<String> getMazePath(int sr, int sc, int dr, int dc) {

        if(sc == dc && sr == dr ) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> paths = new ArrayList<>();

        // move horizontally
        if (sc < dc) {
            ArrayList<String> hPaths = getMazePath(sr, sc + 1, dr, dc);
            for (String path : hPaths) {
                paths.add("h" + path);
            }
        }

        // move vertically
        if (sr < dr) {
            ArrayList<String> vPaths = getMazePath(sr + 1, sc, dr, dc);
            for (String path : vPaths) {
                paths.add("v" + path);
            }
        }

        return paths;
    }
}
