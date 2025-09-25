package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightViewOfBT {

    public static void main(String[] args) {
        /*
               1
             /  \
            2    3
            \     \
              5     4
        */

        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.left.right = new BTNode(5);
        root.right = new BTNode(3);
        root.right.right = new BTNode(4);

        System.out.println("right view: ");
        System.out.println(rightView(root));

        System.out.println("left view: ");
        System.out.println(leftView(root));
    }

    private static List<Integer> rightView(BTNode root) {

        List<Integer> view = new ArrayList<>();
        if (root == null) return view;

        Queue<BTNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int rightmost = 0;
            for (int i = 0; i < size; i++) {
                BTNode node = q.poll();
                rightmost = node.data;
                if (node.left!=null) q.add(node.left);
                if (node.right!=null) q.add(node.right);
            }
            view.add(rightmost);
        }
        return view;
    }

    private static List<Integer> leftView(BTNode root) {

        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Queue<BTNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                BTNode node = q.poll();
                if (i==0) list.add(node.data);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }

        return list;
    }
}
