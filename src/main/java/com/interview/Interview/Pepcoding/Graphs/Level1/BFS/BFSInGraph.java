package com.interview.Interview.Pepcoding.Graphs.Level1.BFS;

import org.interview.prep.code.leetcode.design.implementation.Pepcoding.Graphs.Edge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/*
Time complexity: O(E+V)
Space complexity: O(V) -> This is because BFS uses a queue to store the nodes at the current level of traversal.

 */
public class BFSInGraph {

    int v;
    ArrayList<Edge>[] adj;

    BFSInGraph(int v) {
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
        BFSInGraph c = new BFSInGraph(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        c.bsf(c.adj, 0);

    }

    private void bsf(ArrayList<Edge>[] adj, int src) {
        boolean[] visited = new boolean[v];
        ArrayDeque<Pair> q = new ArrayDeque<>();

        q.add(new Pair(src, src+""));

        while (!q.isEmpty()) {
            //r m* w a*

            Pair rem = q.removeFirst(); //r
            if(visited[rem.v]) {
               continue;
            }
            visited[rem.v] = true;  //m*
            System.out.println(rem.v+"@"+rem.psf);  //w

            Iterator<Edge> i = adj[rem.v].iterator();

            while (i.hasNext()) {
                Edge e = i.next();
                if (!visited[e.nbr]) {
                    q.add(new Pair(e.nbr, rem.psf+e.nbr));
                }
            }
        }
    }
}



class Pair {
    int v;
    String psf;

    Pair(int v, String psf) {
        this.v = v;
        this.psf = psf;
    }
}