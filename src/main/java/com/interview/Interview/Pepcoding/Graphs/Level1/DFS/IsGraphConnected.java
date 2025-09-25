package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

import org.interview.prep.code.leetcode.design.implementation.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IsGraphConnected {

    int v;
    ArrayList<Edge>[] adj;

    IsGraphConnected(int v) {
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

    /*
        0       3       4
        |       |       | \
        |       |       |  \
        1       2       5---6

     */

    public static void main(String[] args) {
        int v = 7;
        IsGraphConnected c = new IsGraphConnected(7);

        c.addEdge(0, 1, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        //sample output: [0,1] [3,2] [4,5,6]
        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if(visited[i] == false) {
                List<Integer> comp = new ArrayList<>();
                c.drawTree(c.adj, i, visited, comp);
                components.add(comp);
            }
        }

        if(components.size() == 1) {
            System.out.println("Graph is connected");
        } else {
            System.out.println("Graph is not connected");
        }

    }

    private void drawTree(ArrayList<Edge>[] adj, int src, boolean[] visited, List<Integer> comp) {

        visited[src] = true;
        comp.add(src);

        Iterator<Edge> i = adj[src].iterator();
        while (i.hasNext()) {
            Edge e = i.next();
            if(!visited[e.nbr]) {
                drawTree(adj, e.nbr, visited, comp);
            }
        }
    }
}
