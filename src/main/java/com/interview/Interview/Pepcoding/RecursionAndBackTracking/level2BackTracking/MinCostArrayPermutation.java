package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level2BackTracking;

public class MinCostArrayPermutation {

    public static void main(String[] args) {

    }
}


/*

score = |a[0] - nums[a[1]]| + |a[1] - nums[a[2]]| + ... + |a[n-1] - nums[a[0]]|


                {1,0,2}             {0} Permutation
                              /               \
             visited={0,1}   /                  \       visited={0,2}
             score=0        /                     \     score=2
                           /                        \
                        {0,1}                       {0,2}
                         |
        visited={0,1,2}  |
        size = 1         |
                         |
                      {0,1,2}
                      score+= 2- nums[Perm[0]]|
 */
