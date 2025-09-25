package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph.DijkstraAlgorithm;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.PriorityQueue;

/*
How to find Shortest Paths from Source to all Vertices using Dijkstra’s Algorithm
 */
public class DijkstraAlgorithm {

    int v;
    ArrayList<Edge> adj[] = null;

    DijkstraAlgorithm(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    private void addEdge(int src, int nbr, int wt) {
        adj[src].add(new Edge(src,nbr,wt));
    }

/*
    0-------3--------4
    |       |       | \
    |       |       |  \
    1-------2       5---6
*/

    public static void main(String[] args) {
        int n = 7;
        DijkstraAlgorithm h = new DijkstraAlgorithm(n);

        h.addEdge(0,1,10);
        h.addEdge(0,3,40);
        h.addEdge(1,0,10);
        h.addEdge(1,2,10);
        h.addEdge(2,1,10);
        h.addEdge(2,3,10);
        h.addEdge(3,0,40);
        h.addEdge(3,2,10);
        h.addEdge(3,4,2);
        h.addEdge(4,3,2);
        h.addEdge(4,5,3);
        h.addEdge(4,6,8);
        h.addEdge(5,4,3);
        h.addEdge(5,6,3);
        h.addEdge(6,4,8);
        h.addEdge(6,5,3);

        boolean[] visited= new boolean[n];
        h.DA(0, visited,"");

    }

    public void DA(int src, boolean[] visited, String psf) {

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src,  src+"", 0));

        while (!pq.isEmpty()) {
            Pair rem = pq.remove();

            if (visited[rem.src] == true ) {
                continue;
            } else {
                visited[rem.src] = true;

                System.out.println(rem.src+" via "+rem.psf+" @ "+rem.wsf);

                ListIterator<Edge> it = adj[rem.src].listIterator();
                while (it.hasNext()) {
                    Edge edge = it.next();
                    if (visited[edge.nbr] == false) {
                        pq.add(new Pair(edge.nbr, rem.psf+edge.nbr, edge.wt+ rem.wsf));
                    }
                }
            }

        }

    }

    class Edge {

        public int src;
        public int nbr;
        public int wt;

        public Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair>{
        int src;
        String psf;
        int wsf;

        Pair(int src, String psf, int wsf) {
            this.src = src;
            this.psf = psf;
            this.wsf = wsf;
        }

        @Override
        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }
}
