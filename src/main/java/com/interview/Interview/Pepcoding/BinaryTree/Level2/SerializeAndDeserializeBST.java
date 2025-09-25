package com.interview.Interview.Pepcoding.BinaryTree.Level2;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class SerializeAndDeserializeBST {

    /*
                    5
                  /   \
                 3     7
                / \
               2   4
     */

    public static void main(String[] args) {

        BTNode root = new BTNode (5);
        root.left = new BTNode (3);
        root.right = new BTNode (7);
        root.left.left = new BTNode (2);
        root.left.right = new BTNode (4);

        String res = serialize(root);
        System.out.println(res);

        BTNode node = deserialize(res);
        System.out.println(node.data);

    }

    private static int index;
    private static BTNode deserialize(String data) {
        if (data.isEmpty()) return null;

        String[] values = data.split(" ");

        int[] preorder = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            preorder[i] = Integer.parseInt(values[i]);
        }
        index = 0;
        return buildBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static BTNode buildBST(int[] preorder, int minValue, int maxValue) {
        if (index >= preorder.length) {
            return null;
        }

        int val = preorder[index];
        if (val < minValue || val > maxValue) return null;

        BTNode node = new BTNode(val);
        index++;

        node.left = buildBST(preorder, minValue, val);
        node.right = buildBST(preorder, val, maxValue);

        return node;
    }

    public static String serialize(BTNode node) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(node, sb);
        return sb.toString().trim();
    }

    private static void serializeHelper(BTNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.data).append(" ");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }


}
