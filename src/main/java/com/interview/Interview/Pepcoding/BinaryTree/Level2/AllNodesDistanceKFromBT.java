package com.interview.Interview.Pepcoding.BinaryTree.Level2;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;
import org.interview.prep.code.leetcode.design.implementation.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;

import java.util.*;

/*
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/

Given the root of a binary tree, the value of a target node target, and an integer k, return
an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

 */
public class AllNodesDistanceKFromBT {

    // Quick demo
    public static void main(String[] args) {
        /*
                  3
                 / \
                5   1
               / \ / \
              6  2 0  8
                / \
               7   4
        target=5, k=2 -> [7,4,1]
        */
        BTNode root = new BTNode(3);
        root.left = new BTNode(5);
        root.right = new BTNode(1);
        root.left.left = new BTNode(6);
        root.left.right = new BTNode(2);
        root.left.right.left = new BTNode(7);
        root.left.right.right = new BTNode(4);
        root.right.left = new BTNode(0);
        root.right.right = new BTNode(8);

        System.out.println(distanceK(root, 5, 2)); // e.g. [7, 4, 1]
    }

    // Main API: returns values of nodes at distance k from target value
    public static List<Integer> distanceK(BTNode root, int target, int k) {
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null) return ans;

        // Ensure parent pointers are set (in case they aren't)
        setParents(root, null);

        // Locate the target node
        BTNode start = find(root, target);
        if (start == null) return ans; // target not present

        // BFS from target outward
        Queue<BTNode> q = new LinkedList<BTNode>();
        HashSet<BTNode> visited = new HashSet<BTNode>();

        q.offer(start);
        visited.add(start);

        int level = 0;
        while (!q.isEmpty()) {
            if (level == k) break; // queue now holds exactly the kth layer
            int size = q.size();

            for (int i = 0; i < size; i++) {
                BTNode cur = q.poll();

                // Explore neighbors: left, right, parent
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    q.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    q.offer(cur.right);
                }
                if (cur.parent != null && !visited.contains(cur.parent)) {
                    visited.add(cur.parent);
                    q.offer(cur.parent);
                }
            }
            level++;
        }

        // Collect all nodes exactly at distance k
        while (!q.isEmpty()) {
            ans.add(q.poll().data);
        }
        return ans;
    }

    // DFS to set parent pointers
    private static void setParents(BTNode node, BTNode parent) {
        if (node == null) return;
        node.parent = parent;
        setParents(node.left, node);
        setParents(node.right, node);
    }

    // DFS to find node by value (returns first match)
    private static BTNode find(BTNode node, int target) {
        if (node == null) return null;
        if (node.data == target) return node;
        BTNode left = find(node.left, target);
        if (left != null) return left;
        return find(node.right, target);
    }

}
