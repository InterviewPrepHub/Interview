package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

import org.interview.prep.code.leetcode.design.implementation.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.Iterator;

public class FindAllPaths {

    int v;
    ArrayList<Edge>[] adj;

    FindAllPaths(int v) {
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
        0-------3-------4
        |       |       | \
        |       |       |  \
        1-------2       5---6

     */

    public static void main(String[] args) {

        int v = 7;
        FindAllPaths c = new FindAllPaths(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        c.printAllPaths(0, 6, new boolean[7], "0");

    }

    public void printAllPaths(int src, int dest, boolean[] visited, String psf) {
        if (src == dest) {
            System.out.println(psf);  // print path so far
            return;
        }

        visited[src] = true;
        Iterator<Edge> i = adj[src].iterator();
        while (i.hasNext()) {
            Edge e = i.next();
            if (!visited[e.nbr]) {
                printAllPaths(e.nbr, dest, visited, psf + e.nbr);
            }
        }
        visited[src] = false; // backtrack
    }

}
