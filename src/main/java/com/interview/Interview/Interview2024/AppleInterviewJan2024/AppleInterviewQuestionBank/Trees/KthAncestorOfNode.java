package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

            10
1       2           7
    3       4           8
        6
*/


public class KthAncestorOfNode {

    static int[] ancestor;

    public static void main(String[] args) {

        BTNode root = new BTNode(10);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);

        int targetNode = 5; // Target node for which ancestor needs to be found

        populateAncestor(root, targetNode);

        // K-th ancestor (in this case, it is the height)
        int k = maxHeightFromRootToTarget(root, new BTNode(targetNode));
        
        int kthAncestor = findKthAncestor(targetNode, k);

        if (kthAncestor != -1) {
            System.out.println("The " + k + "-th ancestor of node " + targetNode + " is: " + kthAncestor);
        } else {
            System.out.println("No such ancestor exists.");
        }

    }

    // Function to perform level order traversal and populate ancestor array
    static void populateAncestor(BTNode root, int n) {
        ancestor = new int[n + 1];
        Arrays.fill(ancestor, -1);

        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BTNode current = queue.poll();

            if (current.left != null) {
                ancestor[current.left.data] = current.data;
                queue.add(current.left);
            }

            if (current.right != null) {
                ancestor[current.right.data] = current.data;
                queue.add(current.right);
            }
        }
    }

    private static int findKthAncestor(int node, int k) { //5, 2
        while (node != -1 && k > 0) {
            node = ancestor[node];
            k--;
        }

        return node;
    }

    private static int maxHeightFromRootToTarget(BTNode root, BTNode targetNode) {

        int maxHeight = 0;

        if (root == null) {
            return -1; // Target node not found
        }

        if(root.data == targetNode.data) {
            return 0;
        }

        int leftHeight = maxHeightFromRootToTarget(root.left, targetNode);
        int rightHeight = maxHeightFromRootToTarget(root.right, targetNode);

        // If the target node is found in the left subtree
        if (leftHeight >= 0) {
            maxHeight = Math.max(maxHeight, leftHeight + 1);
            return leftHeight + 1;
        }

        // If the target node is found in the right subtree
        if (rightHeight >= 0) {
            maxHeight = Math.max(maxHeight, rightHeight + 1);
            return rightHeight + 1;
        }

        return -1;
    }
}
