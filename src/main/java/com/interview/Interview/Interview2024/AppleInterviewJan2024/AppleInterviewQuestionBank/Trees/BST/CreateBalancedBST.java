package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees.BST;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class CreateBalancedBST {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        BTNode root = balancedBST(arr);
        print(root);
    }

    private static void print(BTNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.data);
        print(root.left);
        print(root.right);
    }

    private static BTNode balancedBST(int[] arr) {
        return balancedBST(arr, 0, arr.length-1);
    }

    private static BTNode balancedBST(int[] arr, int start, int end) {

        if(start > end) {
            return null;
        }

        int mid = start + (end - start)/2;
        BTNode root = new BTNode(arr[mid]);

        root.left = balancedBST(arr, start, mid-1);
        root.right = balancedBST(arr, mid+1, end);

        return root;
    }
}
