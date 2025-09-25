package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class BalancedBSTFromSortedArray {

    public static void main(String[] args) {

        int[] a = {1,2,3,4,5,6,7};
        int start = 0, end = a.length-1;

        balancedBST(a, start, end);
    }

    private static BTNode balancedBST(int[] a, int start, int end) {

        if (start>end) return null;
        int mid = start + (end-start)/2;

        BTNode root = new BTNode(a[mid]);

        root.left = balancedBST(a, start, mid-1);
        root.right = balancedBST(a, mid+1, end);

        return root;
    }

}
