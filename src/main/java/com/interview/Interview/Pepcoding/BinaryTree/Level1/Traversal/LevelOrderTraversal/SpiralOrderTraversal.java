package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.LevelOrderTraversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;
import org.interview.prep.code.leetcode.design.implementation.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SpiralOrderTraversal {

    public static void main(String[] args) {
        CreateTree s = new CreateTree();
        BTNode root= s.BTree();

        List<List<Integer>> res = spiralOrder(root);
        System.out.println(res);
    }

    private static List<List<Integer>> spiralOrder(BTNode root) {

        if(root == null) {
            return new ArrayList<>();
        }

        Queue<BTNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        boolean reverse = true;

        q.add(root);

        while (!q.isEmpty()) {

            int len = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < len; i++) {

                BTNode node = q.poll();

                if (reverse) {
                    level.addLast(node.data);
                } else {
                    level.addFirst(node.data);
                }

                if(node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }

            }
            res.add(level);
            reverse = !reverse;
        }
        return res;
    }
}
