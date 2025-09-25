package com.interview.Interview.Pepcoding.BinaryTree.Level1.BinarySearchTree;

import com.interview.Interview.Pepcoding.Node.BTNode;

public class LargestBSTInBT {

    public static void main(String[] args) {

        LargestBSTInBT l = new LargestBSTInBT();
        BTNode node = l.createTree();

        MinMax m = l.largestBST(node);
        System.out.println(m.size);
    }

//                                            25
//                                  /                   \
//                            /                                     \
//                       18                                           50
//           19                  20                      35                   60
//               15         18       25             20        40          55       70
//                                                      25

    private BTNode createTree() {
        BTNode b = new BTNode(25);
        b.left = new BTNode(18);
        b.left.left = new BTNode(19);
        b.left.left.right = new BTNode(15);
        b.left.right = new BTNode(20);
        b.left.right.left = new BTNode(18);
        b.left.right.right = new BTNode(25);

        b.right = new BTNode(50);
        b.right.left = new BTNode(35);
        b.right.right = new BTNode(60);
        b.right.left.left = new BTNode(20);
        b.right.left.left.right = new BTNode(25);
        b.right.left.right = new BTNode(40);
        b.right.right.left = new BTNode(55);
        b.right.right.right = new BTNode(70);

        return b;
    }


    private MinMax largestBST(BTNode root) {

        //if root is null return min as Integer.MAX and max as Integer.MIN
        if(root == null){
            return new MinMax();
        }

        //postorder traversal of tree. First visit left and right then
        //use information of left and right to calculate largest BST.
        MinMax leftMinMax = largestBST(root.left);
        MinMax rightMinMax =largestBST(root.right);

        MinMax m = new MinMax();

        //if either of left or right subtree says its not BST or the data
        //of this node is not greater/equal than max of left and less than min of right
        //then subtree with this node as root will not be BST.
        //Return false and max size of left and right subtree to parent
        if(leftMinMax.isBST == false || rightMinMax.isBST == false || (leftMinMax.max > root.data || rightMinMax.min <= root.data)){
            m.isBST = false;
            m.size = Math.max(leftMinMax.size, rightMinMax.size);
            return m;
        }

        //if we reach this point means subtree with this node as root is BST.
        //Set isBST as true. Then set size as size of left + size of right + 1.
        //Set min and max to be returned to parent.
        m.isBST = true;
        m.size = leftMinMax.size + rightMinMax.size + 1;

        //if root.left is null then set root.data as min else
        //take min of left side as min
        m.min = root.left != null ? leftMinMax.min : root.data;

        //if root.right is null then set root.data as max else
        //take max of right side as max.
        m.max = root.right != null ? rightMinMax.max : root.data;

        return m;
    }
}


class MinMax {
    int min;
    int max;
    boolean isBST;
    int size;

    MinMax() {
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
        isBST = true;
        size = 0;
    }
}