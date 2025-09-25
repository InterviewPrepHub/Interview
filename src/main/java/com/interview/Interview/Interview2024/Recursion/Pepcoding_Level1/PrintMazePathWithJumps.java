package com.interview.Interview.Interview2024.Recursion.Pepcoding_Level1;

import java.util.Scanner;

public class PrintMazePathWithJumps {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        mazePathWithJumps(1, 1, n, m, "");
    }

    private static void mazePathWithJumps(int sr, int sc, int dr, int dc, String psf) {

        /*if (sr < dr || sc < dc) {
            return;
        }*/

        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        }

        for (int ms = 1; ms <= dc-sc; ms++) {
            mazePathWithJumps(sr, ms + sc, dr, dc, psf + "h" + ms);
        }

        for (int ms = 1; ms <= dr-sr; ms++) {
            mazePathWithJumps(ms + sr, sc, dr, dc, psf + "v" + ms);
        }

        for (int ms = 1; ms <= dr - sr && ms <= dc - sc; ms++) {
            mazePathWithJumps(sr, ms + sc, dr, dc, psf + "d" + ms);
        }
    }
}
