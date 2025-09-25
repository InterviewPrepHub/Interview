package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VerticalSumOfBT {

    /*
        Follow the steps below to solve the problem:

            Perform an in-order traversal starting from the root.
            For each node, decrement HD by 1 for the left subtree and increment HD by 1 for the right subtree.
            For each HD, add the node's value to a map that tracks the sum of nodes at each horizontal distance.
            Once traversal is complete, extract and return the sums from the map in sorted order of HDs.
            populates a hashMap that contains the vertical sum
     */
    static void verticalSumUtil(BTNode node, int hd,
                                Map<Integer, Integer> mp) {

        // Base case
        if (node == null)
            return;

        // Recur for left subtree
        verticalSumUtil(node.left, hd - 1, mp);

        // Add val of current BTNode to map entry of
        // corresponding hd
        mp.put(hd, mp.getOrDefault(hd, 0) + node.data);

        // Recur for right subtree
        verticalSumUtil(node.right, hd + 1, mp);
    }

    // Function to find vertical sum
    static List<Integer> verticalSum(BTNode root) {

        // A map to store sum of BTNodes for each
        // horizontal distance
        Map<Integer, Integer> mp = new TreeMap<>();

        // Populate the map
        verticalSumUtil(root, 0, mp);

        // Collect the results
        List<Integer> result = new ArrayList<>();
        for (int sum : mp.values()) {
            result.add(sum);
        }
        return result;
    }

    public static void main(String[] args) {

        // Create binary tree as shown in above figure
        //        1
        //      /  \  
        //     2    3
        //    / \  / \
        //   4   5 6  7
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);

        List<Integer> res = verticalSum(root);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
