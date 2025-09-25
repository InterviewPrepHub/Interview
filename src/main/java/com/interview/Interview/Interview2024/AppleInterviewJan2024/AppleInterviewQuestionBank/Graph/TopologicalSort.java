package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

public class TopologicalSort {

    int v;
    ArrayList<Edge> adj[] = null;

    TopologicalSort(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    private void addEdge(int src, int nbr) {
        adj[src].add(new Edge(src,nbr));
    }

    public static void main(String[] args) {

        int n = 7;
        TopologicalSort s = new TopologicalSort(n);

        s.addEdge(0,1);
        s.addEdge(0,3);
        s.addEdge(1,2);
        s.addEdge(2,3);
        s.addEdge(4,3);
        s.addEdge(4,5);
        s.addEdge(4,6);
        s.addEdge(5,6);

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                s.topoSort(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private void topoSort(int src, boolean[] visited, Stack<Integer> stack) {
        visited[src] = true;

        ListIterator<Edge> it = adj[src].listIterator();
        while (it.hasNext()) {
            Edge edge = it.next();
            if (visited[edge.nbr] == false) {
                topoSort(edge.nbr, visited, stack);
            }
        }
        stack.push(src);
    }

    class Edge {
        int src;
        int nbr;

        public Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
}

