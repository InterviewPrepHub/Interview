package com.interview.Interview.Pepcoding.DynamicProgramming.TusharRoy;

public class BurstBallon {

    public static void main(String[] args) {
        int nums[] = {3,1,5,8};
        //Output: 167

        /*
             0,1,2,3
            {3,1,5,8}

                    0       1       2      3
                0  (3,0)  (30,0)
                1         (15,1)  (135,2)
                2                 (40,2)  (48,3)
                3                         (40,3)

            len = 1 =>
            i = 0 => 0+1*3*1+0 = 3
            i = 1 => 0+3*1*5+0 = 15
            i = 2 => 0+1*5*8+0 = 40
            i = 3 => 0+5*8*1+0 = 40

            len = 2 =>
            i=0; j=1 =>[3,1]
            if 3 is the last ballon to burst => 0[there is nothing on left]+if ballon at j is burst(1,1) [15]+1*3*5
            0+15+1*3*5 = 30
            if 1 is the last ballon to burst =>
            3+0+1*1*5 = 8

            30>8

            i = 12 =>[1,5]
            k = 1 => 0+40+3*1*5 = 55
            k = 2 => 15+3*5*8+0 = 135

            i = 23 =>[5,8]
            k = 2 => 0+40+1*5*1 = 45
            k = 3 => 40+0+1*8*1 = 48

            len = 3 =>
            i=0; j=2 => [3,5]
            k = 0 => 0+135+1*3*8 = 159
            k = 1 => 3+40+1*1*8 = 51
            k = 2 => 30+0+1*5*8 = 70

            len = 4 =>
            i=0; j=3 => [3,8]
            k = 0 => 0+159+1*3*1 = 162
            k = 1 => 3+48+1*1*1 = 51
            k = 2 => 30+40+1*5*1 = 75
            k = 3 => 159+1*8*1+0 = 167

         */
    }

    public int maxCoinsBottomUpDp(int[] nums) {

        int T[][] = new int[nums.length][nums.length];

        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i <= nums.length - len; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    //leftValue/rightValue is initially 1. If there is element on
                    // left/right of k then left/right value will take that value.
                    int leftValue = 1;
                    int rightValue = 1;
                    if (i != 0) {
                        leftValue = nums[i-1];
                    }
                    if (j != nums.length -1) {
                        rightValue = nums[j+1];
                    }

                    //before is initially 0. If k is i then before will
                    //stay 0 otherwise it gets value T[i][k-1]
                    //after is similarly 0 initially. if k is j then after will
                    //stay 0 other will get value T[k+1][j]
                    int before = 0;
                    int after = 0;
                    if (i != k) {
                        before = T[i][k-1];
                    }
                    if (j != k) {
                        after = T[k+1][j];
                    }
                    T[i][j] = Math.max(leftValue * nums[k] * rightValue + before + after,
                            T[i][j]);
                }
            }
        }
        return T[0][nums.length - 1];
    }
}
