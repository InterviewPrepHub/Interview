package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees.BST;

import com.series.InterviewPrep2024.AppleInterviewQuestionBank.Trees.BTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConvertBTToBST {

    public static void main(String args[]) {
 
        /*
        Constructing tree given in the above figure

             10
            / \
           30 15
          /\    \
        20  8    5

        */

        Node root = new Node(10);
        root.left = new Node(30);
        root.right = new Node(15);
        root.left.left = new Node(20);
        root.left.right = new Node(8);
        root.right.right = new Node(5);

        // convert Binary Tree to BST
        binaryTreeToBST(root);

        inorderTraversal(root);

    }

    static List<Node> nodeList = new ArrayList<>();
    public static void binaryTreeToBST(Node root) {
        // Perform in-order traversal and store nodes in a list
        inorderTraversalToList(root);

        // Sort the list of nodes based on their values
        Collections.sort(nodeList, (a, b) -> Integer.compare(a.data, b.data));

        // Reconstruct the BST using the sorted list
        constructBST(root);
    }

    public static void inorderTraversalToList(Node root) {
        if (root != null) {
            inorderTraversalToList(root.left);
            nodeList.add(root);
            inorderTraversalToList(root.right);
        }
    }

    public static void constructBST(Node root) {
        if (root != null) {
            constructBST(root.left);

            // Get the next node from the sorted list
            Node nextNode = nodeList.remove(0);

            // Update the data of the current node
            root.data = nextNode.data;

            constructBST(root.right);
        }
    }

    public static void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    private static int getSizeOfBT(Node root) {
        int count  = 0;
        if (root == null) {
            return 0;
        }
        if(root.left != null) {
            int rNodes = getSizeOfBT(root.left);
            count+=rNodes;
        }
        if(root.right != null) {
            int lNodes = getSizeOfBT(root.right);
            count+=lNodes;
        }
        return count+1;
    }

}

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}