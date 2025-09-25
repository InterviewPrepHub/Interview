package com.interview.Interview.Pepcoding.Graphs.Level1.DFS;

import com.interview.Interview.Pepcoding.Graphs.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Question Statement:
1. You are given a number n (representing the number of students). Each student will have an id from 0 to n - 1.
2. You are given a number k (representing the number of clubs)
3. In the next k lines, two numbers are given separated by a space. The numbers are ids of students belonging to same club.
4. You have to find in how many ways can we select a pair of students such that both students are from different clubs.
 */
public class PerfectFriends {

    int v;
    ArrayList<Edge>[] adj;

    PerfectFriends(int v) {
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
    7 students
    5 clubs
    ids of students belonging to same club
    0 1
    2 3
    4 5
    5 6
    4 6
     */

    public static void main(String[] args) {

        int v = 7;
        PerfectFriends c = new PerfectFriends(7);

        c.addEdge(0, 1, 10);
        c.addEdge(2, 3, 10);
        c.addEdge(4, 5, 3);
        c.addEdge(4, 6, 3);
        c.addEdge(5, 6, 3);

        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                c.drawTreeComponent(c.adj, i, visited, comp);
                components.add(comp);
            }
        }

        System.out.println(components);

        int count = 0;
        for (int i = 0; i < components.size(); i++) {
            for (int j = i+1; j < components.size(); j++) {
                count += components.get(i).size() * components.get(j).size();
            }
        }

        System.out.println(count);
    }

    private void drawTreeComponent(ArrayList<Edge>[] adj, int src, boolean[] visited, List<Integer> comp) {

        visited[src] = true;
        comp.add(src);

        Iterator<Edge> i = adj[src].iterator();
        while (i.hasNext()) {
            Edge e = i.next();
            if(!visited[e.nbr]) {
                drawTreeComponent(adj, e.nbr, visited, comp);
            }
        }

    }
}
