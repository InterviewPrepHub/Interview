package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

import java.util.ArrayList;
import java.util.List;

public class BSTToGreaterSumTree {

/*

          4
        /    \
      1       6
     /  \    /  \
    0    2  5    7
          \       \
           3       8

*/

    public static void main(String[] args) {
        BTNode root = new BTNode(4);
        root.left = new BTNode(1);
        root.right = new BTNode(6);

        root.left.left = new BTNode(0);
        root.left.right = new BTNode(2);
        root.left.right.right = new BTNode(3);

        root.right.left = new BTNode(5);
        root.right.right = new BTNode(7);
        root.right.right.right = new BTNode(8);

        BSTToGreaterSumTree r = new BSTToGreaterSumTree();
        r.greaterSumTree(root);
    }

    List<Integer> list = new ArrayList<>();
    private void greaterSumTree(BTNode root) {

        inOrder(root,0);
        System.out.println(list);
    }

    private int inOrder(BTNode root, int sum) {

        if (root == null) return sum;

        sum = inOrder(root.right, sum);
        sum+= root.data;
        root.data = sum;
        list.add(root.data);
        return inOrder(root.left, sum);
    }

}
