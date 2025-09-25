package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.LevelOrderTraversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;
import org.interview.prep.code.leetcode.design.implementation.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintNodesAtKLevel {

    public static void main(String[] args) {
        CreateTree c = new CreateTree();
        BTNode root = c.BTree();

        int k = 2;
        PrintNodesAtKLevel n = new PrintNodesAtKLevel();
        List<List<Integer>> res = n.print(root);
        System.out.println(res.get(k));
    }

    private List<List<Integer>> print(BTNode root) {
        if (root == null) return null;

        Queue<BTNode> q = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();

        q.add(root);

        while (!q.isEmpty()) {

            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                BTNode node = q.poll();
                level.add(node.data);

                if(node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }

            }
            list.addLast(level);
        }
        return list;
    }
}
