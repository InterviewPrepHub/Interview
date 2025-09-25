package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;
import com.interview.Interview.Pepcoding.Node.BTNode;
import com.interview.Interview.Pepcoding.Node.LLNode;

public class BSTBasics {

    int count = 0;
    int result = 0;
    LLNode head = null;
    LLNode tail = null;

    public static void main(String[] args) {

        CreateTree c = new CreateTree();
        BTNode root = c.BTree3();

        BSTBasics b = new BSTBasics();

        b.searchInBST(root, new BTNode(62));

        b.insertInBST(root, new BTNode(46));

        System.out.println();
        System.out.println("isValid: "+b.isValidBST(root));

//        int[] arr = {5,6,3,8,9,7};
//        b.createBSTFromList(arr);

        System.out.println();
        System.out.println("flatten list");
        b.flattenBTToList(root);
        b.printLL(b.head);
    }

    private void printLL(LLNode head) {

        while (head.next!=null) {
            System.out.print(head.data+", ");
            head = head.next;
        }
        System.out.print(head.data);

    }

    private void flattenBTToList(BTNode root) {
        if (root == null) return;
        flattenBTToList(root.left);
        add(root.data);
        flattenBTToList(root.right);
    }

    public void add(int data) {
        LLNode node = new LLNode(data);

        if(tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    private void createBSTFromList(int[] arr) {

        createBST(arr, 0, arr.length-1);
    }

    private BTNode createBST(int[] arr,int low, int high) {
        int mid = low + (high-low)/2;
        BTNode node = new BTNode(arr[mid]);

        node.left = createBST(arr, low, mid-1);
        node.right = createBST(arr,mid+1, high);

        return node;
    }

    private void insertInBST(BTNode root, BTNode nodeToAdd) {

        if (root == null) {
            root = nodeToAdd;
        } else {
            BTNode traversingNode = root;
            traverseAndAddNode(traversingNode, nodeToAdd);
        }
    }

    private void traverseAndAddNode(BTNode traversingNode, BTNode nodeToAdd) {

        if(traversingNode.data > nodeToAdd.data) {
            if(traversingNode.left == null) {
                nodeToAdd.parent = traversingNode;
                traversingNode.left = nodeToAdd;
            } else {
                traverseAndAddNode(traversingNode.left, nodeToAdd);
            }
        } else {
            if(traversingNode.right == null) {
                nodeToAdd.parent = traversingNode;
                traversingNode.right = nodeToAdd;
            } else {
                traverseAndAddNode(traversingNode.right, nodeToAdd);
            }
        }
    }

    private BTNode searchInBST(BTNode node, BTNode search) {

        if(node.data == search.data) {
            return search;
        } else {
            BTNode returnNode = null;
            if(search.data < node.data) {
                returnNode = searchInBST(node.left, search);
            } else {
                if (returnNode == null) {
                    returnNode = searchInBST(node.right, search);
                }
            }
            return returnNode;
        }
    }

    public boolean isValidBST(BTNode root) {
        return isValidBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTUtil(BTNode root, int minValue, int maxValue) {

        if (root == null) return true;

        if (root.data > maxValue || root.data < minValue) return false;

        return isValidBSTUtil(root.left, minValue, root.data-1)
                && isValidBSTUtil(root.right, root.data + 1, maxValue);
    }
}
