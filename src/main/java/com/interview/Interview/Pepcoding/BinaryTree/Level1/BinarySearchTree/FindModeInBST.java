package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

import java.util.*;

/*
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently
occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 */
public class FindModeInBST {

    public static void main(String[] args) {

        /*

          7
        /    \
      4       8
     /       /  \
   4        8   10


         */

        BTNode root = new BTNode(7);
        root.left = new BTNode(4);
        root.right = new BTNode(8);
        root.left.left = new BTNode(4);
        root.right.left = new BTNode(8);
        root.right.right = new BTNode(10);

        FindModeInBST r = new FindModeInBST();
        System.out.println(r.modeOfBST(root));

    }

    static Map<Integer, Integer> map = new HashMap<>();

    public static Map<Integer, Integer> modeOfBST(BTNode root) {
        inorderCount(root);
        return map;
    }

    private static void inorderCount(BTNode node) {
        if (node == null) {
            return;
        }
        inorderCount(node.left);
        if(!map.containsKey(node.data)) {
            map.put(node.data, 1);
        } else {
            map.put(node.data, map.getOrDefault(node.data, 0)+1);
        }
        inorderCount(node.right);
    }

    /*public static void inorderCount(BTNode root) {
        if (root == null) {
            return;
        }

        inorderCount(root.left);

        if(!map.containsKey(root.data)) {
            map.put(root.data, 1);
        } else {
            map.put(root.data, map.getOrDefault(root.data, 0)+1);
        }

        inorderCount(root.left);
    }*/
}
