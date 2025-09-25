package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

public class LowestCommonAncestorInBT {

    public static void main(String[] args) {
        /*
              3
             /  \
           6     8
          / \     \
         2  11    13
            /\    /
           9  5  7
        */

        BTNode root = new BTNode(3);
        root.left = new BTNode(6);
        root.right = new BTNode(8);
        root.left.left = new BTNode(2);
        root.left.right = new BTNode(11);
        root.left.right.left = new BTNode(9);
        root.left.right.right = new BTNode(5);
        root.right.left = new BTNode(8);
        root.right.right = new BTNode(13);
        root.right.right.left = new BTNode(7);

        BTNode res = lca(root, new BTNode(2), new BTNode(11));
        System.out.println(res.data);
    }

    public static BTNode lca(BTNode root, BTNode n1, BTNode n2) {
        if(root == null) return null;

        if(root.data == n1.data || root.data == n2.data) {
            return root;
        }

        BTNode left = lca(root.left, n1, n2);

        BTNode right = lca(root.right, n1, n2);

        if(left != null && right != null) {
            return root;
        }

        if (left == null && right == null) {  //neither of the two node were found on left n right side of tree
            return null;
        }

        if(left!=null) {
            return left;
        } else {
            return right;
        }
//        return left != null ? left : right;  //just return node if its node null
    }
}
