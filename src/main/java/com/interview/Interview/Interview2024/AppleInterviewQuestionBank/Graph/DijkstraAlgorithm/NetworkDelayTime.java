package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Graph.DijkstraAlgorithm;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.PriorityQueue;

public class NetworkDelayTime {

    int v;
    ArrayList<Edges> adj[];

    NetworkDelayTime(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int t) {
        adj[u].add(new Edges(u, v, t));
    }

    public void networkDelay(int[][] times, int n, int k) {

        for(int[] time : times) {
            int u = time[0];
            int v = time[1];
            int wt = time[2];
            addEdge(u,v,wt);
        }

        da(k, new boolean[n], "");

    }

    private void da(int src, boolean[] visited, String psf) {

        int totalTime = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, src+psf, 0));

        while(!pq.isEmpty()) {
            Pair rem = pq.remove();

            if (visited[rem.src] == true) {
                continue;
            } else {
                visited[rem.src] = true;
                System.out.println(rem.src+" via "+rem.psf+ " @ "+ rem.wsf);
                totalTime+=rem.wsf;

                ListIterator<Edges> it = adj[src].listIterator();
                while (it.hasNext()) {
                    Edges edges = it.next();
                    if (visited.length > edges.nbr && visited[edges.nbr] == false) {
                        pq.add(new Pair(edges.nbr, edges.nbr+rem.psf, edges.wt+ rem.wsf));
                    }
                }
            }
        }
        System.out.println(totalTime > 0 ? totalTime : -1);
//        System.out.println("total time : "+totalTime);
    }

    public static void main(String[] args) {
        /*int[][] times = {{2,1,1},
                         {2,3,1},
                         {3,4,1}};
        int n = 4, k = 2;*/

        int[][] times = {{1,2,1}};
        int n = 2, k = 1;

        NetworkDelayTime nd = new NetworkDelayTime(n);
        nd.networkDelay(times, n, k);
    }

    class Pair implements Comparable<Pair>{
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

class Edges {
    int src;
    int nbr;
    int wt;

    public Edges(int src, int nbr, int wt) {
        this.src = src;
        this.nbr = nbr;
        this.wt = wt;
    }
}