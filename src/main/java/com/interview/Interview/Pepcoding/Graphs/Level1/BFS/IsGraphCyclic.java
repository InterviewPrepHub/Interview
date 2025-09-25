package com.interview.Interview.Pepcoding.Graphs.Level1.BFS;

import com.interview.Interview.Pepcoding.Graphs.Edge;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class IsGraphCyclic {

    int v;
    ArrayList<Edge>[] adj;

    IsGraphCyclic(int v) {
        this.v = v;
        adj = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int u, int v, int wt) {
        adj[u].add(new Edge(u, v, wt));
        adj[v].add(new Edge(v, u, wt));  // <- add reverse edge properly
    }

    public static void main(String[] args) {

        int v = 7;
        IsGraphCyclic c = new IsGraphCyclic(7);

        /*c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);*/

        c.addEdge(0, 1, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);


        boolean[] visited = new boolean[v];
        c.isCyclic(c.adj, 0, visited);

        for (int i = 0; i < v; i++) {
            if(visited[i] == false) {
                boolean cycle = c.isCyclic(c.adj, i, visited);
                if(cycle) {
                    System.out.println("Graph is cyclic");
                }
            }
        }
        System.out.println("Graph is not cyclic");

    }

    private boolean isCyclic(ArrayList<Edge>[] adj, int src, boolean[] visited) {
        ArrayDeque<Pair> q = new ArrayDeque<>();

        q.add(new Pair(src, src+""));

        while (!q.isEmpty()) {
            Pair rem = q.removeFirst();
            if(visited[rem.v]) {
                return true;
            }
            visited[rem.v] = true;

            for(Edge e : adj[rem.v]) {
                if(!visited[e.nbr]) {
                    q.add(new Pair(e.nbr, rem.psf+e.nbr));
                }
            }
        }
        return false;
    }
}

