package com.interview.Interview.Pepcoding.Graphs.Level1.BFS;

import com.interview.Interview.Pepcoding.Graphs.Edge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/*
A bipartite graph is a graph whose vertices can be split into two disjoint sets (often called color sets, like Red and Blue)
such that every edge connects a vertex in one set to a vertex in the other set, and no edge connects vertices within the same set.

so there should be no edge between two vertices in the same set. [so no cycle in the graph]
and if there is a cycle & its and odd length then the graph is not bipartite

every non cylic graph is bipartite
and a cyclic graph has to be even length to be bipartite

similar to isGraphCyclic code

 */
public class IsGraphBipartite {

    int v;
    ArrayList<Edge>[] adj;

    IsGraphBipartite(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // Corrected addEdge
    void addEdge(int u, int v, int wt) {
        adj[u].add(new Edge(u, v, wt));
        adj[v].add(new Edge(v, u, wt));  // <- add reverse edge properly
    }

    public static void main(String[] args) {
        int v = 6;
        IsGraphBipartite graph = new IsGraphBipartite(v);

        /*graph.addEdge(0, 1, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 5, 1);
        graph.addEdge(2, 0, 1);
        graph.addEdge(2, 3, 1);  // <- this will introduce an odd-length cycle
        graph.addEdge(2, 4, 1);*/

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 0, 1);

        int[] visited = new int[v];
        Arrays.fill(visited, -1);

        for (int i = 0; i < v; i++) {
            if (visited[i] == -1) {
                boolean isBipartite = isBipartite(graph.adj, i, visited);
                if (!isBipartite) {
                    System.out.println(false);  // Print false and return
                    return;
                }
            }
        }
        System.out.println(true); // Print true if all components are bipartite
    }

    private static boolean isBipartite(ArrayList<Edge>[] adj, int src, int[] visited) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, 0));  // color 0

        while (!q.isEmpty()) {
            Pair rem = q.removeFirst();

            if (visited[rem.v] != -1) {
                // already colored: must match expected color
                if (visited[rem.v] != rem.level) return false;
                continue;
            }

            // color this node
            visited[rem.v] = rem.level;

            // explore neighbors
            for (Edge e : adj[rem.v]) {
                if (visited[e.nbr] == -1) {
                    q.add(new Pair(e.nbr, (rem.level + 1) % 2)); // flip color
                } else if (visited[e.nbr] == visited[rem.v]) {
                    // same color on both ends of an edge => not bipartite
                    return false;
                }
            }
        }
        return true;
    }

    static class Pair {
        int v;
        int level;

        Pair(int v, int level) {
            this.v = v;
            this.level = level;
        }
    }

}
