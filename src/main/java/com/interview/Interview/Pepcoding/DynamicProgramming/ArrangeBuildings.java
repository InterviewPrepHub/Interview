package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
you are given n , which is length of road. road has n plots on each side
road is planned in such a way that there should not be consecutive buildings on either side of the road.
you are required to find and print the number of ways which buildings an be built on both side of road
 */
public class ArrangeBuildings {

    public static void main(String[] args) {

        int n = 5;
        int[] dpB = new int[n+1];
        int[] dpS = new int[n+1];

        dpB[1] = 1;
        dpS[1] = 1;

        for (int i = 2; i <= n; i++) {
            dpS[i] = dpS[i-1] + dpB[i-1];
            dpB[i] = dpS[i-1];
        }

        System.out.println(dpB[n]+dpS[n]);

        //method two
        int ocB = 1;
        int ocS = 1;

        for (int i=2;i<=n;i++) {
            int ncS = ocS + ocB;
            int ncB = ocS;

            ocS = ncS;
            ocB = ncB;
        }
        System.out.println(ocB+ocS);

    }
}
