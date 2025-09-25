package com.interview.Interview.Interview2024.Arcesium;

import java.util.*;

public class KthNodeInLevelOrderBT {

//            10
//        1       5
//      8    6

    public static void main(String[] args) {
        KthNodeInLevelOrderBT k = new KthNodeInLevelOrderBT();

        Node n = new Node(10);
        n.left = new Node(1);
        n.right = new Node(5);
        n.left.left = new Node(8);
        n.left.right = new Node(6);
        k.kthNode(n,4);
    }

    private void kthNode(Node node,int num) {
        int count = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                count++;

                if(count == num) {
                    System.out.println(curr.data);
                }

                if(node.left!=null) {
                    q.add(node.left);
                }
                if (node.right!=null) {
                    q.add(node.right);
                }
            }
        }
    }

    public void kthNodeInVertical(Node n, int num) {

        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        traversal(n,0,treeMap);
        int k = 0;

        for (Map.Entry<Integer, List<Integer>> map1 : treeMap.entrySet()) {
            int size = map1.getValue().size();

            for (int i = 0; i <size; i++) {
                k++;
                if (k == num) {
                    System.out.println(map1.getValue().get(i));
                }
            }
        }
    }

    private void traversal(Node node, int hd, TreeMap<Integer, List<Integer>> treeMap) {
        if(node == null) {
            return;
        }

        List<Integer> get = new ArrayList<>();

        if(get == null) {
            get = new ArrayList<>();
            get.add(node.data);
        } else {
            get.add(node.data);
        }

        treeMap.put(hd, get);

        traversal(node.left, hd-1, treeMap);
        traversal(node.right, hd+1, treeMap);
    }
}

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
