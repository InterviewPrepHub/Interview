package com.interview.Interview.Pepcoding.Graphs.Level1.BFS.TopologicalSort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class CourseScheduleII {

    int v;
    ArrayList<Integer> adj[];

    public CourseScheduleII(int v) {
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        CourseScheduleII c = new CourseScheduleII(numCourses);

        for (int[] pre : prerequisites) {
            c.addEdge(pre[0], pre[1]);
        }

        boolean[] visited = new boolean[numCourses];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfs(c,i,visited,stk);
            }
        }

        int[] order = new int[stk.size()];
        int i=0;
        while (!stk.isEmpty()) {
            order[i] = stk.pop();
            i++;
        }
        return order;

    }

    public static void dfs(CourseScheduleII c, int src, boolean[] visited, Stack<Integer> stk) {
        visited[src] = true;

        Iterator<Integer> it = c.adj[src].iterator();
        while (it.hasNext()) {
            Integer nbr = it.next();
            if (!visited[nbr]) {
                dfs(c, nbr, visited,stk);
            }
        }

        stk.push(src);
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};  //the pair [0, 1], indicates that to take course 0 you have to first take course 1
        int[] res = findOrder(numCourses, prerequisites);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
