package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class RangeSumInBST {

    public static void main(String[] args) {
        /*

          50
        /    \
      25      75
     /  \    /  \
   12   30  60   80
         \
          35

         */

        BTNode root = new BTNode(50);
        root.left = new BTNode(25);
        root.right = new BTNode(75);

        root.left.left = new BTNode(12);
        root.left.right = new BTNode(30);
        root.left.right.right = new BTNode(35);

        root.right.left = new BTNode(60);
        root.right.right = new BTNode(80);

        RangeSumInBST r = new RangeSumInBST();
        System.out.println(r.rangeSumBST(root, 30, 75));    //30, 35, 50, 60, 75
        System.out.println(r.rangeSumBST(root, 60, 100));   //60, 75, 80

    }

    //pre order dfs
    public int rangeSumBST(BTNode node, int start, int end) {

        if (node == null) return 0;

        if(node.data < start) {
            // current and left are too small; go right
            return rangeSumBST(node.right, start, end);
        }

        if(node.data > end) {
            // current and right are too big; go left
            return rangeSumBST(node.left, start, end);
        }

        return node.data + rangeSumBST(node.left, start, end) + rangeSumBST(node.right, start, end);

    }
}
