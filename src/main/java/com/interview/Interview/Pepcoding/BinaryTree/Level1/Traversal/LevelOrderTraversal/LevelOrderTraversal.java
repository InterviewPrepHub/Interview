package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.LevelOrderTraversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;
import org.interview.prep.code.leetcode.design.implementation.Pepcoding.BinaryTree.Level1.Traversal.CreateTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//breadth first search
public class LevelOrderTraversal {

    public static void main(String[] args) {

        CreateTree c = new CreateTree();
        BTNode root = c.BTree();

        LevelOrderTraversal l = new LevelOrderTraversal();

        List<List<Integer>> res = l.traversal(root);
//        List<Integer> r = res.get(2);
//        System.out.println(r);
        System.out.println(res);
    }

    private List<List<Integer>> traversal(BTNode root) {

        if(root == null) {
            return new ArrayList<>();
        }

        Queue<BTNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        q.add(root);

        while (!q.isEmpty()) {

            int len = q.size(); //1, 2
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < len; i++) {

                BTNode node = q.poll();
                level.add(node.data);

                if(node.left!=null) {
                    q.add(node.left);
                }

                if(node.right!=null) {
                    q.add(node.right);
                }
            }
            res.addLast(level);
        }
        return res;
    }
}
