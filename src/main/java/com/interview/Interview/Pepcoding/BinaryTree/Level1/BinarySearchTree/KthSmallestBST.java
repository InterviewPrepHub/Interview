package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class KthSmallestBST {

    int count = 0;
    int result = -1;

    int count1 = 0;
    int result1 = -1;

    public static void main(String[] args) {
        /*
              50
             /  \
           30    70
          / \   / \
        20  40 60  80
        */
        BTNode root = new BTNode(50);
        root.left = new BTNode(30);
        root.right = new BTNode(70);
        root.left.left = new BTNode(20);
        root.left.right = new BTNode(40);
        root.right.left = new BTNode(60);
        root.right.right = new BTNode(80);

        KthSmallestBST finder = new KthSmallestBST();
        int k = 3;
        System.out.println("Kth smallest: " + finder.findKthSmallest(root, k));  // Output: 40

        System.out.println("Kth largest: "+ finder.findKthLargest(root, k)); // Output: 60
    }

    private int findKthSmallest(BTNode node, int k) {
        inOrder(node,k);
        return result;
    }

    private void inOrder(BTNode node, int k) {

        if (node == null || count >= k) return;

        inOrder(node.left, k); //left

        count++;
        if (count == k) {
            result = node.data;
            return;
        }

        inOrder(node.right, k); //right
    }

    private int findKthLargest(BTNode node, int k) {
        reverseInOrder(node, k);
        return result1;
    }

    private void reverseInOrder(BTNode node, int k) {

        if (node == null || count1 >= k) return;

        reverseInOrder(node.right, k);

        count1++;
        if (count1 == k) {
            result1 = node.data;
            return;
        }

        reverseInOrder(node.left, k);
    }
}
