package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

import com.interview.Interview.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/*
hamiltonian path is a path where a src is given and it visits all vertices without repeating a vertex twice or
there exists a cycle when the start and end vertex have an edge between them.
 */
public class HamiltonianPathAndCycles {

    int v;
    ArrayList<Edge>[] adj;

    HamiltonianPathAndCycles(int v) {
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
        HamiltonianPathAndCycles c = new HamiltonianPathAndCycles(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        HashSet<Integer> visited = new HashSet<>();
        c.hamiltonianPath(c.adj, 0, visited, 0+"", 0);

    }

    private void hamiltonianPath(ArrayList<Edge>[] adj, int src, HashSet<Integer> visited, String psf, int osrc) {

        if(visited.size() == adj.length-1) { //visited has nodes just one less than total nodes as it has all the previous nodes other than current node which is added in path so far
            System.out.print(psf +" ");

            boolean closingEdgeFound = false;
            for(Edge e : adj[src]) {
                if(e.nbr == osrc) {
                    closingEdgeFound = true;
                    break;
                }
            }

            if(closingEdgeFound) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }
            return;
        }

        //0|_|0, 01|0_|01 ......
        visited.add(src);

        Iterator<Edge> i = adj[src].iterator();
        while (i.hasNext()) {
            Edge e = i.next();
            if(!visited.contains(e.nbr)) {
                hamiltonianPath(adj, e.nbr, visited, psf + e.nbr, osrc);
            }
        }

        visited.remove(src);
    }
}
