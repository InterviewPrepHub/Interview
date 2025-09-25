package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Trees;

public class ConstructBTFromTraversal {

    static int preIndex = 0, posIndex = 0;
    public static BTNode constructFromPrePost(int[]pre, int[]post) {
        BTNode root = new BTNode(pre[preIndex++]);
        if (root.data != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.data != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }

    public static BTNode constructFromPreIn(int[] pre, int[] in) {
        return helper(0,0,in.length-1, pre, in);
    }

//    use the preorder traversal to determine the root of the tree at each step,
//    and then use the inorder traversal to identify the left and right subtrees
    private static BTNode helper(int preIndex, int inStart, int inEnd, int[] pre, int[] in) {
        if (preIndex > pre.length || inStart > inEnd) {
            return null;
        }
        //get the current root from the pre-order array
        BTNode root = new BTNode(pre[preIndex]);

        //find the index of root in inorder array
        int inIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (in[i] == root.data) {
                inIndex = i;
                break;
            }
        }
        root.left = helper(preIndex+1, inStart, inIndex-1, pre, in);
        root.right = helper(preIndex+(inIndex-inStart)+1, inIndex+1, inEnd, pre, in);

        return root;

    }

    public static void main(String[] args) {

        int pre[] = { 1, 2, 4, 8, 9, 5, 3, 6, 7 };
        int post[] = { 8, 9, 4, 5, 2, 6, 7, 3, 1 };

        BTNode node = constructFromPrePost(pre, post);
        printInorder(node);
    }

    public static void printInorder(BTNode root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }
}
