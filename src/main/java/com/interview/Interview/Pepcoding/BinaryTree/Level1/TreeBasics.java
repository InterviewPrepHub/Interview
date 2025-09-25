package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

public class TreeBasics {

    public static void main(String[] args) {
        TreeBasics l = new TreeBasics();

        BTNode root = l.createTree();

        System.out.println("size of BT : "+l.size(root));
        System.out.println("sum of BT : "+l.sum(root));
        System.out.println("height of BT : "+l.height(root));
        System.out.println("max of BT : "+l.max(root));
        System.out.println("diameter of BT : "+l.diameter(root));
        /*
        The longest path is: 5 → 8 → 12 → 18
        (Number of edges = 3)
         */
    }

    private int max(BTNode root) {

        if(root == null) return Integer.MIN_VALUE;

        int left = max(root.left);
        int right = max(root.right);

        int total_max = Math.max(root.data, Math.max(left, right));

        return total_max;
    }

    private int size(BTNode root) {

        if(root == null) return 0;

        int left = size(root.left);
        int right = size(root.right);

        int size = left + right + 1;

        return size;

    }

    private int sum(BTNode root) {

        if (root == null) return 0;

        int left = sum(root.left);
        int right = sum(root.right);

        int size = left + right + root.data;

        return size;
    }

    private static int height(BTNode root) {
        if(root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }

    int diameter = 0;
    private int diameter(BTNode root) {
        heightOfTree(root);
        return diameter;
    }

    private int heightOfTree(BTNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        diameter = Math.max(diameter,left+right);

        return Math.max(left, right) + 1;
    }

    private static BTNode createTree() {
        // Representation of the input tree:
        //     12
        //    /  \
        //   8   18
        //  / \
        // 5   11
        BTNode root = new BTNode(12);
        root.left = new BTNode(8);
        root.right = new BTNode(18);
        root.left.left = new BTNode(5);
        root.left.right = new BTNode(11);

        return root;
    }
}
