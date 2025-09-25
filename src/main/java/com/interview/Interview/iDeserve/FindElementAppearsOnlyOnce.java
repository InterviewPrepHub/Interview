package com.interview.Interview.iDeserve;

public class FindElementAppearsOnlyOnce {

    public static void main(String[] args) {

        int[] arr = {5,5,4,8,4,5,8,9,4,8};
        int n = arr.length;

        //for counting set bits for all given number
        int[] countSetBit = new int[32];

        for (int i = 0; i < n; i++) {
            //find set bits in current element
            for (int k = 0; k < 32; k++) {
                int kthSetBit = 1 << k;
                /*
                    k = 2
                    1 << k -> 0100
                 */

                //if the kth bit is set, then increment the count of countSetBit[k]
                if((arr[i] & kthSetBit) == kthSetBit) {
                    countSetBit[k]++;
                }
            }

        }

        // required num
        int occuredOnce = 0;

        int N = 3;
        //iterate over countSetBit
        for (int i = 0; i < 32; i++) {
            countSetBit[i] = countSetBit[i] % N;
            if (countSetBit[i] == 1) {
                occuredOnce = occuredOnce | (1 << i);
            }
        }

        System.out.println(occuredOnce);

    }
}
