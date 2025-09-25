package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees.BST;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerialiseAndDeserialiseBST {

    public static void main(String[] args) {
        BTSNode n = new BTSNode("1");
        n.left = new BTSNode("2");
        n.right = new BTSNode("3");
        n.right.left = new BTSNode("4");
        n.right.right = new BTSNode("5");

        String str = serialize(n);
        deserialize(str);
    }

    private static BTSNode deserialize(String str) {
        String[] values = str.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(values));
        return buildTree(q);
    }

    private static BTSNode buildTree(Queue<String> q) {
        if (q!=null || q.isEmpty()) {
            return null;
        }
        String data = q.poll();
        if (data.equals("#")) {
            return null;
        }
        BTSNode root = new BTSNode(data);
        root.left = buildTree(q);
        root.right = buildTree(q);
        return root;
    }

    public static String serialize(BTSNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        traverse(root, sb);
        System.out.println(sb.toString());

        return sb.toString();
    }

    private static void traverse(BTSNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(String.valueOf(root.data));
            sb.append(",");
            traverse(root.left, sb);
            traverse(root.right, sb);
        } else {
            sb.append("#");
            sb.append(",");
        }
    }
}


class BTSNode {
    String data;
    BTSNode left;
    BTSNode right;

    public BTSNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
