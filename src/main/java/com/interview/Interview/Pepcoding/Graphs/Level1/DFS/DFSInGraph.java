package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

import com.interview.Interview.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/*
Time complexity: O(E+V)
Space complexity: O(V) -> This is primarily due to the use of a recursion stack (or an explicit stack if implemented iteratively) to keep track of the path currently being explored. In the worst case, a graph could be a long chain, requiring the stack to store all V vertices.
 */
public class DFSInGraph {

    int v;
    ArrayList<Edge>[] adj;

    DFSInGraph(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    //undirected graph
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

    int min = Integer.MAX_VALUE;
    String minPath = "";
    String spath = "";
    Integer sPathWt = Integer.MAX_VALUE;
    String lpath = "";
    Integer lPathWt = Integer.MIN_VALUE;
    String cpath = "";  //Just larger path than criteria [ceil path]
    Integer cPathWt = Integer.MAX_VALUE; //[ceil path wt]
    String fpath = "";  //Just smaller path than criteria [floor path]
    Integer fPathWt = Integer.MIN_VALUE; //[floor path wt]

    PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void main(String[] args) {

        int v = 7;
        DFSInGraph c = new DFSInGraph(7);

        c.addEdge(0, 1, 10);
        c.addEdge(0, 3, 40);
        c.addEdge(1, 2, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(3, 4, 2);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        c.printAllPathsWithWts(0, 6, new boolean[7], "0", 0);
        System.out.println("min wt path is : " + c.minPath +" with min wt : "+ c.min);

        c.printPathWithWtForSpecificCriteria(0, 6, new boolean[7], 3, 40,"0", 0);

        System.out.println();
        System.out.println("Smallest Path : "+c.spath+" with wt : "+c.sPathWt);
        System.out.println("Largest Path : "+c.lpath+" with wt : "+c.lPathWt);
        System.out.println("Ceil Path : "+c.cpath+" with wt : "+c.cPathWt);
        System.out.println("Floor Path : "+c.fpath+" with wt : "+c.fPathWt);

    }


    public void printAllPathsWithWts(int src, int dest, boolean[] visited, String psf, int wt) {
        if (src == dest) {
            System.out.println("path so far till dest : "+psf+ " with total wt : "+wt);  // print path so far
            if(min > wt) {
                min = wt;
                minPath = psf;
            }
            return ;
        }

        visited[src] = true;
        Iterator<Edge> i = adj[src].iterator();
        while (i.hasNext()) {
            Edge e = i.next();
            if (!visited[e.nbr]) {
                printAllPathsWithWts(e.nbr, dest, visited, psf + e.nbr, wt+e.wt);
            }
        }
        visited[src] = false; // backtrack
    }

    //just larger path with wt 40 and above
    public void printPathWithWtForSpecificCriteria(int src, int dest, boolean[] visited, int k, int criteria, String psf, int wt) {
        if (src == dest) {
            if(wt > lPathWt){
                lpath = psf;
                lPathWt = wt;
            }

            if(wt < sPathWt){
                spath = psf;
                sPathWt = wt;
            }

            if(wt > criteria && wt < cPathWt){
                cpath = psf;
                cPathWt = wt;
            }

            if(wt < criteria && wt > fPathWt){
                fpath = psf;
                fPathWt = wt;
            }

            if(pq.size() < k) {
                pq.add(new Pair(wt, psf));
            } else {
                if(pq.peek().wsf < wt) {
                    pq.peek();
                    pq.add(new Pair(wt, psf));
                }
            }
            return;

        }

        visited[src] = true;
        Iterator<Edge> i = adj[src].iterator();
        while (i.hasNext()) {
            Edge e = i.next();
            if (!visited[e.nbr]) {
                printPathWithWtForSpecificCriteria(e.nbr, dest, visited, k, criteria, psf + e.nbr, wt+e.wt);
            }
        }
        visited[src] = false; // backtrack
    }
}


class Pair implements Comparable<Pair> {

    int wsf;
    String psf;

    Pair(int wsf, String psf) {
        this.wsf = wsf;
        this.psf = psf;
    }

    @Override
    public int compareTo(Pair o) {
        return this.wsf - o.wsf;
    }
}