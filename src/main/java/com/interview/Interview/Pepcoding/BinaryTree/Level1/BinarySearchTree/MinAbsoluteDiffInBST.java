package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

public class MinAbsoluteDiffInBST {

    public static void main(String[] args) {
        /*

          50
        /    \
      25      75
     /  \    /  \
   12   30  60   80
         \
          35

         */

        BTNode root = new BTNode(50);
        root.left = new BTNode(25);
        root.right = new BTNode(75);

        root.left.left = new BTNode(12);
        root.left.right = new BTNode(30);
        root.left.right.right = new BTNode(35);

        root.right.left = new BTNode(60);
        root.right.right = new BTNode(80);

        MinAbsoluteDiffInBST r = new MinAbsoluteDiffInBST();
        r.minDiff(root);

    }

    Integer prev = null;
    Integer min = Integer.MAX_VALUE;

    public void minDiff(BTNode root) {

        if(root == null) {
            return;
        }

        minDiff(root.left);

        if (prev!=null) {
            min = Math.min(min, root.data-prev);
        }
        prev = root.data;
        minDiff(root.right);
    }


}
