package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees;

import java.util.*;

/*
Check the Horizontal Distances from the root for all nodes. If two nodes have the same Horizontal Distance (HD),
then they are on the same vertical line. The idea of HD is simple. HD for root is 0, a right edge (edge connecting
to right subtree) is considered as +1 horizontal distance and a left edge is considered as -1 horizontal distance.

Do a preorder traversal of the given Binary Tree.
While traversing the tree, we can recursively calculate HDs. We initially pass the horizontal distance as 0 for the root.
For the left subtree, we pass the Horizontal Distance as the Horizontal distance of the root minus 1.
For the right subtree, we pass the Horizontal Distance as the Horizontal Distance of the root plus 1.
For every HD value, we maintain a list of nodes in a hash map. Whenever we see a node in traversal, we go to the
hash map entry and add the node to the hash map using HD as a key in a map.
 */
public class BTVerticalOrderTraversal {

//            3
//        9       20
//            15      7

    public static void main(String[] args) {

        BTNode node = new BTNode(3);
        node.left = new BTNode(9);
        node.right = new BTNode(20);
        node.right.left = new BTNode(15);
        node.right.right = new BTNode(7);

        verticalOrderTraversal(node);
    }

    private static void verticalOrderTraversal(BTNode node) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        traversal(node, 0, map);

        for (Map.Entry<Integer, List<Integer>> map1 : map.entrySet()) {
            System.out.println(map1.getValue());
        }
    }

    private static void traversal(BTNode node, int hd, TreeMap<Integer, List<Integer>> map) {

        if (node == null){
            return;
        }

        List<Integer> get = map.get(hd);

        if (get == null) {
            get = new ArrayList<>();
            get.add(node.data);
        } else {
            get.add(node.data);
        }

        map.put(hd, get);

        traversal(node.left, hd-1, map);
        traversal(node.right, hd+1, map);

    }
}
