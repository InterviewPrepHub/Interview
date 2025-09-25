package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees;

import java.util.HashMap;
import java.util.Map;

public class DiagonalSumOFBT {

    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.left.right.left = new BTNode(8);
        root.right = new BTNode(3);
        root.right.right = new BTNode(6);
        root.right.right.left = new BTNode(7);
        
        diagonalSum(root);
    }

    private static void diagonalSum(BTNode root) {
        Map<Integer, Integer> diagSum = new HashMap<>();
        diagonalSum(root, 0, diagSum);
        for (Map.Entry<Integer,Integer> e : diagSum.entrySet()) {
            System.out.println(e.getKey() +" ---> "+ e.getValue());
        }
    }

//                1
//            2       3
//        4       5       6
//            8       7
    private static void diagonalSum(BTNode root, int currDiag, Map<Integer, Integer> diagSum) {
        if (root == null) return;
        diagonalSum(root.left, currDiag+1, diagSum);
        int prevSum = 0;

        if (diagSum.get(currDiag) != null) {
            prevSum = diagSum.get(currDiag);
        }

        diagSum.put(currDiag, prevSum+ root.data);
        diagonalSum(root.right, currDiag, diagSum);
    }
}
