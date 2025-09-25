package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

public class CreateTree {

    public BTNode BTree() {
        BTNode root = new BTNode(5);
        root.left = new BTNode(12);
        root.right = new BTNode(13);
        root.left.left = new BTNode(7);
        root.left.left.left = new BTNode(17);
        root.left.left.right = new BTNode(23);
        root.right.left = new BTNode(14);
        root.right.right = new BTNode(2);
        root.right.left.left = new BTNode(27);
        root.right.left.right = new BTNode(3);
        root.right.right = new BTNode(2);
        root.right.right.left = new BTNode(8);
        root.right.right.right = new BTNode(11);

        return root;
    }

    public BTNode BTree1() {
        BTNode root = new BTNode(50);
        root.left = new BTNode(25);
        root.right = new BTNode(75);
        root.left.left = new BTNode(12);
        root.right.left = new BTNode(62);
        root.right.right = new BTNode(78);
        root.right.left.left = new BTNode(60);
        root.right.left.right = new BTNode(70);

        return root;
    }

    public BTNode BTree3() {
        BTNode root = new BTNode(50);
        root.left = new BTNode(25);
        root.right = new BTNode(62);

        return root;
    }

}
