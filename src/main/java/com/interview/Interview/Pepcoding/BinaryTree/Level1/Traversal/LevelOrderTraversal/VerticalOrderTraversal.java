package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal.LevelOrderTraversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

import java.util.*;

public class VerticalOrderTraversal {

    public static void main(String[] args) {

        BTNode root = new BTNode(3);
        root.left = new BTNode(9);
        root.right = new BTNode(20);
        root.right.left = new BTNode(15);
        root.right.right = new BTNode(7);

        verticalOrder(root);

    }


    private static void verticalOrder(BTNode root) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        verticalOrder(root, 0, map);

        Set<Set<Integer>> list = new HashSet<>();

//        System.out.println(map);

        for (Set<Integer> set : map.values()){
            list.add(set);
        }

        System.out.println(list);
    }

    private static void verticalOrder(BTNode root, int hd, Map<Integer, Set<Integer>> map) {

        if (root == null) return;

        verticalOrder(root.left, hd-1, map);
        map.computeIfAbsent(hd, k -> new HashSet<>()).add(root.data);
        verticalOrder(root.right, hd+1, map);
    }
}
