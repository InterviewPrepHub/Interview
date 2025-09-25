package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

public class CheckIfTwoBTAreIdentical {

    public static void main(String[] args) {

        BTNode n1 = new BTNode(1);
        n1.left = new BTNode(2);
        n1.right = new BTNode(3);

        BTNode n2 = new BTNode(1);
        n2.left = new BTNode(2);
        n2.right = new BTNode(3);

        checkIdenticalBT(n1, n2);

    }

    private static boolean checkIdenticalBT(BTNode n1, BTNode n2) {

        if (n1 == null || n2 == null) {
            return false;
        }

        if (n1 == null && n2 == null) {
            return true;
        }

        boolean left = checkIdenticalBT(n1.left, n2.left);
        boolean right = checkIdenticalBT(n1.right, n2.right);

        return (n1.data == n2.data) && left && right;
    }
}
