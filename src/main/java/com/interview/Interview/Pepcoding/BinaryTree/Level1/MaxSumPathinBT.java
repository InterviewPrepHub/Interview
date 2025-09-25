package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class MaxSumPathinBT {

    public static void main(String[] args) {
        
        /*
              -10
             /  \
           9    20
                / \
               15  7
        */

        /*BTNode root = new BTNode(-10);
        root.left = new BTNode(9);
        root.right = new BTNode(20);
        root.right.left = new BTNode(15);
        root.right.right = new BTNode(7);*/

        BTNode root = new BTNode(-2);
        root.left = new BTNode(-3);
        root.right = new BTNode(-1);
        System.out.println(maxSumPath(root));
    }


    static int maxSum = Integer.MIN_VALUE;

    private static int maxSumPath(BTNode root) {
        dfs(root);
        return maxSum;
    }

    private static int dfs(BTNode root) {

        if (root == null) return 0;

        int left = Math.max(0, dfs(root.left)); //ignore negative

        int right = Math.max(0, dfs(root.right));

        int currSum = root.data + left + right;

        maxSum = Math.max(maxSum, currSum);

        // contribution to parent: pick the better side
        return root.data + Math.max(left, right);
    }
}


















