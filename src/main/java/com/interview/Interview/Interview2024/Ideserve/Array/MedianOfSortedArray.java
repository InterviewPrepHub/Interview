package com.interview.Interview.Interview2024.Ideserve.Array;

/*
Given two sorted arrays a and b each of size n, find the median of the array obtained by merging these two arrays.

Example –

a = 1, 3, 5, 11, 17
b = 9, 10, 11, 13, 14
Sorted(a+b) = 1, 3, 5, 9, 10, 11, 11, 13, 14, 17
Median = (10 + 11)/2
             = 10.5
 */
public class MedianOfSortedArray {

    /*
    My explanation:
    a : a1, a2
    b : b1, b2

    if n == 2, n = arr.length
    sorted(a+b) = min(a1,b1),?,?,max(a2,b2)
    median -> m1_index = max(a1,b1) m2_index = min(a2,b2)
    int m1 = a[m1_index], m2 = b[m2_index]

    if(m1==m2) return m1 or m2;

    a : a1, a2, m1, a4, a5
    b : b1, b2, m2, b4, b5

    if n > 2, n = arr.length m1 is median of arr a, m2 is median of arr b
    if(m1 < m2)

    sorted(a+b) = {a1,a2,elements of b},m1{,,,}m2,{elements of a,b4,b5}
                                            |
                                            startIndex = m1
                                            endIndex = m2
    find median from elements m1,a4,a5,b1,b2,m2

    if(m1 > m2)

    sorted(a+b) = {b1,b2,elements of a,}m2,{},m1,{a4,a5,elements of b}
                                            |
                                            startIndex = m2
                                            endIndex = m1
    find median from elements m2,b4,b5,a1,a2,m2

     */

    public double findMedianSortedArrays(int[] a, int[] b, int startIndexA, int endIndexA, int startIndexB, int endIndexB) {

        if((endIndexA-startIndexA == 1) && (endIndexB - startIndexB == 1)){
            return 1.0*(Math.max(a[startIndexA], b[startIndexB]) + Math.min(a[endIndexA], b[endIndexB]))/2;
        }

        int m1_index = (startIndexA + endIndexA)/2;
        int m2_index = (startIndexB + endIndexB)/2;

        int m1 = a[m1_index];
        int m2 = b[m2_index];

        if(m1 == m2) {
            return m1;
        }

        if(m1 < m2) {
            startIndexA = m1_index;
            endIndexB = m2_index;
        }

        if (m1 > m2) {
            startIndexB = m2_index;
            endIndexA = m1_index;
        }

        return findMedianSortedArrays(a,b, startIndexA, endIndexA, startIndexB, endIndexB);
    }



    public static void main(String[] args) {
        MedianOfSortedArray solution = new MedianOfSortedArray();

        System.out.println("Case 1: When arrays have odd number of elements in them.");
//        int [] a = {1,2,3,4,5};
//        int [] b = {6,7,8,9,10};

        int[] a = {1,3,6};
        int[] b = {2,8,12};
        System.out.println("Median: " + solution.findMedianSortedArrays(a, b, 0, a.length-1, 0, b.length-1));

    }
}
