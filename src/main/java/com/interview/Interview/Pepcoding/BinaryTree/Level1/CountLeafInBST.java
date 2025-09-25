package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class CountLeafInBST {

    public static void main(String[] args) {
        /*
               1
             /  \
            2    3
            \     \
              5     4
        */

        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.left.right = new BTNode(5);
        root.right = new BTNode(3);
        root.right.right = new BTNode(4);

        System.out.println(countLeaves(root));
    }

    private static int countLeaves(BTNode root) {
        if(root == null) return 0;

        //if leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }

        return countLeaves(root.left)+countLeaves(root.right);
    }
}
