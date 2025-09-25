package com.interview.Interview.Pepcoding.Graphs.Level1.BFS.TopologicalSort;

import org.interview.prep.code.leetcode.design.implementation.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.Stack;

/*
Topological Sort with dfs-> a permutaion of vertices for a directed acyclic graph is called topological sort if for every
edge u->v, u must appear before v in the permutation.

here just like get connected components we should add a loop for every vertex and call dfs
 */
public class TopologicalSortAlgo {

    int v;
    ArrayList<Edge>[] adj;

    TopologicalSortAlgo(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int u, int v, int wt) {
        adj[u].add(new Edge(u, v, wt));
    }

    public static void main(String[] args) {

        int v = 7;
        TopologicalSortAlgo c = new TopologicalSortAlgo(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(4, 3, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        boolean[] visited = new boolean[v];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < v; i++) {
            if(!visited[i]) {
                c.dfs(c.adj, i, visited, st);
            }
        }

        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }

    }

    public void dfs(ArrayList<Edge>[] adj, int src, boolean[] visited, Stack<Integer> st) {

        visited[src] = true;

        for(Edge e : adj[src]) {
            if(!visited[e.nbr]) {
                dfs(adj, e.nbr, visited, st);
            }
        }

        st.push(src);
    }
}
