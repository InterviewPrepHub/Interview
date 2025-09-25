package com.interview.Interview.Pepcoding.Graphs.Level1.BFS;

import org.interview.prep.code.leetcode.design.implementation.Pepcoding.Graphs.Edge;

import java.util.ArrayDeque;
import java.util.ArrayList;

/*
count how many people get infected in t minutes.

You are given a graph representing people and their connectivity.
You are given src person and time t.
You are required to find how many people get infected in t minutes, if the infection spreads to neighbors of infected person.
 */
public class SpreadInfection {

    int v;
    ArrayList<Edge>[] adj;

    SpreadInfection(int v) {
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
        SpreadInfection c = new SpreadInfection(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        System.out.println(bfs(c.adj, 6, 3));

    }

    public static int bfs(ArrayList<Edge>[] adj, int src, int t) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        int[] visited = new int[adj.length];
        int count = 0;

        q.add(new Pair(src, 1));

        while (!q.isEmpty()) {
            Pair rem = q.removeFirst();
            if (visited[rem.v] != 0) {
                continue;
            }
            visited[rem.v] = rem.time;

            if(rem.time > t) {
                break;
            }
            count++;

            for (Edge e : adj[rem.v]) {
                if (visited[e.nbr] == 0) {
                    q.add(new Pair(e.nbr, rem.time+1));
                }
            }

        }
        return count;
    }

    static class Pair {
        int v;
        int time;

        Pair(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }
}
