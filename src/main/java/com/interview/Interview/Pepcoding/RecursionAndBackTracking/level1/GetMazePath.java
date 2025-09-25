package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.util.ArrayList;

public class GetMazePath {

    public static void main(String[] args) {

        getPath(3,3);

        printPath(3,3);

    }

    private static void getPath(int r, int c) {

        ArrayList<String> res = getPath(1,1,r,c);
        System.out.println(res);
    }

    private static void printPath(int r, int c) {


        printPath(1,1,r,c,"");
    }

    /*
        ✅ Time Complexity

You still explore all possible paths, which is:

P = \binom{(n + m - 2)}{(n - 1)}

And you print each path of length O(n + m)

So,

🔷 Time = O(P × (n + m))

(Same as getPath, since printing each path takes linear time)


✅ Space Complexity
	•	No list stored, so no space for storing all strings.
	•	Only space used is call stack and the current ans string in each call.

🔷 Space = O(n + m)

(Much more efficient in terms of space!)

     */
    private static void printPath(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return;
        }

        if (sr > dr || sc > dc) {
            return;
        }

        if (sr < dr) {
            printPath(sr + 1, sc, dr, dc, ans + "v"); // v = vertical move (down)
        }

        if (sc < dc) {
            printPath(sr, sc + 1, dr, dc, ans + "h"); // h = horizontal move (right)
        }
    }

    /*
        To reach from (0, 0) to (n-1, m-1), you can take n-1 vertical and m-1 horizontal steps in any order.

        🔷 Time = O(P × (n + m))
           Where P = number of paths, and each string of length (n + m - 2) is created and stored.


        ✅ Space Complexity
	•	Recursive call stack goes up to depth O(n + m)
	•	Return list stores P strings, each of length O(n + m)

So,

🔷 Space = O(P × (n + m)) (for result storage) + O(n + m) (for recursion)

     */

    private static ArrayList<String> getPath(int sr, int sc, int dr, int dc) {

        if (sr == dr && sc == dc) {
            ArrayList<String> rPaths = new ArrayList<>();
            rPaths.add(""); // reached destination
            return rPaths;
        }

        ArrayList<String> paths = new ArrayList<>();

        // move horizontally
        if (sc < dc) {
            ArrayList<String> hPaths = getPath(sr, sc + 1, dr, dc);
            for (String path : hPaths) {
                paths.add("h" + path);
            }
        }

        // move vertically
        if (sr < dr) {
            ArrayList<String> vPaths = getPath(sr + 1, sc, dr, dc);
            for (String path : vPaths) {
                paths.add("v" + path);
            }
        }

        return paths;
    }
}
