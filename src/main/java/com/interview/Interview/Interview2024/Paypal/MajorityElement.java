package com.interview.Interview.Interview2024.Paypal;

public class MajorityElement {

    public static void main(String[] args) {
        int[] arr = {2,6,2,2,6,2,2,8,2,1};
        findMajorityElement(arr);
    }

    public static void findMajorityElement(int[] arr) {
        int candidate = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if(count == 0) {
                candidate = arr[i];
                count++;
            } else {
                if(candidate == arr[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        System.out.println("candidate : "+candidate);

        if(count == 0) {
            System.out.println("no majority element");
        } else {
            count = 0;
            for (int index = 0; index < arr.length; index++) {
                if(candidate == arr[index]) {
                    count++;
                }
            }
        }

        if(count > (arr.length/2)) {
            System.out.println("candidate : "+ candidate + " count : "+count);
        } else {
            System.out.println(-1);
        }

    }
}
