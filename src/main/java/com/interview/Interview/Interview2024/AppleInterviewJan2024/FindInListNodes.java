package com.interview.Interview.Interview2024.AppleInterviewJan2024;

import java.util.ArrayList;
import java.util.List;

public class FindInListNodes {

    public static void main(String[] args) {

        Node node = new Node('A');

        Node nodeB = new Node('B');
        nodeB.addChild(new Node('A'));
        node.addChild(nodeB);
        node.addChild(new Node('A'));

        System.out.println("A Nodes: "+ countNodes(nodeB,'A'));



    }

    public static  int countNodes(Node root,char ch) { //{[A], A}, {[B], A}
        //implementation
        int count = root.ch == ch ? 1: 0;

        if(root.childNodes!=null) {
            for(Node childNode : root.childNodes) { //[B]
                count = count + countNodes(childNode, ch);
            }
            return count;
        }

        return count;
    }



    static class Node {
        char ch;
        List<Node> childNodes;

        Node(char ch) {
            this.ch = ch;
        }

        void addChild(Node node) {
            if(this.childNodes == null) {
                this.childNodes = new ArrayList<>();
            }
            this.childNodes.add(node);
        }
    }


}
