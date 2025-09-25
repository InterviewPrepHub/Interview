package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal;

import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

remove all leaf nodes of tree
 */
public class RemoveLeaves {

    public static void main(String[] args) {

        CreateTree c = new CreateTree();
        BTNode root = c.BTree1();

        root = removeLeafNodes(root);

        System.out.println("After removing leaves:");

        List<List<Integer>> list = printLevelOrder(root);

        System.out.println(list);

    }

    //post order traversal;
    public static BTNode removeLeafNodes(BTNode node) {

        if (node == null) return null;

        // If it's a leaf, return null (removes the leaf)
        if (node.left == null && node.right == null) {
            return null;
        }

        // Post-order: recurse first, then process
        node.left = removeLeafNodes(node.left);
        node.right = removeLeafNodes(node.right);

        return node;
    }

    public static List<List<Integer>> printLevelOrder(BTNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return null;
        }

        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                BTNode node = queue.poll();
                System.out.print(node.data + " ");
                level.add(node.data);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            list.addLast(level);

            System.out.println(); // move to next line after each level
        }
        return list;
    }
}
