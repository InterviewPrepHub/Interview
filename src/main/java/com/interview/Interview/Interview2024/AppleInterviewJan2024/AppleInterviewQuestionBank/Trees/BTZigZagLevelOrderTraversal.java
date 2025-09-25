package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees;

import java.util.Stack;

public class BTZigZagLevelOrderTraversal {

//            3
//        9       20
//            15      7

    public static void main(String[] args) {

        BTNode node = new BTNode(3);
        node.left = new BTNode(9);
        node.right = new BTNode(20);
        node.right.left = new BTNode(15);
        node.right.right = new BTNode(7);

        zigZagLevelOrderTraversal(node);
    }

    private static void zigZagLevelOrderTraversal(BTNode node) {

        Stack<BTNode> even = new Stack<>();
        Stack<BTNode> odd = new Stack<>();

        even.add(node);
        boolean evenLevel = true;

        while ((evenLevel && !even.isEmpty()) || (!odd.isEmpty())) {

            if(evenLevel) { //if the current level visited is even
                while (!even.isEmpty()) {
                    BTNode evenNode = even.pop();
                    System.out.print(" "+evenNode.data);

                    if (evenNode.left != null) {
                        odd.add(evenNode.left);
                    }
                    if (evenNode.right != null) {
                        odd.add(evenNode.right);
                    }
                }

            } else {
                while (!odd.isEmpty()) {
                    BTNode oddNode = odd.pop();
                    System.out.print(" "+oddNode.data);

                    if (oddNode.left != null){
                        even.add(oddNode.left);
                    }

                    if (oddNode.right != null) {
                        even.add(oddNode.right);
                    }

                }

            }

            evenLevel = !evenLevel;
            System.out.println();
        }

    }
}
