package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {

    public static void main(String[] args) {
        BTNode n = new BTNode(5);
        n.left = new BTNode(4);
        n.left.left = new BTNode(11);
        n.left.left.left = new BTNode(7);
        n.left.left.right = new BTNode(2);
        n.right = new BTNode(8);
        n.right.left = new BTNode(13);
        n.right.right = new BTNode(4);
        n.right.right.right = new BTNode(1);
        n.right.right.left = new BTNode(5);

        int sum = 22;
        List<List<Integer>> res = pathSumII(n, sum);
        System.out.println(res);
    }

    private static List<List<Integer>> pathSumII(BTNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        if (root == null) return paths;
        pathSumHelper(root, sum, currentPath, paths);
        return paths;
    }

    private static void pathSumHelper(BTNode root, int sum, List<Integer> currentPath, List<List<Integer>> paths) {

        if (root == null) return;

        currentPath.add(root.data);

        if (root.data == sum && root.left == null && root.right == null) {
            paths.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() - 1);
            return;
        } else {
            pathSumHelper(root.left, sum - root.data, currentPath, paths);
            pathSumHelper(root.right, sum - root.data, currentPath, paths);
        }

        currentPath.remove(currentPath.size() - 1);
    }
}























