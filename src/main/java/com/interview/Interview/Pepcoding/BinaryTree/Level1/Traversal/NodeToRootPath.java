package com.interview.Interview.Pepcoding.BinaryTree.Level1.Traversal;

import org.interview.prep.code.leetcode.design.implementation.Node.BTNode;

import java.util.ArrayList;

public class NodeToRootPath {

    static ArrayList<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        CreateTree c = new CreateTree();
        BTNode root = c.BTree();

        NodeToRootPath n = new NodeToRootPath();
        boolean b = n.find(root, 8);

        System.out.println("node found : "+b);
        
        n.nodeToRootPath(root, 8);
        System.out.println(path);
    }


    private boolean nodeToRootPath(BTNode node, int data) {

        if(node == null) return false;

        if (node.data == data) {
            path.add(node.data);
            return true;
        }

        boolean filt = nodeToRootPath(node.left, data);
        if (filt) {
            path.add(node.data);
            return true;
        }

        boolean firt = nodeToRootPath(node.right, data);
        if (firt) {
            path.add(node.data);
            return true;
        }

        return false;

    }

    public boolean find(BTNode node, int data) {

        if (node == null) return false;

        if(node.data == data) {
            return true;
        }

        boolean filt = find(node.left, data);

        if(filt) {
            return true;
        }

        boolean firt = find(node.right, data);

        if (firt) {
            return true;
        }

        return false;
    }
}
