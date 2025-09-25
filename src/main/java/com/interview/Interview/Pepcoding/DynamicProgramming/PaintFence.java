package com.interview.Interview.Pepcoding.DynamicProgramming;

/*
you have n fences and 3 colours.
how many ways you can colour the fences such that not more than 2 consecutive fences can have same colour
 */
public class PaintFence {
    public static void main(String[] args) {

        int n = 5;
        int k = 3;

        //there is no case for i = 1
        //for i = 2,
        //we can have two fences of same colour [rr, gg, bb] -> 3 or
        //diff colour [rg, rb, gb, gr, br, bg] -> 6
        //total = 3+6

        /*
        in the 3rd fence, in same take all the diff result in 2nd fence and add same colour as last colour
        also in the diff

                    fence ->    1       2           3       4       5
                    same        -   rr,gg,bb    rgg,rbb,
                                                grr,gbb
                                                brr,bgg
                    diff        -   rg,rb,      total*[k-1]
                                    gr,gb,      9*2 = 18
                                    br,bg

                    total       -   rr,gg,bb,   same*diff
                                    rg,rb,
                                    gr,gb,
                                    br,bg

         */
        long same = k*1;
        long diff = k*(k-1);
        long total = same + diff;

        for (int i=3;i<=n;i++) {
            same = diff * 1;    //6
            diff = total * (k-1);
            total = same + diff;
        }

        System.out.println(total);

    }
}
