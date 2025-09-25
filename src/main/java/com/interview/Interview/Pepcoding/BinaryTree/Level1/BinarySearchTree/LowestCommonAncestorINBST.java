package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

public class LowestCommonAncestorINBST {

    public static void main(String[] args) {

        /*
              50
             /  \
           30    70
          / \   / \
        20  40 60  80
        */

        BTNode root = new BTNode(50);
        root.left = new BTNode(30);
        root.right = new BTNode(70);
        root.left.left = new BTNode(20);
        root.left.right = new BTNode(40);
        root.right.left = new BTNode(60);
        root.right.right = new BTNode(80);

        LCA(root, 20, 40);
    }

    private static void LCA(BTNode root, int c1, int c2) {
        BTNode node = LCAUtil(root, c1, c2);
        System.out.println(node.data);
    }

    private static BTNode LCAUtil(BTNode root, int n1, int n2) {

        if (root == null) return null;

        if (root.data == n1 || root.data == n2) {
            return root;
        }

        BTNode left = LCAUtil(root.left, n1, n2);

        BTNode right = LCAUtil(root.right, n1, n2);

        if (left == null && right == null) {
            return null;
        }

        if (left != null && right != null) {
            return root;
        }

        if (left!=null) {
            return left;
        } else {
            return right;
        }
    }
}
