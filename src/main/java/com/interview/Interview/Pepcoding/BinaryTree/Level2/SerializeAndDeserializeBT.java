package com.interview.Interview.Pepcoding.BinaryTree.Level2;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBT {

    public static void main(String[] args) {
        BTNode node = new BTNode(1);
        node.left = new BTNode(2);
        node.right = new BTNode(3);
        node.right.left = new BTNode(4);
        node.right.right = new BTNode(5);

        Codec c = new Codec();
        String serialisedString = c.serialize(node);
        System.out.println(serialisedString);

        BTNode deserialised = c.deserialise(serialisedString);
        System.out.println(deserialised.data);

    }


    static class Codec {

        //Convert the tree to a string
        public Codec() {

        }

        public String serialize(BTNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root,sb);
            return sb.toString();
        }

        public void serialize(BTNode root, StringBuilder sb) {

            if (root == null) {
                sb.append("null,");
                return;
            }

            sb.append(root.data).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }


        //Convert the string back to a tree
        public BTNode deserialise(String data) {

            Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserialise(nodes);
        }

        public BTNode deserialise(Queue<String> nodes) {
            String val = nodes.poll();
            if (val.equals("null")) return null;

            BTNode node = new BTNode(Integer.parseInt(val));
            node.left = deserialise(nodes);
            if (node.left != null) node.left.parent = node;

            node.right = deserialise(nodes);
            if (node.right != null) node.right.parent = node;

            return node;
        }
    }
}
