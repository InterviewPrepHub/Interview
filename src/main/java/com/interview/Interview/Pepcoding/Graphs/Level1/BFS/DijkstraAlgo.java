package com.interview.Interview.Pepcoding.Graphs.Level1.BFS;

import org.interview.prep.code.leetcode.design.implementation.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraAlgo {

    int v;
    ArrayList<Edge>[] adj;

    DijkstraAlgo(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int u, int v, int wt) {
        adj[u].add(new Edge(u, v, wt));
        adj[v].add(new Edge(v, u, wt));
    }

    public static void main(String[] args) {

        int v = 7;
        DijkstraAlgo c = new DijkstraAlgo(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        c.shortestPath(c.adj, 0);

    }

    private void shortestPath(ArrayList<Edge>[] adj, int src) {

        boolean[] visited = new boolean[v];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.wsf - b.wsf); // min heap

        pq.add(new Pair(src, 0, "" + src));

        while (!pq.isEmpty()) {
            Pair rem = pq.remove(); //remove

            if (visited[rem.v]) {
                continue;
            }
            visited[rem.v] = true;  // mark*
            System.out.println(rem.v + " via "+rem.psf+" @ " + rem.wsf);    //work

            for (Edge e : adj[rem.v]) {
                if (!visited[e.nbr]) {
                    pq.add(new Pair(e.nbr, rem.wsf + e.wt, rem.psf + e.nbr));   //add*
                }
            }
        }
    }


    static class Pair {
        int v;
        int wsf;    //weight so far
        String psf; //path so far

        Pair(int v, int wsf, String psf) {
            this.v = v;
            this.wsf = wsf;
            this.psf = psf;
        }
    }
}
