package com.interview.Interview.Pepcoding.DynamicProgramming;

import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.HashMap;
import java.util.Map;

/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses
in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken
into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

Example 1:

Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

 */
public class HouseRobberIII {

    public static void main(String[] args) {

        BTNode root = new BTNode(3);
        root.left = new BTNode(2);
        root.right = new BTNode(3);

        root.left.right = new BTNode(3);
        root.right.right = new BTNode(1);

        System.out.println(rob(root));

    }

    static Map<BTNode, Integer> memo = new HashMap<>();
    public static int rob(BTNode root) {

        if (root == null) return 0;

        // If already computed for this node, return it
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        int sum = root.data;

        if (root.left!=null) {
            sum+=rob(root.left.left);
            sum+=rob(root.left.right);
        }

        if (root.right!=null) {
            sum+=rob(root.right.left);
            sum+=rob(root.right.right);
        }

        int next_sum = rob(root.left) + rob(root.right);
        int res = Math.max(sum, next_sum);

        // Save result for this node
        memo.put(root, res);

        return res;
    }

}
