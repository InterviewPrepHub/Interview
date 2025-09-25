package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

    public static void main(String[] args) {
        BTNode n = new BTNode(10);
        n.left = new BTNode(16);
        n.right = new BTNode(5);
        n.left.right = new BTNode(-3);
        n.right.left = new BTNode(6);
        n.right.right = new BTNode(11);

        int sum = 26;

        rootToLeafSum(n,sum, new ArrayList<Integer>());

    }

    private static boolean rootToLeafSum(BTNode root, int sum, ArrayList<Integer> result) {

        if(root == null) return false;
        if (root.left == null && root.right == null) {
            if (root.data == sum) {
                result.add(root.data);
                return true;
            } else {
                return false;
            }
        }

        if (rootToLeafSum(root.left, sum- root.data, result)) {
            result.add(root.data);
            return true;
        }

        if (rootToLeafSum(root.right, sum- root.data, result)) {
            result.add(root.data);
            return true;
        }

        return false;

    }
}
