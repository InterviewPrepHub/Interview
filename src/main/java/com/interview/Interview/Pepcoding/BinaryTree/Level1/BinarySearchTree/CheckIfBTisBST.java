package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;
import com.interview.Interview.Pepcoding.Node.BTNode;

public class CheckIfBTisBST {

    public static void main(String[] args) {
        CreateTree c = new CreateTree();
        BTNode root = c.BTree1();

        CheckIfBTisBST bst = new CheckIfBTisBST();
        System.out.println(bst.isBST(root));
    }

    private boolean isBST(BTNode root) {

        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTUtil(BTNode root, int minValue, int maxValue) {

        if (root == null) return true;

        if (root.data<minValue || root.data>maxValue) return false;

        return isBSTUtil(root.left, minValue, root.data) &&
                isBSTUtil(root.right, root.data, maxValue);
    }
}
