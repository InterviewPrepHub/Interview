package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees;

import java.util.LinkedList;
import java.util.Queue;

public class BTLevelOrderTraversal {

    public static void main(String[] args) {

        BTNode node = new BTNode(3);
        node.left = new BTNode(9);
        node.right = new BTNode(20);
        node.right.left = new BTNode(15);
        node.right.right = new BTNode(7);

        levelOrderTraversal(node);
    }

    private static void levelOrderTraversal(BTNode node) {
        Queue<BTNode> q = new LinkedList<>();

        q.add(node);

        while (!q.isEmpty()) {
            BTNode btNode = q.poll();
            System.out.println(btNode.data);

            if (btNode.left != null) {
                q.add(btNode.left);
            }

            if(btNode.right != null) {
                q.add(btNode.right);
            }
        }
    }
}
