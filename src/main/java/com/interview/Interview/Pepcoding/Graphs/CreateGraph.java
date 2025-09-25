package com.interview.Interview.Pepcoding.Graphs;

import java.util.ArrayList;

public class CreateGraph {

    int v;
    ArrayList<Edge> adj[];

    public CreateGraph(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int wt) {
        adj[u].add(new Edge(u, v, wt));
        adj[v].add(new Edge(v, u, wt));
    }

    public static void main(String[] args) {
        int v = 7;
        CreateGraph c = new CreateGraph(v);
    /*
        0-------3-------4
        |       |       | \
        |       |       |  \
        1-------2       5---6

     */


        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 0, 10);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 1, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 0, 40);
        c.addEdge(3, 2, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 3, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 4, 3);
        c.addEdge(5, 6, 3);
        c.addEdge(6, 5, 3);
        c.addEdge(6, 4, 3);
    }
}
