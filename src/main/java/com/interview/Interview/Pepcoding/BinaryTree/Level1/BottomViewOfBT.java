package com.interview.Interview.Pepcoding.BinaryTree.Level1;

import com.interview.Interview.Pepcoding.Node.BTNode;

import java.util.*;

public class BottomViewOfBT {

    public static void main(String[] args) {
        
        /*
                 20
                /  \
               8   22
              / \    \
             5  3    25
               / \
              10 14

         Bottom View: 5 10 3 14 25
        */

        BTNode root = new BTNode(20);
        root.left = new BTNode(8);
        root.right = new BTNode(22);
        root.left.left = new BTNode(5);
        root.left.right = new BTNode(3);
        root.right.right = new BTNode(25);
        root.left.right.left = new BTNode(10);
        root.left.right.right = new BTNode(14);

        BottomViewOfBT solution = new BottomViewOfBT();
        System.out.println("Bottom View: " + solution.bottomView(root));  // Output: [5, 10, 3, 14, 25]

    }

    public List<Integer> bottomView(BTNode root) {

        //Treemap to keep the keys sorted by HD
        Map<Integer, Integer> hdToValue = new TreeMap<>();
        Queue<NodeHD> q = new LinkedList<>();

        q.add(new NodeHD(0,root));

        while (!q.isEmpty()) {
            NodeHD nodeHD = q.poll();

            int hd = nodeHD.hd;
            BTNode node = nodeHD.node;

            hdToValue.put(nodeHD.hd, node.data);

            if (node.left != null) {
                q.add(new NodeHD(hd-1, node.left));
            }

            if (node.right != null) {
                q.add(new NodeHD(hd+1, node.right));
            }
        }
        return new ArrayList<>(hdToValue.values());
    }

}

class  NodeHD {
    int hd;
    BTNode node;

    public NodeHD(int hd, BTNode node) {
        this.hd = hd;
        this.node = node;
    }
}
