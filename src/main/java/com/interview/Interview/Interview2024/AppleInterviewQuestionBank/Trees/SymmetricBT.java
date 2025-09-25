package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees;

//leftSubTree should be mirror image of rightSubTree

public class SymmetricBT {

    public static void main(String[] args) {

        BTNode n1 = new BTNode(0);
        n1.left = new BTNode(1);
        n1.right = new BTNode(2);

        BTNode n2 = new BTNode(0);
        n2.left = new BTNode(2);
        n2.right = new BTNode(1);

        System.out.println(checkSymmetry(n1, n2));

    }

    public static boolean checkSymmetry(BTNode n1, BTNode n2) {
        if(n1==null && n2==null) {
            return true;
        }

        if(n1==null || n2==null) {
            return false;
        }

        if (n1.data == n2.data) {

            if (checkSymmetry(n1.left, n2.right) && checkSymmetry(n1.right, n2.left)) {
                return true;
            }
        }
        return false;

    }
}
