package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimsAlgorithm {

    int v;
    ArrayList<Edge> adj[] = null;

    PrimsAlgorithm(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    private void addEdge(int src, int nbr, int wt) {
        adj[src].add(new Edge(src, nbr, wt));
    }

    public static void main(String[] args) {
        int n = 7;
        PrimsAlgorithm h = new PrimsAlgorithm(n);

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
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, -1, 0));

        while (!pq.isEmpty()) {
            Pair rem = pq.poll();

            if (visited[rem.v] == true) {
                continue;
            }
            visited[rem.v] = true;
            if(rem.av != -1) {
                System.out.println(rem.v+" via "+rem.av+" @ "+rem.wt);
            }

            for (Edge e : h.adj[rem.v]) {
                if(visited[e.nbr] == false){
                    pq.add(new Pair(e.nbr, e.src, e.wt));
                }
            }
        }
    }

}
class Edge {
    int src;
    int nbr;
    int wt;

    Edge(int src, int nbr, int wt) {
        this.src = src;
        this.nbr = nbr;
        this.wt = wt;
    }
}

class Pair implements Comparable<Pair> {

    int v;
    int av;
    int wt;

    public Pair(int v, int av, int wt) {
        this.v = v;
        this.av = av;
        this.wt = wt;
    }

    @Override
    public int compareTo(Pair o) {
        return this.wt = o.wt;
    }
}
