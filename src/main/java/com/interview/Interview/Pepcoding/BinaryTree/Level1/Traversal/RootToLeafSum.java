package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal;

import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.ArrayList;
import java.util.List;

/*
find if there is a path between root to leaf such that sum of nodes in that path is equal to given sum
 */
public class RootToLeafSum {

    public static void main(String[] args) {
        CreateTree c = new CreateTree();
        BTNode root = c.BTree1();

        List<Integer> list = new ArrayList<>();
        boolean res = rootToLeafSum(root, 247, list);

        System.out.println(res);

        for (int i : list) {
            System.out.print(i+" ");
        }
    }

    private static boolean rootToLeafSum(BTNode node, int sum, List<Integer> path) {
        if (node == null) return false;

        // Leaf node check
        if (node.left == null && node.right == null) {
            if (node.data == sum) {
                path.add(node.data);
                return true;
            } else {
                return false;
            }
        }

        if (rootToLeafSum(node.left, sum - node.data, path)) {
            path.add(node.data);
            return true;
        }

        if (rootToLeafSum(node.right, sum - node.data, path)) {
            path.add(node.data);
            return true;
        }
        return false;
    }
}
