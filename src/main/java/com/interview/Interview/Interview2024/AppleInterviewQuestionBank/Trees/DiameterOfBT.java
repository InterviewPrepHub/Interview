package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees;

public class DiameterOfBT {

//  Longest Path between two end nodes

//            3
//        9       20
//            15      7

    public static void main(String[] args) {

        BTNode node = new BTNode(3);
        node.left = new BTNode(9);
        node.right = new BTNode(20);
        node.right.left = new BTNode(15);
        node.right.right = new BTNode(7);

        System.out.println(diameter(node));
    }

    private static int diameter(BTNode node) {

        if (node == null) {
            return 0;
        }

        int leftHt = height(node.left);
        int rightHt = height(node.right);

        int leftD = diameter(node.left);
        int rightD = diameter(node.right);

        return Math.max((leftHt + rightHt + 1), Math.max(leftD, rightD));
    }

    private static int height(BTNode node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(height(node.left), height(node.right)));
    }
}
