package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

import com.interview.Interview.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.Iterator;

public class HasPath {

    int v;
    ArrayList<Edge>[] adj;

    public HasPath(int v) {
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

    public boolean hasPath(int src, int dest) {
        boolean[] visited = new boolean[v];
        return hasPath(src, dest, visited);
    }

    public boolean hasPath(int src, int dest, boolean[] visited) {
        if (src==dest) {
            return true;
        }

        visited[src] = true;
        Iterator<Edge> i = adj[src].iterator();

        while(i.hasNext()) {
            Edge e = i.next();
            if (!visited[e.nbr]) {
                boolean hasPath = hasPath(e.nbr, dest, visited);
                if(hasPath) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int v = 7;
        HasPath c = new HasPath(v);

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

        boolean res =c.hasPath(0, 6);
        System.out.println(res);

    }
}
