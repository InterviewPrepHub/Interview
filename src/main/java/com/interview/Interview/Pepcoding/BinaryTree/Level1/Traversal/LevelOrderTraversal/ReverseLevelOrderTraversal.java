package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.LevelOrderTraversal;

import com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;
import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReverseLevelOrderTraversal {

    public static void main(String[] args) {
        CreateTree s = new CreateTree();
        BTNode root= s.BTree();

        List<List<Integer>> res = reverseOrder(root);
        System.out.println(res);
    }

    private static List<List<Integer>> reverseOrder(BTNode root) {

        if(root == null) return null;

        Queue<BTNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

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

                if(node.right != null) {
                    q.add(node.right);
                }

            }
            res.addFirst(level);
        }
        return res;
    }
}
