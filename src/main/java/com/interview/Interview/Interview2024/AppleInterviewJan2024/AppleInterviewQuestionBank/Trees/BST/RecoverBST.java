package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees.BST;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class RecoverBST {

    static BTNode firstStartPoint = null;
    static BTNode secondStartPoint = null;
    static BTNode prevNode = null;

    public static void recoverTree(BTNode root) {
        findSegment(root);
        print(root);
        swap();
        System.out.println();
        print(root);
    }

    private static void print(BTNode root) {
        if (root == null) {
            return;
        }

        print(root.left);
        System.out.print(" "+root.data);
        print(root.right);
    }

    private static void swap() {
        int temp = firstStartPoint.data;
        firstStartPoint.data = secondStartPoint.data;
        secondStartPoint.data = temp;
    }

    private static void findSegment(BTNode root) {
        if (root == null) return;
        findSegment(root.left);
        if (prevNode != null) {
            if(prevNode.data > root.data) {
                if (firstStartPoint == null) {
                    firstStartPoint = prevNode;
                }
                secondStartPoint = root;
            }
        }
        prevNode = root;
        findSegment(root.right);
    }

    public static void main(String[] args) {

        BTNode root = new BTNode(10);
        root.left = new BTNode(15);
        root.right = new BTNode(5);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(7);
        root.right.left = new BTNode(14);
        root.right.right = new BTNode(17);

        recoverTree(root);
    }
}
