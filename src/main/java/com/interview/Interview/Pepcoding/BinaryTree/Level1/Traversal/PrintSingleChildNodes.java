package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class PrintSingleChildNodes {

    public static void main(String[] args) {

        CreateTree c = new CreateTree();
        BTNode root = c.BTree1();

        singleChildNodes(root, null);

    }

    private static void singleChildNodes(BTNode root, BTNode parent) {

        if (root == null) return;

        if (parent != null && parent.left == root && parent.right == null) {
            System.out.println(root.data);
        } else if (parent != null && parent.right == root && parent.left == null) {
            System.out.println(root.data);
        } else if(parent != null && parent.left == null && parent.right == root) {
            System.out.println(root.data);
        }

        singleChildNodes(root.left, root);
        singleChildNodes(root.right, root);
    }
}
