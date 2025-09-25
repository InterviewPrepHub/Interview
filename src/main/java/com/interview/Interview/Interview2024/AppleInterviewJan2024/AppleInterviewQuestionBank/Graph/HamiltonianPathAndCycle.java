package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

public class HamiltonianPathAndCycle {

    int v;
    ArrayList<Edge> adj[];

    HamiltonianPathAndCycle(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adj[src].add(new Edge(src, dest));
    }

    public static void main(String[] args) {
        int n = 7;
        HamiltonianPathAndCycle h = new HamiltonianPathAndCycle(n);

        h.addEdge(0,1);
        h.addEdge(0,3);
        h.addEdge(1,2);
        h.addEdge(3,2);
        h.addEdge(3,4);
        h.addEdge(4,5);
        h.addEdge(4,6);
        h.addEdge(5,6);

        HashSet<Integer> visited = new HashSet<>();
        h.findPathOrCycle(0, 0+"", visited, 0);

    }

    private void findPathOrCycle(int src, String psf, HashSet<Integer> visited, int osrc) {
        if (visited.size() == adj.length-1) {
            System.out.println(psf);
            boolean closingEdgeFound = false;
            for (Edge e : adj[src]) {
                if (src == osrc){
                    closingEdgeFound = true;
                    break;
                }
            }

            if (closingEdgeFound == true) {
                System.out.println("its a cycle");
            } else {
                System.out.println("not a cycle");
            }

            return;
        }

        visited.add(src);
        ListIterator<Edge> it = adj[src].listIterator();
        while (it.hasNext()) {
            Edge edge = it.next();
            if (visited.contains(edge.nbr)) {
                findPathOrCycle(edge.nbr, psf+edge.nbr, visited, osrc);
            }
        }
        visited.remove(src);
    }

    class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
}
