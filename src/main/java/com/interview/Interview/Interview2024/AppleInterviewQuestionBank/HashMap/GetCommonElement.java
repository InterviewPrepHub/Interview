package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.HashMap;

import java.util.HashSet;
import java.util.Set;

public class GetCommonElement {

    public static void main(String[] args) {
        int[] nums1 = {4,3,2,3,1};
        int[] nums2 = {2,2,5,2,3,6};
        findIntersectionValues(nums1, nums2);
    }

    public static int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        int n = 0;
        if(nums1.length <= nums2.length) {
            n = nums1.length;
        } else {
            n = nums2.length;
        }

        int[] res = new int[2];

        for(int i=0;i<nums1.length;i++) {
            set1.add(nums1[i]);
        }

        for(int i=0;i<nums2.length;i++) {
            set2.add(nums2[i]);
        }

        for(int num : nums1) {
            res[0] += set2.contains(num) ? 1:0;
        }

        for(int num : nums2) {
            res[1] += set1.contains(num) ? 1:0;
        }


        return res;
    }
}
